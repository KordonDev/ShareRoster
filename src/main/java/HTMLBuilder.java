import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

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

    public void createHTMLTableFromPractice(List<Practice> practices) throws IOException {
        Handlebars hadHandlebars = new Handlebars();

        Template tableSchema = hadHandlebars.compile("table");
        FileWriter writer = new FileWriter(printFile, false);
        writer.write(tableSchema.apply(practices));
        writer.flush();
    }

}
