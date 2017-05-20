package my.assignments.service;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class MeetingService {

    private final int officeStartingHours;
    private final int officeEndingHours;

    final private Map<Date, String> meetingMap = new TreeMap();

    public MeetingService(int officeStartingHours, int officeEndingHours) {
        this.officeStartingHours = officeStartingHours;
        this.officeEndingHours = officeEndingHours;
    }


    public boolean checkAndUpdateCalendarWithBooking(String booking) throws BookingException {


        return false;
    }
}
