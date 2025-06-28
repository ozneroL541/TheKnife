package it.uninsubria.controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static java.lang.System.exit;

/**
 * ServerAddress class for managing the RMI registry connection.
 * This class provides a singleton instance of the RMI registry used by the client to communicate with the server.
 * It initializes the connection to the RMI registry at a specified IP address and port.
 */
public class ServerAddress {
    /** Server IP Address */
    public final static String IP_ADDRESS = "localhost";
    /** Server RMI Registry Port */
    public final static int PORT = 1099;
    /** RMI Registry instance */
    private static Registry registry;

    /**
     * Private constructor to prevent instantiation.
     * Initializes the RMI registry connection.
     */
    private ServerAddress() {
        init();
    }
    /**
     * Initializes the RMI registry connection.
     * This method attempts to connect to the RMI registry at the specified IP address and port.
     * If the connection fails, it prints an error message and exits the application.
     */
    private void init() {
        try {
            registry = LocateRegistry.getRegistry(IP_ADDRESS, PORT);
        } catch (RemoteException e) {
            System.err.println("Error connecting to RMI registry: " + e.getMessage());
            exit(1);
        }
    }
    /**
     * Returns the RMI registry instance.
     * If the registry is not initialized, it initializes it first.
     *
     * @return The RMI registry instance
     */
    public static Registry getRegistry() {
        if (registry == null) {
            new ServerAddress();
        }
        return registry;
    }
}
