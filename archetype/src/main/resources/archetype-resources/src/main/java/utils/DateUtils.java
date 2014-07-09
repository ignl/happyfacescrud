#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.utils;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Utility class to help working with dates.
 * 
 * @author Ignas
 *
 */
// TODO use java 1.8 date api instead of joda time
public final class DateUtils {
    
    /**
     * Private constructor (to forbid utility class instantiation).
     */
    private DateUtils() {
        super();
    }

    /**
     * Create date from provided fields.
     */
    public static Date createDate(int year, int month, int day) {
        return createJodaDate(year, month, day, 0, 0, 0).toDate();
    }

    /**
     * Create date from provided fields.
     */
    public static Date createDate(int year, int month, int day, int hour, int minute, int sec) {
        return createJodaDate(year, month, day, hour, minute, sec).toDate();
    }
    
    /**
     * Create Calendar with date from provided fields.
     */
    private static DateTime createJodaDate(int year, int month, int day, int hourOfDay, int minute, int sec) {
        DateTime date = new DateTime(year, month, day, hourOfDay, minute, sec);
        return date;
    }

    /**
     * Check if to dates are the same (same year/month/day) not including time.
     */
    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.setTime(date1);
        cal2.setTime(date2);
        if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
            return false;
        }
        if (cal1.get(Calendar.DAY_OF_YEAR) != cal2.get(Calendar.DAY_OF_YEAR)) {
            return false;
        }

        return true;
    }
    
    /**
     * Adds days to provided date.
     * 
     * @param date Date
     * @param nbDaysToAdd Number of days to add
     * 
     * @return Date nbDaysToAdd later.
     */
    public static Date addDaysToDate(Date date, int nbDaysToAdd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, nbDaysToAdd);
        return cal.getTime();
    }
    
    /**
     * Returns difference in number of days between firstDate and secondDate.
     */
    @SuppressWarnings("deprecation")
    public static int substractInDays(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        return Days.daysBetween(new DateTime(startDate).toDateMidnight(), new DateTime(endDate).toDateMidnight()).getDays();
    }
    
}
