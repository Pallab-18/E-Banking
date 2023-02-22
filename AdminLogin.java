import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet
public class AdminLogin extends HttpServlet
{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
        {
            res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String nm1=req.getParameter("n1");
        String nm2=req.getParameter("n2");

        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //registering type4 driver of oracle
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HRITHIK","Hrithik");
            Statement stmt = con.createStatement();
            String s1 = "select * from Admin1 where ADMIN_ID='"+nm1+"' and ADMIN_PWD='"+nm2+"'";
            ResultSet rst = stmt.executeQuery(s1);
//            pw.println(s1);
            if (rst.next()) {
                pw.println("login successful");
            } 
            else {
                pw.println("login unsuccessful");
            }
            con.close();

                
        }
        catch(Exception e)
        {
            pw.println(e);
        }
        
        }
}