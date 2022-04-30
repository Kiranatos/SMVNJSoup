package net.kiranatos.sandbox;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* Нечего интерсного, уже законспектировал в примерах папкой выше
оставил в качестве примера, т.к. интересные CSS селекторы */

public class UnimportantExample002 {
    public static void main(String[] args) throws IOException {
        String bodyFragment = "<div><a href=\"/documentation\">Stack Overflow Documentation</a></div>";
        Document doc = Jsoup.parseBodyFragment(bodyFragment);
        String link = doc
                .select("div > a")
                .first()
                .attr("href");
        System.out.println(link);
        
        doc = Jsoup.parseBodyFragment(bodyFragment, "http://stackoverflow.com");
        link = doc
                .select("div > a")
                .first()
                .absUrl("href");
        System.out.println(link);
    }            
}
