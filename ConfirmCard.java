import java.io.*;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet

public class ConfirmCard extends HttpServlet {
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String card_type = req.getParameter("card_type");
        String card_number = req.getParameter("card_number");
        String expiry = req.getParameter("expiry");
        String pin = req.getParameter("pin");
        String CVV= req.getParameter("CVV");
        String Amount = req.getParameter("Amount");
         try{
            HttpSession ses=req.getSession();
            
            ses.setAttribute("card_type",card_type);
            ses.setAttribute("card_number",card_number);
            ses.setAttribute("expiry",expiry);
             ses.setAttribute("Pin",pin);
            ses.setAttribute("CVV",CVV);
            ses.setAttribute("amount",Amount);
            
            pw.println("<html>\n" +
"  <head>\n" +
"  </head>\n" +
"  <body>\n" +
"    <form action=\"ConfirmCard\" method=\"post\">\n" +
"      <h2>Money Sending Form</h2>\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"transaction-type\">Card Type:</label>\n" +
"        <select id=\"transaction-type\" name=\"card_type\" value=\""+card_type+"\" required>\n" +
"          <option value=\"Credit\">Credit Card</option>\n" +
"          <option value=\"Debit\">Debit Card</option>\n" +
"        </select>\n" +
"      </div>\n" +
"      \n" +
"      \n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-account-number\">Card Number:</label>\n" +
"        <input type=\"number\" id=\"card_number\" name=\"card_number\" value=\""+card_number+"\" disabled>\n" +
"      </div>\n" +
"\n" +
"\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-name\">Expiry:</label>\n" +
"        <input type=\"date\" id=\"Expiry\" name=\"expiry\" value=\""+expiry+"\" disabled>\n" +
"      </div>\n" +
"\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-name\">CVV:</label>\n" +
"        <input type=\"number\" id=\"CVV\" name=\"CVV\" value=\""+CVV+"\" disabled>\n" +
"      </div>\n" +
"\n" +
"\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-bank\">PIN:</label>\n" +
"        <input type=\"number\" id=\"pin\" name=\"pin\" value=\""+pin+"\" disabled>\n" +
"      </div>\n" +
"      \n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-bank\">Amount:</label>\n" +
"        <input type=\"number\" id=\"amaount\" name=\"amount\" value=\"amount\" required>\n" +
"      </div>\n" +
"      <input type=\"submit\" value=\"Submit\">\n" +
"    </form>\n" +
"  </body>\n" +
"</html>");
        }
        catch(Exception e){pw.println(e);}
    }
}
