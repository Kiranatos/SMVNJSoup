package net.kiranatos.sandbox;

import static org.jsoup.Jsoup.parse;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// пример стянут с какого-то сайта, не запустился - нужно изучить и разобрать

public class JsoupDemoTest {
    private final static String URL_TO_PARSE  = "http://robgravelle.com/albums/";
    private final static String LINK = "t=60";
    @SuppressWarnings("serial")
    private static class InvalidFileTypeException extends Exception {}
   
    public static void main(String[] args) throws IOException {
        //these two lines are only required if your Internet
        //connection uses a proxy server
        //System.setProperty("http.proxyHost", "my.proxy.server");
        //System.setProperty("http.proxyPort", "8081");
        URL url = new URL(URL_TO_PARSE);
        Document doc = parse(url, 30000);
       
        Elements links = doc.select("a[href$=" + LINK + "]");
        int linksSize = links.size();
        if (linksSize > 0) {
            if (linksSize > 1) {
                System.out.println("Warning: more than one link found.  Downloading first match.");
            }
            Element link    = links.first();
            String  linkUrl = link.attr("abs:href");
            //Thanks to Jeremy Chung for maxBodySize solution
            //https://jmchung.github.io/post/how-to-solve-jsoup-does-not-get-complete-html-document/
            byte[] bytes = Jsoup.connect(linkUrl)
                .header("Accept-Encoding", "gzip, deflate")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                .referrer(URL_TO_PARSE)
                .ignoreContentType(true)
                .maxBodySize(0)
                .timeout(600000)
                .execute()
                .bodyAsBytes();
           
                try {
                    validateMP3File(bytes);
                   
                    String savedFileName = link.text();
                    if (!savedFileName.endsWith(".mp3")) savedFileName.concat(".mp3");
                    FileOutputStream fos = new FileOutputStream(savedFileName);
                    fos.write(bytes);
                    fos.close();
                   
                    System.out.println("File has been downloaded.");
                } catch (IOException e) {
                    System.err.println("Could not read the file at '" + linkUrl + "'.");
                }
                catch (InvalidFileTypeException e) {
                    System.err.println("'" + linkUrl + "' does not appear to point to an MP3 file.");
                }
        }
        else {
            System.out.println("Could not find the link ending with '" + LINK + "' in web page.");
        }
    }
   
    public static void validateMP3File(byte[] song) throws IOException, InvalidFileTypeException {
        InputStream file = new ByteArrayInputStream(song);
        byte[] startOfFile = new byte[6];
        file.read(startOfFile);
        String id3 = new String(startOfFile);
        //String tag = id3.substring(0, 3);
        if  ( ! "ID3".equals(id3) ) {
            throw new InvalidFileTypeException();
        }
    }
   
    //validateMP3File() is based on this method
    public static void getMP3Metadata(byte[] song) {
        try {
            InputStream file = new ByteArrayInputStream(song);
            int size = (int)song.length;
            byte[] startOfFile = new byte[1024];
            file.read(startOfFile);
            String id3 = new String(startOfFile);
            String tag = id3.substring(0, 3);
            if  ("ID3".equals(tag)) {
                System.out.println("Title: " + id3.substring(3, 32));
                System.out.println("Artist: " + id3.substring(33, 62));
                System.out.println("Album: " + id3.substring(63, 91));
                System.out.println("Year: " + id3.substring(93, 97));
            } else
                System.out.println("does not contain" + " ID3 information.");
            file.close();
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
        }
    }
}