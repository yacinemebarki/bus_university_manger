package Controllers;

import java.sql.*;

import javax.swing.JOptionPane;

import Models.LineModel;
import Views.LineDashboard_view;
import Members.Line;
import Models.AllModels;
import Views.Manager_view;
import Views.ProblemDashboard;
import Views.bus_view;

public class LineDashboardController {

    LineDashboard_view lineDashboardView;

    public LineDashboardController(LineDashboard_view lineDashboardView) {
        this.lineDashboardView = lineDashboardView;

        initController();
        loadLines();
    }

    // ================= LOAD =================
    public void loadLines() {

        lineDashboardView.model.setRowCount(0);

        String sql = "SELECT * FROM LineDashboard";

        try (Connection conn = DBConnections.LinesConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lineDashboardView.model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("line_code"),
                        rs.getString("name"),
                        rs.getString("destination"),
                        rs.getDouble("distance_km")
                });
            }

            lineDashboardView.updateTotal();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= INIT =================
    public void initController() {

        lineDashboardView.addBtn.addActionListener(e -> addLine());
        lineDashboardView.removeBtn.addActionListener(e -> removeLine());
        lineDashboardView.updateBtn.addActionListener(e -> updateLine());
        lineDashboardView.searchBtn.addActionListener(e -> searchLines());

        lineDashboardView.menu.busBtn.addActionListener(e -> goToBus());
        lineDashboardView.menu.personBtn.addActionListener(e -> goToperson());
        lineDashboardView.menu.problemBtn.addActionListener(e -> gotToproblem());
    }

    // ================= ADD =================
    public void addLine() {

        String sql = "INSERT INTO LineDashboard (line_code, name, destination, distance_km) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnections.LinesConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            Line line = new Line();
            line.setCode(lineDashboardView.codeField.getText());
            line.setName(lineDashboardView.nameField.getText());
            line.setDestination(lineDashboardView.destinationField.getText());
            line.setDistance(Double.parseDouble(lineDashboardView.distanceField.getText()));

            if(!LineModel.validateLine(line)) {
                JOptionPane.showMessageDialog(lineDashboardView.frame, "Fill all fields correctly");
                return;
            }

            ps.setString(1, line.getCode());
            ps.setString(2, line.getName());
            ps.setString(3, line.getDestination());
            ps.setDouble(4, line.getDistance());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(lineDashboardView.frame, "Line added successfully!");

            clearFields();
            loadLines();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(lineDashboardView.frame, "Error: " + e.getMessage());
        }
    }

    // ================= REMOVE =================
    public void removeLine() {

        String sql = "DELETE FROM LineDashboard WHERE line_code = ?";

        try (Connection conn = DBConnections.LinesConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, lineDashboardView.codeField.getText());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(lineDashboardView.frame, "Line removed");
                clearFields();
                loadLines();
            } else {
                JOptionPane.showMessageDialog(lineDashboardView.frame, "Line not found");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(lineDashboardView.frame, "Error: " + e.getMessage());
        }
    }

    // ================= UPDATE =================
    public void updateLine() {

        String sql = "UPDATE LineDashboard SET name=?, destination=?, distance_km=? WHERE line_code=?";

        try (Connection conn = DBConnections.LinesConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            Line line = new Line();
            line.setCode(lineDashboardView.codeField.getText());
            line.setName(lineDashboardView.nameField.getText());
            line.setDestination(lineDashboardView.destinationField.getText());
            line.setDistance(Double.parseDouble(lineDashboardView.distanceField.getText()));

            if(!LineModel.validateLine(line)) {
                JOptionPane.showMessageDialog(lineDashboardView.frame, "Fill all fields correctly");
                return;
            }

            ps.setString(1, line.getName());
            ps.setString(2, line.getDestination());
            ps.setDouble(3, line.getDistance());
            ps.setString(4, line.getCode());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(lineDashboardView.frame, "Line updated");
                clearFields();
                loadLines();
            } else {
                JOptionPane.showMessageDialog(lineDashboardView.frame, "Line not found");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(lineDashboardView.frame, "Error: " + e.getMessage());
        }
    }

    // ================= SEARCH =================
    public void searchLines() {

        String sql = "SELECT * FROM LineDashboard WHERE line_code LIKE ? OR destination LIKE ?";

        try (Connection conn = DBConnections.LinesConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String keyword = "%" + lineDashboardView.searchField.getText() + "%";

            ps.setString(1, keyword);
            ps.setString(2, keyword);

            ResultSet rs = ps.executeQuery();

            lineDashboardView.model.setRowCount(0);

            while (rs.next()) {
                lineDashboardView.model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("line_code"),
                        rs.getString("name"),
                        rs.getString("destination"),
                        rs.getDouble("distance_km")
                });
            }

            lineDashboardView.updateTotal();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(lineDashboardView.frame, "Error: " + e.getMessage());
        }
    }

    // ================= CLEAR =================
    private void clearFields() {
        lineDashboardView.codeField.setText("");
        lineDashboardView.nameField.setText("");
        lineDashboardView.destinationField.setText("");
        lineDashboardView.distanceField.setText("");
    }

    // ================= NAVIGATION =================
    public void goToBus(){
        new BusController(new bus_view());
        lineDashboardView.frame.setVisible(false);
    }

    public void goToperson(){
        new ManagerController(new AllModels<>(), new Manager_view());
        lineDashboardView.frame.setVisible(false);
    }

    public void gotToproblem(){
        new ProblemDashboardController(new ProblemDashboard());
        lineDashboardView.frame.setVisible(false);
    }
}
