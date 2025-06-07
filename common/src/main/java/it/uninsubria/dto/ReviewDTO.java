package it.uninsubria.dto;

import java.io.Serializable;

/**
 * Data Transfer Object for Review information.
 * This class is responsible for encapsulating review data to transfer between client and server.
 * Uses public fields for direct access without getters and setters.
 *
 * @author Lorenzo Radice, 753252, CO
 */
public class ReviewDTO implements Serializable {
    /** Serial version UID for serialization */
    private static final long serialVersionUID = 1L;
    /** Username of the user who created the review */
    private String username;
    /** ID of the restaurant being reviewed */
    private String restaurant_id;
    /** Restaurant rating given by the user (1-5 stars) */
    private Integer rating;
    /** Optional review message written by the customer */
    private String comment = null;
    /** Optional reply written by the restaurant owner */
    private String reply = null;

    /**
     * Complete constructor with all review information.
     * The restaurant reply field can be null if no reply has been made.
     *
     * @param username Username of the user who created the review
     * @param restaurant_id ID of the restaurant being reviewed
     * @param rating Star rating (1-5)
     * @param comment Review message from the customer
     * @param reply Optional reply from the restaurant owner
     */
    public ReviewDTO(String username, String restaurant_id, Integer rating, String comment, String reply) {
        this.username = username;
        this.restaurant_id = restaurant_id;
        this.rating = rating;
        this.comment = comment;
        this.reply = reply;
    }
    /** Constructor with no arguments */
    public ReviewDTO() {}

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "usr_id='" + username + '\'' +
                ", rest_id='" + restaurant_id + '\'' +
                ", customer_msg='" + comment + '\'' +
                ", rating=" + rating +
                ", rest_rep='" + (reply != null ? reply : "No reply yet") + '\'' +
                '}';
    }
    /**
     * Getters and Setters for all fields.
     * These methods allow access to private fields while maintaining encapsulation.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username of the user who created the review.
     * @param username Username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Gets the ID of the restaurant being reviewed.
     * @return Restaurant ID
     */
    public String getRestaurant_id() {
        return restaurant_id;
    }
    /**
     * Sets the ID of the restaurant being reviewed.
     * @param restaurant_id Restaurant ID to set
     */
    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
    /**
     * Gets the rating given by the user.
     * @return Rating (1-5)
     */
    public Integer getRating() {
        return rating;
    }
    /**
     * Sets the rating given by the user.
     * @param rating Rating to set (1-5)
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    /**
     * Gets the comment written by the user.
     * @return Comment text
     */
    public String getComment() {
        return comment;
    }
    /**
     * Sets the comment written by the user.
     * @param comment Comment text to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * Gets the reply written by the restaurant owner.
     * @return Reply text, or null if no reply has been made
     */
    public String getReply() {
        return reply;
    }
    /**
     * Sets the reply written by the restaurant owner.
     * @param reply Reply text to set, can be null if no reply exists
     */
    public void setReply(String reply) {
        this.reply = reply;
    }
}
