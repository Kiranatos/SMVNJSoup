package net.kiranatos.undecided;

import java.io.File;
import java.io.IOException;
import net.kiranatos.ProjectHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UnimportantExample003 {
    private final static String path = "/html/DemoUndecided.html";
    public static void main(String[] args) throws IOException {
//        Document doc = Jsoup.parse(new File("files/register.html"), "utf-8");
        String _htmlString = ProjectHelper.getHTMLStringFromFile(path);
        Document _docString = Jsoup.parse(_htmlString);
        System.out.println(_docString + "\n\n");
        
        Element form = _docString.getElementById("registerForm");
        System.out.println("Form action = "+ form.attr("action"));
        Elements inputElements = form.getElementsByTag("input");
        
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");
            System.out.println(key + " = " + value);
        }        
    }
}
