package my.assignments.service.domain;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    public LocalDateTime getMeetingStartTime() {
        return meetingStartTime;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public String startTimeWithoutDate() {
        return meetingStartTime.format(DateTimeFormatter.ofPattern(MEETING_TIME_FORMAT_PATTERN)).substring(11, 16);
    }

    public String endTimeWithoutDate() {
        return meetingStartTime.plusHours(durationInHours).format(DateTimeFormatter.ofPattern(MEETING_TIME_FORMAT_PATTERN)).substring(11, 16);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingRequest that = (MeetingRequest) o;
        return durationInHours == that.durationInHours &&
                Objects.equals(meetingStartTime, that.meetingStartTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingStartTime, durationInHours);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MeetingRequest{");
        sb.append("meetingStartTime=").append(meetingStartTime);
        sb.append(", durationInHours=").append(durationInHours);
        sb.append('}');
        return sb.toString();
    }
}
