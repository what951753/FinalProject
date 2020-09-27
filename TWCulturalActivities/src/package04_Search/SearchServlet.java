package package04_Search;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.*;
import javax.sql.*;
import org.apache.catalina.loader.WebappClassLoader;
import org.apache.el.lang.ELArithmetic;
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	DataSource ds = null;

	public void init() throws ServletException {
		try {
			InitialContext ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe"); // for Oracle DB
		} catch (NamingException ne) {
			throw new ServletException(ne);
		}
	}

	public void doPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String queryVal = request.getParameter("queryVal");
		String querysite = request.getParameter("site");
		String querystartdate = request.getParameter("startdate");
		String queryenddate = request.getParameter("enddate");
		
		request.getRequestDispatcher("/SearchAllServlet").forward(request, response);	

//		if (queryVal!=null) {	
//			
//		}
//		else if (querysite!=null) {
//			request.getRequestDispatcher("/SiteSearchServlet").forward(request, response);	
//			
//		}
//		else if (querystartdate!=null) {
//			request.getRequestDispatcher("/StartDateSearchServlet").forward(request, response);	
//			
//		}
//		else if (queryenddate!=null) {
//			request.getRequestDispatcher("/EndDateSearchServlet").forward(request, response);	
//			
//		}
//		else {
//			request.getRequestDispatcher("/SearchAllServlet").forward(request, response);	
//
//		}
	}
}

		
////Searchall
////	  	request.setCharacterEncoding("big5");
//	    String queryVal = request.getParameter("queryVal");
////	    String queryVal2 = new String(queryVal.getBytes("ISO-8859-1"), "UTF-8");
//
//	    String query = "SELECT PK,TITLE FROM MAINTABLE " + 
//	                   "WHERE TITLE LIKE \'%" + queryVal + "%\'";
//	    
////	    System.out.println(query);
//	      
//	    response.setContentType(CONTENT_TYPE);
//	    PrintWriter out = response.getWriter();
//	    out.println("<html>");
//	    out.println("<head><title>模糊查詢</title></head>");
//	    out.println("<body>");
//	    
//	    try 
//	    {
////	      System.out.println("before connection");	
//	      Connection conn = ds.getConnection();
////	      System.out.println("after connection");	
//	      Statement stmt = conn.createStatement();
//	      ResultSet rs = stmt.executeQuery(query);
//
//	      out.println("<table border=1 width=50%>");
//	      out.println("<tr><th width=25%>UID</th>" +
//	                  "<th width=75%>Title</th></tr>");
//	      for (int count = 0; ; count++) 
//	      {
//	        if (rs.next())
//	        {
//	          out.println("<tr><td>"+ rs.getString(1) + "</td><td>" +
//	                      rs.getString(2) + "</td></tr>");
//	        }
//	        else 
//	        {
//	          out.println("</table><h3>" + count + " rows retrieved</h3>");
//	          break;
//	        }
//	      }
//	      rs.close();
//	      stmt.close();
//	      conn.close();
//	    }
//	    catch (SQLException se)
//	    {
//	      se.printStackTrace(out);
//	    }
//	    
//	    out.println("</body></html>");
//	    out.close();
//	  } 

////用地區查詢	  
////	request.setCharacterEncoding("big5");
//  String queryVal = request.getParameter("site");
////  String queryVal2 = new String(queryVal.getBytes("ISO-8859-1"), "UTF-8");
//  String query = "SELECT PK,TITLE FROM MAINTABLE " + 
//                 "WHERE TITLE LIKE \'%" + queryVal + "%\'"; 
////  System.out.println(query);
//    
//  response.setContentType(CONTENT_TYPE);
//  PrintWriter out = response.getWriter();
//  out.println("<html>");
//  out.println("<head><title>模糊查詢</title></head>");
//  out.println("<body>");
//  
//  try 
//  {
////    System.out.println("before connection");	
//    Connection conn = ds.getConnection();
////    System.out.println("after connection");	
//    Statement stmt = conn.createStatement();
//    ResultSet rs = stmt.executeQuery(query);
//
//    out.println("<table border=1 width=50%>");
//    out.println("<tr><th width=25%>UID</th>" +
//                "<th width=75%>Title</th></tr>");
//    for (int count = 0; ; count++) 
//    {
//      if (rs.next())
//      {
//        out.println("<tr><td>"+ rs.getString(1) + "</td><td>" +
//                    rs.getString(2) + "</td></tr>");
//      }
//      else 
//      {
//        out.println("</table><h3>" + count + " rows retrieved</h3>");
//        break;
//      }
//    }
//    rs.close();
//    stmt.close();
//    conn.close();
//  }
//  catch (SQLException se)
//  {
//    se.printStackTrace(out);
//  }
//  
//  out.println("</body></html>");
//  out.close();
//} 

		
		
		
		
