package net.kiranatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Demo07DifferentExamples {
    private static final String imagePath = "https://www.supercars.net/blog/wp-content/uploads/2020/12/SuperCars-Logo-500.png";
    private static final String webSiteURL = "http://www.supercars.net/gallery/119513/2841/5.html";    
    private static final String folderPath = "wfiles/demo07";    
    private static final String eroPath = "https://www.sex.com/pin/27842415-amateur-blonde-college-hot-lesbian-pussy-teen/";
    private static final String eroImagePath = "https://cdn.sex.com/images/pinporn/2012/05/09/237039.jpg?width=620&site=sex&user=supergia";
    private final static String fileNameOutsideProject = "rfiles/demo07baseUri.html";
    
    public static void main(String[] args) throws IOException {
        // пример из https://stackoverflow.com/questions/12465586/how-can-i-download-an-image-using-jsoup
        // just get HTML code, я надеялся, что это поможет заполучить куки-файлы, но не сработало
        getPageHTMLCode(eroPath);        
        
        // подстановка url в url ссылок из кода
        parse_baseUri_demonstration();
    }
        
    private static void getPageHTMLCode(String url) throws IOException {
        Map<String, String> cookies = new HashMap<>();
                
        Response resultImageResponse = Jsoup //Open a URL Stream
                .connect(url)
                .cookies(cookies)
                .ignoreContentType(true)
                .execute();

        // output here
        FileOutputStream out = (new FileOutputStream(new java.io.File(folderPath + "htmlCode.txt")));        
        out.write(resultImageResponse.bodyAsBytes());  // resultImageResponse.body() is where the image's contents are.
        out.close();
        System.out.println("site code was written in file " + folderPath + "htmlCode.txt\n\n");
    }
    
    private static void parse_baseUri_demonstration () throws IOException {
        File in = new File(fileNameOutsideProject);
                
        Document docWithBaseURI = Jsoup.parse(in, "UTF-8", "http://example.com/input/");
        Document docWithoutBaseURI = Jsoup.parse(in, "UTF-8");

        System.out.println(docWithBaseURI.select("img").first().absUrl("src"));         // img.jpg ->   http://example.com/input/img.jpg
        System.out.println(docWithBaseURI.select("a[href]").first().absUrl("href"));    // a.jpg ->     http://example.com/input/a.jpg
        System.out.println(docWithBaseURI.select("img").last().absUrl("src"));          // /img.jpg ->  http://example.com/img.jpg
        System.out.println(docWithBaseURI.select("a[href]").last().absUrl("href"));     // /a.jpg ->    http://example.com/a.jpg
        
//        System.out.println(doc2.select("img").first().absUrl("src"));
//        System.out.println(doc2.select("img").first().baseUri());
//        System.out.println(doc2.select("img").first().html());
//        System.out.println(doc2.select("a[href]").first().absUrl("href"));

        System.out.println("====================================");

//        System.out.println(doc1.html());
    }
}

/*
        URLConnection connection = new URL(eroImagePath).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:97.0) Gecko/20100101 Firefox/97.0");
        connection.connect();
        
        InputStreamReader in = new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8"));
        
        OutputStreamWriter fos = new OutputStreamWriter(new FileOutputStream(new File(folderPath + "yourFile.jpg")));
        
        System.out.println("reading from resource and writing to file...");
        int length = -1;
        char[] buffer = new char[1024];// buffer for portion of data from connection
        while ((length = in.read(buffer)) > -1) {            
            fos.write(buffer, 0, length);
        }
        fos.close();
        in.close();
        System.out.println("File downloaded");
                
                
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb.toString());
        
        
        System.out.println("opening connection");
        URL url = new URL(eroImagePath);
        InputStream in = url.openStream();
        FileOutputStream fos = new FileOutputStream(new File(folderPath + "yourFile.jpg"));
        
        System.out.println("reading from resource and writing to file...");
        int length = -1;
        byte[] buffer = new byte[1024];// buffer for portion of data from connection
        while ((length = in.read(buffer)) > -1) {
            fos.write(buffer, 0, length);
        }
        fos.close();
        in.close();
        System.out.println("File downloaded"); */