package my.assignments.service.validation;

import my.assignments.service.BookingException;
import my.assignments.service.domain.BookingRequest;


public class BookingRequestValidation {
    final int positionDate = 0;
    final int positionTime = 1;
    final int positionEmployee = 2;
    private static final String separator = " ";


    public BookingRequestValidation() {
    }

    public BookingRequest validate(String booking) throws BookingException {
        if (booking == null || booking.isEmpty()) {
            throw new BookingException("Empty or null booking record");
        }
        String[] bookingElements = booking.split(" ");
        if (bookingElements.length != 3) {
            throw new BookingException("Booking record format is incorrect. Please use 'YYYY-MM-DD HH:MM:SS <EmpId>'");
        }
        return new BookingRequest(bookingElements[positionDate] + separator + bookingElements[positionTime] + ":00", bookingElements[positionEmployee]);
    }
}