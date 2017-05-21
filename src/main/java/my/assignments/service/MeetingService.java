package my.assignments.service;

import my.assignments.service.domain.BookingRequest;
import my.assignments.service.domain.MeetingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class MeetingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeetingService.class);

    private final int officeStartingHours;
    private final int officeEndingHours;

    private final Map<MeetingRequest, String> meetingCalendar = new HashMap<>();

    public MeetingService(int officeStartingHours, int officeEndingHours) {
        this.officeStartingHours = officeStartingHours;
        this.officeEndingHours = officeEndingHours;
    }

    public boolean checkAndUpdateCalendarWithBooking(BookingRequest booking) throws BookingException {

        final int hours = booking.getMeetingRequest().getDurationInHours();
        LocalDateTime timeOfBookingEnd = booking.getMeetingRequest().getMeetingStartTime().plusHours(hours);
        if(booking.getMeetingRequest().getMeetingStartTime().getHour() < officeStartingHours ||
                ((timeOfBookingEnd.getHour() > officeEndingHours) ||
                 (timeOfBookingEnd.getHour() == officeEndingHours && timeOfBookingEnd.getMinute() != 0)))   {
            //Omitting millis and nanos
            return false;
        }

        Optional<MeetingRequest> immediateBookingBefore = meetingCalendar.keySet().stream()
                .sorted(Comparator.comparing(MeetingRequest::getMeetingStartTime))
                .filter(m -> booking.getMeetingRequest().getMeetingStartTime().isAfter(m.getMeetingStartTime()))
                .sorted(Comparator.comparing(MeetingRequest::getMeetingStartTime).reversed())
                .findFirst();

        Optional<MeetingRequest> immediateBookingAfter = meetingCalendar.keySet().stream()
                .sorted(Comparator.comparing(MeetingRequest::getMeetingStartTime))
                .filter(m -> booking.getMeetingRequest().getMeetingStartTime().isBefore(m.getMeetingStartTime()))
                .sorted(Comparator.comparing(MeetingRequest::getMeetingStartTime))
                .findFirst();

        if(immediateBookingBefore.isPresent() && ! endTimeOfMeetingBefore(immediateBookingBefore)
                                                    .isBefore(booking.getMeetingRequest()
                                                    .getMeetingStartTime())) {
            return false;
        }

        if(immediateBookingAfter.isPresent() && timeOfBookingEnd
                                                    .isAfter(immediateBookingAfter.get()
                                                    .getMeetingStartTime())) {
            return false;
        }

        meetingCalendar.put(booking.getMeetingRequest(), booking.getEmployeeId());
        return true;
    }

    private LocalDateTime endTimeOfMeetingBefore(Optional<MeetingRequest> immediateBookingBefore) {
        MeetingRequest immediateMeetingBefore = immediateBookingBefore.get();
        final int hours = immediateMeetingBefore.getDurationInHours();
        LocalDateTime timeOfMeetingBeforeEnd = immediateMeetingBefore.getMeetingStartTime().plusHours(hours);
        return timeOfMeetingBeforeEnd;
    }

    public void calendarSnapshot() {
        Set<String> dates = meetingCalendar.keySet().stream()
                .map(meetingRequest -> meetingRequest.getMeetingStartTime().toLocalDate().toString())
                .collect(Collectors.toSet());
        Map<String, List<MeetingRequest>> calendarByDate = new HashMap<>();

        for(String date: dates) {
            calendarByDate.put(date, new ArrayList<>());
        }
        meetingCalendar.keySet().stream().forEach(meetingRequest -> calendarByDate
                                                                        .get(meetingRequest.getMeetingStartTime().toLocalDate().toString())
                                                                        .add(meetingRequest));

        for (String date: calendarByDate.keySet()) {
            System.out.println(date);
            for(MeetingRequest meeting: calendarByDate.get(date)) {

                System.out.println(meeting.startTimeWithoutDate() + " " +meeting.endTimeWithoutDate() + " " + meetingCalendar.get(meeting));
            }
        }


    }






}