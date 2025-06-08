package it.uninsubria.server_services;

import it.uninsubria.DBConnection;
import it.uninsubria.dto.AddressDTO;
import it.uninsubria.dto.UserDTO;
import it.uninsubria.dto.UserRoleDTO;
import it.uninsubria.exceptions.UserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @BeforeEach
    void premise() {
        String[] args = {"theknife", "password"};
        try {
            DBConnection.login(args);
        } catch (Exception e) {
            fail("DBConnection failed to login with valid credentials");
        }
    }

    @AfterEach
    void tearDown() {
        try {
            DBConnection.closeConnection();
        } catch (Exception e) {
            fail("DBConnection failed to close: " + e.getMessage());
        }
    }

    @Test
    void register() {
        String username = "testUser";
        String password = "testPassword";
        String name = "Test User";
        String surname = "Test Surname";
        Date birthDate = Date.valueOf("2000-01-01");
        UserRoleDTO role = UserRoleDTO.CLIENT;
        AddressDTO address = new AddressDTO("Italy", "Como", "Via Valleggio 4", 15.0, 45.8);
        UserDTO user = new UserDTO(username, password, name, surname, birthDate, role, address);
        try {
            UserServiceImpl userService = new UserServiceImpl();
            userService.register(user);
            UserDTO registeredUser = userService.login(new UserDTO(username, password));
            assertNotNull(registeredUser, "Registered user should not be null");
            assertEquals(username, registeredUser.getUsername(), "Username should match");
        } catch (UserException e) {
            if (e.getMessage().contains("User already exists")) {
                System.out.println("User already exists, skipping registration test.");
            } else {
                fail("Registration failed with valid parameters: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            fail("Registration failed with valid parameters: " + e.getMessage());
        }
    }

    @Test
    void login() {
        String username = "testUser";
        String password = "testPassword";
        try {
            UserServiceImpl userService = new UserServiceImpl();
            UserDTO user = userService.login(new UserDTO(username, password));
            assertNotNull(user, "Logged in user should not be null");
            assertEquals(username, user.getUsername(), "Username should match");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            fail("Login failed with valid parameters: " + e.getMessage());
        }

    }
}