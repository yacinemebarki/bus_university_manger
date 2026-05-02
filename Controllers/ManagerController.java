package Controllers;

import java.sql.*;

import DBConnections.DriversConnection;
import DBConnections.StudentConnection;

import Members.Manager;
import Members.Person;
import Members.Student;
import Members.Driver;

import Models.AllModels;
import Models.AllModels;
import Views.LineDashboard_view;
import Views.Manager_view;

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

        managerView.linesDashboardBtn.addActionListener(e -> lineDashboard());
    }


    // ADD and REMOVE STUDENT ##########################
    public void addStudent() {
        String sql = "INSERT INTO students (name, matricule, password) VALUES (?, ?, ?);";

        Student student = new Student();

        try (Connection conn = StudentConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            student.setfullname(managerView.nameField.getText());
            student.setmatricule(managerView.codeField.getText());
            student.setpassword(new String(managerView.passField.getPassword()));

            if(!allModels.validateMember(student)) {
                JOptionPane.showMessageDialog(managerView.frame, "Please fill in all student fields");
                return;
            }

            ps.setString(1, student.getfull_name());
            ps.setString(2, student.getmatricule());
            ps.setString(3, student.getpassword());

            int r = ps.executeUpdate();
            if (r > 0) {
                JOptionPane.showMessageDialog(managerView.frame, "Student added successfully");
            } else {
                JOptionPane.showMessageDialog(managerView.frame, "Failed to add student");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeStudent() {
        String sql = "DELETE FROM students WHERE matricule = ?;";

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
        String sql = "INSERT INTO drivers (name, code, password) VALUES (?, ?, ?);";

        Driver driver = new Driver();

        try (Connection conn = DriversConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            driver.setfullname(managerView.nameField.getText());
            driver.setCode(managerView.codeField.getText());
            driver.setpassword(new String(managerView.passField.getPassword()));

            if(!allModels.validateMember(driver)) {
                JOptionPane.showMessageDialog(managerView.frame, "Please fill in all driver fields");
                return;
            }

            ps.setString(1, driver.getfull_name());
            ps.setString(2, driver.getCode());
            ps.setString(3, driver.getpassword());

            int r = ps.executeUpdate();
            if (r > 0) {
                JOptionPane.showMessageDialog(managerView.frame, "Driver added successfully");
            } else {
                JOptionPane.showMessageDialog(managerView.frame, "Failed to add driver");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeDriver() {
        String sql = "DELETE FROM drivers WHERE code = ?;";

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
    public void lineDashboard() {
        new LineDashboard_view();
    }

    // LOGOUT ##########################
    public void logout() {
        managerView.frame.setVisible(false);
        new LoginController(new Views.Login_view());
    }
}
