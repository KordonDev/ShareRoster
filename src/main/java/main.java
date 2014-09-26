import java.io.IOException;
import java.util.List;

/**
 * User: arne
 * Date: 10/19/13
 * Time: 11:57 AM
 */
public class main {

    public static void main(String[] args) throws IOException {
        XMLParser xmlMontag = new XMLParser("Freitag.xml");
        List<Practice> practices = xmlMontag.parseXML();
        HTMLBuilder table = new HTMLBuilder("freitag2014.html");
        table.createHTMLTableFromPractice(practices);
    }

}
