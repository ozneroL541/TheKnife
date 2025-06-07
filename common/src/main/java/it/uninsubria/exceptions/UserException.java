package it.uninsubria.exceptions;

/**
 * Custom exception class for handling user-related errors.
 * This exception is thrown when there are issues related to user operations,
 * such as retrieving or inserting user data.
 * @extends RuntimeException
 */
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
