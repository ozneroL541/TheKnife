package it.uninsubria.server_services;

import it.uninsubria.dao.AddressDAO;
import it.uninsubria.dto.UserDTO;
import it.uninsubria.dao.UserDAO;
import it.uninsubria.exceptions.AddressException;
import it.uninsubria.exceptions.UserException;
import it.uninsubria.services.UserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

/**
 * Implementation of the UserService interface for handling user-related operations.
 * This class provides methods for user login and registration, ensuring thread safety
 * and proper exception handling.
 *
 * @author Lorenzo Radice
 */
public class UserServiceImpl extends UnicastRemoteObject implements UserService {
    /**
     * Default constructor for UserServiceImpl.
     * It throws RemoteException to handle RMI-related issues.
     *
     * @throws RemoteException if there is an error during remote method invocation
     */
    public UserServiceImpl() throws RemoteException {}

    @Override
    public synchronized UserDTO login(UserDTO credentials) throws RemoteException, SecurityException {
        String usr = credentials.getUsername();
        String psw = credentials.getPassword();
        // Get user from database
        UserDTO userRecord = UserDAO.getUserByID(usr);
        // Check if user exists
        if (userRecord == null) {
            System.err.println("Login attempt failed: User not found - " + usr);
            throw new SecurityException("Invalid credentials");
        }
        // Check password
        if (!userRecord.verifyPassword(psw)) {
            System.err.println("Login attempt failed: Wrong password for user - " + usr);
            throw new SecurityException("Invalid credentials");
        }
        // Authentication successful
        System.out.println("User successfully logged in: " + usr);
        return userRecord;
    }

    @Override
    public synchronized void register(UserDTO userData) throws RemoteException, UserException {
        // check if user already exists
        if (UserDAO.getUserByID(userData.getUsername()) != null) {
            System.err.println("Registration attempt failed: User already exists - " + userData.getUsername());
            throw new UserException("Username already exists"); // to handle better, maybe custom exception
        }
        Integer addressId = null;
        try {
            // add address to database and get address id
            addressId = AddressDAO.insert(userData.getAddress());
        } catch (AddressException e) {
            System.err.println("Registration attempt failed: Error inserting address - " + userData.getAddress());
            throw new UserException("Invalid address");
        }
        // add user to database
        try {
            UserDAO.addUser(userData, addressId);
        } catch (SQLException e) {
            System.err.println("Registration attempt failed: Error adding user to database - " + userData.getUsername());
            throw new RemoteException("Error adding user to database");
        }
    }
}
