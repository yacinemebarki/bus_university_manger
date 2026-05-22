package Controllers;

import Views.Login_view;
import Views.Manager_view;
import Views.Student_view;
import DBConnections.ManagerConnection;
import DBConnections.StudentConnection;
import Models.AllModels;
import DBConnections.DriversConnection;

import java.sql.*;

import javax.swing.JOptionPane;

public class LoginController {

    private Login_view loginView;

    public LoginController(Login_view loginView) {
        this.loginView = loginView;

        initController();
    }

    public void initController() {
        loginView.loginBtn.addActionListener(e -> login());
    }

    private void login() {

        String name = loginView.nameField.getText();
        String password = new String(loginView.passField.getPassword());
        String role = loginView.roleBox.getSelectedItem().toString();

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(loginView.frame, "Please enter both name and password");
            return;
        }

        switch (role) {

            case "Manager":
                String sql = "SELECT * FROM Manager WHERE code = ? AND password = ?";

                try (Connection conn = ManagerConnection.getConnection();
                        PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setString(1, name);
                    ps.setString(2, password);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(loginView.frame, "Manager login successful");

                        loginView.frame.setVisible(false); // close the login view
                        new ManagerController(new AllModels<>(), new Manager_view()); // Open the manager view

                    } else {
                        JOptionPane.showMessageDialog(loginView.frame, "Invalid manager credentials");
                        return;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(loginView.frame, "Database error: " + e.getMessage());
                    return;
                }
                break;

            case "Driver":
                String sqlDriver = "SELECT * FROM Driver WHERE code = ? AND password = ?";
                try (Connection conn = DriversConnection.getConnection();
                        PreparedStatement ps = conn.prepareStatement(sqlDriver)) {

                    ps.setString(1, name);
                    ps.setString(2, password);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(loginView.frame, "Driver login successful");

                        loginView.frame.setVisible(false); // close the login view
                        new DriverController(new Views.Driver_view(), rs.getString("code")); // Open the driver view

                    } else {
                        JOptionPane.showMessageDialog(loginView.frame, "Invalid driver credentials");
                        return;
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(loginView.frame, "Database error: " + e.getMessage());
                    return;
                }

                break;

            case "Student":
                String sqlStudent = "SELECT * FROM Student WHERE matricule = ? AND password = ?";

                try (Connection conn = StudentConnection.getConnection();
                        PreparedStatement ps = conn.prepareStatement(sqlStudent)) {

                    ps.setString(1, name);
                    ps.setString(2, password);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(loginView.frame, "Student login successful");

                        loginView.frame.setVisible(false); // close the login view
                        new StudentController(new Student_view()); // Open the student view

                    } else {
                        JOptionPane.showMessageDialog(loginView.frame, "Invalid student credentials");
                        return;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(loginView.frame, "Database error: " + e.getMessage());
                    return;
                }
                break;

            default:
                JOptionPane.showMessageDialog(loginView.frame, "Invalid role");
                break;
        }

    }
}