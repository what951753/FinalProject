package package35_controller;

import package35_javaBean.*;
import sun.security.provider.JavaKeyStore.CaseExactJKS;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectCategoryServlet
 */
@WebServlet("/SelectCategoryServlet")
public class SelectCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType(CONTENT_TYPE);//設定編碼
		
		JDBCDAO jdbcdao = new JDBCDAO();
		int i =Integer.parseInt(request.getParameter("activity_category"));
		ArrayList<MainTable> list = jdbcdao.selectDBtoMT(i);
		String str="";
		switch(i) {
		case 1: str="音樂"; break;
		case 2: str="戲劇"; break;
		case 3: str="舞蹈"; break;
		case 4: str="親子"; break;
		case 5: str="獨立音樂"; break;
		case 6: str="展覽"; break;
		case 7: str="講座"; break;
		case 8: str="電影"; break;
		case 11: str="綜藝"; break;
		case 13: str="競賽"; break;
		case 14: str="徵選"; break;
		case 15: str="其他"; break;
		case 16: str="未知分類"; break;
		case 17: str="演唱會"; break;
		case 19: str="研習課程"; break;
		}
		request.setAttribute("queryResults", list);
		request.setAttribute("queryCategory", str);
		request.getRequestDispatcher("/35_SelectActivityFormRes.jsp").forward(request, response);	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
