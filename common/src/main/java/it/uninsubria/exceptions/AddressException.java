package it.uninsubria.exceptions;

/**
 * Custom exception class for handling address-related errors.
 * This exception is thrown when there are issues related to address operations,
 * such as retrieving or inserting address data.
 */
public class AddressException extends RuntimeException {
    public AddressException(String message) {
        super(message);
    }
}
