package it.uninsubria.dao;

import it.uninsubria.DBConnection;
import it.uninsubria.dto.ReviewDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for managing restaurant reviews.
 * This class provides methods to interact with the reviews table in the database.
 * It includes methods to get reviews for a restaurant, update a review, delete a review,
 * and get all reviews by a user.
 *
 * @author Lorenzo Radice, 753252, CO
 */
public class ReviewDAO {
    /**
     * Retrieves all reviews for a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant to get reviews for.
     * @return A list of ReviewDTO objects containing the reviews for the restaurant.
     * @throws SQLException If there is an error accessing the database.
     */
    public static List<ReviewDTO> getRestaurantReviews(String restaurantId) throws SQLException {
        final String query = "SELECT * FROM reviews WHERE restaurant_id = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, Integer.parseInt(restaurantId));
        ResultSet rs = stmt.executeQuery();
        List<ReviewDTO> reviews = parseReviewResultSet(rs);
        rs.close();
        stmt.close();
        return reviews;
    }
    /**
     * Parses the ResultSet from a database query into a list of ReviewDTO objects.
     *
     * @param rs The ResultSet containing the review data.
     * @return A list of ReviewDTO objects.
     * @throws SQLException If there is an error accessing the ResultSet.
     */
    private static List<ReviewDTO> parseReviewResultSet(ResultSet rs) throws SQLException {
        List<ReviewDTO> reviews = new ArrayList<>();
        while (rs.next()) {
            reviews.add(parseReviewResult(rs));
        }
        return reviews;
    }
    /**
     * Parses a single row of the ResultSet into a ReviewDTO object.
     *
     * @param rs The ResultSet containing the review data.
     * @return A ReviewDTO object representing the review.
     * @throws SQLException If there is an error accessing the ResultSet.
     */
    private static ReviewDTO parseReviewResult(ResultSet rs) throws SQLException {
        ReviewDTO review = new ReviewDTO();
        review.setUsername(rs.getString(1));
        review.setRestaurant_id(rs.getString(2));
        review.setRating(rs.getInt(3));
        review.setComment(rs.getString(4));
        review.setReply(rs.getString(5));
        return review;
    }
    /**
     * Updates or inserts a review for a restaurant.
     * If a review already exists for the user and restaurant, it updates the existing review.
     * Otherwise, it inserts a new review.
     *
     * @param review The ReviewDTO object containing the review data to be updated or inserted.
     * @return true if the operation was successful, false otherwise.
     * @throws SQLException If there is an error accessing the database.
     */
    public static boolean updateReview(ReviewDTO review) throws SQLException {
        final String query = "INSERT INTO reviews (username, restaurant_id, rating, comment, reply) VALUES (?, ?, ?, ?, ?) " +
                "ON CONFLICT (username, restaurant_id) DO UPDATE SET rating = ?, comment = ?, reply = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, review.getUsername());
        stmt.setInt(2, Integer.parseInt(review.getRestaurant_id()));
        stmt.setInt(3, review.getRating());
        stmt.setString(4, review.getComment());
        stmt.setString(5, review.getReply());
        stmt.setInt(6, review.getRating());
        stmt.setString(7, review.getComment());
        stmt.setString(8, review.getReply());
        return stmt.executeUpdate() > 0;
    }
    /**
     * Deletes a review for a restaurant by a specific user.
     *
     * @param userId The ID of the user who wrote the review.
     * @param restaurantId The ID of the restaurant for which the review is to be deleted.
     * @return true if the review was successfully deleted, false otherwise.
     * @throws SQLException If there is an error accessing the database.
     */
    public static boolean deleteReview(String userId, String restaurantId) throws SQLException {
        final String query = "DELETE FROM reviews WHERE username = ? AND restaurant_id = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, userId);
        stmt.setInt(2, Integer.parseInt(restaurantId));
        return (stmt.executeUpdate() > 0);
    }
    /**
     * Retrieves all reviews written by a specific user.
     *
     * @param userId The ID of the user whose reviews are to be retrieved.
     * @return A list of ReviewDTO objects containing the user's reviews.
     * @throws SQLException If there is an error accessing the database.
     */
    public static List<ReviewDTO> getUserReviews(String userId) throws SQLException {
        final String query = "SELECT * FROM reviews WHERE username = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, userId);
        ResultSet rs = stmt.executeQuery();
        List<ReviewDTO> reviews = parseReviewResultSet(rs);
        rs.close();
        stmt.close();
        return reviews;
    }
}
