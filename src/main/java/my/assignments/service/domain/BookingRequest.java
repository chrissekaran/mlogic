package my.assignments.service.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingRequest {
    private static final String BOOKING_REQUEST_TIME_FORMAT_PATTERN = "YYYY-MM-DD HH:MM:SS";
    private static final DateTimeFormatter bookingRequestTimeFormatter = DateTimeFormatter.ofPattern(BOOKING_REQUEST_TIME_FORMAT_PATTERN);

    private final LocalDateTime bookingRequestDateTime;
    private final String employeeId;
    private final MeetingRequest meetingRequest;

    public BookingRequest(final String bookingRequestDateTimeString,
                          final String employeeId,
                          final MeetingRequest meetingRequest) {
        this.bookingRequestDateTime = LocalDateTime.parse(bookingRequestDateTimeString, bookingRequestTimeFormatter);
        this.employeeId = employeeId;
        this.meetingRequest = meetingRequest;
    }

    public LocalDateTime getBookingRequestDateTime() {
        return bookingRequestDateTime;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public MeetingRequest getMeetingRequest() {
        return meetingRequest;
    }

}
