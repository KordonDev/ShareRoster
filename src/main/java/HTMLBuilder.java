import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLTableElement;

import javax.swing.text.html.HTML;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * User: arne
 * Date: 10/19/13
 * Time: 11:50 PM
 */
public class HTMLBuilder {

    private final File printFile;

    public HTMLBuilder(String filename) {
        printFile = new File(filename);
    }

    public void createHTMLTableFromPractice(List<Practice> practices) throws RuntimeException{
        Handlebars hadHandlebars = new Handlebars();

        try {
            Template tableSchema = hadHandlebars.compile("table");
//            System.out.println(tableSchema.apply(practices));
            FileWriter writer = new FileWriter(printFile, false);
            writer.write(tableSchema.apply(practices));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
