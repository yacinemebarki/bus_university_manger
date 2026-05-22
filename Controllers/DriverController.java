package Controllers;

import DBConnections.ProblemConnection;
import DBConnections.TripConnection;
import Views.Driver_view;

import javax.swing.*;
import java.sql.*;

public class DriverController {

        private Driver_view view;

        private String driver_code;

        public DriverController(
                        Driver_view view,
                        String driver_code) {

                this.view = view;

                this.driver_code = driver_code;

                buildTable();

                initController();
        }

        public void initController() {

                view.tripTable.addMouseListener(

                                new java.awt.event.MouseAdapter() {

                                        @Override
                                        public void mouseClicked(
                                                        java.awt.event.MouseEvent e) {

                                                int row = view.tripTable.getSelectedRow();

                                                if (row != -1) {

                                                        String trip_id = view.model.getValueAt(
                                                                        row,
                                                                        0).toString();

                                                        showOptions(trip_id, row);
                                                }
                                        }
                                });
        }

        // LOAD DRIVER TRIPS
        public void buildTable() {

                String sql = "SELECT * FROM trips WHERE driver_id=?";

                try {

                        Connection con = TripConnection.getConnection();

                        PreparedStatement ps = con.prepareStatement(sql);

                        ps.setString(1, driver_code);

                        ResultSet rs = ps.executeQuery();

                        view.model.setRowCount(0);

                        while (rs.next()) {

                                String id = rs.getString("id");

                                String depart = rs.getString("depart");

                                String direction = rs.getString("direction");

                                String start_time = rs.getString("start_time");

                                String bus = rs.getString("bus_id");

                                view.model.addRow(
                                                new Object[] {

                                                                id,
                                                                depart,
                                                                direction,
                                                                start_time,
                                                                bus

                                                });
                        }

                }

                catch (Exception e) {

                        e.printStackTrace();
                }
        }

        // OPTIONS PANEL
        public void showOptions(
                        String trip_id,
                        int row) {

                String[] options = {

                                "Update Status",
                                "Report Problem"

                };

                int choice = JOptionPane.showOptionDialog(

                                null,

                                "Choose Action For Trip "
                                                + trip_id,

                                "Trip Options",

                                JOptionPane.DEFAULT_OPTION,

                                JOptionPane.INFORMATION_MESSAGE,

                                null,

                                options,

                                options[0]);

                // STATUS
                if (choice == 0) {

                        showStatusPanel(
                                        trip_id,
                                        row);
                }

                // REPORT
                else if (choice == 1) {

                        showProblemPanel(
                                        trip_id);
                }
        }

        // STATUS PANEL
        public void showStatusPanel(
                        String trip_id,
                        int row) {

                String[] status = {

                                "AT STATION",
                                "ON ROAD",
                                "DELAYED",
                                "FINISHED"

                };

                String selected_status = (String) JOptionPane.showInputDialog(

                                null,

                                "Select Trip Status",

                                "Status",

                                JOptionPane.QUESTION_MESSAGE,

                                null,

                                status,

                                status[0]);

                if (selected_status != null) {

                        updateStatus(
                                        trip_id,
                                        selected_status);

                        JOptionPane.showMessageDialog(
                                        null,
                                        "Status Updated");
                }
        }

        // REPORT PANEL
        public void showProblemPanel(
                        String trip_id) {

                String[] problems = {

                                "Driver Fatigue",
                                "Full Bus",
                                "Vehicle Problem",
                                "Road Problem"

                };

                String selected_problem = (String) JOptionPane.showInputDialog(

                                null,

                                "Select Problem",

                                "Report Problem",

                                JOptionPane.QUESTION_MESSAGE,

                                null,

                                problems,

                                problems[0]);

                if (selected_problem != null) {

                        reportProblem(
                                        trip_id,
                                        selected_problem);

                        JOptionPane.showMessageDialog(
                                        null,
                                        "Problem Reported");
                }
        }

        // UPDATE STATUS DATABASE
        public void updateStatus(
                        String trip_id,
                        String status) {

                String sql = "UPDATE trips SET status=? WHERE id=?";

                try {

                        Connection con = TripConnection.getConnection();

                        PreparedStatement ps = con.prepareStatement(sql);

                        ps.setString(1, status);

                        ps.setString(2, trip_id);

                        ps.executeUpdate();

                        buildTable();

                }

                catch (Exception e) {

                        e.printStackTrace();
                }
        }

        // REPORT PROBLEM DATABASE
        public void reportProblem(
                        String trip_id,
                        String problem_type) {

                String sql = "INSERT INTO Problem "
                                + "(driver_id,trip_id,"
                                + "problem_date,"
                                + "problem_time,"
                                + "problem_type)"
                                + " VALUES(?,?,?,?,?)";

                try {

                        Connection con = ProblemConnection.getConnection();

                        PreparedStatement ps = con.prepareStatement(sql);

                        ps.setString(1, driver_code);

                        ps.setString(2, trip_id);

                        ps.setDate(
                                        3,
                                        new java.sql.Date(
                                                        System.currentTimeMillis()));

                        ps.setTime(
                                        4,
                                        new java.sql.Time(
                                                        System.currentTimeMillis()));

                        ps.setString(
                                        5,
                                        problem_type);

                        ps.executeUpdate();
                }

                catch (Exception e) {

                        e.printStackTrace();
                }
        }
}
