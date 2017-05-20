package my.assignments.service;

import my.assignments.service.validation.BookingRequestValidation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.format.DateTimeParseException;

/**
 * Created by chrissekaran on 16/05/17.
 */
public class BookingServiceTest {

    @Spy
    BookingRequestValidation bookingValidationMock;
    @Mock
    MeetingService meetingService;
    @InjectMocks
    BookingService bookingService;
    String bookingStringInCorrectFormat = "2015-08-16 09:28:23 EMP003";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bookingService = new BookingService(meetingService);
    }

    @Test(expected = DateTimeParseException.class)
    public void submitBookingFailsForWrongFormatWithoutSpace() throws Exception {
        //doNothing().when(bookingValidationMock.validate(eq(bookingStringInCorrectFormat)));

        bookingService.submitBooking(bookingStringInCorrectFormat);

    }

    @Test(expected = BookingException.class)
    public void submitBookingFailsForWrongDateFormatWithoutSpace() throws Exception {
        bookingService.submitBooking("2015-08-1710:17:06 EMP001");
    }

    @Test(expected = DateTimeParseException.class)
    public void submitBookingFailsForInvalidDateFormat() throws Exception {
        bookingService.submitBooking("2015-08-37 10:17:06 EMP001");
    }

}
