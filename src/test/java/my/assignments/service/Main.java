package my.assignments.service;

import my.assignments.service.domain.BookingRequest;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chrissekaran on 20/05/17.
 */
public class Main {

    public static void main(String[] args) {
        Set<BookingRequest> bookingRequests = new HashSet<>();
        new BookingService(new MeetingService(9, 18))
                .addBookingRequests(bookingRequests)
                .process();
    }
}
