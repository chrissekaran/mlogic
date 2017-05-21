package my.assignments.service.validation;

import my.assignments.service.BookingException;
import my.assignments.service.domain.BookingRequest;
import my.assignments.service.domain.MeetingRequest;


public class BookingRequestValidation {
    final int positionDate = 0;
    final int positionTime = 1;
    final int positionEmployee = 2;
    private static final String separator = " ";

    final MeetingRequestValidation meetingRequestValidation = new MeetingRequestValidation();

    public BookingRequestValidation() {
    }

    public BookingRequest validate(String booking, String meeting) throws BookingException {
        if (booking == null || booking.isEmpty()) {
            throw new BookingException("Empty or null booking record");
        }
        String[] bookingElements = booking.split(" ");
        if (bookingElements.length != 3) {
            throw new BookingException("Booking record format is incorrect. Please use 'YYYY-MM-DD HH:MM:SS <EmpId>'");
        }
        MeetingRequest meetingRequest = meetingRequestValidation.validate(meeting);
        return new BookingRequest(bookingElements[positionDate] + separator + bookingElements[positionTime], bookingElements[positionEmployee], meetingRequest);
    }
}