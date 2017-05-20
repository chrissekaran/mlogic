package my.assignments.service;

/**
 * Created by chrissekaran on 20/05/17.
 */
public class BookingException extends Exception {

    public BookingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingException(String message) {
        super(message);
    }
}
