package Controllers;
import Views.ProblemDashboard;
import DBConnections.ProblemConnection;
import java.sql.*;
import java.awt.*;

import javax.swing.JOptionPane;

public class ProblemDashboardController {
    public ProblemDashboard view;
    public ProblemDashboardController(ProblemDashboard view){
        this.view=view;
        buildtable();
    }
    
    public void initController(){
        view.searchBtn.addActionListener(e->{
            String text=(String) view.seachBox.getSelectedItem();
            if(text.equals("By name")){
                searchByLine();
            }
            if(text.equals("By Type")){
                searchByProblem();
            }
            if(text.equals("Both")){
                searchByBoth();
            }    
        });
        //delete just click on row
        view.table.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent e){
                int row=view.table.getSelectedRow();
                if(row!=-1){
                    int confirm=JOptionPane.showConfirmDialog(null, "Delete this Problem","Confirm",JOptionPane.YES_NO_OPTION);
                    if(confirm==JOptionPane.YES_OPTION){
                        try{
                            String id_text=view.table.getValueAt(row, 0).toString();
                            int id=Integer.parseInt(id_text);
                            Connection con=ProblemConnection.getConnection();
                            String sql = "DELETE FROM driver_problems WHERE problem_id = ?";
                            PreparedStatement ps=con.prepareStatement(sql);
                            ps.setInt(1, id);
                            int nb=ps.executeUpdate();
                            if(nb>0){
                                view.model.removeRow(row);
                                buildtable();
                                JOptionPane.showMessageDialog(view.frame, "Problem deleted");
                            }
                            else{
                                JOptionPane.showMessageDialog(view.frame, "fialed to delete");
                            }
                        }catch(SQLException v){
                            v.printStackTrace();
                        }
                    }
                }
            }
        });  
    }

    public void buildtable(){
        String sql="SELECT * FROM driver_problems";
        try{
            Connection con=ProblemConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            view.model.setRowCount(0);
            boolean found=false;   
            while(rs.next()){
                String trip_id=rs.getString("trip_id");
                String driver_id=rs.getString("driver_id");
                String problem_type=rs.getString("problem_type");
                String message=rs.getString("message");
                Date date=rs.getDate("problem_date");
                Time time=rs.getTime("problem_time");
                String name=rs.getString("Line_name");
                int id=rs.getInt("problem_id");
                view.model.addRow(new Object[]{id,trip_id,name,driver_id,problem_type,date,time,message});
                found=true;
            }
            if(!found){
                view.model.addRow(new Object[] {"Nothing found","","","","",""});
            }    
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void searchByLine(){
        String sql="SELECT problem_id,trip_id,driver_id,problem_type,message,problem_date,problem_time FROM driver_problems WHERE Line_name=?";
        try{
            Connection con=ProblemConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            String text=view.nameField.getText();
            if(text.isEmpty()){
                JOptionPane.showMessageDialog(view.frame, "empty name field");
                return;
            }
            ps.setString(1, text);
            ResultSet rs=ps.executeQuery();
            Boolean found=false;
            while (rs.next()) {
                String trip_id=rs.getString("trip_id");
                String driver_id=rs.getString("driver_id");
                String problem_type=rs.getString("problem_type");
                String message=rs.getString("message");
                Date date=rs.getDate("problem_date");
                Time time=rs.getTime("problem_time");
                int id=rs.getInt("problem_id");
                view.model.addRow(new Object[]{id,trip_id,text,driver_id,problem_type,date,time,message});
                found=true;
            }
            if(!found){
                view.model.addRow(new Object[] {"Nothing found","","","","",""});
            }  

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void searchByProblem(){
        String sql="SELECT problem_id,trip_id,driver_id,name,message,problem_date,problem_time FROM driver_problems WHERE problem_type=?";
        try{
            Connection con=ProblemConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            String problem_type=(String) view.ProblemBox.getSelectedItem();
            ps.setString(1, problem_type);
            ResultSet rs=ps.executeQuery();
            Boolean found=false;
            while (rs.next()) {
                String trip_id=rs.getString("trip_id");
                String driver_id=rs.getString("driver_id");
                String name=rs.getString("Line_name");
                String message=rs.getString("message");
                Date date=rs.getDate("problem_date");
                Time time=rs.getTime("problem_time");
                int id=rs.getInt("problem_id");
                view.model.addRow(new Object[]{id,trip_id,name,driver_id,problem_type,date,time,message});
                found=true;
            }
            if(!found){
                view.model.addRow(new Object[] {"Nothing found","","","","",""});
            }  

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void searchByBoth(){
        String sql="SELECT problem_id,trip_id,driver_id,message,problem_date,problem_time FROM driver_problems WHERE Line_name=?,problem_type=?";
        try{
            Connection con=ProblemConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            String text=view.nameField.getText();
            if(text.isEmpty()){
                JOptionPane.showMessageDialog(view.frame, "empty name field");
                return;
            }
            String problem_type=(String)view.ProblemBox.getSelectedItem();
            ResultSet rs=ps.executeQuery();
            Boolean found=false;
            while (rs.next()) {
                String trip_id=rs.getString("trip_id");
                String driver_id=rs.getString("driver_id");
              
                String message=rs.getString("message");
                Date date=rs.getDate("problem_date");
                Time time=rs.getTime("problem_time");
                int id=rs.getInt("problem_id");
                view.model.addRow(new Object[]{id,trip_id,text,driver_id,problem_type,date,time,message});
                found=true;
            }
            if(!found){
                view.model.addRow(new Object[] {"Nothing found","","","","",""});
            }  

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
