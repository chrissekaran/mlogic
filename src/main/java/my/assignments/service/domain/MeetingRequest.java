package my.assignments.service.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by chrissekaran on 20/05/17.
 */
public class MeetingRequest {
    private static final String MEETING_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter meetingRequestTimeFormatter = DateTimeFormatter.ofPattern(MEETING_TIME_FORMAT_PATTERN);

    private final LocalDateTime meetingStartTime;
    private final int durationInHours;

    public MeetingRequest(String meetingStartTimeString, String duration) {
        this.meetingStartTime = LocalDateTime.parse(meetingStartTimeString, meetingRequestTimeFormatter);
        this.durationInHours = Integer.valueOf(duration);
    }

}
