import java.io.*;
import java.sql.*;
class insertLoginAdmin
{
    public static void main(String args[])throws IOException
    {
        
                {
                    DataInputStream ds=new DataInputStream(System.in);
                    //keyboard input
                    System.out.println("Enter Admin id");
                    String ADMIN_ID=ds.readLine();
                    System.out.println("Enter password");
                    String ADMIN_PWD=ds.readLine();
                    try
                    {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        //registering type4 driver of oracle
                        Connection con=DriverManager.getConnection
                                ("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","ujala02");
                        Statement stmt=con.createStatement();
                        String q1="insert into Admin1 values('"+ADMIN_ID+"','"+ADMIN_PWD+"')";
                        int x=stmt.executeUpdate(q1);
                        if(x>0)
                        {
                            System.out.println("Insert success");
                        }
                        else
                        {
                            System.out.println("Insert unsuccess");
                            con.close();
                        }
                        
                    }
                    catch(Exception e)
                        {
                        System.out.println(e);
                        }
                    
                }
                
    }
}