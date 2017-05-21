package my.assignments.service;

import my.assignments.service.domain.BookingRequest;
import my.assignments.service.domain.MeetingRequest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class MeetingServiceTest {

    MeetingService meetingService = new MeetingService(10, 3);

    @Test
    public void bookMeetingThrowsExceptionsIfStartingTimeIsBeforeOfficeHours() throws Exception {
        BookingRequest bookingRequest = new BookingRequest("2015-08-17 10:17:06", "EMP001", new MeetingRequest("2015-08-17 09:17", "1"));
        boolean updated = meetingService.checkAndUpdateCalendarWithBooking(bookingRequest);
        assertFalse(updated);
    }

    @Test
    public void bookMeetingThrowsExceptionsIfEndingOfBookingMeetingRequestIsAfterOfficehours() throws Exception {
        BookingRequest bookingRequest = new BookingRequest("2015-08-17 10:17:06", "EMP001", new MeetingRequest("2015-08-17 15:17", "1"));
        boolean updated = meetingService.checkAndUpdateCalendarWithBooking(bookingRequest);
        assertFalse(updated);
    }





}
