import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public abstract class Account {
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract void displayMenu();

    public static Account login(String username, String password) {
        String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    if (role.equals("Admin")) {
                        return new Admin(username, password);
                    } else if (role.equals("User")) {
                        return new User(username, password);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean register(String username, String password, String role) {
    String query = "INSERT INTO accounts (username, password, role) VALUES (?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.setString(3, role);
        stmt.executeUpdate();
        return true;
    } catch (SQLIntegrityConstraintViolationException e) {
        System.out.println("+-------------------------------+");
        System.out.println("|   Username already exists.    |");
        System.out.println("+-------------------------------+");
        return false; 
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}

class User extends Account {
    public User(String username, String password) {
        super(username, password);
    }

    @Override
    public void displayMenu() {
        System.out.printf("User");
    }
}

class Admin extends Account {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void displayMenu() {
        System.out.printf("Admin");
    }
}
