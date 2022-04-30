package net.kiranatos.sandbox.ex01;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.Response;

/*
source: https://www.baeldung.com/java-download-file
*/

public class DownloadTests {
    private static final String FILE_NAME = "wfiles/demo06/result.jpg";
    private static final String USER_AGENT_1 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:98.0) Gecko/20100101 Firefox/98.0";
    private static final String USER_AGENT_2 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:91.0) Gecko/20100101 Firefox/91.0 Waterfox/91.7.0";
    
    //private static final String IMAGE_URL = "https://cdn.sex.com/images/pinporn/2021/02/21/24626155.gif?width=620";
    //private static final String IMAGE_URL = "https://cdn.sex.com/images/pinporn/2021/01/10/24355095.jpg?width=620&site=sex&user=kaloo781";    
    private static final String IMAGE_URL = "https://www.supercars.net/blog/wp-content/uploads/2016/03/1035636.jpg";
    
    public static void main(String[] args) throws IOException {
        getImagesVariant6(IMAGE_URL);
    }
    
    private static void getImagesVariant6(String FILE_URL) throws IOException {        
        AsyncHttpClient client = Dsl.asyncHttpClient();
        FileOutputStream stream = new FileOutputStream(FILE_NAME);
        
        client.prepareGet(FILE_URL).execute(new AsyncCompletionHandler<FileOutputStream>() {
            @Override
            public State onBodyPartReceived(HttpResponseBodyPart bodyPart) 
                    throws Exception {
                stream.getChannel().write(bodyPart.getBodyByteBuffer());
                return State.CONTINUE;
            }
            @Override
            public FileOutputStream onCompleted(Response response) throws Exception {
                return stream;
            }
        });
        //client.close();
    }
   
    private static void getImagesVariant5(String FILE_URL) throws IOException {
        ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(FILE_URL).openStream());
        
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
        FileChannel fileChannel = fileOutputStream.getChannel();
        
        fileOutputStream.getChannel()
                .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);                
    }
    
    private static void getImagesVariant4(String FILE_URL) throws IOException {        
        HttpURLConnection httpcon = (HttpURLConnection) new URL(FILE_URL).openConnection();
        httpcon.addRequestProperty("User-Agent", USER_AGENT_1);
        InputStream in = httpcon.getInputStream();
        Files.copy(in, Paths.get(FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
    }
    
    private static void getImagesVariant3(String FILE_URL) throws IOException {
        InputStream in = new URL(FILE_URL).openStream();
        Files.copy(in, Paths.get(FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
    }    
    
    private static void getImagesVariant2(String FILE_URL) throws IOException {
        try ( BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME) ) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) { // handle exception
            System.out.println(e);            
        }
    }
        
    private static void getImagesVariant1(String src) throws IOException {                 
        URL url = new URL(src);
        InputStream in = url.openStream();
        //BufferedInputStream va = new BufferedInputStream(in);
 
        OutputStream out = new BufferedOutputStream(new FileOutputStream(FILE_NAME));
 
        byte[] b = new byte[2048];
        int length;
        while ((length = in.read(b)) != -1) {
            out.write(b, 0, length);
        }
        
        out.close();
        in.close(); 
        
        System.out.println("file saved: " + FILE_NAME);
    }
}
