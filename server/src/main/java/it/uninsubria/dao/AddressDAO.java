package it.uninsubria.dao;

import it.uninsubria.DBConnection;
import it.uninsubria.dto.AddressDTO;
import it.uninsubria.exceptions.AddressException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * AddressDAO is a Data Access Object for managing addresses in the database.
 * It provides methods to insert a new address and retrieve an address by its ID.
 */
public class AddressDAO {
    /**
     * Inserts a new address into the database and returns its ID.
     * @param address address to be inserted
     * @return the ID of the inserted address
     * @throws AddressException if an error occurs while inserting or retrieving the address ID
     */
    public static synchronized Integer getAddressId(AddressDTO address) throws AddressException {
        final String insertAddressSQL = """
                INSERT INTO addresses (address_id,
                country, city, street, house_number, latitude, longitude) VALUES ((
                SELECT MAX(a.address_id)+1
                FROM addresses a
                ),?, ?, ?, ?, ?, ?);""";
        final String getAddressIdSQL = "SELECT address_id " +
                "FROM addresses " +
                "WHERE country = ? AND city = ? AND street = ? " +
                "AND house_number = ? AND latitude = ? AND longitude = ?;";
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement insertAddressStmt = conn.prepareStatement(insertAddressSQL);
            insertAddressStmt.setString(1, address.getCountry());
            insertAddressStmt.setString(2, address.getCity());
            insertAddressStmt.setString(3, address.getStreet());
            insertAddressStmt.setString(4, address.getHouseNumber());
            insertAddressStmt.setDouble(5, address.getLatitude());
            insertAddressStmt.setDouble(6, address.getLongitude());
            insertAddressStmt.executeUpdate();
            conn.commit();
        } catch (SQLException ignored) {}
        try {
            PreparedStatement getAddressIdStmt = conn.prepareStatement(getAddressIdSQL);
            getAddressIdStmt.setString(1, address.getCountry());
            getAddressIdStmt.setString(2, address.getCity());
            getAddressIdStmt.setString(3, address.getStreet());
            getAddressIdStmt.setString(4, address.getHouseNumber());
            getAddressIdStmt.setDouble(5, address.getLatitude());
            getAddressIdStmt.setDouble(6, address.getLongitude());
            ResultSet rs = getAddressIdStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("address_id");
            } else {
                throw new AddressException("Error getting address ID");
            }
        } catch (SQLException e) {
            throw new AddressException("Error querying address ID");
        }
    }
    /**
     * Retrieves an address by its ID.
     * @param addressId the ID of the address to retrieve
     * @return AddressDTO containing the address details, or null if not found
     */
    public static synchronized AddressDTO getAddress(int addressId) {
        final String getAddressSQL = "SELECT country, city, street, house_number, latitude, longitude " +
                "FROM addresses " +
                "WHERE address_id = ?;";
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(getAddressSQL);
            stmt.setInt(1, addressId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String country = rs.getString("country");
                String city = rs.getString("city");
                String street = rs.getString("street");
                String houseNumber = rs.getString("house_number");
                Double latitude = rs.getDouble("latitude");
                Double longitude = rs.getDouble("longitude");
                return new AddressDTO(country, city, street, houseNumber, latitude, longitude);
            } else {
                return null; // Address not found
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving address: " + e.getMessage());
            return null; // Error occurred
        }
    }
}
