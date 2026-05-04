package Controllers;
import DBConnections.BusConnection;
import Models.AllModels;
import Views.LineDashboard_view;
import Views.Manager_view;
import Views.bus_view;
import java.sql.*;

import javax.swing.JOptionPane;

public class BusController {
    public bus_view view;
    public BusController(bus_view view){
        this.view=view;
        buildtable();
        initControll();
    }
    public void initControll(){
        view.searchBtn.addActionListener(e->{
            String type=(String) view.searchTypeCombo.getSelectedItem();
            ;
            if(type.equals("By Matricule")){
                String matricule=view.matriculeField.getText();
                searchByMatricule(matricule);
            }
            else if(type.equals("By Work Status")){
                String work=(String) view.workStatusCombo.getSelectedItem();
                searchBywork(work);
            }
            else if(type.equals("By Problem Status")){
                String problem=(String) view.problemStatusCombo.getSelectedItem();
                    searchByProblem(problem);
            }       
        });
        view.addBusBtn.addActionListener(e->addBus());
        view.removeBusBtn.addActionListener(e->delete());
        view.statusBtn.addActionListener(e->setbus());
        view.menu.personBtn.addActionListener(e->goToperson());
        view.menu.lineBtn.addActionListener(e->goToLine());
    }
    public boolean searchByMatricule(String matricule){
        String sql="SELECT work_status,problem_status FROM buses WHERE matricule=?";
        try{
            Connection con=BusConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            if (matricule.length()<10 || matricule.length()>11){
                JOptionPane.showMessageDialog(view.frame, "the matricule must have 10 digits");
                return false;
            }
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
    public void addBus(){
        String sql="INSERT INTO buses (matricule, work_status, problem_status) VALUES (?, ?, ?)";
        try{
            Connection con=BusConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            String work_status=(String) view.workStatusCombo.getSelectedItem();
            String problem_status=(String) view.problemStatusCombo.getSelectedItem();
            String matricule=view.matriculeField.getText();
            if (matricule.length()<10 || matricule.length()>11){
                JOptionPane.showMessageDialog(view.frame, "the matricule must have 10 digits");
                return;
            }
            boolean found=searchByMatricule(matricule);
            if(!found){
                ps.setString(1,matricule);
                ps.setString(2, work_status);
                ps.setString(3,problem_status);
                int r=ps.executeUpdate();
                if (r > 0) {
                    JOptionPane.showMessageDialog(view.frame, "Bus added successfully");
                    buildtable();
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
    public void buildtable(){
        String sql="SELECT * FROM buses";
        try{
            Connection con=BusConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            Boolean found=false;
            view.lines.setRowCount(0);
            while (rs.next()) {
                found=true;
                String matricule=rs.getString("matricule");
                String work_status=rs.getString("work_status");
                String problem_status=rs.getString("problem_status");
                view.lines.addRow(new Object[]{matricule,work_status,problem_status});        
            }
            if(!found){
                view.lines.addRow(new Object[]{"Nothing found","",""});
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public boolean searchByProblem(String problem_status){
        String sql="SELECT matricule,work_status FROM buses WHERE problem_status=?";
        try{
            Connection con=BusConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, problem_status);
            boolean found=false;
            ResultSet rs=ps.executeQuery();
            view.lines.setRowCount(0);
            while (rs.next()) {
                found=true;
                String work_status=rs.getString("work_status");
                String matricule=rs.getString("matricule");
                view.lines.addRow(new Object[]{matricule,work_status,problem_status});
            }
            return found;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }   
    }
    public boolean searchBywork(String work_status){
        String sql="SELECT problem_status,matricule FROM buses WHERE work_status=?";
        try{
            Connection con=BusConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, work_status);
            boolean found=false;
            ResultSet rs=ps.executeQuery();
            view.lines.setRowCount(0);
            while (rs.next()) {
                found=true;
                String matricule=rs.getString("matricule");
                String problem_status=rs.getString("problem_status");
                view.lines.addRow(new Object[]{matricule,work_status,problem_status});
            }
            return found;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }   
    }
    public void delete(){
        String sql="DELETE FROM buses WHERE matricule=?";
        try{
            Connection con=BusConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            String matricule=view.matriculeField.getText();
            ps.setString(1, matricule);
            int nb=ps.executeUpdate();
            if(nb>0){
                JOptionPane.showMessageDialog(view.frame, "the bus was deleted");
            }
            else{
                JOptionPane.showMessageDialog(view.frame, "not existing bus with this matricule");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void setbus(){
        String sql="UPDATE buses SET work_status=?, problem_status=? WHERE matricule=?";
        try{
            Connection con=BusConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            String matricule=view.matriculeField.getText();
            String work=(String) view.workStatusCombo.getSelectedItem();
            String problem=(String) view.problemStatusCombo.getSelectedItem();
            if(matricule.equals("")){
                JOptionPane.showMessageDialog(view.frame, "matricule is required (matriucle fiels is empty)");
                return;
            }
            ps.setString(1, work);
            ps.setString(2, problem);
            ps.setString(3,matricule);
            int nb=ps.executeUpdate();
            if(nb>0){
                JOptionPane.showMessageDialog(view.frame, "the bus stats was updated");
                buildtable();
            }
            else{
                JOptionPane.showMessageDialog(view.frame, "no existing bus with this matricule");        
            }   
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void goToLine(){
        new LineDashboardController(new LineDashboard_view());
        view.frame.setVisible(false);
    }
    public void goToperson(){
        new ManagerController(new AllModels<>(),new Manager_view());
        view.frame.setVisible(false);
    }
}
