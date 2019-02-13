/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts;

import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author nzs52
 */
public class ClipPlayer extends Thread implements Constants {

    public static void main(String[] args) {
        AudioFileFormat.Type[] arr = AudioSystem.getAudioFileTypes();
        for (AudioFileFormat.Type type : arr) {
            System.out.println("TYPE: " + type.toString());
        }
        //Start ClipPlayer running
        ClipPlayer player = new ClipPlayer();
        player.start();
    }

    @Override
    public void run() {
        try {
            //Open file containing music data
            String currentPath = new java.io.File(".").getCanonicalPath();
            String topDirectory = currentPath.substring(0, currentPath.indexOf("Hearts"));
            String filePath = topDirectory + "Hearts/" + musicFilePath;
            File musicFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);

            //Create a clip and start it
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            //Loop thread until clip has finished playing
            while (true) {
                sleep(1000);
                System.out.println("Clip player running: " + clip.isRunning());

                //Exit thread when clip player is finished
                if (!clip.isRunning()) {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
