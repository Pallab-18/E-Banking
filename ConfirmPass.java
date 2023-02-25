import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet
public class ConfirmPass extends HttpServlet {
  public void doPostt(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
         res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String sec_qus=req.getParameter("sec_qus");
        String ans=req.getParameter("answer");
         try
        {
             HttpSession ses=req.getSession();
          String email=(String) ses.getAttribute("email");
            
             Class.forName("oracle.jdbc.driver.OracleDriver");
            //registering type4 driver for oracle 
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HRITHIK","Hrithik");
            Statement stmt=con.createStatement();
            String s = "select * from app_user where email='"+email+"' and sec_ans='"+ans+"'";
            
             ResultSet rs=stmt.executeQuery(s);
              if(rs.next()){
                pw.println("<html>\n" +
"    <head>\n" +
"        <title>\n" +
"            Create New Passsword\n" +
"        </title>\n" +
"    </head>\n" +
"    <body>\n" +
"        <form action=\"UpdatePass\" method=\"post\">\n" +
"            \n" +
"            <h3>Reset Password</h3>\n" +
"        <p>\n" +
"            Create Password:\n" +
"            <input type=\"password\" name=\"create_password\" value=\"\">\n" +
"        </p>\n" +
"        \n" +
"        <p>\n" +
"            Confirm Password:\n" +
"            <input type=\"password\" name=\"confirm_password\" value=\"\">\n" +
"        </p>\n" +
"        \n" +
"        <input type=\"submit\" value=\"SUBMIT\">\n" +
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