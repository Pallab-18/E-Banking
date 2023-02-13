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
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","cha2023");
            Statement stmt=con.createStatement();
            String s="select * from app_user where email='"+login_email+"' and password='"+login_password+"' ";
            ResultSet rs=stmt.executeQuery(s);
            pw.println(s);
            if(rs.next())
            {
             pw.println("Login Successful");
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
       


