package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Views.Driver_view;

public class DriverController {
    private Driver_view driverView;
    private String driverCode;

    public DriverController(Driver_view driverView, String code) {
        this.driverView = driverView;
        this.driverCode = code;

        initController();
    }

    public void initController() {

        // Load driver info (bus, time, destination)
        String sql = "SELECT matricule FROM buses WHERE driverId = ?";

        try (Connection conn = DBConnections.BusesConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, driverCode);
            ps.executeQuery();

            ResultSet rs = ps.executeQuery();

            driverView.busValue.setText(rs.next() ? rs.getString("matricule") : "N/A");

        } catch (Exception ex) {
            // For demo, just show a message dialog
        }

        // Actions
        driverView.startBtn.addActionListener(e -> start());
        driverView.problemBtn.addActionListener(e -> declareProblem());
    }

    // start bus
    public void start() {
        String sql = "UPDATE buses SET work_status = 'in_progress' WHERE driverId = ?";

        if (driverView.problemBtn.getText().equals("Problem done")) {
            JOptionPane.showMessageDialog(driverView.frame, "Please resolve the problem before starting the bus.");
            return ;
        }

        try (Connection conn = DBConnections.BusesConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, driverCode);
            ps.executeUpdate();

            // Update UI
            driverView.routeLabel.setText("Bus is now in progress...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(driverView.frame, "Error starting the bus: " + ex.getMessage());
        }
    }

    // declare a problem
    public void declareProblem() {
        String sql = "UPDATE buses SET problem_status = 'problem' WHERE driverId = ?";

        try (Connection conn = DBConnections.BusesConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, driverCode);
            ps.executeUpdate();

            // Update UI
            driverView.routeLabel.setText("Bus has reported a problem!");

            if (driverView.problemBtn.getText().equals("Problem done")) {
                String sqlDone = "UPDATE buses SET problem_status = 'OK' WHERE driverId = ?";

                try (PreparedStatement psDone = conn.prepareStatement(sqlDone)) {
                    psDone.setString(1, driverCode);
                    psDone.executeUpdate();
                }

                driverView.routeLabel.setText("Problem resolved. Bus is back to normal.");
                driverView.problemBtn.setText("Report Problem");
            }else {
                driverView.problemBtn.setText("Problem done");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(driverView.frame, "Error declaring the problem: " + ex.getMessage());
        }
    }
}
