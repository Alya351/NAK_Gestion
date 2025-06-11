package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; // adapte si nécessaire
    private static final String USER = "uic1"; // ton utilisateur Oracle
    private static final String PASSWORD = "ic12024"; // ton mot de passe Oracle

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}