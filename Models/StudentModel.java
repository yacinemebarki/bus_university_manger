package Models;
import java.sql.*;
import Members.Student;

public class StudentModel {

    public static Student searchByMatricule(Connection con,String matricule){
        String sql = "SELECT name, password FROM students WHERE matricule=?";
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, matricule);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                String name=rs.getString("name");
                String password=rs.getString("password");
                return new Student(name,password,matricule);
            }
            else{
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("Database error", e);
        }
    }
    
    
    public static String insert(Connection con,Student student){
        String sql="INSERT INTO students (name, password, matricule) VALUES (?, ?, ?)";
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            String full_name=student.getfull_name();
            String password=student.getpassword();
            String matricule=student.getmatricule();
            ps.setString(1, full_name);
            ps.setString(2, password);
            ps.setString(3, matricule);
            Student s=searchByMatricule(con, matricule);
            if (s==null){
                int nb=ps.executeUpdate();
                return "inserted lignes: "+nb;
            }
            else{
                return "Student already exists (matricule duplicate)";
            }
        
        }catch(SQLException e){
            throw new RuntimeException("Database error", e);
        }

    }
    public static void delete() {
        
    }  
}
