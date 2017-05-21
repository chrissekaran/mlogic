package my.assignments.service.validation;

import my.assignments.service.BookingException;
import org.junit.Before;
import org.junit.Test;

import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

/**
 * Created by chrissekaran on 20/05/17.
 */
public class MeetingRequestValidationTest {

    private MeetingRequestValidation meetingRequestValidation;
    @Before
    public void setUp() throws Exception {
        meetingRequestValidation = new MeetingRequestValidation();
    }

    @Test(expected = BookingException.class)
    public void validateFailsForWrongFormatWithoutSpace() throws Exception {
        meetingRequestValidation.validate("2015-08-17 10:17:0601");
    }

    @Test(expected = BookingException.class)
    public void validateFailsForWrongDateFormatWithoutSpace() throws Exception {
        meetingRequestValidation.validate("2015-08-1710:17:06 01");
    }

    @Test(expected = DateTimeParseException.class)
    public void validateFailsForInvalidDateValue() throws Exception {
        meetingRequestValidation.validate("2015-08-37 10:17 1");
    }

    @Test(expected = NumberFormatException.class)
    public void validateFailsForInvalidDuration() throws Exception {
        meetingRequestValidation.validate("2015-08-17 10:17 1XX");
    }

    @Test
    public void validateWontFailWhenCorrectDateFormatAndDuration() throws Exception {
        meetingRequestValidation.validate("2015-08-17 10:17 1");
    }
}