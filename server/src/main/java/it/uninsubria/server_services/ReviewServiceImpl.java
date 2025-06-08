package it.uninsubria.server_services;

import it.uninsubria.dao.ReviewDAO;
import it.uninsubria.dto.ReviewDTO;
import it.uninsubria.services.ReviewService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the ReviewService interface for managing restaurant reviews.
 * This class provides methods to retrieve, create, update, and delete reviews.
 *
 * @author Lorenzo Radice
 */
public class ReviewServiceImpl extends UnicastRemoteObject implements ReviewService {
    /**
     * Constructs a new ReviewServiceImpl instance.
     *
     * @throws RemoteException if there is an error during remote method invocation
     */
    public ReviewServiceImpl() throws RemoteException {}

    @Override
    public synchronized List<ReviewDTO> getReviews(String restaurantId) throws RemoteException {
        try {
            return ReviewDAO.getRestaurantReviews(restaurantId);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving reviews for restaurant ID: " + restaurantId, e);
        }
    }

    @Override
    public synchronized boolean createOrUpdateReview(ReviewDTO review) throws RemoteException, SecurityException, IllegalArgumentException {
        try {
            return ReviewDAO.updateReview(review);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating or updating review for user ID: " + review.getUsername() + " and restaurant ID: " + review.getRestaurant_id(), e);
        }
    }

    @Override
    public synchronized boolean deleteReview(String username, String restaurantId) throws RemoteException, SecurityException {
        try {
            ReviewDAO.deleteReview(username, restaurantId);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting review for user ID: " + username + " and restaurant ID: " + restaurantId, e);
        }
    }

    @Override
    public synchronized List<ReviewDTO> getUserReviews(String username) throws RemoteException {
        try {
            return ReviewDAO.getUserReviews(username);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving reviews for user ID: " + username, e);
        }
    }
}
