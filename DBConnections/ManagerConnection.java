package DBConnections;

import java.sql.*;

public class ManagerConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/Managers"; // Manaers is the name of the database for managers
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}