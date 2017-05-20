package my.assignments.service.validation;

import my.assignments.service.BookingException;
import my.assignments.service.domain.BookingRequest;
import org.junit.Before;
import org.junit.Test;

import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

/**
 * Created by chrissekaran on 20/05/17.
 */
public class BookingRequestValidationTest {

    private BookingRequestValidation bookingRequestValidation;

    @Before
    public void setUp() throws Exception {
        bookingRequestValidation = new BookingRequestValidation();
    }

    @Test(expected = BookingException.class)
    public void validateFailsForWrongFormatWithoutSpace() throws Exception {
        bookingRequestValidation.validate("2015-08-17 10:17:06EMP001");
    }

    @Test(expected = BookingException.class)
    public void validateFailsForWrongDateFormatWithoutSpace() throws Exception {
        bookingRequestValidation.validate("2015-08-1710:17:06 EMP001");
    }

    @Test(expected = DateTimeParseException.class)
    public void validateFailsForInvalidDateFormat() throws Exception {
        bookingRequestValidation.validate("2015-08-37 10:17:06 EMP001");
    }

}