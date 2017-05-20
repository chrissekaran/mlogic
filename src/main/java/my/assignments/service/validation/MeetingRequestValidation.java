package my.assignments.service.validation;

import my.assignments.service.BookingException;
import my.assignments.service.domain.MeetingRequest;

/**
 * Created by chrissekaran on 20/05/17.
 */
public class MeetingRequestValidation {
    final int positionDate = 0;
    final int positionTime = 1;
    final int positionDurationInHours = 2;
    private static final String separator = " ";

    public MeetingRequestValidation() {
    }

    public MeetingRequest validate(String meeting) throws BookingException {
        if(meeting == null || meeting.isEmpty()) {
          throw new BookingException("Empty or null meeting record");
        }
        String[] meetingElements = meeting.split(" ");
        if (meetingElements.length != 3) {
            throw new BookingException("Meeting record format is incorrect. Please use 'yyyy-MM-dd hh:mm <DurationInHours>'");
        }
        return new MeetingRequest(meetingElements[positionDate] + separator + meetingElements[positionTime], meetingElements[positionDurationInHours]);
    }

}
