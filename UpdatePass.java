import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet
public class UpdatePass extends HttpServlet {
  public void doPostt(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
         res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String password=req.getParameter("create_password");
         try
        {
               HttpSession ses=req.getSession();
          String email=(String) ses.getAttribute("email");
            
             Class.forName("oracle.jdbc.driver.OracleDriver");
            //registering type4 driver for oracle 
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HRITHIK","Hrithik");
            Statement stmt=con.createStatement();
            String s="update app_user set password='"+password+"' where email='"+email+"'";
            int rs1=stmt.executeUpdate(s);
               if(rs1>0){
                pw.println("Update Success");
                        }
     
            else
            {
                pw.println("Update Unscccess");
            }
            con.close();
        }
        catch(Exception e)
        {
            pw.println(e);
        }
    }
}