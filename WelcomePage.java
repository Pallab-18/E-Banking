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
            pw.println(s);
            if(rs.next())
            {
             pw.println("Login Successful "+rs.getString(2));
             pw.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <title>Main page</title>\n" +
"    <!--<link rel=\"stylesheet\" href=\"after_login.css\">-->\n" +
"    <style>\n" +
"        *{\n" +
"    margin: 0px;\n" +
"    padding: 0px;\n" +
"}\n" +
".top-navbar{\n" +
"    background-color: black;\n" +
"    color: white;\n" +
"    height: 60px;\n" +
"    width: 100%;\n" +
"    position: fixed;\n" +
"    top: 0;\n" +
"    display: flex;\n" +
"    align-items: center;\n" +
"    justify-content: space-around;\n" +
"    font-size: 16px;\n" +
"}\n" +
".nav-item-pannel{\n" +
"    display: flex;\n" +
"    margin: 2px;\n" +
"    padding: 2px;\n" +
"    justify-content: space-between;\n" +
"    list-style: none;\n" +
"}\n" +
".nav-items{\n" +
"    padding: 5px 25px;\n" +
"    border-right: 1px solid grey;\n" +
"    border-left: 1px solid gray;\n" +
"}\n" +
"\n" +
".main{\n" +
"    margin: 80px;\n" +
"    padding: 5px;\n" +
"}\n" +
"    </style>\n" +
"</head>\n" +
"<body>\n" +
"    <div class=\"top-navbar\">\n" +
"        <div class=\"logo\">Lets Pay</div>\n" +
"        <ul class=\"nav-item-pannel\">\n" +
"            <li class=\"nav-items\">Transaction</li>\n" +
"                <ul>\n" +
"                    <li>Bank Transaction</li>\n" +
"                    <li>UPI Transaction</li>\n" +
"                </ul>\n" +
"            <li class=\"nav-items\">Loan Offers</li>\n" +
"            <li class=\"nav-items\">Insurance </li>\n" +
"            <li class=\"nav-items\">Pay Bills</li>\n" +
"            <li class=\"nav-items\">Gov or NGO Funds</li>\n" +
"        </ul>\n" +
"    </div>\n" +
"    <div class=\"main\">\n" +
"        <h1>Welcome "+rs.getString(2)+" &"+rs.getString(1)+"</h1>\n" +
"        <a href=\"#\">Adding Debit / Credit Card To Your Account</a>\n" +
"    </div>\n" +
"</body>\n" +
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