package net.kiranatos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

public class Demo05HTMLEditor {
    private final static String pathR = "rfiles/Demo05.html";
    private final static String pathW = "wfiles/Demo05.html";
    
    public static void main(String[] args) throws IOException {        
        // If the input is from a file, use:
        File file = new File(pathR);
        Document doc = Jsoup.parse(file, "utf-8");
                
        System.out.println("Изначальная страница: \n" + doc);
        
        // Add a <meta> tag to <head>.
        Element tagMetaCharset = new Element(Tag.valueOf("meta"), "");
        tagMetaCharset.attr("charset","utf-8");
        doc.head().appendChild(tagMetaCharset);
        
        // Add a <p> tag for body content description
        Element tagPDescription = new Element(Tag.valueOf("p"), "");
        tagPDescription.text("my HTML parser");
        doc.body().appendChild(tagPDescription);
        
        // Add a <p> tag for body content autor
        tagPDescription.before("<p> some text 11Kiranatos11 </p>");
        
        // Add an attribute to the <p> tag of the Kiranatos11
        Element tagPAutor = doc.body().select("p:contains(Kiranatos11)").first();
        tagPAutor.attr("align", "center");
        
        // Add a class for the <body> tag
        doc.body().addClass("content");

        System.out.println("\nКонечная страница: \n" + doc);
        
        // Для записи информации в новый html-файл:
        FileWriter writer = new FileWriter(pathW);
        writer.write(doc.toString());
        writer.close();
    }    
}