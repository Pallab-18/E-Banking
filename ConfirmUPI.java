import java.io.*;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet

public class ConfirmUPI extends HttpServlet {
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String Yvpa = req.getParameter("yvpa");
        String Svpa = req.getParameter("svpa");
        String Pin = req.getParameter("pin");
        String Amount = req.getParameter("amount");
         try{
            HttpSession ses=req.getSession();
            
            ses.setAttribute("yv",Yvpa);
            ses.setAttribute("svpa",Svpa);
            ses.setAttribute("pin",Pin);
            ses.setAttribute("amount",Amount);
            
            pw.println("<html>\n" +
"  <head>\n" +
"  </head>\n" +
"  <body>\n" +
"    <form>\n" +
"      <h2>Money Sending Form</h2>\n" +
"      <div class=\"form-group\">\n" +
"      \n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-account-number\">Your VPA:</label>\n" +
"        <input type=\"email\" id=\"card_number\" name=\"yvpa\" value=\""+Yvpa+"\" disabled>\n" +
"      </div>\n" +
"\n" +
"\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-name\">Send to VPA:</label>\n" +
"        <input type=\"email\" id=\"receiver-name\" name=\"svpa\" value=\""+Svpa+"\"  disabled>\n" +
"      </div>\n" +
"\n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-bank\">Nick Name:</label>\n" +
"        <input type=\"text\" id=\"receiver-bank\" name=\"pin\" value=\""+Pin+"\" disabled>\n" +
"      </div>\n" +
"      \n" +
"      <div class=\"form-group\">\n" +
"        <label for=\"receiver-bank\">Amount:</label>\n" +
"        <input type=\"number\" id=\"amaount\" name=\"amaount\" value=\""+Amount+"\" disabled>\n" +
"      </div>\n" +
"    </form>\n" +
"  </body>\n" +
"</html>");
        }
        catch(Exception e){pw.println(e);}
    }
}