package net.kiranatos;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// https://www.javacodeexamples.com/jsoup-download-images-from-webpage-example/815

public class Demo06DownloadingImages {
    //The url of the website. This is just an example
    private static final String webSiteURL = "http://www.supercars.net/gallery/119513/2841/5.html";
    //The path of the folder that you want to save the images to
    private static final String folderPath = "wfiles/demo06";

    public static void main(String[] args) {        
        try {            
            Files.createDirectories(Paths.get(folderPath));
            
            Document doc = Jsoup.connect(webSiteURL).get(); //Connect to the website and get the html            
            Elements img = doc.getElementsByTag("img"); //Get all elements with img tag

            for (Element el : img) {                
                String src = el.absUrl("src"); //for each element get the srs url

                System.out.println("\nImage Found!");
                System.out.println("src attribute is : " + src);
 
                if (src.length() > 0)
                    getImages(src); 
            }
        } catch (IOException ex) {
            System.err.println("There was an error");
            Logger.getLogger(Demo06DownloadingImages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Downloading image's file happens here. In main method only prepearing.
     * @param src
     * @throws IOException 
     */
    private static void getImages(String src) throws IOException { 
        String folder = null;
         
        int indexname = src.lastIndexOf("/"); //Exctract the name of the image from the src attribute
 
        if (indexname == src.length()) {
            src = src.substring(1, indexname);  // отбрасывает / в конце
        }
 
        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname, src.length());
 
        System.out.println(name);
 
        //Open a URL Stream
        URL url = new URL(src);
        InputStream in = url.openStream();
 
        OutputStream out = new BufferedOutputStream(new FileOutputStream(folderPath + name));
 
        byte[] b = new byte[2048];
        int length;
        while ((length = in.read(b)) != -1) {
            out.write(b, 0, length);
        }
        
        out.close();
        in.close(); 
    }
}
