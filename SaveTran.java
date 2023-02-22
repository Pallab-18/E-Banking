import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.*;//for GenericServlet
import javax.servlet.http.*;//for HttpServlet

public class SaveTran extends HttpServlet {
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
    
        String amount = req.getParameter("amount");
        try{
            HttpSession ses=req.getSession();
            String rev_acc=(String)ses.getAttribute("receiver_account_number");
            String rev_acc_typ=(String)ses.getAttribute("receiver_account_type");
            String rev_name=(String)ses.getAttribute("receiver_name");
            String rev_bank=(String)ses.getAttribute("receiver_bank");
            String rev_ifsc=(String) ses.getAttribute("receiver_ifsc");
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //registering type4 driver for oracle 
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HRITHIK","Hrithik");
            Statement stmt=con.createStatement();
            String q1="insert into transaction_table values(TRAN_ID.nextval,

           pw.println("Transaction Successful");
        }
        catch(Exception e){pw.println(e);}
    }
}