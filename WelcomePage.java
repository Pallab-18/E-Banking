import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet

public class WelcomePage extends HttpServlet
{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        try
        {
             HttpSession ses=req.getSession();
           String uid=(String)ses.getAttribute("unique_id");
           ses.setAttribute("uid",uid);
//           pw.println(uid);
           
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //registering type4 driver for oracle 
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HRITHIK","Hrithik");
            Statement stmt=con.createStatement();
            String s="select * from app_user where unique_id='"+uid+"'";
             ResultSet rs=stmt.executeQuery(s);
//            pw.println(s);
            if(rs.next())
            {
             pw.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"  <head>\n" +
"    <meta charset=\"UTF-8\" />\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
"    <title>Welcome page</title>\n" +
"    <style>\n" +
"      * {\n" +
"        margin: 0px;\n" +
"        padding: 0px;\n" +
"      }\n" +
"      body {\n" +
"        background: linear-gradient(#6dd5ed, #2193b0);\n" +
"        margin: 0;\n" +
"	padding: 0;\n" +
"	font-family: sans-serif;\n" +
"      }\n" +
"      .navbar {\n" +
"        background: linear-gradient(black, #2f5b8b);\n" +
"        color: white;\n" +
"        height: 60px;\n" +
"        width: 100%;\n" +
"        position: fixed;\n" +
"        top: 0;\n" +
"        display: flex;\n" +
"        align-items: center;\n" +
"        justify-content: space-around;\n" +
"        font-size: 16px;\n" +
"      }\n" +
"      div.navbar > ul {\n" +
"        list-style: none;\n" +
"        margin: 0;\n" +
"        padding: 0;\n" +
"        display: flex;\n" +
"        color: white;\n" +
"      }\n" +
"\n" +
"      ul li {\n" +
"        position: relative;\n" +
"      }\n" +
"\n" +
"      ul li a {\n" +
"        display: block;\n" +
"        padding: 1em;\n" +
"        color: linear-gradient(black, #2f5b8b);\n" +
"        text-decoration: none;\n" +
"      }\n" +
"      ul ul {\n" +
"        display: none;\n" +
"        position: absolute;\n" +
"        top: 100%;\n" +
"        background-color: #2f5b8b;\n" +
"        box-shadow: 3px 2px 20px 1px #111111ed;\n" +
"        border-radius: 6px;\n" +
"      }\n" +
"\n" +
"      .navbar a {\n" +
"        text-decoration: none;\n" +
"        color: white;\n" +
"        margin-right: 20px;\n" +
"        font-size: 18px;\n" +
"      }\n" +
"      .navbar a:hover {\n" +
"        background-color: #3777bc;\n" +
"        border-radius: 6px;\n" +
"      }\n" +
"      ul li:hover > ul {\n" +
"        display: block;\n" +
"      }\n" +
"\n" +
"      ul ul li {\n" +
"        display: block;\n" +
"      }\n" +
"\n" +
"      main {\n" +
"	max-width: 800px;\n" +
"	margin: 6em auto;\n" +
"	text-align: center;\n" +
"}\n" +
"  a{\n" +
"    color: white;\n" +
"  }\n" +
"\n" +
"      h1 {\n" +
"        font-size: 3em;\n" +
"        margin-bottom: 0.5em;\n" +
"      }\n" +
"\n" +
"      p {\n" +
"        font-size: 1.2em;\n" +
"      }\n" +
"\n" +
"      .container {\n" +
"        max-width: 960px;\n" +
"        margin: 40px auto;\n" +
"        text-align: center;\n" +
"      }\n" +
"      .p {\n" +
"        text-align: center;\n" +
"        font-size: 25px;\n" +
"      }\n" +
"      .h {\n" +
"        text-align: left;\n" +
"      }\n" +
"      footer {\n" +
"        background-color: #136f63;\n" +
"        color: #fff;\n" +
"        padding: 50px 0;\n" +
"        display: flex;\n" +
"        flex-wrap: wrap;\n" +
"      }\n" +
"\n" +
"      .footer-left,\n" +
"      .footer-center,\n" +
"      .footer-right {\n" +
"        flex: 1;\n" +
"        padding: 0 20px;\n" +
"      }\n" +
"\n" +
"      .footer-left p {\n" +
"        line-height: 2;\n" +
"      }\n" +
"\n" +
"      .footer-center ul {\n" +
"        list-style: none;\n" +
"        line-height: 2;\n" +
"      }\n" +
"\n" +
"      .footer-center li a {\n" +
"        color: #fff;\n" +
"        text-decoration: none;\n" +
"      }\n" +
"\n" +
"      .footer-right .footer-icons {\n" +
"        display: flex;\n" +
"      }\n" +
"\n" +
"      .footer-right .footer-icons a {\n" +
"        display: inline-block;\n" +
"        margin-right: 10px;\n" +
"      }\n" +
"\n" +
"      .footer-right .footer-icons i {\n" +
"        font-size: 2em;\n" +
"        color: #fff;\n" +
"      }\n" +
"      .profiles {\n" +
"        display: flex;\n" +
"        justify-content: space-between;\n" +
"      }\n" +
"\n" +
"      .profile {\n" +
"        text-align: center;\n" +
"      }\n" +
"\n" +
"      .image {\n" +
"        width: 100px;\n" +
"        height: 100px;\n" +
"        border-radius: 50%;\n" +
"        background-size: cover;\n" +
"        margin: 0 auto;\n" +
"      }\n" +
"\n" +
"      .name {\n" +
"        margin-top: 20px;\n" +
"        font-size: 25px;\n" +
"      }\n" +
"      .nme {\n" +
"        margin-bottom: 5px;\n" +
"        font-size: 15px;\n" +
"      }\n" +
"\n" +
"      .image-container {\n" +
"        /* width: 800px; */\n" +
"        height: 350px;\n" +
"        overflow-x: scroll;\n" +
"        white-space: nowrap;\n" +
"      }\n" +
"\n" +
"      .image-container img {\n" +
"        width: 770px;\n" +
"        height: 300px;\n" +
"        display: inline-block;\n" +
"      }\n" +
"    </style>\n" +
"  </head>\n" +
"  <body>\n" +
"    <div class=\"navbar\">\n" +
"      <div class=\"logo\">Lets Pay</div>\n" +
"      <ul>\n" +
"        <li>\n" +
"          <a href=\"#\">Transaction</a>\n" +
"          <ul>\n" +
"            <a href=\"TransactionPage.html\" style=\"margin-right: 0px\"><li>Bank Transaction</li></a>\n" +
"            <a href=\"UPIPayment.html\" style=\"margin-right: 0px\"><li>UPI Transaction</li></a>\n" +
"          </ul>\n" +
"        </li>\n" +
"        <li><a href=\"#\">Pay Bills</a></li>\n" +
"        <li><a href=\"#\">Loan Offer</a></li>\n" +
"        <li><a href=\"#\">Insurance</a></li>\n" +
"        <li><a href=\"user_registration.html\">Registration</a></li>\n" +
"        <li><a href=\"index.html\">Sign Out</a></li>\n" +
"      </ul>\n" +
"    </div>\n" +
"    <main>\n" +
"      <div class=\"container\">\n" +
"        <h1>Welcome "+rs.getString(2)+" to Lets Pay</h1>\n" +
"        <p>When security is the key of Success</p>\n" +
"      </div>\n" +
"      <div class=\"container\">\n" +
"        <p>\n" +
"          A good bank is not only the financial heart of the community, but also\n" +
"          one with an obligation of helping in every possible manner to improve\n" +
"          the economic conditions of the common people\n" +
"        </p>\n" +
"      </div>\n" +
"      <a href=\"CardPayment.html\"><p>Adding Debit / Credit Card To Your Account</p></a>\n" +
"      <div class=\"container\">\n" +
"        <p>\n" +
"          Customers are requested to call on our mentioned Toll Free Numbers\n" +
"          only for any complaints/issues. Bank shall not be responsible for any\n" +
"          consequences arising out of customers calling any other unverified\n" +
"          numbers.\n" +
"        </p>\n" +
"      </div>\n" +
"      <div class=\"container\">\n" +
"        <p>Toll Free Number:- +91 111222333</p>\n" +
"      </div>\n" +
"    </main>\n" +
"      <div class=\"image-container\">\n" +
"        <img class=\"image1\" src=\"img1.jpg\">\n" +
"        <img class=\"image2\" src=\"img2.jpg\">\n" +
"        <img class=\"image3\" src=\"img3.png\">\n" +
"        <img class=\"image4\" src=\"img4.jpg\">\n" +
"      </div>\n" +
"      <main>\n" +
"      <h1>Contributors</h1>\n" +
"      <div class=\"profiles\">\n" +
"        <div class=\"profile\">\n" +
"          <div class=\"image\" style=\"background-image: url('Hriktik.jpg')\"></div>\n" +
"          <div class=\"name\">Hrithik Gupta</div>\n" +
"          <div class=\"nme\">Team Leader</div>\n" +
"        </div>\n" +
"        <div class=\"profile\">\n" +
"          <div class=\"image\" style=\"background-image: url('Ujala.jpeg')\"></div>\n" +
"          <div class=\"name\">Ujala Naaz</div>\n" +
"        </div>\n" +
"        <div class=\"profile\">\n" +
"          <div class=\"image\" style=\"background-image: url('pallab.jpg')\"></div>\n" +
"          <div class=\"name\">Pallab Chatterjee</div>\n" +
"        </div>\n" +
"        <div class=\"profile\">\n" +
"          <div class=\"image\" style=\"background-image: url('Subhrajit (3).jpeg')\"></div>\n" +
"          <div class=\"name\">Subhrajit Mukherjee</div>\n" +
"        </div>\n" +
"      </div>\n" +
"\n" +
"      <footer>\n" +
"        <div class=\"footer-left\">\n" +
"          <h3>Lets Pay</h3>\n" +
"          <p>221B,Baker Street</p>\n" +
"          <p>London, UK 12345</p>\n" +
"          <p>Phone: (033) 456-7890</p>\n" +
"          <p>Email: info@Letspay.com</p>\n" +
"        </div>\n" +
"        <div class=\"footer-center\">\n" +
"          <h3>Quick Links</h3>\n" +
"          <ul>\n" +
"            <li><a href=\"#\">Home</a></li>\n" +
"            <li><a href=\"#\">About Us</a></li>\n" +
"            <li><a href=\"#\">Transaction</a></li>\n" +
"          </ul>\n" +
"        </div>\n" +
"      </footer>\n" +
"    </main>\n" +
"  </body>\n" +
"</html>");
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