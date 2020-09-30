package packge14_SaleTicket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class ProductArray
 */
@WebServlet("/ProductArray")
public class ProductArray extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductArray() {
        super();
    }
    DataSource ds = null;
    public void init() throws ServletException
    {
      try 
      {
        InitialContext ctxt = new InitialContext();
        ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");  // for Oracle DB
      }
      catch (NamingException ne)
      {
        throw new ServletException(ne);
      }
    }
   
    List<Product> productList = new ArrayList<Product>();
	
	DAOPage daoPage = new DAOPage(); 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String method = request.getParameter("method");
		String sCurrePage = request.getParameter("currentPage");
		String param1 = request.getParameter("param1");
		String check = request.getParameter("check");
//		String CarSize = request.getParameter("carSize");
		System.out.println(method);
		System.out.println(sCurrePage);
		
//		System.out.println(request.getAttribute("currentPage"));
		
		if ("selectItem".equals(method)) {
			request.setAttribute("currentPage", Integer.parseInt(sCurrePage));
			gotoSubmitProcess(param1, request, response);
		}else if("changePage".equals(method)) {
			changePage(sCurrePage, request, response);
		};
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
	}
	public void changePage(String sCurrePage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("currentPage", Integer.parseInt(sCurrePage));
		HttpSession session = request.getSession(true);
		List<ProductItem> carList = (List<ProductItem>)session.getAttribute("carList");
		 if (carList == null) {
	    	  carList = new ArrayList<ProductItem>();
	     
	       
	        session.setAttribute("carList", carList);
	        

	      }else {
	    	  
	    	  request.setAttribute("carSize", carList.size());
	      }
		request.getRequestDispatcher("/14_serach.jsp").forward(request, response);
	}
	
	public void gotoSubmitProcess(String param1, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
//		request.getRequestDispatcher("/orderPage.jsp").forward(request,response);
		System.out.println("進入方法");
		
		HttpSession session = request.getSession(true);
		synchronized(session) {

			
			List<ProductItem> carList = (List<ProductItem>)session.getAttribute("carList");
			

		      if (carList == null) {
		    	  carList = new ArrayList<ProductItem>();
		     
		       
		        session.setAttribute("carList", carList);
		        

		      }
			
			

			String act_nameString = param1;
			
				
			ProductItem ca = new ProductItem();
			ca.setProdutTitle(act_nameString);
			ca.setProductNum("1");
			
			
			for(int i=0; i < carList.size(); i++) {
				ProductItem order;
				order = (ProductItem) carList.get(i);
				
				if (ca.getProdutTitle()==(order.getProdutTitle())) {
					System.out.println(carList.remove(i));
					
				}
				
				if (ca.getProdutTitle().equals(order.getProdutTitle())) {
					ca.setProductNum(Integer.toString(Integer.parseInt(ca.getProductNum())+Integer.parseInt(order.getProductNum())));
					
					System.out.println(carList.remove(i));
				}
			
			}
			synchronized(this) {
				
				carList.add(ca);

			}
			
			
			
		
		request.setAttribute("carSize", carList.size());
		
		
		request.getRequestDispatcher("/14_serach.jsp").forward(request, response);
//		response.sendRedirect("orderPage.jsp");
		
		
	  }
		
		

	  }
	
	
	
}
