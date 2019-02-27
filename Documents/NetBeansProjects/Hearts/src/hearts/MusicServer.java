/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author nzs52
 */
public class MusicServer implements Constants {

    public static void main(String[] args) {

        try {
            //Internet address of host
            InetSocketAddress hostAddress = new InetSocketAddress(hostName, httpPortNumber);

            //Create HttpServer which is listening on the given port
            HttpServer httpServer = HttpServer.create(hostAddress, 0);

            //Create the handler for the /MusicServer context
            MusicHttpHandler musicHandler = new MusicHttpHandler();

            //Load up music file into memory - expensive operation, but an easy way to make it work.
            String currentPath = new java.io.File(".").getCanonicalPath();
            String topDirectory = currentPath.substring(0, currentPath.indexOf("Hearts"));
            String filePath = topDirectory + "Hearts/" + musicFilePath;
            musicHandler.loadData(filePath);
            
            //Create a new context and handler for this context
            httpServer.createContext(httpContext, musicHandler);

            //Start the ser
            httpServer.start();
            System.out.println("Server has started. Type 'stop' to shut it down.");

            //Read input from user.
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            while (!userInput.equals("stop")) {
                userInput = scanner.nextLine();
            }

            //Shut the server down
            httpServer.stop(0);
            if (SERVER_DEBUG) {
                System.out.println("Server has shut down.");
            }
        } catch (UnsupportedAudioFileException | IOException ex) {
            ex.printStackTrace();
        }
    }

}
