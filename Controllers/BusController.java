package Controllers;
import DBConnections.BusConnection;
import Members.bus;
import Views.bus_view;
import java.sql.*;

import javax.swing.JOptionPane;

public class BusController {
    public bus_view view;
    public BusController(bus_view view){
        this.view=view;
    }
    public boolean search(String matricule){
        String sql="SELECT work_status,problem_status FROM buses WHERE matricule=?";
        try{
            Connection con=BusConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, matricule);
            boolean found=false;
            ResultSet rs=ps.executeQuery();
            view.lines.setRowCount(0);
            while (rs.next()) {
                found=true;
                String work_status=rs.getString("work_status");
                String problem_status=rs.getString("problem_status");
                view.lines.addRow(new Object[]{matricule,work_status,problem_status});
            }
            return found;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public void addBus(String matricule){
        String sql="INSERT INTO buses (matricule, work_status, problem_status) VALUES (?, ?, ?)";
        try{
            Connection con=BusConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            String work_status="OFF_WORK";
            String problem_status="OK";
            boolean found=search(matricule);
            if(found){
                ps.setString(1,matricule);
                ps.setString(2, work_status);
                ps.setString(3,problem_status);
                int r=ps.executeUpdate();
                if (r > 0) {
                    JOptionPane.showMessageDialog(view.frame, "Bus added successfully");
                } else {
                    JOptionPane.showMessageDialog(view.frame, "failed to add bus");
                }

            }else{
                JOptionPane.showMessageDialog(view.frame, "Bus exist with same matricule");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
