import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet

public class afterRegConfirm extends HttpServlet
{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw1=res.getWriter();

        String id_proof=req.getParameter("id_proof");
        String uid_no=req.getParameter("uid_no");
        //pw1.println("<html><body bgcolor=skyblue>Welcome "+nm+"</body></html>");//response from servlet
         try
        {   
         HttpSession ses=req.getSession();
        String first_name=(String) ses.getAttribute("first_name");
        String middle_name=(String) ses.getAttribute("middle_name");
        String last_name=(String) ses.getAttribute("last_name");
        String user_email=(String) ses.getAttribute("user_email");
        String mobile=(String) ses.getAttribute("mobile");
        String password=(String) ses.getAttribute("password");
        String sec_qus=(String) ses.getAttribute("sec_qus");
        String answer=(String) ses.getAttribute("answer");
        String user_bank=(String) ses.getAttribute("user_bank");
        String ifsc=(String) ses.getAttribute("ifsc");
        String acc=(String) ses.getAttribute("acc");
        String branch=(String) ses.getAttribute("branch");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //registering type4 driver for oracle 
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HRITHIK","Hrithik");
            Statement stmt=con.createStatement();
            String q1="insert into app_user values(user_seq.nextval,'"+first_name+"','"+middle_name+"','"+last_name+"','"+user_email+"','"+mobile+"','"+password+"',' "+sec_qus+" ' ,' "+answer+" ' ,' "+user_bank+" ' ,'"+ifsc+" ', ' "+acc+" ' ,' "+branch+" ',' "+id_proof+" ' ,' "+uid_no+" ')";
            int x=stmt.executeUpdate(q1);
            
          // pw1.println(q1);
            if(x>0)
            {
                pw1.println("I<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <meta http-equiv=\"refresh\" content=\"3; URL=login.html\" />\n" +
"    <title>redirect</title>\n" +
"</head>\n" +
"<body>\n" +
"    <h1> Redirect in 3 sec to Login</h1>\n" +
"</body>\n" +
"</html>");
            }
            else
            {
                pw1.println("Insert Unsuccesful");
                con.close();
            }
        
        }
        catch(Exception e)
        {
            pw1.println(e);
        }
    }
}