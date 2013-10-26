import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: arne
 * Date: 10/19/13
 * Time: 11:14 PM
 */
public class PracticeTest {

    private Practice testObject;
    private final String date = "09.09.2013";
    private final String meetingPlace = "GH";
    private final String subject = "1. Übung 2. Halbjahr";
    private final String startTime = "19:00";

    @Before
    public void setUp() {
        testObject = new Practice("2013-09-09T19:00:00+02:00","1. Übung 2. Halbjahr","GH");
    }

    @Test
    public void testMeetingPlace() throws Exception {
        assertThat(testObject.getMeetingPlace(), is(meetingPlace));
    }

    @Test
    public void testSubject() throws Exception {
        assertThat(testObject.getSubject(),is(subject));
    }

    @Test
    public void testDate() throws Exception {
        assertThat(testObject.getDate(),is(date));
    }

    @Test
    public void testStartTime() throws Exception {
        assertThat(testObject.getStartTime(),is(startTime));
    }

    @Test
    public void testDayOfWeekIsMonday() throws Exception {
        assertThat(testObject.getDayOfWeek(),is(WeekDay.Montag));
    }

    @Test
    public void testDayOfWeekIsDienstag() throws Exception {
        Practice dienstagTestObject = new Practice("2013-10-15T19:00:00+02:00","","");
        assertThat(dienstagTestObject.getDayOfWeek().name(),is("Dienstag"));
    }

    @Test
    public void testDayOfWeekIsMittwoch() throws Exception {
        Practice mittwochTestObject = new Practice("2015-05-20T19:00:00+02:00","","");
        assertThat(mittwochTestObject.getDayOfWeek(),is(WeekDay.Mittwoch));
    }

    @Test
    public void testDayOfWeekIsDonnerstag() throws Exception {
        Practice donnerstagTestObject = new Practice("2013-12-05T19:00:00+02:00","","");
        assertThat(donnerstagTestObject.getDayOfWeek(),is(WeekDay.Donnerstag));
    }

    @Test
    public void testDayOfWeekIsFreitag() throws Exception {
        Practice freitagTestObject = new Practice("2008-02-29T19:00:00+02:00","","");
        assertThat(freitagTestObject.getDayOfWeek(),is(WeekDay.Freitag));
    }

    @Test
    public void testDayOfWeekIsSamstag() throws Exception {
        Practice samstagTestObject = new Practice("2020-06-20T19:00:00+02:00","","");
        assertThat(samstagTestObject.getDayOfWeek(),is(WeekDay.Samstag));
    }

    @Test
    public void testDayOfWeekIsSonntag() throws Exception {
        Practice sonntagTestObject = new Practice("2013-10-20T19:00:00+02:00","","");
        assertThat(sonntagTestObject.getDayOfWeek(),is(WeekDay.Sonntag));
    }
}
