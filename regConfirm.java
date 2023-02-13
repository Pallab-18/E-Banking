
import java.io.*;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet

public class RegConfirm extends HttpServlet {
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String first_name = req.getParameter("first_name");
        String middle_name = req.getParameter("middle_name");
        String last_name = req.getParameter("last_name");
        String user_email = req.getParameter("user_email");
        String mobile = req.getParameter("mobile");
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");
        String user_bank = req.getParameter("user_bank");
        String ifsc = req.getParameter("ifsc");
        String acc = req.getParameter("acc");
        String branch = req.getParameter("branch");
         try{
            HttpSession ses=req.getSession();
            
            ses.setAttribute("first_name",first_name);
            ses.setAttribute("middle_name",middle_name);
            ses.setAttribute("last_name",last_name);
            ses.setAttribute("user_email",user_email);
            ses.setAttribute("mobile",mobile);
            ses.setAttribute("password",password);
            ses.setAttribute("user_bank",user_bank);
            ses.setAttribute("ifsc",ifsc);
            ses.setAttribute("acc",acc);
            ses.setAttribute("branch",branch);//Will have to add more setAttributes for transfering the data to the sql table
            
            pw.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <title>Registration Confirm Page</title>\n" +
"</head>");
            pw.println("<body>\n" +
"    <h3>Confirm Your Details And Your Bank Details</h3>\n" +
"    <form  method=\"POST\" action=\"afterRegConfirm\">\n" +
"        <p>First Name:\n" +
"            <input type=\"text\" name=\"first_name\" value=\""+first_name+"\" disabled>\n" +
"        </p>\n" +
"\n" +
"        <p>Middle Name:\n" +
"            <input type=\"text\" name=\"middle_name\" value=\""+middle_name+"\" disabled>\n" +
"        </p>\n" +
"\n" +
"        <p>Last Name:\n" +
"            <input type=\"text\" name=\"last_name\" value=\""+last_name+"\" disabled>\n" +
"        </p>\n" +
"\n" +
"        <p> Email ID:\n" +
"            <input type=\"email\" name=\"user_email\" value=\""+user_email+"\" disabled>\n" +
"        </p>\n" +
"\n" +
"        <p>Mobile No:\n" +
"            <input type=\"tel\" name=\"mobile\" value=\""+mobile+"\" disabled>\n" +
"        </p>\n" +
"\n" +
"        <p>" +
"        <p>Password:\n" +
"            <input type=\"password\" name=\"password\" value=\""+password+"\" disabled>\n" +
"        </p>\n" +
"\n" +
"        <p>Bank:\n" +
"            <input type=\"text\" name=\"user_bank\" value=\""+user_bank+"\" disabled>\n" +
"        </p>\n" +
"\n" +
"        <p>IFSC Code:\n" +
"            <input type=\"text\" name=\"ifsc\" value=\""+ifsc+"\" disabled>\n" +
"        </p>\n" +
"\n" +
"        <p>A/C No:\n" +
"            <input type=\"text\" name=\"acc\" value=\""+acc+"\" disabled>\n" +
"        </p>\n" +
"\n" +
"        <p>Branch:\n" +
"            <input type=\"text\" name=\"branch\" value=\""+branch+"\" disabled>\n" +
"        </p>\n" +
"\n" +
"        <hr>\n" +
"\n" +
"        <p>Choose an ID Proof:\n" +
"        <input type=\"radio\" name=\"id_proof\" value=\"voterID\">Voter ID Card\n" +
"        <input type=\"radio\" name=\"id_proof\" value=\"aadhar\">Aadhar Card</p>\n" +
"\n" +
"        <p>Enter the UID:<input type=\"text\" name=\"uid_no\"></p>\n" +
"\n" +
"        <p>Uplode the Document: <input type=\"file\"></p>\n" +
"\n" +
"        <input type=\"checkbox\" name=\"checkbox\" value=\"checked\">I hereby Confirm that All the above details filed is correct<br>\n" +
"        <br>\n" +
"        <button type=\"submit\">CONFIRM</button>\n" +
"        \n" +
"    </form>\n" +
"</body></html>");
        }
        catch(Exception e){pw.println(e);}
    }
}
