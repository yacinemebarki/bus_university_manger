package Controllers;

import Views.Manager_view;
import Views.ProblemDashboard;
import Views.Trip_view;
import Views.bus_view;
import DBConnections.ProblemConnection;
import DBConnections.TripConnection;
import Models.AllModels;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProblemDashboardController {

    public ProblemDashboard view;

    public ProblemDashboardController(ProblemDashboard view){
        this.view=view;
        buildtable();
        initController();
    }

    // ================= INIT =================
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

        // delete on click row
        view.table.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent e){

                int row=view.table.getSelectedRow();

                if(row!=-1){

                    int confirm=JOptionPane.showConfirmDialog(
                            null,
                            "Delete this Problem",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION
                    );

                    if(confirm==JOptionPane.YES_OPTION){

                        try{
                            String id_text=view.table.getValueAt(row, 0).toString();
                            int id=Integer.parseInt(id_text);

                            Connection con=ProblemConnection.getConnection();

                            String sql = "DELETE FROM Problem WHERE problem_id = ?";
                            PreparedStatement ps=con.prepareStatement(sql);

                            ps.setInt(1, id);

                            int nb=ps.executeUpdate();

                            if(nb>0){
                                view.model.removeRow(row);
                                buildtable();
                                JOptionPane.showMessageDialog(view.frame, "Problem deleted");
                            }
                            else{
                                JOptionPane.showMessageDialog(view.frame, "failed to delete");
                            }

                        }catch(SQLException v){
                            v.printStackTrace();
                        }
                    }
                }
            }
        });
        

        view.menu.busBtn.addActionListener(e->goToBus());
        view.menu.personBtn.addActionListener(e->goToperson());
    }
    

    // ================= BUILD TABLE =================
    public void buildtable(){

        String sql="SELECT * FROM Problem";

        try{
            Connection con=ProblemConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            view.model.setRowCount(0);

            boolean found=false;

            while(rs.next()){

                int id=rs.getInt("problem_id");
                String trip_id=rs.getString("trip_id");
                String driver_id=rs.getString("driver_id");
                String problem_type=rs.getString("problem_type");
                String message=rs.getString("message");
                Date date=rs.getDate("problem_date");
                Time time=rs.getTime("problem_time");
                String name=rs.getString("Line_name");

                view.model.addRow(new Object[]{
                        id, trip_id, name, driver_id,
                        problem_type, date, time, message
                });

                found=true;
            }

            if(!found){
                view.model.addRow(new Object[] {"Nothing found","","","","","","",""});
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    // ================= SEARCH BY LINE =================
    public void searchByLine(){

        String sql="SELECT * FROM Problem WHERE Line_name=?";

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

            view.model.setRowCount(0);

            boolean found=false;

            while (rs.next()) {

                int id=rs.getInt("problem_id");
                String trip_id=rs.getString("trip_id");
                String driver_id=rs.getString("driver_id");
                String problem_type=rs.getString("problem_type");
                String message=rs.getString("message");
                Date date=rs.getDate("problem_date");
                Time time=rs.getTime("problem_time");

                view.model.addRow(new Object[]{
                        id, trip_id, text, driver_id,
                        problem_type, date, time, message
                });

                found=true;
            }

            if(!found){
                view.model.addRow(new Object[] {"Nothing found","","","","","","",""});
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    // ================= SEARCH BY TYPE =================
    public void searchByProblem(){

        String sql="SELECT * FROM Problem WHERE problem_type=?";

        try{
            Connection con=ProblemConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);

            String problem_type=(String) view.ProblemBox.getSelectedItem();
            ps.setString(1, problem_type);

            ResultSet rs=ps.executeQuery();

            view.model.setRowCount(0);

            boolean found=false;

            while (rs.next()) {

                int id=rs.getInt("problem_id");
                String trip_id=rs.getString("trip_id");
                String driver_id=rs.getString("driver_id");
                String name=rs.getString("Line_name");
                String message=rs.getString("message");
                Date date=rs.getDate("problem_date");
                Time time=rs.getTime("problem_time");

                view.model.addRow(new Object[]{
                        id, trip_id, name, driver_id,
                        problem_type, date, time, message
                });

                found=true;
            }

            if(!found){
                view.model.addRow(new Object[] {"Nothing found","","","","","","",""});
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    // ================= SEARCH BY BOTH =================
    public void searchByBoth(){

        String sql="SELECT * FROM Problem WHERE Line_name=? AND problem_type=?";

        try{
            Connection con=ProblemConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);

            String text=view.nameField.getText();

            if(text.isEmpty()){
                JOptionPane.showMessageDialog(view.frame, "empty name field");
                return;
            }

            String problem_type=(String)view.ProblemBox.getSelectedItem();

            ps.setString(1, text);
            ps.setString(2, problem_type);

            ResultSet rs=ps.executeQuery();

            view.model.setRowCount(0);

            boolean found=false;

            while (rs.next()) {

                int id=rs.getInt("problem_id");
                String trip_id=rs.getString("trip_id");
                String driver_id=rs.getString("driver_id");
                String message=rs.getString("message");
                Date date=rs.getDate("problem_date");
                Time time=rs.getTime("problem_time");

                view.model.addRow(new Object[]{
                        id, trip_id, text, driver_id,
                        problem_type, date, time, message
                });

                found=true;
            }

            if(!found){
                view.model.addRow(new Object[] {"Nothing found","","","","","","",""});
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    // ================= NAVIGATION =================
    public void goToLine(){
        new LineDashboardController(new Trip_view());
        view.frame.setVisible(false);
    }

    public void goToperson(){
        new ManagerController(new AllModels<>(),new Manager_view());
        view.frame.setVisible(false);
    }

    public void goToBus(){
        new BusController(new bus_view());
        view.frame.setVisible(false);
    }
}
