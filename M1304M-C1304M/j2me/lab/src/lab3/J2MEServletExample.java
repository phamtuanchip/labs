package lab3;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class J2MEServletExample extends HttpServlet{
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "test";
	String driverName = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "root";
	Connection con = null;
		
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		Cookie[] cookies = req.getCookies();
		System.out.println("cookies "+cookies);
		res.setContentType("text/html");    
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<head><title>J2ME Servlet</title></head>");
		out.println("<body>");
		out.println("<h1>J2ME Servlet</h1>");
		out.println("</body></html>");  
		
		if(cookies != null){
			Cookie theCookie = cookies[0];
			String id = theCookie.getValue();
			out.println("<b>The Cookies Value is:</b> " + id);
			out.print("<br>");
			out.println("And The Table Data is Display Here<br>");
		} else {
			int random = (int) Math.round(100 * Math.random());
			Cookie cookie = new Cookie("ID", Integer.toString(random));
			System.out.println("cookie "+cookie);
            res.addCookie(cookie);
		}

		try{
			Class.forName(driverName).newInstance(); 
			con = DriverManager.getConnection(url+dbName, userName, password);
			Statement stmt = con.createStatement();
			java.util.Date currentTime = new java.util.Date(); 
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(currentTime);
			String query = "UPDATE emp set lastAccess = '" + dateString + "' where id = 2";
			stmt.executeUpdate(query);

			ResultSet rs = stmt.executeQuery("Select * from emp"); 		  
			while(rs.next()){
				out.println(rs.getString(1) + "     " + rs.getString(2) + "     " + rs.getString(3));
				out.println("<br>");
			}
		}catch (Exception e){
			System.out.println(e);
		}	
		out.close();
	}
}