package DBConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LinesConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/bus_lines"; // so put 'bus_lines' is the name of the database
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection problem");
            e.printStackTrace();
            throw e;
        }
    }
}