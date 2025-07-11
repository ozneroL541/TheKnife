package it.uninsubria;


import it.uninsubria.server_services.RestaurantServiceImpl;
import it.uninsubria.server_services.ReviewServiceImpl;
import it.uninsubria.server_services.UserServiceImpl;
import it.uninsubria.services.RestaurantService;
import it.uninsubria.services.ReviewService;
import it.uninsubria.services.UserService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Server class for TheKnife application
 * @author Lorenzo Radice
 * @version 1.1.0
 */
public class ServerTK {
    /** Version of the application */
    private static final String version = "1.1.0";
    /** Application title */
    private static final String title = "TheKnife Server";
    /**
     * Main method for the server
     * @param args command line arguments
     */
    public static void main( String[] args ) {
        if (hasOption(args)) {
            return;
        }
        System.out.println(title);
        DBConnection.login(args);
        createRMIRegistry();
    }

    /**
     * Creates the RMI registry and binds the services to it
     */
    private static void createRMIRegistry() {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            UserService userService = new UserServiceImpl();
            RestaurantService restaurantService = new RestaurantServiceImpl();
            ReviewService reviewService = new ReviewServiceImpl();

            reg.rebind("UserService", userService);
            reg.rebind("RestaurantService", restaurantService);
            reg.rebind("ReviewService", reviewService);

        }catch (RemoteException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Check if the user has provided any options and execute the corresponding action
     * @param args command line arguments
     * @return true if the user has provided any options, false otherwise
     */
    private static boolean hasOption(String[] args) {
        for (String arg : args) {
            if (isHelp(arg)) {
                showHelp();
                return true;
            }
            if (isVersion(arg)) {
                showVersion();
                return true;
            }
        }
        return false;
    }
    /**
     * Show the help message
     */
    private static void showHelp() {
        final String usage =
                """
                        Usage: java -jar TheKnifeServer.jar [option|username] [password]
                        Options:
                        \t-h, --help\t\tShow this help message
                        \t-v, --version\t\tShow version information""";
        System.out.println(usage);
    }
    /**
     * Show the version information
     */
    private static void showVersion() {
        final String versionInfo = title + version;
        System.out.println(versionInfo);
    }
    /**
     * Check if the argument is a help option
     * @param arg argument to check
     * @return true if the argument is a help option, false otherwise
     */
    private static boolean isHelp(String arg) {
        final String[] helps = {"-h", "--help"};
        for (String help: helps) {
            if (arg.equals(help)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Check if the argument is a version option
     * @param arg argument to check
     * @return true if the argument is a version option, false otherwise
     */
    private static boolean isVersion(String arg) {
        final String[] versions = {"-v", "--version"};
        for (String version: versions) {
            if (arg.equals(version)) {
                return true;
            }
        }
        return false;
    }
}
