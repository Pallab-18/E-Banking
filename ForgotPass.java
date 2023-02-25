import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet
public class ForgotPass extends HttpServlet {
  public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String email=req.getParameter("user_email");
        try
        {
             HttpSession ses=req.getSession();
           ses.setAttribute("email",email);
            
             Class.forName("oracle.jdbc.driver.OracleDriver");
            //registering type4 driver for oracle 
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HRITHIK","Hrithik");
            Statement stmt=con.createStatement();
            String s="select * from app_user where email='"+email+"'";
            ResultSet rs=stmt.executeQuery(s);
//            pw.println(s);
            if(rs.next()){
                pw.println("<html>\n" +
"    <head>\n" +
"    <tiitle>Security Check</tiitle>\n" +
"    </head>\n" +
"    <body>\n" +
"        <form action=\"ConfirmPass\" method=\"post\">\n" +
"            <p>Security Question:\n" +
"                <input type=\"text\" name=\"sec_qus\" value="+rs.getString(8)+"></p>\n" +
"                \n" +
"                <p>Answer:\n" +
"                    <input type=\"text\" name=\"answer\" value=\"\"></p>\n" +
"                \n" +
"                <input type=\"submit\" value=\"SUBMIT\">\n" +
"                <input type=\"reset\" value=\"RESET\">\n" +
"        </form>\n" +
"    </body>\n" +
"</html>");
                }
     
            else
            {
                pw.println("Email Incorrect");
            }
            con.close();
        }
        catch(Exception e)
        {
            pw.println(e);
        }
    }
}