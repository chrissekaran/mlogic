package my.assignments.service;


import my.assignments.service.domain.BookingRequest;
import my.assignments.service.validation.BookingRequestValidation;

import java.util.Comparator;
import java.util.Set;

/**
 * Created by chrissekaran on 16/05/17.
 */
public class BookingService {

    final private BookingRequestValidation bookingValidation = new BookingRequestValidation();
    final private MeetingService meetingService;

    public BookingService(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    public void submitBooking(String booking) throws BookingException {
        bookingValidation.validate(booking);
        try {
            meetingService.checkAndUpdateCalendarWithBooking(booking);
        } catch (BookingException e) {
            e.printStackTrace();
        }

    }

    public BookingService addBookingRequests(Set<BookingRequest> bookingRequests) {
        if(!bookingRequests.isEmpty())  {
            bookingRequests.stream().sorted((o1, o2) -> {
                if(o1.getBookingRequestDateTime().equals(o2.getBookingRequestDateTime()))
                    return 0;
                else {
                    return o1.getBookingRequestDateTime().isBefore(o2.getBookingRequestDateTime())?1:-1;
                }
            });
            //TODO
        }
        return this;
    }


    public void process() {

    }
}