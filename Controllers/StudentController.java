package Controllers;
import java.sql.*;

import Views.Login_view;
import Views.Student_view;
import DBConnections.TripConnection;

public class StudentController {
    private Student_view view;

    public StudentController(Student_view view){
        this.view=view;
        builldTable();
        initController();
    }
    public void initController(){
        view.searchBtn.addActionListener(e -> searchByLine());
        view.clearBtn.addActionListener(e -> clear());
        view.logoutBtn.addActionListener(e -> logout());

    }

    public void builldTable(){
        String sql="SELECT * FROM trips";
        
        try{
            Connection con=TripConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            view.lines.setRowCount(0);

            while(rs.next()){
                String depart=rs.getString("depart");
                String matricule=rs.getString("bus_id");
                Time hour=rs.getTime("start_time");
                String status=rs.getString("status");
                String direction=rs.getString("direction");
                view.lines.addRow(new Object[] {depart,matricule,hour,direction,status});
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void searchByLine(){
        String sql="SELECT * FROM trips";
        try{
            Connection con=TripConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            String line_name=view.searchField.getText();
            
            ResultSet rs=ps.executeQuery();

            view.lines.setRowCount(0);
            boolean found = false;
            while(rs.next()){
                
                String line=rs.getString("depart");
                String direction=rs.getString("direction");
                if(similarity(line_name.toLowerCase(), line.toLowerCase())>0.5 || similarity(line_name.toLowerCase(), direction.toLowerCase())>0.5){
                    found=true;
                    String matricule=rs.getString("bus_id");
                    Time hour=rs.getTime("start_time");
                    String status=rs.getString("status");
                    
                    view.lines.addRow(new Object[] {line,matricule,hour,direction,status});
                }
            }
            if(!found){
                view.lines.addRow(new Object[]{"Nothing Match","","","",""});
            }
                

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void clear(){
        view.searchField.setText("");
        builldTable();
    }
    public static double similarity(String w1,String w2){
        int min_len=Math.min(w1.length(), w2.length());
        int s=0;
        for(int i=0;i<min_len;i++){
            if(w1.charAt(i)==w2.charAt(i)){
                s++;
            }
        }
        if(min_len!=0){
            return s/min_len;
        }
        else{
            return 0;
        }
    }
    public void logout() {
        new LoginController(new Login_view());
        view.frame.setVisible(false);
    }
}
