package hearts;

/**
 *
 * @author nzs52
 */
public interface Constants {
        //Name of the host
    final static String hostName = "localhost";
        
    //Port number for the connection
    final static  int httpPortNumber = 4446;
        
    //Context - last part of URL for GET request
    final static String httpContext = "/MusicServer";
    
    //Path to the music file that is streamed
    final static String musicFilePath = "audio/Singham.wav";
    
    //Used to determine the size of the buffer 
    final static int numberOfFrames = 4096;

    //A setting in Java AudioFormat class
    final static int frameSize = 4;
    
    //Output debugging information for the client.
    final static boolean CLIENT_DEBUG = true;
    
    //Output debugging information for the server
    final static boolean SERVER_DEBUG = true;
    
    //Ouput debugging information that is not specific to client or server
    final static boolean DEBUG = true;    
}
