package net.kiranatos.undecided;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
из херового видео индуса, не понятно что он делал, поєтому я єтот код не запускал
так как боюсь, что изменения может похерить мою систему.
НУЖНО ИЗУЧИТЬ РАБОТУ ИНТЕРНЕТА
*/

public class JSoupCrawler2 {
    public static void main(String[] args) throws IOException {       

        String whaIsThis = Jsoup.connect("http://useragentstring.com/").get().text();
        System.out.println(whaIsThis);        
        
        System.out.println("\n How to set user agent of your choice in jsoup java scaper "
                + "(crawler).You can get user agent string of your browser from "
                + "useragentstring.com and you can also add that string in your jsoup crawler");        
        whaIsThis = Jsoup.connect("http://useragentstring.com/")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0")
                .get().text();
        System.out.println(whaIsThis);
        
        /* How to set proxy of your choice in jsoup java scraper / crawler.
        And we will also set time out of our choice in crawler.the parameter that 
        is given to timeout method is in mili seconds so that`s why i have given 
        parameter of 5000 or 3000 so we can get time out of 5 or 3 seconds respectively.
        we will also lean to handle different content types in jsoup.Jsoup only allows working 
        with HTML and XML content type and throws exceptions for others. So, you will need 
        to specify this properly in order to work with other content types, such as RSS, Atom, and so on*/        
        System.setProperty("http.proxyhost", "127.0.0.1");
        System.setProperty("http.proxyport", "3128");
        whaIsThis = Jsoup.connect("http://useragentstring.com/")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0")
                .ignoreContentType(true).timeout(5000)
                .get().text();
        
    }    
}
