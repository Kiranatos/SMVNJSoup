package net.kiranatos;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Demo02ElementMethods {
    private final static String path = "/html/Demo02and03.html";
    private static Document document = Jsoup.parse(ProjectHelper.getHTMLStringFromFile(path));
    private static int index = 1;
    
    public static void main(String[] args) {
        System.out.printf("%d)%n", index++);
        Element elem01 = document.select("a").first(); //<a href = "http://www.exampleA.net">Example A</a>
        System.out.println("[attr(href)]\t "      + elem01.attr("href"));
        System.out.println("[html]\t"       + elem01.html());
        System.out.println("[outerHtml]\t"  + elem01.outerHtml());
        System.out.println("[text]\t"       + elem01.text());
        System.out.println("[text]\t"       + elem01.text("Set the text of this element"));
        
        OzoHelper.lineSeparator(60, 1, '*');
        
        System.out.printf("%d)%n", index++);
        // tag element can be chose by number in list
        elem01 = document.select("div").get(1); //<div id="exB" class="classB1 classB2 classB3">Example B2</div>
        System.out.println("[className]\t"       + elem01.className());
        
        OzoHelper.lineSeparator(60, 1, '*');
        
        System.out.printf("%d)%n", index++);
        elem01 = document.select("img").last(); //<img src="D:\Java\img\price.png" alt="alt text" width="600" height="600">        
        System.out.println("[attr(src)]\t" + elem01.attr("src"));
        System.out.println("[attr(abs:src)]\t" + elem01.attr("abs:src"));
        System.out.println("[absUrl(src)]\t" + elem01.absUrl("src"));  
        /* absUrl - Get an absolute URL from a URL attribute that may be relative (i.e. an <a href> or <img src>).
            As an alternate, you can use the .attr("abs:href")
            https://jsoup.org/apidocs/org/jsoup/nodes/Node.html#absUrl(java.lang.String)       */
        
    } 
}
