package DBConnections;
import java.sql.*;

public class BusConnection {
    private static final String url="jdbc:mysql://localhost:3306/bus_management";  // databse name is 
    private static final String root="root";
    private static final String password="root";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(url,root,password);
        }catch(SQLException e){
            System.out.println("connection problem");
            e.printStackTrace();
            return null;
        }
    }
}
