import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class getConnection extends HttpServlet {
      public void init() { }

      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
            DataInputStream in = new DataInputStream((InputStream)request.getInputStream()); 
            String db = in.readUTF();
            String user = in.readUTF();
            String pwd = in.readUTF(); 
            String message ="jdbc:mysql://localhost:3306/"+db+","+user+","+pwd;
            try { 
                  connect(db.toLowerCase().trim(),user.toLowerCase().trim(), pwd.toLowerCase().trim()); 
                  message += "100 ok"; 
            } catch (Throwable t) {
                  message += "200 " + t.toString();
            }
            response.setContentType("text/plain");
            response.setContentLength(message.length());
            PrintWriter out = response.getWriter();
            out.println(message);
            in.close();
            out.close();
            out.flush();
      }

      public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
            doPost(request,response);
      }

      /* This method connects to MYSQL database*/
      private void connect(String db, String user,String pwd) throws Exception { 
            // Establish a JDBC connection to the MYSQL database server.
            //Class.forName("org.gjt.mm.mysql.Driver");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db,user,pwd); 
            // Establish a JDBC connection to the Oracle database server.
            //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            //Connection conn = DriverManager.getConnection(
            // "jdbc:oracle:thin:@localhost:1521:"+db,user,pwd);

            // Establish a JDBC connection to the SQL database server.
            //Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //Connection conn = DriverManager.getConnection(
            // "jdbc:jtds:sqlserver://localhost:1433/"+db,user,pwd);
      }
}