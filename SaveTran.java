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
                    pw.println("Transaction Successful");
                    pw.println(uid);
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
