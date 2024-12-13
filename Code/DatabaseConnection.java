import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/Batangas_db";
    private static final String USER = "root";
    private static final String PASSWORD = "superpass";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
