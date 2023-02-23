import java.io.*;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet

public class Confirmtran extends HttpServlet {
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String transaction_type = req.getParameter("transaction_type");
        String receiver_account_number = req.getParameter("receiver_account_number");
        String receiver_account_type = req.getParameter("receiver_account_type");
        String receiver_name = req.getParameter("receiver_name");
        String receiver_bank = req.getParameter("receiver_bank");
        String receiver_ifsc = req.getParameter("receiver_ifsc");
        String user_email= req.getParameter("user_email");
         try{
            HttpSession ses=req.getSession();
            
            ses.setAttribute("transaction_type",transaction_type);
            ses.setAttribute("receiver_account_number",receiver_account_number);
            ses.setAttribute("receiver_account_type",receiver_account_type);
            ses.setAttribute("receiver_name",receiver_name);
            ses.setAttribute("receiver_bank",receiver_bank);
            ses.setAttribute("receiver_ifsc", receiver_ifsc);
            ses.setAttribute("user_email",user_email);
            
            pw.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <title>Document</title>\n" +
"</head>\n" +
"<body>\n" +
"    <form action=\"SaveTran\" method=\"post\">\n" +
"      <h2>Money Sending Form</h2>\n" +
"      \n" +
"        <input name=\"mode_of_pay\" value=\"Bank Transaction\" hidden>\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"user_email\">Your Email:</label>\n" +
"        <input type=\"text\" id=\"receiver_account_number\" name=\"user_email\" value=\""+User_Email+"\" disable>\n" +
"      </div>\n" +
"\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-account-number\">Receiver Account Number:</label>\n" +
"        <input type=\"text\" id=\"receiver_account_number\" name=\"receiver_account_number\" value=\""+Receiver_account_number+"\" disable>\n" +
"      </div>\n" +
"\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-account-number\">Account Type:</label>\n" +
"        <select id=\"receiver-account-type\" name=\"receiver_account_type\" value=\""+Receiver_Account_Type+"\" disable>\n" +
"          <option value=\"savings\">Saving Account</option>\n" +
"          <option value=\"current\">Current Account</option>\n" +
"        </select>\n" +
"      </div>\n" +
"\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-name\">Account Holder Name:</label>\n" +
"        <input type=\"text\" id=\"receiver-name\" name=\"receiver_name\" value=\""+Receiver_Name+"\" disable>\n" +
"      </div>\n" +
"\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-bank\">Receiver Bank:</label>\n" +
"        <input type=\"text\" id=\"receiver-bank\" name=\"receiver_bank\" value=\""+Receiver_Bank+"\" disable>\n" +
"      </div>\n" +
"      \n" +
"      \n" +
"     <div class=\"form-group\">\n" +
"        <label for=\"receiver-ifsc\">IFSC Code:</label>\n" +
"        <input type=\"text\" id=\"receiver-ifsc\" name=\"receiver_ifsc\" value=\""+Receiver_IFSC+"\" disable>\n" +
"      </div>\n" +
"      \n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-bank\">Amount:</label>\n" +
"        <input type=\"number\" id=\"amaount\" name=\"amount\" value=\"amount\" required>\n" +
"      </div>\n" +
"\n" +
"      <input type=\"submit\" value=\"Submit\">\n" +
"      <input type=\"reset\" value=\"Reset\">\n" +
"    </form>\n" +
"  </body>\n" +
"</html>");
        }
        catch(Exception e){pw.println(e);}
    }
}