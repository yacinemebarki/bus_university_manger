package Controllers;

import java.sql.*;

import DBConnections.DriversConnection;
import DBConnections.StudentConnection;

import Members.Manager;
import Members.Person;
import Members.Student;
import Members.Driver;

import Models.AllModels;
import Views.Trip_view;
import Views.Manager_view;
import Views.ProblemDashboard;
import Views.bus_view;

import javax.swing.JOptionPane;

public class ManagerController {
    private AllModels<Person> allModels;
    private Manager_view managerView;

    public ManagerController(AllModels<Person> allModels, Manager_view managerView) {
        this.allModels = allModels;
        this.managerView = managerView;

        initController();
    }

    // button actions
    public void initController() {
        managerView.addStudentBtn.addActionListener(e -> addStudent());
        managerView.removeStudentBtn.addActionListener(e -> removeStudent());

        managerView.addDriverBtn.addActionListener(e -> addDriver());
        managerView.removeDriverBtn.addActionListener(e -> removeDriver());

        managerView.logoutBtn.addActionListener(e -> logout());

        managerView.menu.busBtn.addActionListener(e -> goToBus());
        managerView.menu.lineBtn.addActionListener(e -> goToLine());
        managerView.menu.problemBtn.addActionListener(e -> gotToproblem());
        managerView.searchBtn.addActionListener(e -> searchUser());

    }

    // ADD and REMOVE STUDENT ##########################
    public void addStudent() {

        String checkSql = "SELECT COUNT(*) FROM Student WHERE matricule = ?";
        String sql = "INSERT INTO Student (name, matricule, password) VALUES (?, ?, ?)";

        Student student = new Student();

        try (Connection conn = StudentConnection.getConnection()) {

            student.setfullname(managerView.nameField.getText());
            student.setmatricule(managerView.codeField.getText());
            student.setpassword(new String(managerView.passField.getPassword()));

            if (!allModels.validateMember(student)) {
                JOptionPane.showMessageDialog(managerView.frame, "Please fill in all student fields");
                return;
            }

            // 🔎 CHECK DUPLICATE
            try (PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
                checkPs.setString(1, student.getmatricule());

                try (ResultSet rs = checkPs.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(managerView.frame,
                                "Student already exists with this matricule");
                        return;
                    }
                }
            }

            // ✅ INSERT
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, student.getfull_name());
                ps.setString(2, student.getmatricule());
                ps.setString(3, student.getpassword());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(managerView.frame, "Student added successfully");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchUser() {

        String code = managerView.codeField.getText();

        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(managerView.frame, "Enter a matricule/code first");
            return;
        }

        // 🔎 SEARCH STUDENT FIRST
        String sqlStudent = "SELECT * FROM Student WHERE matricule = ?";

        try (Connection conn = StudentConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlStudent)) {

            ps.setString(1, code);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String name = rs.getString("name");
                    String matricule = rs.getString("matricule");
                    String password = rs.getString("password");

                    JOptionPane.showMessageDialog(managerView.frame,
                            "STUDENT FOUND:\n" +
                            "Name: " + name + "\n" +
                            "Matricule: " + matricule + "\n" +
                            "Password: " + password
                    );

                    return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 🔎 IF NOT FOUND IN STUDENT → SEARCH DRIVER
        String sqlDriver = "SELECT * FROM Driver WHERE code = ?";

        try (Connection conn = DriversConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlDriver)) {

            ps.setString(1, code);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String name = rs.getString("name");
                    String driverCode = rs.getString("code");
                    String password = rs.getString("password");

                    JOptionPane.showMessageDialog(managerView.frame,
                            "DRIVER FOUND:\n" +
                            "Name: " + name + "\n" +
                            "Code: " + driverCode + "\n" +
                            "Password: " + password
                    );

                    return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // ❌ NOT FOUND
        JOptionPane.showMessageDialog(managerView.frame,
                "No user found with this code/matricule");
    }

    public void removeStudent() {
        String sql = "DELETE FROM Student WHERE matricule = ?;";

        try (Connection conn = StudentConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, managerView.codeField.getText());

            ps.executeUpdate();

            if (ps.getUpdateCount() > 0) {
                JOptionPane.showMessageDialog(managerView.frame, "Student removed successfully");
            } else {
                JOptionPane.showMessageDialog(managerView.frame, "No student found with that matricule");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ADD and REMOVE DRIVER ##########################
    public void addDriver() {
        String sql = "INSERT INTO Driver (name, code, password) VALUES (?, ?, ?);";

        Driver driver = new Driver();

        try (Connection conn = DriversConnection.getConnection()) {
            driver.setfullname(managerView.nameField.getText());
            driver.setCode(managerView.codeField.getText());
            driver.setpassword(new String(managerView.passField.getPassword()));

            if (!allModels.validateMember(driver)) {
                JOptionPane.showMessageDialog(managerView.frame, "Please fill in all driver fields");
                return;
            }

            String checkSql = "SELECT COUNT(*) FROM Driver WHERE code = ?";
            try (PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
                checkPs.setString(1, driver.getCode());
                try (ResultSet rs = checkPs.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(managerView.frame, "A driver with this code already exists.");
                        return;
                    }
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, driver.getfull_name());
                ps.setString(2, driver.getCode());
                ps.setString(3, driver.getpassword());

                int r = ps.executeUpdate();
                if (r > 0) {
                    JOptionPane.showMessageDialog(managerView.frame, "Driver added successfully");
                } else {
                    JOptionPane.showMessageDialog(managerView.frame, "Failed to add driver");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeDriver() {
        String sql = "DELETE FROM Driver WHERE code = ?;";

        try (Connection conn = DriversConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, managerView.codeField.getText());

            ps.executeUpdate();

            if (ps.getUpdateCount() > 0) {
                JOptionPane.showMessageDialog(managerView.frame, "Driver removed successfully");
            } else {
                JOptionPane.showMessageDialog(managerView.frame, "No driver found with that code");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lines and Buses management can be added here in the future

    // LOGOUT ##########################
    public void logout() {
        new LoginController(new Views.Login_view());
        managerView.frame.setVisible(false);

    }

    // to change pages
    public void goToBus() {
        new BusController(new bus_view());
        managerView.frame.setVisible(false);

    }

    public void goToLine() {
        new LineDashboardController(new Trip_view());
        managerView.frame.setVisible(false);

    }

    public void gotToproblem() {
        new ProblemDashboardController(new ProblemDashboard());
        managerView.frame.setVisible(false);
    }
}
