/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
