import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * User: arne
 * Date: 10/19/13
 * Time: 11:57 AM
 */
public class Practice {

    private final String subject;
    private final String place;
    private final LocalDate date;
    private final LocalTime startTime;
    private final WeekDay dayOfWeek;

    public Practice(String timeInformation, String subject, String place) {
        this.subject = subject;
        this.place = place;
        date = dateOfTimeInformation(timeInformation);
        startTime = startTimeOfTimeInformation(timeInformation);
        dayOfWeek = weekDayOfDate();
    }

    private WeekDay weekDayOfDate() {
        int i = date.getDayOfWeek()-1;
        return WeekDay.values()[i];
    }

    private LocalTime startTimeOfTimeInformation(String timeInformation) {
        int hourOfDay = Integer.valueOf(timeInformation.substring(11,13));
        int minuteOfHour = Integer.valueOf(timeInformation.substring(14,16));
        return new LocalTime(hourOfDay,minuteOfHour);
    }

    private LocalDate dateOfTimeInformation(String timeInformation) {
        int year = Integer.valueOf(timeInformation.substring(0, 4));
        int monthOfYear = Integer.valueOf(timeInformation.substring(5,7));
        int dayOfMonth = Integer.valueOf(timeInformation.substring(8,10));
        return new LocalDate(year,monthOfYear,dayOfMonth);
    }


    public String getMeetingPlace() {
        return place;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return new String(stringOfTwoNumbers(date.getDayOfMonth())+ "."+ stringOfTwoNumbers(date.getMonthOfYear())+"."+
                stringOfTwoNumbers(date.getYear()));
    }

    public WeekDay getDayOfWeek() {
        return dayOfWeek;
    }

    public String getStartTime() {
        return new String(stringOfTwoNumbers(startTime.getHourOfDay())+":"+
                stringOfTwoNumbers(startTime.getMinuteOfHour()));
    }

    private String stringOfTwoNumbers(int number) {
        String result = String.valueOf(number);
        if (result.length()==1) {
            result = "0" + result;
        }
        return result;
    }

}
