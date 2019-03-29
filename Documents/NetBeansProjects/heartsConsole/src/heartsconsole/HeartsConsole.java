/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartsconsole;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author nzs52
 */
public class HeartsConsole {

    /**
     * @param args the command line arguments
     */
    int portNumber = 2000;
    // The default host.
    String host = "localhost";

    // The client socket
    private static Socket clientSocket = null;
    // The output stream
    private static OutputStream os = null;
    private static DataOutputStream dos = null;
    // The input stream
    private static DataInputStream is = null;

    private static BufferedReader inputLine = null;
    private static boolean closed = false;

    public static void main(String[] args) {
        // TODO code application logic here
        // The default port.
        int portNumber = 2000;
        // The default host.
        String host = "localhost";
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to Hearts Game.");
        System.out.println("Please select from the options below");
        while (true) {
            System.out.println("1. Play Hearts");
            System.out.println("2.Rules");
            System.out.println("3.Exit");
            int option = s.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter your name");
                    String[] nPlayer = new String[4];
                    String userName = s.next();
                    int playerScore[] = {0,0,0,0};
                    //Open a socket on a given host and port. Open input and output streams.
                    for (int i = 0; i < 4; i++) {
                        try {
                            clientSocket = new Socket(InetAddress.getByName(host), portNumber);
                            //inputLine = new BufferedReader(new InputStreamReader(System.in));
                            os = clientSocket.getOutputStream();
                            //PrintWriter pw = new PrintWriter(os);
                            dos = new DataOutputStream(os);
                            dos.writeUTF(userName);
                            dos.flush();
                            is = new DataInputStream(clientSocket.getInputStream());
                        } catch (UnknownHostException e) {
                            System.err.println("Don't know about host " + host);
                        } catch (IOException e) {
                            System.err.println("Couldn't get I/O for the connection to the host "
                                    + host);
                        }
                    }
                    
                    
                    break;
                case 2:
                    System.out.println("You have selected rules.");
                    System.out.println("The 52 cards are shuffled and are distributed evenly between the four players (13 cards per player). \n"
                            + "The game of Hearts allows for cards to be exchanged in a certain order at the start of each round. \n"
                            + "They are as follows: \n"
                            + "Round 1: Each player must select 3 cards to give to the player on their left. \n"
                            + "Round 2: Each player must select 3 cards to give to the player on their right.\n"
                            + "Round 3: Each player must select 3 cards to give to the player opposite them.\n"
                            + "Round 4: The round starts without exchanging cards.\n"
                            + "Round 5: Same as round 1 and the pattern follows. \n"
                            + " After the cards are exchanged, the user with 2? plays that card in the middle of the table."
                            + " The next player is the player to the left and so on until all 4 players have played a card."
                            + " This is known as a ‘trick’. The person who plays the highest ranked card, takes the cards."
                            + " For example, if player 1 plays ‘2?’, player 2 plays ‘K?’,"
                            + " player 3 follows with ‘10?’ and player 4 plays ‘A?’, then player 4 takes the cards."
                            + " Players must follow suit of the card that leads off the trick unless the player does not possess that card."
                            + " The cards at the end of the trick are not usable for the rest of the round, but their point values are added to the user who collects them."
                            + " Since Clubs ?, Diamonds ? and Spades ? (except for Q?) don’t have any points attributed to them, collecting these cards will not increase a player score."
                            + " The player who wins the trick, leads off the next trick and so on until all 52 cards are used. \n"
                            + " No point cards allowed in the first round: At the start of the game, none of the users can play the Q? or any Hearts ? cards."
                            + " For example, if a round starts and one of the 4 players does not have the clubs,"
                            + " they can play any other suit cards which do not have any point values. \n "
                            + " Hearts cannot lead till broken: After the first trick is completed,"
                            + "the player who wins the trick leads the next one with any card except for Hearts."
                            + "If another player who is not leading the trick cannot follow suit,"
                            + "they can break the trick with any hearts card."
                            + " For example, if the player leading the trick plays ‘9?’ and the next user plays ‘6?’ "
                            + "and if the third player cannot follow suit (does not have a ? clubs card to continue the trick),"
                            + " they can play a card from any suit, even a Hearts ? card."
                            + "Shoot the moon: If at the end of the round, a player collects all the point value cards (All the ? cards and the Q?),"
                            + " then the player has shot the moon. At the end of this round, the player who shot the moon has the choice to either"
                            + "remove 26 points from his score (if his score is above 26), or to increase the score of all the other players by 26. ");
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Enter a valid option");
                    break;
            }
        }
    }
}
