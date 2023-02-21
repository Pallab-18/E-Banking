
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;  //for GenericServlet
import javax.servlet.http.*;  //for HttpServlet

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
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HRITHIK","Hrithik");
            Statement stmt=con.createStatement();
            String s1="select * from app_user where email='"+login_email+"' and password='"+login_password+"' ";
            
            
            ResultSet rs1=stmt.executeQuery(s1);
           
            
            if(rs1.next())
            {
             pw.println("Login Succesful");
             //pw.println(s1);
            //pw.println(s2);
            String uid=rs1.getString(1);
           HttpSession ses=req.getSession();
           ses.setAttribute("unique_id",uid);
//           pw.println(uid);
           
            
            res.sendRedirect("WelcomePage");
            
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
       


