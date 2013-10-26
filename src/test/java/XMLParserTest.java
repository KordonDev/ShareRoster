import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * User: arne
 * Date: 10/19/13
 * Time: 9:41 PM
 */
public class XMLParserTest {

    private XMLParser testObject;
    private final String testXMLFileName = "testXML.xml";
    Iterable<Practice> testResult;

    @Before
    public void initializeTest() throws IOException {
        createTestXML();
        testObject = new XMLParser(testXMLFileName);
        testResult = testObject.parseXML();
    }

    @Test
    public void correctNumberOfPractices() {
        Iterator<Practice> it = testResult.iterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(),is(true));
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void correctMeetingPlaceOfPractices() {
        Iterator<Practice> it = testResult.iterator();
        assertThat(it.next().getMeetingPlace(), is("GH"));
        assertThat(it.next().getMeetingPlace(), is("GH"));
    }

    @Test
    public void correctSubjectOfPractices() {
        Iterator<Practice> it = testResult.iterator();
        assertThat(it.next().getSubject(), is("1. Übung 2. Halbjahr"));
        assertThat(it.next().getSubject(), is("Löschangriff"));
    }

    private void createTestXML() throws IOException {
        String XML = "<Roster xmlns=\"http://schemas.datacontract.org/2004/07/FloraX.JfwManager.JfwDataModel.RostersData\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "<Entries>\n" +
                "<RosterEntry>\n" +
                "\t<Date>2013-09-09T19:00:00+02:00</Date>\n" +
                "\t<MeetingPlace>GH</MeetingPlace>\n" +
                "\t<ResponsiblePerson>A. Maier</ResponsiblePerson>\n" +
                "\t<Subject>1. Übung 2. Halbjahr</Subject>\n" +
                "</RosterEntry><RosterEntry>\n" +
                "\t<Date>2013-09-16T19:00:00+02:00</Date><MeetingPlace>GH</MeetingPlace><ResponsiblePerson>S. Brecht</ResponsiblePerson><Subject>Löschangriff</Subject></RosterEntry></Entries>\n" +
                "<GroupId>1</GroupId>\n" +
                "<Name>Gruppe Montag 2. HJ 2013</Name>\n" +
                "<ValidityDate>2013-12-16T00:00:00</ValidityDate>\n" +
                "</Roster>";
        File testFile = new File(testXMLFileName);
        FileWriter fileWriter = new FileWriter(testFile);
        fileWriter.write(XML);
        fileWriter.flush();
        fileWriter.close();
    }
}
