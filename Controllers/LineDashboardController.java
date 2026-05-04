package Controllers;

import java.sql.*;

import javax.swing.JOptionPane;

import Models.LineModel;
import Views.LineDashboard_view;
import Members.Line;

public class LineDashboardController {
    LineDashboard_view lineDashboardView;

    public LineDashboardController(LineDashboard_view lineDashboardView) {
        this.lineDashboardView = lineDashboardView;

        initController();
        loadLines();
    }

    public void loadLines() {

        lineDashboardView.model.setRowCount(0);

        String sql = "SELECT * FROM linesDB";

        try (Connection conn = DBConnections.LinesConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lineDashboardView.model.addRow(new Object[]{
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

    public void initController() {
        System.out.println("ok");
        // Buttons
        lineDashboardView.addBtn.addActionListener(e -> addLine());
        lineDashboardView.removeBtn.addActionListener(e -> removeLine());
        lineDashboardView.updateBtn.addActionListener(e -> updateLine());
        lineDashboardView.searchBtn.addActionListener(e -> searchLines());
    }

    // add
    public void addLine() {
        String sql = "INSERT INTO linesDB (line_code, name, destination, distance_km) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnections.LinesConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            Line line = new Line();
            line.setCode(lineDashboardView.codeField.getText());
            line.setName(lineDashboardView.nameField.getText());
            line.setDestination(lineDashboardView.destinationField.getText());
            line.setDistance(Double.parseDouble(lineDashboardView.distanceField.getText()));

            if(!LineModel.validateLine(line)) {
                JOptionPane.showMessageDialog(lineDashboardView.frame, "Please fill in all line fields correctly");
                return;
            }

            ps.setString(1, line.getCode());
            ps.setString(2, line.getName());
            ps.setString(3, line.getDestination());
            ps.setDouble(4, line.getDistance());

            int rowsAffected = ps.executeUpdate();

            JOptionPane.showMessageDialog(lineDashboardView.frame, "Line added successfully!");

            clearFields();
            loadLines();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(lineDashboardView.frame, "Error adding line: " + e.getMessage());
        }
    }

    // remove
    public void removeLine() {
        String sql = "DELETE FROM linesDB WHERE line_code = ?";

        try (Connection conn = DBConnections.LinesConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, lineDashboardView.codeField.getText());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(lineDashboardView.frame, "Line removed successfully!");
                clearFields();
                loadLines();
            } else {
                JOptionPane.showMessageDialog(lineDashboardView.frame, "No line found with that code.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(lineDashboardView.frame, "Error removing line: " + e.getMessage());
        }
    }

    // update
    public void updateLine() {
        String sql = "UPDATE linesDB SET name = ?, destination = ?, distance_km = ? WHERE line_code = ?";

        try (Connection conn = DBConnections.LinesConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            Line line = new Line();
            line.setCode(lineDashboardView.codeField.getText());
            line.setName(lineDashboardView.nameField.getText());
            line.setDestination(lineDashboardView.destinationField.getText());
            line.setDistance(Double.parseDouble(lineDashboardView.distanceField.getText()));

            if(!LineModel.validateLine(line)) {
                JOptionPane.showMessageDialog(lineDashboardView.frame, "Please fill in all line fields correctly");
                return;
            }

            ps.setString(1, line.getName());
            ps.setString(2, line.getDestination());
            ps.setDouble(3, line.getDistance());
            ps.setString(4, line.getCode());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(lineDashboardView.frame, "Line updated successfully!");
                clearFields();
                loadLines();
            } else {
                JOptionPane.showMessageDialog(lineDashboardView.frame, "No line found with that code.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(lineDashboardView.frame, "Error updating line: " + e.getMessage());
        }
    }

    // search
    public void searchLines() {
        String sql = "SELECT * FROM linesDB WHERE line_code LIKE ? OR destination LIKE ?";

        try (Connection conn = DBConnections.LinesConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            String keyword = "%" + lineDashboardView.searchField.getText() + "%";
            ps.setString(1, keyword);
            ps.setString(2, keyword);

            ResultSet rs = ps.executeQuery();

            lineDashboardView.model.setRowCount(0);

            while (rs.next()) {
                lineDashboardView.model.addRow(new Object[]{
                        rs.getString("line_code"),
                        rs.getString("name"),
                        rs.getString("destination"),
                        rs.getDouble("distance_km")
                });
            }

            lineDashboardView.updateTotal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(lineDashboardView.frame, "Error searching lines: " + e.getMessage());
        }
    }

    // clear input fields
    private void clearFields() {
        lineDashboardView.codeField.setText("");
        lineDashboardView.nameField.setText("");
        lineDashboardView.destinationField.setText("");
        lineDashboardView.distanceField.setText("");
    }
}
