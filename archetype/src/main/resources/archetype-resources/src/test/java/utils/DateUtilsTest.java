#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.utils;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for Date operations.
 * 
 * @author Ignas
 *
 */
public class DateUtilsTest {
    
    @Test
    public void testCreateDate() {
        Date createdDate = DateUtils.createDate(2011, 1, 2);
        Assert.assertEquals(2011, new DateTime(createdDate).getYear());
        Assert.assertEquals(1, new DateTime(createdDate).getMonthOfYear());
        Assert.assertEquals(2, new DateTime(createdDate).getDayOfMonth());
        createdDate = DateUtils.createDate(2013, 12, 31, 23, 59, 59);
        Assert.assertEquals(2013, new DateTime(createdDate).getYear());
        Assert.assertEquals(12, new DateTime(createdDate).getMonthOfYear());
        Assert.assertEquals(31, new DateTime(createdDate).getDayOfMonth());
        Assert.assertEquals(23, new DateTime(createdDate).getHourOfDay());
        Assert.assertEquals(59, new DateTime(createdDate).getMinuteOfHour());
        Assert.assertEquals(59, new DateTime(createdDate).getSecondOfMinute());
    }
    
    @Test
    public void testAddDaysToDate() {
        Date date1 = DateUtils.createDate(2011, 2, 1);
        Assert.assertEquals(DateUtils.createDate(2011, 2, 21), DateUtils.addDaysToDate(date1, 20));
    }
    
    @Test
    public void testSubstractInDaysSameDay() {
        Date date1 = new Date();
        Date date2 = new Date();
        Assert.assertEquals(0, DateUtils.substractInDays(date1, date2));
        date1 = DateUtils.createDate(2011, 2, 1, 0, 0, 0);
        date2 = DateUtils.createDate(2011, 2, 1, 11, 59, 59);
        Assert.assertEquals(0, DateUtils.substractInDays(date1, date2));
        
    }

    @Test
    public void testSubstractInDaysSameYearDifferentDays() {
        Date date1 =  DateUtils.createDate(2011, 2, 1, 0, 0, 0);
        Date date2 =  DateUtils.createDate(2011, 2, 3, 23, 59, 59);
        Assert.assertEquals(2, DateUtils.substractInDays(date1, date2));
        date1 = DateUtils.createDate(2011, 2, 1, 23, 59, 59);
        date2 = DateUtils.createDate(2011, 2, 4, 0, 0, 1);
        Assert.assertEquals(3, DateUtils.substractInDays(date1, date2));
        
    }
    
    @Test
    public void testSubstractInDaysDifferentYearDifferentDays() {
        Date date1 = DateUtils.createDate(2011, 12, 31, 23, 59, 59);
        Date date2 = DateUtils.createDate(2012, 1, 1, 0 , 0, 0);
        Assert.assertEquals(1, DateUtils.substractInDays(date1, date2));
        date1 = DateUtils.createDate(2011, 12, 31, 23, 59, 59);
        date2 = DateUtils.createDate(2012, 1, 4, 0 , 0, 0);
        Assert.assertEquals(4, DateUtils.substractInDays(date1, date2));
        date1 = DateUtils.createDate(2012, 1, 1, 0 , 0, 0);
        date2 = DateUtils.createDate(2011, 12, 30, 23, 59, 59);
        Assert.assertEquals(-2, DateUtils.substractInDays(date1, date2));
        
    }

}
