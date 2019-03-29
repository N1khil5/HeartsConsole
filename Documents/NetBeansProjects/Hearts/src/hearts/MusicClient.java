package hearts;

/**
 *
 * @author nzs52
 */
public class MusicClient {
    public static void main(String[] args) {
        MusicClientThread musicClientThread = new MusicClientThread();
        musicClientThread.start();
    }    
}
