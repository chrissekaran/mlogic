package my.assignments.service;


import my.assignments.service.domain.BookingRequest;
import my.assignments.service.validation.BookingRequestValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Set;

/**
 * Created by chrissekaran on 16/05/17.
 */
public class BookingService {

    public static Logger LOG = LoggerFactory.getLogger(BookingService.class);

    final private BookingRequestValidation bookingValidation = new BookingRequestValidation();
    final private MeetingService meetingService;

    public BookingService(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    public void submitBooking(String bookingRecord, String meetingRecord) throws BookingException {
        BookingRequest bookingRequest = bookingValidation.validate(bookingRecord, meetingRecord);
        if(!meetingService.checkAndUpdateCalendarWithBooking(bookingRequest)) {
            LOG.error("Failed Calendar Availability for booking: {} with meeting record: {}", bookingRecord, meetingRecord);
        }
    }

    public void process() {
        meetingService.calendarSnapshot();

    }

}