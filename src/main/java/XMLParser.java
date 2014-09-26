import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * User: arne
 * Date: 10/19/13
 * Time: 11:57 AM
 */
public class XMLParser {

    private final String filename;

    public XMLParser(String filename) {
        super();
        this.filename = filename;
    }

    public List<Practice> parseXML() {
        Document document = readXMLToDocument();
        return practicesListFromDocument(document);
    }

    private List<Practice> practicesListFromDocument(Document XMLDocument) {
        Element docElement = XMLDocument.getDocumentElement();
        NodeList nodeList = docElement.getElementsByTagName("RosterEntry");
        List<Practice> result = new ArrayList<Practice>();
        for(int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element)nodeList.item(i);
             result.add(createAPractice(element));
        }
        return result;
    }

    private Practice createAPractice(Element element) {
        String date = getTextOf("Date",element);
        String subject = getTextOf("Subject",element);
        System.out.println(subject);
        String place = getTextOf("MeetingPlace",element);
        return new Practice(date, subject, place);
    }

    private String getTextOf(String tag, Element practice) {
        String tagValue = null;
        NodeList tagsOfAPractice = practice.getElementsByTagName(tag);
        if (tagsOfAPractice.getLength() > 0) {
            Element element = (Element)tagsOfAPractice.item(0);
            tagValue = element.getFirstChild().getNodeValue();
        }
        return tagValue;
    }

    private Document readXMLToDocument() throws RuntimeException{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document;
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(filename);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return document;
    }

}
