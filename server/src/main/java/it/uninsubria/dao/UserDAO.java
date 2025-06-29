package it.uninsubria.dao;

import it.uninsubria.DBConnection;
import it.uninsubria.dto.AddressDTO;
import it.uninsubria.dto.UserDTO;
import it.uninsubria.dto.UserRoleDTO;

import java.sql.*;

/**
 * Data Access Object for managing user data in the database.
 * This class provides methods to retrieve and add users.
 * It uses prepared statements to prevent SQL injection attacks.
 *
 * @author Lorenzo Radice, 753252, CO
 */
public class UserDAO {
    /**
     * SQL query to retrieve a user by their username.
     * This query selects all relevant fields from the Users table.
     */
    private static final String QUERY_GET_USER_BY_USERID = """
            SELECT username, h_password, name, surname, birth_date, role, address_id
            FROM Users
            WHERE username = ?
            """;
    /**
     * SQL query to add a new user to the database.
     * This query inserts a new record into the Users table with all necessary fields.
     */
    private static final String QUERY_ADD_USER = "INSERT INTO users (username, h_password, name, surname, birth_date, role, address_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";


    /**
     * Retrieves a user by their username.
     * @param usr username of the user to retrieve
     * @return UserDTO containing user information, or null if not found
     */
    public static UserDTO getUserByID(String usr) {
        Connection conn = DBConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(QUERY_GET_USER_BY_USERID)) {
            // Set the parameter (username)
            stmt.setString(1, usr);
            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                // Check if user was found
                if (rs.next()) {
                    // Extract data from ResultSet
                    String username = rs.getString("username");
                    String hashedPassword = rs.getString("h_password");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    System.out.println(surname);
                    Date birthDate = rs.getDate("birth_date");  // Can be null
                    String roleString = rs.getString("role");
                    Integer addressId = rs.getInt("address_id");
                    AddressDTO address = AddressDAO.getAddress(addressId); // Retrieve address by ID
                    UserRoleDTO role = UserRoleDTO.fromDisplayName(roleString);  // ENUM converted to String
                    // Create and return UserDTO with all available information
                    return new UserDTO(username, hashedPassword, name, surname, birthDate, role, address);
                } else {
                    // User not found
                    System.out.println("User not found: " + usr);
                    return null;
                }
            }

        } catch (SQLException e) {
            // Log the error and handle appropriately
            System.err.println("Database error while retrieving user: " + e.getMessage());
            // You might want to throw a custom exception here instead
            throw new RuntimeException("Failed to retrieve user from database", e);
        }
    }


    /**
     * Adds a new user to the database.
     * @param userData UserDTO containing user information
     * @param addressId ID of the address associated with the user
     * @throws SQLException if there is an error during the database operation
     */
    public static synchronized void addUser(UserDTO userData, Integer addressId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(QUERY_ADD_USER);
        stmt.setString(1, userData.getUsername());
        stmt.setString(2, userData.getHashedPassword());
        stmt.setString(3, userData.getName());
        stmt.setString(4, userData.getSurname());
        stmt.setDate(5, userData.getBirthday());
        stmt.setObject(6, userData.getRole(), Types.OTHER); // Convert ENUM to String
        stmt.setInt(7, addressId);
        stmt.executeUpdate();
    }
}
