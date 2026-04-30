package DBConnections;
import java.sql.*;;

public class StdudentConnection {
    private static final String url="jdbc:mysql://localhost:3306/sysdb";  
    private static final String root="root";
    private static final String password="root";
    public static Connection getconnection(){
        try{
            return DriverManager.getConnection(url,root,password);
        }catch(SQLException e){
            System.out.println("connection problem");
            e.printStackTrace();
            return null;
        }
    }
}
