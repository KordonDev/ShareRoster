import java.util.List;

/**
 * User: arne
 * Date: 10/19/13
 * Time: 11:57 AM
 */
public class main {

    public static void main(String[] args) {
        XMLParser xmlMontag = new XMLParser("Montag.xml");
        List<Practice> practices = xmlMontag.parseXML();
        HTMLBuilder table = new HTMLBuilder("montag2014.html");
        table.createHTMLTableFromPractice(practices);
    }

}
