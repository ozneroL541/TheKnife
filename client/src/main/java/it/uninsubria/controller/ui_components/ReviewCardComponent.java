package it.uninsubria.controller.ui_components;

import it.uninsubria.dto.ReviewDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.function.Consumer;

/**
 * A custom JavaFX component representing a review card.
 * This component displays review information in a clickable card format.
 *
 * @author Lorenzo Radice
 */
public class ReviewCardComponent extends VBox {

    private final ReviewDTO review;
    private Consumer<ReviewDTO> onCardClick;

    /**
     * Creates a new review card component.
     *
     * @param review The review data to display
     */
    public ReviewCardComponent(ReviewDTO review) {
        this.review = review;
        setupCard();
        setupContent();
        setupClickHandler();
    }

    /**
     * Sets the callback function to be called when the card is clicked.
     *
     * @param onCardClick Callback function that receives the review data
     */
    public void setOnCardClick(Consumer<ReviewDTO> onCardClick) {
        this.onCardClick = onCardClick;
    }

    /**
     * Sets up the basic card appearance and layout.
     */
    private void setupCard() {
        // Card styling
        setStyle("-fx-background-color: white; " +
                "-fx-border-color: #e0e0e0; " +
                "-fx-border-width: 1; " +
                "-fx-border-radius: 8; " +
                "-fx-background-radius: 8;");

        setPadding(new Insets(15));
        setSpacing(10);
        setCursor(Cursor.HAND);

        // Hover effect
        setOnMouseEntered(e -> setStyle("-fx-background-color: #f8f9fa; " +
                "-fx-border-color: #1a73e8; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 8; " +
                "-fx-background-radius: 8;"));

        setOnMouseExited(e -> setStyle("-fx-background-color: white; " +
                "-fx-border-color: #e0e0e0; " +
                "-fx-border-width: 1; " +
                "-fx-border-radius: 8; " +
                "-fx-background-radius: 8;"));
    }

    /**
     * Sets up the content layout and information display.
     */
    private void setupContent() {
        // Header with reviewer and rating
        HBox headerBox = createHeaderBox();

        // Review text
        Label reviewTextLabel = createReviewTextLabel();

        // Restaurant reply (if exists)
        VBox replyBox = createReplyBox();

        getChildren().addAll(headerBox, reviewTextLabel);

        // Add reply section if reply exists
        if (review.getReply() != null && !review.getReply().trim().isEmpty()) {
            Separator separator = new Separator();
            separator.setStyle("-fx-background-color: #e0e0e0;");
            getChildren().addAll(separator, replyBox);
        }
    }

    /**
     * Creates the header box with reviewer name and rating.
     *
     * @return HBox containing header information
     */
    private HBox createHeaderBox() {
        HBox headerBox = new HBox();
        headerBox.setAlignment(Pos.CENTER_LEFT);
        headerBox.setSpacing(10);

        // Reviewer name
        Label reviewerLabel = new Label(review.getUsername());
        reviewerLabel.setFont(Font.font("System", FontWeight.BOLD, 14));

        // Spacer
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Rating stars
        HBox ratingBox = createRatingBox();

        headerBox.getChildren().addAll(reviewerLabel, spacer, ratingBox);
        return headerBox;
    }

    /**
     * Creates the rating display box.
     *
     * @return HBox containing star rating
     */
    private HBox createRatingBox() {
        HBox ratingBox = new HBox(2);
        ratingBox.setAlignment(Pos.CENTER_RIGHT);

        // Create star rating display
        String stars = "★".repeat(Math.max(0, Math.min(5, review.getRating()))) +
                "☆".repeat(Math.max(0, 5 - review.getRating()));
        Label starsLabel = new Label(stars);
        starsLabel.setStyle("-fx-text-fill: #ffc107; -fx-font-size: 14px;");

        // Rating number
        Label ratingNumberLabel = new Label("(" + review.getRating() + "/5)");
        ratingNumberLabel.setStyle("-fx-text-fill: #666666; -fx-font-size: 12px;");

        ratingBox.getChildren().addAll(starsLabel, ratingNumberLabel);
        return ratingBox;
    }

    /**
     * Creates the review text label.
     *
     * @return Label containing the review text
     */
    private Label createReviewTextLabel() {
        Label reviewTextLabel = new Label();
        reviewTextLabel.setWrapText(true);
        reviewTextLabel.setStyle("-fx-text-fill: #333333; -fx-font-size: 13px;");
        reviewTextLabel.setMaxWidth(Double.MAX_VALUE);

        // Handle null customer message
        if (review.getComment() == null) {
            reviewTextLabel.setText("");
            return reviewTextLabel;
        }

        // Limit display length for long reviews
        String displayText = review.getComment();
        if (displayText.length() > 200) {
            displayText = displayText.substring(0, 200) + "...";
        }
        reviewTextLabel.setText(displayText);

        return reviewTextLabel;
    }

    /**
     * Creates the restaurant reply box.
     *
     * @return VBox containing the restaurant reply
     */
    private VBox createReplyBox() {
        VBox replyBox = new VBox(5);
        replyBox.setStyle("-fx-background-color: #f5f5f5; " +
                "-fx-background-radius: 5; " +
                "-fx-border-color: #e0e0e0; " +
                "-fx-border-radius: 5;");
        replyBox.setPadding(new Insets(10));

        // Reply header
        Label replyHeaderLabel = new Label("Restaurant Reply:");
        replyHeaderLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        replyHeaderLabel.setStyle("-fx-text-fill: #1976d2;");

        // Reply text - handle null safely
        String replyText = review.getReply() != null ? review.getReply() : "";
        Label replyTextLabel = new Label(replyText);
        replyTextLabel.setWrapText(true);
        replyTextLabel.setStyle("-fx-text-fill: #555555; -fx-font-size: 12px;");

        // Limit reply display length only if reply exists
        if (replyText.length() > 150) {
            replyText = replyText.substring(0, 150) + "...";
            replyTextLabel.setText(replyText);
        }

        replyBox.getChildren().addAll(replyHeaderLabel, replyTextLabel);
        return replyBox;
    }

    /**
     * Sets up the click handler for the card.
     */
    private void setupClickHandler() {
        setOnMouseClicked(event -> {
            if (onCardClick != null) {
                onCardClick.accept(review);
            }
        });
    }

    /**
     * Gets the review data associated with this card.
     *
     * @return The review data
     */
    public ReviewDTO getReview() {
        return review;
    }
}