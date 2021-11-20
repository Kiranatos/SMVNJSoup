package net.kiranatos;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Demo01HTMLInternetCrawler {
    private static final String INTERNET_URL = "https://bash.im/";
    public static void main(String[] args) throws IOException {   
        // If input from a URL, use:
        Document docInternet = Jsoup.connect(INTERNET_URL)  
//                .userAgent("Chrome/4.0.249.0 Safari/532.5") /* является идентификатором, который сообщается 
//                    посещаемому сайту. На многих сайтах он является важнейшим критерием для антиспам фильтра.
//                    http://useragentstring.com/index.php */
//                .referrer("http://www.google.com") // содержит URL источника запроса.
//                .timeout(6000)
                .get();
        
        System.out.println("Title: " + docInternet.title());        
        
        //Listing all URLs within an HTML page. Elements extends ArrayList<Element>.
        Elements elems = docInternet.select("a[href]");
        System.out.println("Size: " + elems.size());
        ProjectHelper.printElements(elems);                   
    }    
}