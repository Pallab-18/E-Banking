import java.io.*;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet

public class ConfirmUPI extends HttpServlet {
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String yvpa = req.getParameter("yvpa");
        String svpa = req.getParameter("svpa");
        String nick = req.getParameter("nick");
        String email=req.getParameter("email");
        String transaction_type = req.getParameter("mode_of_pay");
        
         try{
            HttpSession ses=req.getSession();
            ses.setAttribute("transaction_type",transaction_type);
            ses.setAttribute("yvpa",yvpa);
            ses.setAttribute("svpa",svpa);
            ses.setAttribute("nick",nick);
            ses.setAttribute("email",email);
        
            
            pw.println("<html>\n" +
"  <head> \n" +
"    <style>\n" +
"        form {\n" +
"          width: 500px;\n" +
"          margin: 0 auto;\n" +
"          padding: 30px;\n" +
"          border: 1px solid #ccc;\n" +
"          border-radius: 10px;\n" +
"        }\n" +
"  \n" +
"        h2 {\n" +
"          text-align: center;\n" +
"          margin-bottom: 30px;\n" +
"        }\n" +
"  \n" +
"  \n" +
"        .form-group {\n" +
"          display: flex;\n" +
"          flex-direction: column;\n" +
"          margin-bottom: 20px;\n" +
"        }\n" +
"  \n" +
"        .form-group label {\n" +
"          font-weight: bold;\n" +
"          margin-bottom: 10px;\n" +
"        }\n" +
"  \n" +
"        .form-group input[type=\"text\"], .form-group input[type=\"number\"], .form-group input[type=\"email\"],.form-group select {\n" +
"          padding: 10px;\n" +
"          font-size: 16px;\n" +
"          width: 100%;\n" +
"          border-radius: 5px;\n" +
"          border: 1px solid #ccc;\n" +
"        }\n" +
"  \n" +
"      \n" +
"        input[type=\"submit\"] {\n" +
"          width: 120px;\n" +
"          height: 40px;\n" +
"          background-color: #4CAF50;\n" +
"          color: #fff;\n" +
"          border: none;\n" +
"          border-radius: 5px;\n" +
"          cursor: pointer;\n" +
"          margin-top: 20px;\n" +
"        }\n" +
"        </style>\n" +
"  </head>\n" +
"  <body>\n" +
"    <form  action=\"Saveupi\" method=\"post\">\n" +
"      <h2>Money Sending Form</h2>\n" +
"      <div class=\"form-group\">\n" +
"        <input name=\"mode_of_pay\" value=\"UPI Transaction\" hidden>\n" +
"      \n" +
"        <div class=\"form-group\">\n" +
"            <label for=\"receiver-bank\">Email:</label>\n" +
"            <input type=\"text\" id=\"receiver-bank\" name=\"email\" value=\""+email+"\" hideden>\n" +
"          </div>\n" +
"      <div class=\"form-group\">\n" +
"       <label for=\"receiver-account-number\">Your VPA:</label>\n" +
"        <input type=\"email\" id=\"card_number\" name=\"yvpa\" value=\""+yvpa+"\" disabled>\n" +
"      </div>\n" +
"\n" +
"\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-name\">Send to VPA:</label>\n" +
"        <input type=\"email\" id=\"receiver-name\" name=\"svpa\" value=\""+svpa+"\"  disabled>\n" +
"      </div>\n" +
"\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-bank\">Nick Name:</label>\n" +
"        <input type=\"text\" id=\"receiver-bank\" name=\"nick\" value=\""+nick+"\" disabled>\n" +
"      </div>\n" +
"      <div class=\"form-group\"> \n" +
"        <label for=\"receiver-bank\">Amount:</label>\n" +
"        <input type=\"number\" id=\"amount\" name=\"amount\" required>\n" +
"      </div>\n" +
"      <input type=\"submit\" value=\"Submit\">\n" +
"    </form>\n" +
"  </body>\n" +
"</html>");
        }
        catch(Exception e){pw.println(e);}
    }
}
