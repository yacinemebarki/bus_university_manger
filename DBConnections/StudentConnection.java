package DBConnections;
import java.sql.*;;

public class StudentConnection {
    private static final String url="jdbc:mysql://localhost:3306/Students";  // so put 'Students' instead of 'sysdb'
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
