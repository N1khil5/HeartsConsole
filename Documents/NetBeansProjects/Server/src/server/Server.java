/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;
import java.net.*;

/**
 *
 * @author lonewolf
 */
public class Server {

    // The server socket.
    private static ServerSocket serverSocket;
    // The client socket.
    private static Socket clientSocket;

    // This chat server can accept up to maxClientsCount clients' connections.
    private static final int maxClientsCount = 4;
    private static final clientThread[] threads = new clientThread[maxClientsCount];

    public static void main(String args[]) {

        // The default port number.
        int portNumber = 2000;
        if (args.length < 1) {
            System.out
                    .println("Usage: java MultiThreadChatServer <portNumber>\n"
                            + "Now using port number=" + portNumber);
        } else {
            portNumber = Integer.valueOf(args[0]).intValue();
        }

        //Open a server socket on the portNumber 8000.
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println(e);
        }

        //Create a client socket for each connection and pass it to a new client thread.
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("CONECTED");
                int i = 0;
                for (i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new clientThread(clientSocket, threads)).start();
                        break;
                    }
                }
                if (i == maxClientsCount) {
                    PrintStream os = new PrintStream(clientSocket.getOutputStream());
                    os.println("Server too busy. Try later.");
                    os.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

// The chat client thread. This client thread opens the input and the output streams for a particular client, ask the client's name, 
//informs all the clients connected to the server about the new client and as long as it receive data, echos that data back to all  other clients. 
//When a client leaves the chat room this thread informs also all the clients about that and terminates.
class clientThread extends Thread {

    private DataInputStream is;
    private PrintStream os;
    private InputStreamReader isr;
    private Socket clientSocket;
    private final clientThread[] threads;
    private int maxClientsCount;

    public clientThread(Socket clientSocket, clientThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClientsCount = threads.length;
    }

    public void run() {
        System.out.println("thread started");
        int maxClientsCount = this.maxClientsCount;
        clientThread[] threads = this.threads;

        try {
            // Create input and output streams for this client.
            is = new DataInputStream(clientSocket.getInputStream());
            isr = new InputStreamReader(is);

            os = new PrintStream(clientSocket.getOutputStream());
            //System.out.println(isr.toString());
            String name = is.readUTF();
            System.out.println(name);
            //String name = Integer.toString(isr.read());
            //System.out.println("1"+name);
            for (int i = 0; i < maxClientsCount; i++) {
                if (threads[i] != null && threads[i] != this) {
                    threads[i].os.println("*** A new user " + name + " entered the chat room !!! ***");
                }
            }
            System.out.println("*** A new user " + name + " entered the chat room !!! ***");
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine().trim();
                if (line.startsWith("/quit")) {
                    break;
                }
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null) {
                        threads[i].os.println("<" + name + ">: " + line);
                    }
                }
                System.out.println("<" + name + ">: " + line);
            }
            for (int i = 0; i < maxClientsCount; i++) {
                if (threads[i] != null && threads[i] != this) {
                    threads[i].os.println("*** The user " + name + " is leaving the chat room !!! ***");
                }
            }

            os.println("*** Bye " + name + " ***");
            System.out.println("*** Bye " + name + " ***");

            // Clean up. Set the current thread variable to null so that a new client could be accepted by the server.
            for (int i = 0; i < maxClientsCount; i++) {
                if (threads[i] == this) {
                    threads[i] = null;
                }
            }

            //Close the output stream, close the input stream, close the socket.
            is.close();
            os.close();
            clientSocket.close();
        } catch (IOException e) {
        }
    }

}
