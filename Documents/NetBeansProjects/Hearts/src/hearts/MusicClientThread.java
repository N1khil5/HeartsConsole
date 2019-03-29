package hearts;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import javax.sound.sampled.*;
/**
 *
 * @author nzs52
 */
public class MusicClientThread extends Thread implements Constants{
        //The block that we are requesting from the server
    int blockNumber;
    
    //Buffer to receive data from server
    byte [] receivingBuffer;
    
    //Player used to play back streamed audio
    AudioPlayer audioPlayer;

    
    //Constructor
    MusicClientThread(){
        try{
            //Create an audio format that matches the sending format
            AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, frameSize, 44100, false);

            //Calculate the buffer size and build buffer

            int bufferSize = frameSize * numberOfFrames;
            receivingBuffer = new byte[bufferSize];

            //Create audio player
            audioPlayer = new AudioPlayer(audioFormat, bufferSize); 
            audioPlayer.play();
        }
        catch(LineUnavailableException ex){
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public void run(){
        blockNumber = 0;
        while(true){
            try{
                if(CLIENT_DEBUG) System.out.println("Getting music data for block: " + blockNumber);
                getMusicData();
                ++blockNumber;
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    
    /* Uses HTTP GET method to request the next chunk of audio data from music server */
    public void getMusicData(){
        //Create Apache HTTP client
        CloseableHttpClient httpClient= HttpClients.createDefault();
        
        try {
            //Build GET request
            HttpGet httpGet = new HttpGet("http://localhost:" + httpPortNumber + httpContext + "?" + blockNumber);
            
            //Contact server to request resource
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                //Check response and print it
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    if(entity != null){
                        InputStream stream = entity.getContent();
                            int bytesRead = stream.read(receivingBuffer);
                            if(CLIENT_DEBUG) System.out.println("Number of bytes read: " + bytesRead);
                            audioPlayer.addData(receivingBuffer);
                    }
                }
            } 
            finally {
                response.close();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            try{
                //Close the client
                httpClient.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
        
}
