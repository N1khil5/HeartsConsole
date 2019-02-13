/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts;
import java.io.*;
import javax.sound.sampled.*;
/**
 *
 * @author nzs52
 */
public class FileAudioStreamer implements Constants{
        public static void main(String[] args) {      
        try{
            //Open file containing music data
            String currentPath = new java.io.File(".").getCanonicalPath();
            String topDirectory = currentPath.substring(0, currentPath.indexOf("Hearts"));
            String filePath = topDirectory + "Hearts/" + musicFilePath;
            File musicFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);           

            //Sort out buffer size
            int bytesPerFrame = audioInputStream.getFormat().getFrameSize();
            if(bytesPerFrame == AudioSystem.NOT_SPECIFIED){
                //Some audio formats might have unspecified frame size, in that case we can read any amount of bytes
                bytesPerFrame = 1;
            }
            int bufferSize = bytesPerFrame * numberOfFrames;
            
            //Start audio player
            if(DEBUG) printFormat(audioInputStream.getFormat());
            AudioPlayer audioPlayer = new AudioPlayer(audioInputStream.getFormat(), bufferSize); 
            audioPlayer.play();

            //Create array to hold audio data
            byte[] audioByteArray = new byte[bufferSize];
            
            //Read audio data from file in bufferSize chunks
            int numBytesRead = 0;
            while((numBytesRead = audioInputStream.read(audioByteArray)) != -1){
                //Write to audio player
                audioPlayer.addData(audioByteArray);
             } 
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    

    /* Used to output the parameters of the music format, to enable 
        this to be set manually when streaming */
    private static void printFormat(AudioFormat audioFormat){
        System.out.println("Encoding: " + audioFormat.getEncoding().toString());
        System.out.println("Sample rate: " + audioFormat.getSampleRate());
        System.out.println("Sample size in bits: " + audioFormat.getSampleSizeInBits());
        System.out.println("Channels: " + audioFormat.getChannels());
        System.out.println("Frame size: " + audioFormat.getFrameSize());
        System.out.println("Frame rate: " + audioFormat.getFrameRate());
        System.out.println("Big Endian: " + audioFormat.isBigEndian());
    }    
}
