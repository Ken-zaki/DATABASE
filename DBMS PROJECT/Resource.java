import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Resource {
    private String category;
    private String name;
    private String location;
    private String details;

    public Resource(String category, String name, String location, String details) {
        this.category = category;
        this.name = name;
        this.location = location;
        this.details = details;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return String.format(
            "+--------------------------------+\n" +
            " Category: %-20s \n" +
            " Name: %-23s \n" +
            " Location: %-20s \n" +
            " Details: %-20s \n" +
            "+--------------------------------+",
            category, name, location, details
        );
    }

    public static boolean addResourceToDB(String category, String name, String location, String details) {
        String query = "INSERT INTO resources (category, name, location, details) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category);
            stmt.setString(2, name);
            stmt.setString(3, location);
            stmt.setString(4, details);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error adding resource: " + e.getMessage());
            return false;
        }
    }

    public static boolean removeResourceFromDB(String name) {
        String query = "DELETE FROM resources WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error removing resource: " + e.getMessage());
            return false;
        }
    }

    public static List<Resource> fetchResourcesByCategory(String category) {
        List<Resource> resources = new ArrayList<>();
        if (category == null || category.trim().isEmpty()) {
            System.err.println("Category cannot be null or empty.");
            return resources;
        }

        String sql = "SELECT * FROM resources WHERE category = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                resources.add(new Resource(
                    rs.getString("category"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("details")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching resources by category: " + e.getMessage());
        }

        return resources;
    }

    public static List<Resource> fetchAllResources() {
        String query = "SELECT * FROM resources";
        List<Resource> resourceList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                resourceList.add(new Resource(
                    rs.getString("category"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("details")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all resources: " + e.getMessage());
        }
        return resourceList;
    }
}
