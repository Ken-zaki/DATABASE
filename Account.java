import java.sql.*;

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
        String accountQuery = "SELECT user_id FROM accounts WHERE username = ? AND password = ?";
        String roleQuery = "SELECT role FROM account_role WHERE user_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement accountStmt = conn.prepareStatement(accountQuery)) {
                accountStmt.setString(1, username);
                accountStmt.setString(2, password);
                
                try (ResultSet accountRs = accountStmt.executeQuery()) {
                    if (accountRs.next()) {
                        int userId = accountRs.getInt("user_id");

                        try (PreparedStatement roleStmt = conn.prepareStatement(roleQuery)) {
                            roleStmt.setInt(1, userId);
                            
                            try (ResultSet roleRs = roleStmt.executeQuery()) {
                                if (roleRs.next()) {
                                    String role = roleRs.getString("role");

                                    if (role.equals("Admin")) {
                                        return new Admin(username, password);
                                    } else if (role.equals("User")) {
                                        return new User(username, password);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if login fails
    }
    

    public static boolean register(String username, String password, String role) {
        String userQuery = "INSERT INTO accounts (username, password) VALUES (?, ?)";
        String roleQuery = "INSERT INTO account_role (user_id, role) VALUES (?, ?)";
    
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
    
            try (PreparedStatement userStmt = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS)) {
                userStmt.setString(1, username);
                userStmt.setString(2, password);
                userStmt.executeUpdate();
    
                // Retrieve the generated user_id
                try (ResultSet generatedKeys = userStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
    
                        try (PreparedStatement roleStmt = conn.prepareStatement(roleQuery)) {
                            roleStmt.setInt(1, userId);
                            roleStmt.setString(2, role);
                            roleStmt.executeUpdate();
                        }
    
                        conn.commit();
                        return true;
                    } else {
                        throw new SQLException("Failed to retrieve user_id.");
                    }
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("+-------------------------------+");
                System.out.println("|   Username already exists.    |");
                System.out.println("+-------------------------------+");
                conn.rollback(); // Rollback in case of duplication
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
                conn.rollback();
                return false;
            }
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
