package net.kiranatos;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProjectHelper {    
    public static String getHTMLStringFromFile(String path) {
        String htmlCodeFromExampleFile = new String();
        try {
            InputStream is = ProjectHelper.class.getResourceAsStream(path);
            htmlCodeFromExampleFile = new String(is.readAllBytes());
        } catch (IOException ex) {
            Logger.getLogger(ProjectHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return htmlCodeFromExampleFile;
    }
    
    static void printElements(Elements elems){
        for (Element el: elems) {
            OzoHelper.lineSeparator(30, 1, '-');
            System.out.println(el.outerHtml());
        }
    }
}