import java.io.*;
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
    
            ses.getAttribute("receiver_account_number");
            ses.getAttribute("receiver_account_type");
            ses.getAttribute("receiver_name");
            ses.getAttribute("receiver_bank");
            ses.getAttribute("receiver_ifsc");
        
            pw.println("Transaction Successful");
        }
        catch(Exception e){pw.println(e);}
    }
}