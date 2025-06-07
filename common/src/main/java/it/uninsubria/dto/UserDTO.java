package it.uninsubria.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * Data Transfer Object for User information.
 * This class is responsible for encapsulating user data to transfer between client and server.
 * Uses public fields for direct access with minimal constructors for different use cases.
 *
 * @author Lorenzo Radice, 753252, CO
 */
public class UserDTO implements Serializable {
    /** Serial version UID for serialization compatibility. */
    private static final long serialVersionUID = 1L;
    /** Username */
    private String username;
    /** User's password */
    private String password;
    /** User's first name */
    private String name;
    /** User's last name */
    private String surname;
    /** User's birthdate */
    private Date birth_date;
    /** User's role (client/owner) */
    private UserRoleDTO role;
    /** User's address information */
    private AddressDTO address;

    /**
     * Default constructor.
     * Initializes a completely empty UserDTO.
     */
    public UserDTO() {
    }

    /**
     * Constructor with minimal authentication information.
     * Useful for login operations.
     *
     * @param username User's unique identifier
     * @param password User's password
     */
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor with all user information including nation and city.
     * Useful for full data transfer operations.
     *
     * @param username User's unique identifier
     * @param password User's password
     * @param name User's first name
     * @param surname User's last name
     * @param nation User's nation of residence
     * @param city User's city of residence
     * @param address User's street address
     * @param latitude User's latitude location
     * @param longitude User's longitude location
     * @param birth_date User's birthdate (can be null)
     * @param role User's role (client/owner)
     *
     */
    public UserDTO(String username, String password, String name, String surname,
                   String nation, String city, String address, Double latitude, Double longitude,
                   Date birth_date, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = new AddressDTO(nation, city, address, latitude, longitude);
        this.birth_date = birth_date;
        this.role = UserRoleDTO.valueOf(role.toUpperCase());
    }

    /**
     * Constructor with all user information except address.
     * Useful for operations where address is not needed.
     * @param username User's username
     * @param password User's password
     * @param name User's first name
     * @param surname User's last name
     * @param birth_date User's birthdate (can be null)
     * @param role User's role (client/owner)
     */
    public UserDTO(String username, String password, String name, String surname,
                   Date birth_date, UserRoleDTO role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "usr_id='" + username + '\'' +
                ", password_pt='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth_date=" + birth_date +
                ", role='" + role + '\'' +
                '}';
    }

    /**
     * Get username.
     * @return username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Get password.
     * @return username
     */
    public String getPassword() { return password;}
    /**
     * Get name.
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Get surname.
     * @return surname
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Get address.
     * @return address
     */
    public AddressDTO getAddress() {
        return address;
    }
    /**
     * Get birthdate.
     * @return birthdate
     */
    public Date getBirthday() {
        return birth_date;
    }
    /**
     * Get role.
     * @return role
     */
    public String getRole() {
        return role.getDisplayName().toLowerCase();
    }

    /**
     * Get country.
     * @return address country
     */
    public String getCountry() {
        return this.address.getCountry();
    }
    /**
     * Get city.
     * @return address city
     */
    public String getCity() {
        return this.address.getCity();
    }
    /**
     * Get street.
     * @return address street
     */
    public String getStreet() {
        return this.address.getStreet();
    }
    /**
     * Set username.
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Set password.
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Set name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Set surname.
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
     * Set birthdate.
     * @param birth_date the birthdate to set
     */
    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
    /**
     * Set role.
     * @param role the role to set
     */
    public void setRole(UserRoleDTO role) {
        this.role = role;
    }
    /**
     * Set address.
     * @param address the address to set
     */
    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
