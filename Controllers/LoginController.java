package Controllers;

import Views.Login_view;
import Views.Manager_view;
import DBConnections.ManagerConnection;

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
            return ;
        }

        switch (role) {

            case "Manager":
                String sql = "SELECT * FROM managers WHERE name = ? AND password = ?";
                
                try (Connection conn =  ManagerConnection.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setString(1, name);
                    ps.setString(2, password);

                    var rs = ps.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(loginView.frame, "Manager login successful");

                        loginView.frame.setVisible(false); // close the login view
                        new Manager_view(); // Open the manager view

                    } else {
                        JOptionPane.showMessageDialog(loginView.frame, "Invalid manager credentials");
                        return ;
                    }
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(loginView.frame, "Database error: " + e.getMessage());
                    return;
                }
                break;

            case "Driver":
                JOptionPane.showMessageDialog(loginView.frame, "Driver login (not implemented yet)");
                break;

            case "Student":
                JOptionPane.showMessageDialog(loginView.frame, "Student login (not implemented yet)");
                break;

            default:
                JOptionPane.showMessageDialog(loginView.frame, "Invalid role");
                break;
        }

    }
}