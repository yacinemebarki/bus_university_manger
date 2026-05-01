package Controllers;
import java.sql.*;
import Members.*;
import Views.Student_view;
import DBConnections.LinesConnection;

public class StudentController {
    private Student_view view;

    public StudentController(Student_view view){
        this.view=view;
        builldTable();
    }
    public void initController(){
        view.searchBtn.addActionListener(e -> searchByLine());
    }

    public void builldTable(){
        String sql="SELECT * FROM transport_lines";
        
        try{
            Connection con=LinesConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            view.lines.setRowCount(0);

            while(rs.next()){
                String line=rs.getString("line");
                String matricule=rs.getString("matricule");
                String hour=rs.getString("hour");
                String status=rs.getString("status");
                String direction=rs.getString("direction");
                view.lines.addRow(new Object[] {line,matricule,hour,direction,status});
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void searchByLine(){
        String sql="SELECT * FROM transport_lines WHERE line LIKE ?";
        try{
            Connection con=LinesConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            String line_name=view.searchField.getText();
            ps.setString(1,line_name);
            ResultSet rs=ps.executeQuery();

            view.lines.setRowCount(0);
            boolean found = false;
            while(rs.next()){
                found=true;
                String line=rs.getString("line");
                String matricule=rs.getString("matricule");
                String hour=rs.getString("hour");
                String status=rs.getString("status");
                String direction=rs.getString("direction");
                view.lines.addRow(new Object[] {line,matricule,hour,direction,status});
            }
            if(!found){
                view.lines.addRow(new Object[]{"Nothing Match","","","",""});
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
