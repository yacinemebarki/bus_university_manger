package Controllers;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DBConnections.BusConnection;
import DBConnections.DriversConnection;
import DBConnections.TripConnection;

import Views.Trip_view;
import Models.AllModels;
import Views.Manager_view;
import Views.ProblemDashboard;
import Views.bus_view;
import java.awt.*;

public class LineDashboardController {

    Trip_view view;

    public LineDashboardController(Trip_view view) {
        this.view = view;
        builldTable();
        initControll();
    }

    public void initControll() {
        view.searchBtn.addActionListener(e -> {
            String type = (String) view.searchBox.getSelectedItem();
            if (type.equals("Trip ID")) {
                searchByid();
            } else if (type.equals("Driver ID")) {
                searchBydriver();
            } else if (type.equals("Matricule")) {
                searchByMatricule();
            } else if (type.equals("Status")) {
                searchByStatus();
            } else if (type.equals("station")) {
                searchByLine();
            }
        });
        view.table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = view.table.getSelectedRow();
                if (row != -1) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Delete this Trip",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            Connection con = TripConnection.getConnection();
                            String id_text = view.table.getValueAt(row, 0).toString();
                            int id = Integer.parseInt(id_text);
                            String sql = "DELETE FROM trips WHERE id = ?";
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setInt(1, id);
                            int nb = ps.executeUpdate();

                            if (nb > 0) {
                                view.model.removeRow(row);
                                builldTable();
                                JOptionPane.showMessageDialog(view.frame, "Trip deleted");
                            } else {
                                JOptionPane.showMessageDialog(view.frame, "failed to delete");
                            }
                        } catch (SQLException v) {
                            v.printStackTrace();
                        }
                    }
                }
            }
        });
        view.addBtn.addActionListener(e -> {
            openAddTripDialog();
        });
        view.menu.personBtn.addActionListener(e -> goToperson());
        view.menu.busBtn.addActionListener(e -> goToBus());
        view.menu.problemBtn.addActionListener(e -> gotToproblem());
    }

    private void openAddTripDialog() {

        JTextField start = new JTextField();
        JTextField driver = new JTextField();
        JTextField matricule = new JTextField();
        JTextField time = new JTextField();
        JTextField direction = new JTextField();
        JTextField status = new JTextField();

        Object[] message = {
                "Start:", start,
                "Driver ID:", driver,
                "Matricule:", matricule,
                "Depart Time:", time,
                "Direction:", direction,
                "Status:", status
        };

        int option = JOptionPane.showConfirmDialog(
                view.frame,
                message,
                "Add New Trip",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {

            try {

                Connection con = TripConnection.getConnection();

                String tripDepart = start.getText().trim();
                String driverId = driver.getText().trim();
                String busId = matricule.getText().trim();
                String departureTime = time.getText().trim();
                String tripDirection = direction.getText().trim();
                String tripStatus = status.getText().trim();

                if (tripDepart.isEmpty() || driverId.isEmpty() || busId.isEmpty() || departureTime.isEmpty()
                        || tripDirection.isEmpty() || tripStatus.isEmpty()) {
                    JOptionPane.showMessageDialog(view.frame, "Please complete all trip fields.");
                    return;
                }

                Time newTripTime = parseTime(departureTime);
                if (newTripTime == null) {
                    JOptionPane.showMessageDialog(view.frame, "Enter a valid start time in HH:mm or HH:mm:ss format.");
                    return;
                }

                if (!driverExists(driverId)) {
                    JOptionPane.showMessageDialog(view.frame, "Driver does not exist.");
                    return;
                }

                if (isDriverAssigned(driverId)) {
                    JOptionPane.showMessageDialog(view.frame,
                            "This driver is already assigned to another active trip.");
                    return;
                }

                if (hasDriverTimeConflict(driverId, newTripTime)) {
                    JOptionPane.showMessageDialog(view.frame,
                            "This driver already has a trip within 3 hours of the requested start time.");
                    return;
                }

                if (isBusAssignedToOtherDriver(busId, driverId)) {
                    JOptionPane.showMessageDialog(view.frame,
                            "This bus is already assigned to another driver on an active trip.");
                    return;
                }

                String sql = "INSERT INTO trips(depart, driver_id, bus_id, start_time, direction, status) VALUES(?,?,?,?,?,?)";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, tripDepart);
                ps.setString(2, driverId);
                ps.setString(3, busId);
                ps.setTime(4, newTripTime);
                ps.setString(5, tripDirection);
                ps.setString(6, tripStatus);

                int nb = ps.executeUpdate();

                if (nb > 0) {

                    JOptionPane.showMessageDialog(view.frame, "Trip Added");

                    builldTable(); // reload table
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void builldTable() {
        String sql = "SELECT * FROM trips";

        try {
            Connection con = TripConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            view.model.setRowCount(0);

            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("driver_id");
                String depart = rs.getString("depart");
                String matricule = rs.getString("bus_id");
                Time hour = rs.getTime("start_time");
                String status = rs.getString("status");
                String direction = rs.getString("direction");
                view.model.addRow(new Object[] { id, depart, code, matricule, hour, direction, status });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByid() {
        String sql = "SELECT * FROM trips WHERE id=?";
        try {
            Connection con = TripConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            String id_text = view.searchField.getText();
            if (id_text.isEmpty()) {
                JOptionPane.showMessageDialog(view.frame, "you must enter trip id");
                return;
            }
            int id = Integer.parseInt(id_text);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            view.model.setRowCount(0);
            Boolean found = false;
            while (rs.next()) {
                found = true;
                String code = rs.getString("driver_id");
                String depart = rs.getString("depart");
                String matricule = rs.getString("bus_id");
                Time hour = rs.getTime("start_time");
                String status = rs.getString("status");
                String direction = rs.getString("direction");
                view.model.addRow(new Object[] { id, depart, code, matricule, hour, direction, status });
            }
            if (!found) {
                view.model.addRow(new Object[] { "Nothing found", "", "", "", "", "", "" });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBydriver() {
        String sql = "SELECT * FROM trips WHERE driver_id=?";
        try {
            Connection con = TripConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            String driver_id = view.searchField.getText();
            if (driver_id.isEmpty()) {
                JOptionPane.showMessageDialog(view.frame, "you must enter driver id");
                return;
            }

            ps.setString(1, driver_id);
            ResultSet rs = ps.executeQuery();
            view.model.setRowCount(0);
            Boolean found = false;
            while (rs.next()) {
                found = true;
                String id = rs.getString("id");
                String depart = rs.getString("depart");
                String matricule = rs.getString("bus_id");
                Time hour = rs.getTime("start_time");
                String status = rs.getString("status");
                String direction = rs.getString("direction");
                view.model.addRow(new Object[] { id, depart, driver_id, matricule, hour, direction, status });
            }
            if (!found) {
                view.model.addRow(new Object[] { "Nothing found", "", "", "", "", "", "" });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByMatricule() {
        String sql = "SELECT * FROM trips WHERE bus_id=?";
        try {
            Connection con = TripConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            String bus_id = view.searchField.getText();
            if (bus_id.isEmpty()) {
                JOptionPane.showMessageDialog(view.frame, "you must enter bus id");
                return;
            }

            ps.setString(1, bus_id);
            ResultSet rs = ps.executeQuery();
            view.model.setRowCount(0);
            Boolean found = false;
            while (rs.next()) {
                found = true;
                String id = rs.getString("id");
                String depart = rs.getString("depart");
                String driver_id = rs.getString("driver_id");
                Time hour = rs.getTime("start_time");
                String status = rs.getString("status");
                String direction = rs.getString("direction");
                view.model.addRow(new Object[] { id, depart, driver_id, bus_id, hour, direction, status });
            }
            if (!found) {
                view.model.addRow(new Object[] { "Nothing found", "", "", "", "", "", "" });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByStatus() {
        String sql = "SELECT * FROM trips WHERE status=?";
        try {
            Connection con = TripConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            String status = view.searchField.getText();
            if (status.isEmpty()) {
                JOptionPane.showMessageDialog(view.frame, "you must enter status");
                return;
            }

            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            view.model.setRowCount(0);
            Boolean found = false;
            while (rs.next()) {
                found = true;
                String id = rs.getString("id");
                String depart = rs.getString("depart");
                String matricule = rs.getString("bus_id");
                Time hour = rs.getTime("start_time");
                String driver_id = rs.getString("driver_id");
                String direction = rs.getString("direction");
                view.model.addRow(new Object[] { id, depart, driver_id, matricule, hour, direction, status });
            }
            if (!found) {
                view.model.addRow(new Object[] { "Nothing found", "", "", "", "", "", "" });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByLine() {
        String sql = "SELECT * FROM trips";
        System.out.println("enter");
        try {
            Connection con = TripConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            String line_name = view.searchField.getText();
            System.out.println("enter 2");
            if (line_name.isEmpty()) {
                JOptionPane.showMessageDialog(view.frame, "you must enter line name");
                return;
            }
            ResultSet rs = ps.executeQuery();

            view.model.setRowCount(0);
            boolean found = false;
            while (rs.next()) {

                System.out.println("enter3");
                String line = rs.getString("depart");
                String direction = rs.getString("direction");

                if (similarity(line_name.toLowerCase(), line.toLowerCase()) > 0.5
                        || similarity(line_name.toLowerCase(), direction.toLowerCase()) > 0.5) {
                    found = true;
                    int id = rs.getInt("id");
                    String driver_id = rs.getString("driver_id");
                    String matricule = rs.getString("bus_id");
                    Time hour = rs.getTime("start_time");
                    String status = rs.getString("status");

                    view.model.addRow(new Object[] { id, line, driver_id, matricule, hour, direction, status });
                }
            }
            if (!found) {
                view.model.addRow(new Object[] { "Nothing Match", "", "", "", "", "", "" });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean driverExists(String driverId) {
        String sql = "SELECT COUNT(*) FROM Driver WHERE code = ?";
        try (Connection con = DriversConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, driverId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isDriverAssigned(String driverId) {
        String sql = "SELECT COUNT(*) FROM trips WHERE driver_id = ? AND status <> 'FINISHED'";
        try (Connection con = TripConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, driverId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean hasDriverTimeConflict(String driverId, Time newTripTime) {
        String sql = "SELECT start_time FROM trips WHERE driver_id = ?";
        try (Connection con = TripConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, driverId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Time existingTime = rs.getTime("start_time");
                    if (existingTime == null) {
                        continue;
                    }
                    long diff = Math.abs(newTripTime.getTime() - existingTime.getTime());
                    if (diff < 3 * 60 * 60 * 1000L) {
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Time parseTime(String timeString) {
        try {
            if (timeString.matches("^\\d{1,2}:\\d{2}(:\\d{2})?$")) {
                if (timeString.length() == 5) {
                    return Time.valueOf(timeString + ":00");
                }
                return Time.valueOf(timeString);
            }
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            format.setLenient(false);
            long millis = format.parse(timeString).getTime();
            return new Time(millis);
        } catch (IllegalArgumentException | ParseException e) {
            return null;
        }
    }

    private boolean isBusAssignedToOtherDriver(String busId, String driverId) {
        String sql = "SELECT COUNT(*) FROM trips WHERE bus_id = ? AND driver_id <> ? AND status <> 'FINISHED'";
        try (Connection con = TripConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, busId);
            ps.setString(2, driverId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void goToBus() {
        new BusController(new bus_view());
        view.frame.setVisible(false);
    }

    public void goToperson() {
        new ManagerController(new AllModels<>(), new Manager_view());
        view.frame.setVisible(false);
    }

    public void gotToproblem() {
        new ProblemDashboardController(new ProblemDashboard());
        view.frame.setVisible(false);
    }

    public static double similarity(String w1, String w2) {
        int min_len = Math.min(w1.length(), w2.length());
        int s = 0;
        for (int i = 0; i < min_len; i++) {
            if (w1.charAt(i) == w2.charAt(i)) {
                s++;
            }
        }
        if (min_len != 0) {
            return s / min_len;
        } else {
            return 0;
        }
    }
}
