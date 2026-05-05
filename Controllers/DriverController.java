package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DBConnections.BusConnection;
import DBConnections.DriversConnection;

import javax.swing.JOptionPane;

import Views.Driver_view;

public class DriverController {
    private Driver_view driverView;

    public DriverController(Driver_view driverView) {
        this.driverView = driverView;

        initController();
    }

    public void initController() {

        // Load driver info (bus, time, destination)
        String sql = "SELECT busMatricule FROM drivers";

        try (Connection conn = DriversConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeQuery();

            ResultSet rs = ps.executeQuery();

            driverView.busValue.setText(rs.next() ? rs.getString("busMatricule") : "N/A");

        } catch (Exception ex) {
            // For demo, just show a message dialog
        }

        // Actions
        driverView.startBtn.addActionListener(e -> start());
        driverView.problemBtn.addActionListener(e -> declareProblem());
    }

    // start bus
    public void start() {
        String sql = "UPDATE buses SET work_status = 'ON_WORK' WHERE matricule = ?";

        if (driverView.problemBtn.getText().equals("Problem done")) {
            JOptionPane.showMessageDialog(driverView.frame, "Please resolve the problem before starting the bus.");
            return ;
        }

        try (Connection conn = BusConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, driverView.busValue.getText());
            ps.executeUpdate();

            // Update UI
            driverView.routeLabel.setText("Bus is now in progress...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(driverView.frame, "Error starting the bus: " + ex.getMessage());
        }
    }

    // declare a problem
    public void declareProblem() {
        String sql = "UPDATE buses SET problem_status = 'problem', work_status = 'stopped' WHERE matricule = ?";

        try (Connection conn = BusConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, driverView.busValue.getText());
            ps.executeUpdate();

            // Update UI
            driverView.routeLabel.setText("Bus has reported a problem!");

            if (driverView.problemBtn.getText().equals("Problem done")) {
                String sqlDone = "UPDATE buses SET problem_status = 'OK' WHERE matricule = ?";

                try (PreparedStatement psDone = conn.prepareStatement(sqlDone)) {
                    psDone.setString(1, driverView.busValue.getText());
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
