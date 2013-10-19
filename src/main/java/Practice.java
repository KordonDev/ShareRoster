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
    private final String timeInformation;

    public Practice(String timeInformation, String subject, String place) {
        this.subject = subject;
        this.place = place;
        this.timeInformation = timeInformation;
    }

    public String meetingPlace() {
        return place;
    }

    public String subject() {
        return subject;
    }
}