////開始日查詢
//
//		response.setContentType(CONTENT_TYPE);
//		PrintWriter out = response.getWriter();
//		out.println("<html>");
//		out.println("<head><title>模糊查詢</title></head>");
//		out.println("<body>");
//
//		out.println("<table border=1 width=60%>");
//		out.println("<tr><th width=25%>UID</th>" + "<th width=50%>Title</th>" + "<th width=25%>Date</th><tr>");
//
//		try {
//
////將request日期字串轉為date
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//			String queryVal = request.getParameter("strtdate");
//			Date requestDate = sdf2.parse(queryVal);
//			System.out.println(requestDate);
////帶入list內日期字串 轉為date格式	  
//
//			SelectDAO selectDAO = new SelectDAO();
//			List<ShowOj> showList = selectDAO.Showlist();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-EE");
//			for (ShowOj showOj : showList) {
//				String dateString = showOj.getSTART_DATE();
//				String uIDString = showOj.getPK();
//				String titleString = showOj.getTITLE();
//				Date date;
//				date = sdf.parse(dateString);
//				// System.out.println(date);
//				if (date.after(requestDate)) {
//					System.out.println(date + "在" + requestDate + "之後");
//					// System.out.println(date.before(requestDate);
//					out.println(
//							"<tr><td>" + uIDString + "</td><td>" + titleString + "</td><td>" + dateFormat.format(date) + "</td></tr>");
//				}
//			}
//
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		out.println("</body></html>");
//		out.close();
//
//	}
//}

		
		
		
////結束日查詢
//
//		response.setContentType(CONTENT_TYPE);
//		PrintWriter out = response.getWriter();
//		out.println("<html>");
//		out.println("<head><title>模糊查詢</title></head>");
//		out.println("<body>");
//
//		try {
////System.out.println("before connection");	
//			Connection conn = ds.getConnection();
////System.out.println("after connection");	
//			Statement stmt = conn.createStatement();
////			ResultSet rs = stmt.executeQuery(query);
//
//			out.println("<table border=1 width=60%>");
//			out.println("<tr><th width=25%>UID</th>" + "<th width=50%>Title</th>" + "<th width=25%>Date</th><tr>");
//
//			try {
//
////將request日期字串轉為date
//				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//				String queryVal = request.getParameter("enddate");
//				Date requestDate = sdf2.parse(queryVal);
//				System.out.println(requestDate);
////帶入list內日期字串 轉為date格式	  
//
//				SelectDAO selectDAO = new SelectDAO();
//				List<ShowOj> showList = selectDAO.Showlist();	
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-EE");
//				
//				for (ShowOj showOj : showList) {
//					String dateString = showOj.getEND_DATE();
//					String uIDString = showOj.getPK();
//					String titleString = showOj.getTITLE();
//	
//					Date date;
//					date = sdf.parse(dateString);
//					// System.out.println(date);
//					if (date.before(requestDate)) {
//						System.out.print(date + "在" + requestDate + "之前");
//						// System.out.println(date.before(requestDate);
//						//dateFormat.format(date)將date轉格式
//						out.println(
//						"<tr><td>" + uIDString + "</td><td>" + titleString + "</td><td>" +dateFormat.format(date) + "</td></tr>");
//					}
//				}
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		} catch (SQLException se) {
//			se.printStackTrace(out);
//		}
//
//		out.println("</body></html>");
//		out.close();
//	}
//}


