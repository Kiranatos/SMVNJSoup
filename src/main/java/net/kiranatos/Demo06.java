package net.kiranatos;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
https://stackoverflow.com/questions/12465586/how-can-i-download-an-image-using-jsoup
https://www.javacodeexamples.com/jsoup-download-images-from-webpage-example/815
*/

public class Demo06 {
    //The url of the website. This is just an example
    private static final String webSiteURL = "http://www.supercars.net/gallery/119513/2841/5.html";
    //The path of the folder that you want to save the images to
    private static final String folderPath = "wfiles/";

    public static void main(String[] args) {
        try {
            //Connect to the website and get the html
            Document doc = Jsoup.connect(webSiteURL).get();

            //Get all elements with img tag ,
            Elements img = doc.getElementsByTag("img");

            for (Element el : img) {
                //for each element get the srs url
                String src = el.absUrl("src");

                System.out.println("Image Found!");
                System.out.println("src attribute is : " + src);

                getImages(src);
            }
        } catch (IOException ex) {
            System.err.println("There was an error");
            Logger.getLogger(Demo06.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void getImages(String src) throws IOException { 
        String folder = null;
 
        //Exctract the name of the image from the src attribute
        int indexname = src.lastIndexOf("/");
 
        if (indexname == src.length()) {
            src = src.substring(1, indexname);
        }
 
        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname, src.length());
 
        System.out.println(name);
 
        //Open a URL Stream
        URL url = new URL(src);
        InputStream in = url.openStream();
 
        OutputStream out = new BufferedOutputStream(new FileOutputStream(folderPath + name));
 
        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }
        out.close();
        in.close(); 
    }
}
