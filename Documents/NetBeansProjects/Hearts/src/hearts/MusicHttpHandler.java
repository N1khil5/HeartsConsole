/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author nzs52
 */
public class MusicHttpHandler implements HttpHandler, Constants{
        //HTTP status code
    private static final int HTTP_OK_STATUS = 200;
    
    //Byte array holding data
    byte[] musicData;
    
    //Size of buffer to be sent with each HTTP message
    int bufferSize= frameSize * numberOfFrames;
    
    int numberOfBlocks;
    
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //Get request method and handle appropriately
        String reqMethod = exchange.getRequestMethod();
        if(!reqMethod.equals("GET"))
            System.err.println("Not GET: " + reqMethod);
        
        //Find out which block number is requested
        String queryStr = exchange.getRequestURI().getQuery();
        int blockNumber = Integer.parseInt(queryStr);
        
        //Check that block is within range
        blockNumber = blockNumber % numberOfBlocks;
        if(SERVER_DEBUG) System.out.println("Block number: " + blockNumber);
        
        //Set response header
        exchange.sendResponseHeaders(HTTP_OK_STATUS, bufferSize);
               
        //Write the response string
        OutputStream outStr = exchange.getResponseBody();
        outStr.write(musicData, blockNumber * bufferSize, bufferSize);
                
        //Close output stream
        outStr.close();
    }
    
    public void loadData(String fileName) throws UnsupportedAudioFileException, IOException {
        //Open file containing music data
        File musicFile = new File(fileName);
        if(SERVER_DEBUG) System.out.println("Music file size: " + musicFile.length());
        musicData = new byte[(int)musicFile.length()];
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);    
        audioInputStream.read(musicData);
        if(SERVER_DEBUG) System.out.println("MusicHttpHandler: Music data loaded.");
        
        //Calculate the number of blocks in the sample
        numberOfBlocks = musicData.length / bufferSize;
    }
        
}
