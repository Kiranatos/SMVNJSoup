package net.kiranatos.webcrawlers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetImagesFromAnySite {
    public static void main(String[] args) throws IOException {
        
        Document _docInternet = Jsoup.connect("https://m.gamer.com.tw/forum/Co.php?bsn=28688&snB=14084")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .timeout(6000)
                .get();
        
        Elements links = _docInternet.select("a[href].photoswipe-image");
        System.out.println("Size: " + links.size());
        for (Element link: links) {
            System.out.println(".attr(href) = " + link.attr("href"));
        }
        
    }    
}
