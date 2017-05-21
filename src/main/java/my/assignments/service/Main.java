package my.assignments.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chrissekaran on 20/05/17.
 */
public class Main {

    public static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        BookingService bookingService = new BookingService(new MeetingService(9, 18));
        String[][] bookingAndMeeting = {
                {"2015-08-17 10:17:06 EMP001", "2015-08-21 09:00 2"},
                {"2015-08-16 12:34:56 EMP002", "2015-08-21 09:00 2"},
                {"2015-08-16 09:28:23 EMP003", "2015-08-22 14:00 2"},
                {"2015-08-17 11:23:45 EMP004", "2015-08-22 16:00 1"},
                {"2015-08-15 17:29:12 EMP005", "2015-08-21 16:00 3"},
                {"", ""},
        };

        for (int i = 0; i <  bookingAndMeeting.length; i++) {
            try {
                bookingService.submitBooking(bookingAndMeeting[i][0], bookingAndMeeting[i][1]);
            } catch (BookingException e) {
                LOG.error(e.getMessage());
            }
        }

        bookingService.process();
    }










}