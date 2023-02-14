import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet

public class ConfirmLogin extends HttpServlet
{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String login_email=req.getParameter("login_email");
        String login_password=req.getParameter("login_password");
         try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //registering type4 driver for oracle 
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","Hrithik");
            Statement stmt=con.createStatement();
            String s="select * from app_user where email='"+login_email+"'and password='"+login_password+"' ";
            ResultSet rs=stmt.executeQuery(s);
            //pw.println(s);
            if(rs.next())
            {
             pw.println("Login Successful");
             pw.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <meta http-equiv=\"refresh\" content=\"3; URL=after_login.html\" />\n" +
"    <title>redirect</title>\n" +
"</head>\n" +
"<body>\n" +
"    <h2> Login Successful Wait for 3sec</h2>\n" +
"</body>\n" +
"</html>");
            }
            else
            {
                pw.println("Login Unsuccesful");
            }
            con.close();
        }
        catch(Exception e)
        {
            pw.println(e);
        }
    }
}
       


