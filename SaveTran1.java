import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet

public class SaveTran1 extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        java.util.Date date = new java.util.Date();

        String amount = req.getParameter("amount");
        try {
            HttpSession ses = req.getSession();
            String rev_acc = (String) ses.getAttribute("receiver_account_number");
            String rev_acc_typ = (String) ses.getAttribute("receiver_account_type");
            String rev_name = (String) ses.getAttribute("receiver_name");
            String rev_bank = (String) ses.getAttribute("receiver_bank");
            String rev_ifsc = (String) ses.getAttribute("receiver_ifsc");
            String pay_mode = (String) ses.getAttribute("transaction_type");
            String user_email = (String) ses.getAttribute("user_email");
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // registering type4 driver for oracle
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HRITHIK","Hrithik");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();

            String q1 = "select * from APP_USER where EMAIL='"+user_email+"'";
            ResultSet rs3 = stmt.executeQuery(q1);
            
            if( rs3.next()) {
                String uid = rs3.getString(1);
                String fname = rs3.getString(2);
                String lname = rs3.getString(4);
                String sacc = rs3.getString(12);
                String sbank = rs3.getString(10);
                String sifsc = rs3.getString(11);

                ResultSet rs2 = stmt1.executeQuery("SELECT TRAN_ID.NEXTVAL FROM dual");
                    if(rs2.next()){
                  int tid= rs2.getInt(1);    

                String q2 = "insert into tran_table values('"+tid+"','"+uid+"','"+pay_mode+"','"+rev_acc+"','" +rev_acc_typ+"','"+rev_name+"','"+rev_bank+"','"+rev_ifsc+"',null,null,'"+amount+"')";
                int rowsInserted = stmt2.executeUpdate(q2);

                if (rowsInserted > 0) {
                    pw.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <title>Reciept</title>\n" +
"    <style>\n" +
"body{\n" +
"  background: linear-gradient(white,#2e555e);\n" +
"  height: 92vh;\n" +
"}\n" +
"\n" +
".receipt {\n" +
"  width: 520px;\n" +
"  margin: 50px auto;\n" +
"  border: 1px solid #ccc;\n" +
"  border-radius: 5px;\n" +
"  padding: 20px;\n" +
"  display: flex;\n" +
"  flex-direction: column;\n" +
"  align-items: center;\n" +
"  background: linear-gradient(#6dd5edd1, white);\n" +
"  box-shadow: 5px 5px 10px black;\n" +
"}\n" +
"\n" +
".transaction{\n" +
"  display: flex;\n" +
"    justify-content: space-between;\n" +
"}\n" +
"\n" +
".transaction h2 {\n" +
"  margin-top: 0;\n" +
"}\n" +
"\n" +
".qr-code {\n" +
"  border: 1px solid black;\n" +
"    width: 110px;\n" +
"    height: 110px;\n" +
"  margin: 20px 0;\n" +
"}\n" +
"\n" +
".qr-code img {\n" +
"  width: 110px;\n" +
"    height: 110px;\n" +
"}\n" +
".sender-receiver {\n" +
"  display: flex;\n" +
"  justify-content: space-between;\n" +
"  padding: 5px 20px;\n" +
"}\n" +
"\n" +
".sender, .receiver {\n" +
"  flex-basis: 48%;\n" +
"  padding: 0 20px;\n" +
"}\n" +
"\n" +
".sender h2, .receiver h2 {\n" +
"  margin-top: 0;\n" +
"}\n" +
"\n" +
"    </style>\n" +
"</head>\n" +
"<body>\n" +
"    <div class=\"receipt\">\n" +
"      <h2 style=\"margin-bottom: 0px;\">Transaction Details</h2>\n" +
"      <hr color=\"black\" width=\"100%\">\n" +
"        <div style=\"width: 100%\">\n" +
"          <p class=\"transaction\"><span><strong>Transaction ID: </strong>"+tid+"</span>\n" +
"            <span><strong>Date & Time: </strong>"+date.toString()+"</span></p>\n" +
"          <p class=\"transaction\"><span><strong>Amount: </strong> &#8377 "+amount+"</span>\n" +
"            <span><strong>Transaction Type: </strong>"+pay_mode+"</span></p>\n" +
"        </div>\n" +
"          \n" +
"          <div class=\"qr-code\">\n" +
"            <img src=\"qr-code.svg\" alt=\"QR Code\">\n" +
"          </div>\n" +
"        <div class=\"sender-receiver\">\n" +
"          <div class=\"sender\">\n" +
"            <h2>Sender Details</h2>\n" +
"            <p><strong>Name: </strong>"+fname+" "+lname+"</p>\n" +
"            <p><strong>Email: </strong>"+user_email+"</p>\n" +
"            <p><strong>Account No.: </strong>"+sacc+"</p>\n" +
"            <p><strong>Bank Name :</strong>"+sbank+"</p>\n" +
"            <p><strong>IFSC Code: </strong>"+sifsc+"</p>\n" +
"          </div>\n" +
"          <div class=\"receiver\">\n" +
"            <h2>Receiver Details</h2>\n" +
"            <p><strong>Name: </strong>"+rev_name+"</p>\n" +
"            <p><strong>Account Type: </strong>"+rev_acc_typ+"</p>\n" +
"            <p><strong>Account No.: </strong>"+rev_acc+"</p>\n" +
"            <p><strong>Bank Name: </strong>"+rev_bank+"</p>\n" +
"            <p><strong>IFSC Code: </strong>"+rev_ifsc+"</p>\n" +
"          </div>\n" +
"        </div>\n" +
"      </div>\n" +
"      \n" +
"</body>\n" +
"</html>");
                } else {
                    pw.println("Transaction Unsuccessful");
                }
                    }
            } else {
                pw.println("User not found");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            pw.println(e);
        }
    }
}
