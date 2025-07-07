package it.uninsubria.controller;

import it.uninsubria.session.UserSession;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for TheKnife client.
 * Launches the JavaFX application and initializes the UI.
 *
 * @version 1.1.0
 * @author Lorenzo Radice
 */
public class ClientTK extends Application {

    private UserSession userSession;

    /**
     * The main entry point for the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Ensure JavaFX platform is properly initialized
        try {
            // Launch the actual JavaFX application
            launch(args);
        } catch (Exception e) {
            System.err.println("Failed to launch application: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Method called at application startup.
     *
     * @param primaryStage The primary stage for this application
     * @throws Exception If any errors occur during startup
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize the user session
        userSession = UserSession.getInstance();

        // Load the login view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = loader.load();

        // Set up the primary stage
        primaryStage.setTitle("TheKnife - Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Method called when the application is stopping.
     * Performs any necessary cleanup.
     */
    @Override
    public void stop() {
        // Perform any cleanup operations here
        System.out.println("TheKnife client application is shutting down...");
    }
}