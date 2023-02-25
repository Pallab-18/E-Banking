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
                
                ResultSet rs2 = stmt1.executeQuery("SELECT TRAN_ID.NEXTVAL FROM dual");
                    if(rs2.next()){
                  int tid= rs2.getInt(1);               

                String q2 = "insert into tran_table values('"+tid+"','"+uid+"','"+pay_mode+"','"+rev_acc+"','" +rev_acc_typ+"','"+rev_name+"','"+rev_bank+"','"+rev_ifsc+"',null,null,'"+amount+"')";
                int rowsInserted = stmt2.executeUpdate(q2);

                if (rowsInserted > 0) {
                    pw.println("Transaction successful");

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
