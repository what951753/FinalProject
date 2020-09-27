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
@WebServlet("/SitSearchServlet")
public class SiteSearchServlet extends HttpServlet {

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



//用地區查詢	  
//	request.setCharacterEncoding("big5");
  String queryVal = request.getParameter("site");
//  String queryVal2 = new String(queryVal.getBytes("ISO-8859-1"), "UTF-8");
  String query = "SELECT PK,TITLE FROM MAINTABLE " + 
                 "WHERE TITLE LIKE \'%" + queryVal + "%\'"; 
//  System.out.println(query);
    
  response.setContentType(CONTENT_TYPE);
  PrintWriter out = response.getWriter();
  out.println("<html>");
  out.println("<head><title>地區查詢</title></head>");
  out.println("<body>");
  
  try 
  {
//    System.out.println("before connection");	
    Connection conn = ds.getConnection();
//    System.out.println("after connection");	
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(query);

    out.println("<table border=1 width=50%>");
    out.println("<tr><th width=25%>UID</th>" +
                "<th width=75%>Title</th></tr>");
    for (int count = 0; ; count++) 
    {
      if (rs.next())
      {
        out.println("<tr><td>"+ rs.getString(1) + "</td><td>" +
                    rs.getString(2) + "</td></tr>");
      }
      else 
      {
        out.println("</table><h3>" + count + " rows retrieved</h3>");
        break;
      }
    }
    rs.close();
    stmt.close();
    conn.close();
  }
  catch (SQLException se)
  {
    se.printStackTrace(out);
  }
  
  out.println("</body></html>");
  out.close();
} 
}
		
		
		
		


