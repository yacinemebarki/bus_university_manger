package DBConnections;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = ManagerConnection.getConnection()) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
