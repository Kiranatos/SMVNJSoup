package net.kiranatos.undecided;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* Нечего интерсного, уже законспектировал в примерах папкой выше
оставил в качестве примера, т.к. интересные CSS селекторы */

public class UnimportantExample001 {
	public static void main(String[] args) throws IOException {
            System.out.println("\n Example #1 \n");
            Document d=Jsoup.connect("http://www.wikihow.com/Adjust-Bass-on-Computer").timeout(6000).get();
            Elements body=d.select("div");
            System.out.println(" SIZE: " + body.size());
            for (Element step : body.select("div.steps")) {
                String method = step.select("h3 div.altblock").text();
                String title = step.select("h3 span.mw-headline").text();
                System.out.println("GET_METHOD: "+method+" | "+title);
                for (Element section : step.select("div.section_text .mwimg a.image img.whcdn")) {
                    String img=section.attr("data-src");
                    System.out.println("GET_URL: "+img);
                }
                System.out.println("==============");
            }
            
            System.out.println("\n Example #2 \n");    
            d = Jsoup.connect("http://www.wikihow.com/wikiHowTo?search=Signal+Wifi").timeout(6000).get();
            Elements ele=d.select("div#searchresults_list"); //<div id='searchresults_list' class='wh_block'>
            for (Element element : ele.select("div.result")) {
                String img_url=element.select("div.result_thumb img").attr("src");
                if (img_url.length()>0) System.out.println(img_url);			
                String title=element.select("div.result_data a").text();
                if (title.length()>0) System.out.println(title);
            }
        }
}
