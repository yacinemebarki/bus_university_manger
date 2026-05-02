package Controllers;

import java.sql.*;

import Views.LineDashboard_view;

public class LineDashboardController {
    LineDashboard_view lineDashboardView;

    public LineDashboardController(LineDashboard_view lineDashboardView) {
        this.lineDashboardView = lineDashboardView;

        initController();
        loadLines();
    }

    public void loadLines() {

        lineDashboardView.model.setRowCount(0);

        String sql = "SELECT * FROM lines";

        try (Connection conn = DBConnections.LinesConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lineDashboardView.model.addRow(new Object[]{
                        rs.getString("line_code"),
                        rs.getString("name"),
                        rs.getString("start_time"),
                        rs.getString("start_point"),
                        rs.getString("end_point"),
                        rs.getDouble("distance_km"),
                        rs.getInt("bus_count")
                });
            }

            lineDashboardView.updateTotalLines();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initController() {
        
        // Buttons
        lineDashboardView.addBtn.addActionListener(e -> addLine());
        lineDashboardView.removeBtn.addActionListener(e -> removeLine());
        lineDashboardView.updateBtn.addActionListener(e -> updateLine());
        lineDashboardView.searchBtn.addActionListener(e -> searchLines());
    }

    // add
    public void addLine() {}

    // remove
    public void removeLine() {}

    // update
    public void updateLine() {}

    // search
    public void searchLines() {}
}
