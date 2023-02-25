import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet

public class SaveTran extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String amount = req.getParameter("amount");
        try {
            HttpSession ses = req.getSession();
            String rev_acc = (String) ses.getAttribute("receiver_account_number");
            String rev_acc_typ = (String) ses.getAttribute("receiver_account_type");
            String rev_name = (String) ses.getAttribute("receiver_name");
            String rev_bank = (String) ses.getAttribute("receiver_bank");
            String rev_ifsc = (String) ses.getAttribute("receiver_ifsc ");
            String pay_mode = (String) ses.getAttribute("transaction_type");
            String user_email = (String) ses.getAttribute("user_email");

            Class.forName("oracle.jdbc.driver.OracleDriver");
            // registering type4 driver for oracle
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "RICK", "123456789");
            Statement stmt = con.createStatement();
            String q1 = "select UNIQUE_ID from APP_USER where EMAIL='" + user_email + "'";
            ResultSet rs = stmt.executeQuery(q1);

            if (rs.next()) {
                String uid = rs.getString("UNIQUE_ID");
                String q2 = "insert into transaction_table values(TRAN_ID.nextval, '" + uid + ", '" + pay_mode + "', '"
                        + rev_acc + "', '" + rev_acc_typ + "', '" + rev_name + "', '" + rev_bank + "', '" + rev_ifsc
                        + "', null, null)";
                int rowsInserted = stmt.executeUpdate(q2);

                if (rowsInserted > 0) {
                    pw.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <title>Document</title>\n" +
"    <style>\n" +
"        .receipt {\n" +
"  width: 500px;\n" +
"  margin: 0 auto;\n" +
"  border: 1px solid #ccc;\n" +
"  padding: 20px;\n" +
"}\n" +
"\n" +
".transaction {\n" +
"  text-align: center;\n" +
"}\n" +
"\n" +
".transaction h2 {\n" +
"  margin-top: 0;\n" +
"}\n" +
"\n" +
".qr-code {\n" +
"  display: flex;\n" +
"  justify-content: center;\n" +
"  align-items: center;\n" +
"  margin: 20px 0;\n" +
"}\n" +
"\n" +
".qr-code img {\n" +
"  width: 100px;\n" +
"  height: 100px;\n" +
"}\n" +
".sender-receiver {\n" +
"  display: flex;\n" +
"  justify-content: space-between;\n" +
"  margin-top: 20px;\n" +
"}\n" +
"\n" +
".sender, .receiver {\n" +
"  flex-basis: 48%;\n" +
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
"        <div class=\"transaction\">\n" +
"          <h2>Transaction Details</h2>\n" +
"          <p><strong>Transaction ID:</strong> 123456789</p>\n" +
"          <p><strong>Amount:</strong> $100.00</p>\n" +
"          <p><strong>Date & Time:</strong> 22 Feb 2023 20:00:00 IST</p>\n" +
"          <div class=\"qr-code\">\n" +
"            <img src=\"qr.png\" alt=\"QR Code\">\n" +
"          </div>\n" +
"        </div>\n" +
"        <div class=\"sender-receiver\">\n" +
"          <div class=\"sender\">\n" +
"            <h2>Sender Details</h2>\n" +
"            <p><strong>Name:</strong> Subhrajit Mukherjee</p>\n" +
"            <p><strong>Bank Name:</strong> XYZ Bank</p>\n" +
"            <p><strong>IFSC Code:</strong> XYZ123456</p>\n" +
"            <p><strong>Account Number:</strong> 1234567890</p>\n" +
"            <p><strong>Transaction Type:</strong> Bank Transaction</p>\n" +
"          </div>\n" +
"          <div class=\"receiver\">\n" +
"            <h2>Receiver Details</h2>\n" +
"            <p><strong>Name:</strong> Elon Musk</p>\n" +
"            <p><strong>Bank Name:</strong> ABC Bank</p>\n" +
"            <p><strong>IFSC Code:</strong> ABC123456</p>\n" +
"            <p><strong>Account Number:</strong> 0987654321</p>\n" +
"            <p><strong>Type of Account:</strong> Savings</p>\n" +
"          </div>\n" +
"        </div>\n" +
"      </div>\n" +
"      \n" +
"</body>\n" +
"</html>");
                   // pw.println("");
                } else {
                    pw.println("Transaction unsuccessful");
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
