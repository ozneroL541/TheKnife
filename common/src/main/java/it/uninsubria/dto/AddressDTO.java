package it.uninsubria.dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Data Transfer Object for Address information.
 * This class is responsible for encapsulating address data to transfer between client and server.
 * Uses public fields for direct access with minimal constructors for different use cases.
 *
 * @author Lorenzo Radice, 753252, CO
 */
public class AddressDTO implements Serializable {
    /** Serial version UID for serialization compatibility */
    private static final long serialVersionUID = 1L;
    /** Country of the address */
    private String nation;
    /** City of the address */
    private String city;
    /** Street of the address */
    private String street;
    /** House Number of the address */
    private String houseNumber = null;
    /** Latitude of the address */
    private Double latitude;
    /** Longitude of the address */
    private Double longitude;

    /**
     * Default constructor.
     * Initializes a completely empty AddressDTO.
     */
    public AddressDTO() {
    }

    /**
     * Constructor with some address information.
     * Useful for full data transfer operations.
     *
     * @param nation nation
     * @param city city
     * @param address address containing street and house number
     * @param latitude latitude
     * @param longitude longitude
     */
    public AddressDTO(String nation, String city, String address, Double latitude, Double longitude) {
        this.nation = nation;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        try {
            String[] parts = address.split(" ");
            if (parts.length > 1) {
                String[] copyOfRange = Arrays.copyOfRange(parts, 1, parts.length);
                this.street = String.join(" ", copyOfRange).strip().replace("'", "");
                this.houseNumber = parts[0].strip().replace("'", "");
            }
        } catch (Exception e) {
            this.street = address;
            this.houseNumber = null;
        }
    }
    /**
     * Constructor with all address information.
     * Useful for full data transfer operations.
     *
     * @param nation User's nation of residence
     * @param city User's city of residence
     * @param address User's street address
     * @param houseNumber User's house number
     */
    public AddressDTO(String nation, String city, String address, String houseNumber) {
        this.nation = nation;
        this.city = city;
        this.street = address;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "nation='" + nation + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'';
    }

    /**
     * Returns the full address as a formatted string.
     * @return Formatted string containing the full address.
     */
    public String getFullAddress() {
        return nation + ", " + city + ", " + street + (houseNumber != null ? ", " + houseNumber : "");
    }

    /**
     * Returns the nation of the address.
     * @return nation
     */
    public String getCountry() {
        return nation;
    }

    /**
     * Returns the city of the address.
     * @return city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Returns the street of the address.
     * @return street
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Returns the house number
     * @return houseNumber
     */
    public String getHouseNumber() {
        return this.houseNumber;
    }

    /**
     * Returns the latitude of the address.
     * @return latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude of the address.
     * @return longitude
     */
    public Double getLongitude() {
        return longitude;
    }
}

