package it.uninsubria.server_services;

import it.uninsubria.DBConnection;
import it.uninsubria.dto.ReviewDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class ReviewServiceImplTest {

    @BeforeEach
    void premise() {
        String[] args = {"theknife", "password"};
        try {
            DBConnection.login(args);
        } catch (Exception e) {
            fail("DBConnection failed to login with valid credentials");
        }
    }

    @Test
    void getReviews() {
        try {
            ReviewServiceImpl reviewService = new ReviewServiceImpl();
            var reviews = reviewService.getReviews("1");
            assertNotNull(reviews);
            assertFalse(reviews.isEmpty());
        } catch (Exception e) {
            fail("getReviews failed with valid parameters: " + e.getMessage());
        }
    }

    @Test
    void createOrUpdateReview() {
        // Test with valid review
        try {
            ReviewServiceImpl rw = new ReviewServiceImpl();
            ReviewDTO rew = new ReviewDTO("Jamal_Lynch", "1",  5, "Great food!",null);
            boolean review = rw.createOrUpdateReview(rew);
            assertTrue(review, "createOrUpdateReview should return true for valid review");
        } catch (Exception e) {
            fail("createOrUpdateReview failed with valid parameters: " + e.getMessage());
        }
    }

    @Test
    void deleteReview() {
        // Test with valid review ID
        try {
            ReviewServiceImpl reviewService = new ReviewServiceImpl();
            boolean result = reviewService.deleteReview("1", "1");
            assertTrue(result, "deleteReview should return true for valid review ID");
        } catch (Exception e) {
            fail("deleteReview failed with valid review ID: " + e.getMessage());
        }
    }

    @Test
    void getUserReviews() {
        // Test with valid user ID
        try {
            ReviewServiceImpl reviewService = new ReviewServiceImpl();
            List<ReviewDTO> reviews = reviewService.getUserReviews("Jamal_Lynch");
            assertNotNull(reviews);
            assertFalse(reviews.isEmpty());
        } catch (Exception e) {
            fail("getUserReviews failed with valid user ID: " + e.getMessage());
        }
    }
}