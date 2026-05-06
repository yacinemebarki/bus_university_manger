package data_base_charger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import DBConnections.*;

public class DatabaseSeeder {

    public static void main(String[] args) {

        fillManager();
        fillStudents();
        fillDrivers();
        fillBuses();
        fillLines();
        fillTrips();
        fillProblems();

        System.out.println("✅ Database filled successfully!");
    }

    // ================= MANAGER =================
    static void fillManager() {
        String sql = "INSERT INTO Manager (code, password, name) VALUES (?, ?, ?)";

        try (Connection conn = ManagerConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "ma1");
            ps.setString(2, "root");
            ps.setString(3, "admin");

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Manager: " + e.getMessage());
        }
    }

    // ================= STUDENTS =================
    static void fillStudents() {
        String sql = "INSERT INTO Student (id, name, password, matricule) VALUES (?, ?, ?, ?)";

        try (Connection conn = StudentConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 2);
            ps.setString(2, "cherif mohamed");
            ps.setString(3, "hdeo021s");
            ps.setString(4, "242432225503");

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Student: " + e.getMessage());
        }
    }

    // ================= DRIVERS =================
    static void fillDrivers() {
        String sql = "INSERT INTO Driver (id, name, password, code, matricule) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriversConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 1);
            ps.setString(2, "sabar moussa");
            ps.setString(3, "kdk99kei");
            ps.setString(4, "D0192");
            ps.setString(5, "1234567890"); // 10-digit bus matricule

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Driver: " + e.getMessage());
        }
    }

    // ================= BUSES =================
    static void fillBuses() {
        String sql = "INSERT INTO Bus (matricule, capacity, work_status, problem_status) VALUES (?, ?, ?, ?)";

        try (Connection conn = BusConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "1234567890"); // 10 digits ✔
            ps.setInt(2, 50);
            ps.setString(3, "ACTIVE");
            ps.setString(4, "OK");

            ps.executeUpdate();

            // second bus
            ps.setString(1, "0987654321");
            ps.setInt(2, 45);
            ps.setString(3, "INACTIVE");
            ps.setString(4, "PROBLEM");

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Bus: " + e.getMessage());
        }
    }

    // ================= LINES =================
    static void fillLines() {
        String sql = "INSERT INTO LineDashboard (id, line_code, name, destination, distance_km) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = LinesConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 1);
            ps.setString(2, "L1");
            ps.setString(3, "USTHB-Ar");
            ps.setString(4, "ARBAA");
            ps.setInt(5, 35);
            ps.executeUpdate();

            ps.setInt(1, 2);
            ps.setString(2, "L2");
            ps.setString(3, "HB-Dargna");
            ps.setString(4, "dargna");
            ps.setInt(5, 35);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Line: " + e.getMessage());
        }
    }

    // ================= TRIPS =================
    static void fillTrips() {
        String sql = "INSERT INTO trips (line_id, start_time, bus_id, driver_id, depart, direction, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = TripConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 1);
            ps.setString(2, "08:00:00");
            ps.setInt(3, 1);
            ps.setString(4, "D0192");
            ps.setString(5, "USTHB");
            ps.setString(6, "ARBAA");
            ps.setString(7, "ACTIVE");

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Trips: " + e.getMessage());
        }
    }

    // ================= PROBLEMS =================
    static void fillProblems() {
        String sql = "INSERT INTO Problem (driver_id, trip_id, problem_date, problem_time, problem_type, message, Line_name) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ProblemConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "D0192");
            ps.setString(2, "1");
            ps.setString(3, "2026-05-06");
            ps.setString(4, "10:30:00");
            ps.setString(5, "Vehicle Problem");
            ps.setString(6, "Engine overheating");
            ps.setString(7, "L1");

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Problem: " + e.getMessage());
        }
    }
}
