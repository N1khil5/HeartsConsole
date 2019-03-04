/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author nzs52
 */
public class Hearts extends Application {

    Stage window;
    Scene scene, scene2;
    private Menu Menu;
    String portNumber = "8000";
        // The default host.
    String host = "10.2.147.44";
    
        // The client socket
    private static Socket clientSocket = null;
    // The output stream
    private static PrintStream os = null;
    // The input stream
    private static DataInputStream is = null;

    private static BufferedReader inputLine = null;
    private static boolean closed = false;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        window = primaryStage;
        
        GridPane root = new GridPane();
        root.setPrefSize(800,600);
        root.setAlignment(Pos.CENTER);
      
        //background image
        InputStream is = Files.newInputStream(Paths.get("images/hearts bg1.jpg"));
        Image img = new Image(is);
        is.close();
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(800);
        imgView.setFitHeight(600);
        
        Menu = new Menu();
        
        Label label1 = new Label("Hearts");
        
        root.getChildren().addAll(imgView, Menu);
        
        scene = new Scene(root);

        primaryStage.setTitle("Hearts");
        primaryStage.setScene(scene); 
        primaryStage.show();
        
    }
    
    private class Menu extends Parent {
        public Menu() {
            
            VBox menu0 = new VBox(80);
            VBox menu1 = new VBox(80);
            VBox menurules = new VBox(80);
            VBox menujoin = new VBox(40);
            
            menu0.setTranslateX(270);
            menu0.setTranslateY(100);
            
            menu1.setTranslateX(270);
            menu1.setTranslateY(230); 
            
            menurules.setTranslateX(270);
            menurules.setTranslateY(100);
            
            menujoin.setTranslateX(270);
            menujoin.setTranslateY(100);
 
            MenuButton btnPlay = new MenuButton("Play");
            btnPlay.setOnMouseClicked(event ->  { 
                getChildren().remove(menu0);
                getChildren().add(menu1);
            });
            
            MenuButton btnHelp = new MenuButton("Help");
            btnHelp.setOnMouseClicked(event -> {
                getChildren().remove(menu0);  
                getChildren().add(menurules);
            });
            
            MenuButton btnExit = new MenuButton("Exit");
            btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });
            
            MenuButton btnBack = new MenuButton("Back");
            btnBack.setOnMouseClicked(event -> {
                getChildren().add(menu0);
                getChildren().remove(menu1);
                getChildren().remove(menurules);
            });
            
            MenuButton btnBack2 = new MenuButton("Back");
            btnBack2.setOnMouseClicked(event -> {
                getChildren().add(menu0);
                getChildren().remove(menu1);
                getChildren().remove(menurules);
            });
            
            MenuButton btnBack3 = new MenuButton("Back");
            btnBack3.setOnMouseClicked(event -> {
                getChildren().remove(menujoin);
                getChildren().add(menu1);
            });
            
            MenuButton btnJaG = new MenuButton("Join a game");
            btnJaG.setOnMouseClicked(event -> {  
                getChildren().add(menujoin);
                getChildren().remove(menu1);    
            });
            
            
            Label label = new Label ("Hearts");
            label.setTextFill(Color.web("#FFFFFF"));
            label.setFont(Font.font("Cambria", 90));
            
            Label rules = new Label ("Rules");
            rules.setTextFill(Color.web("#FFFFFF"));
            rules.setFont(Font.font("Cambria", 20));
            
            //Add rule document from file.
            Label ruleText = new Label ("rules/rules.txt");
            ruleText.setWrapText(true);
            ruleText.setTextFill(Color.web("#FFFFFF"));
            rules.setFont(Font.font("Cambria",10));
            
            TextField IP = new TextField(host);
            
            TextField Port = new TextField(portNumber);
            
            TextField Name = new TextField("Enter player name");
            
            
            
            Button enter = new Button("Enter");
                 enter.setOnAction(action -> {
                 System.out.println(IP.getText());
                 });

            //main menu
            menu0.getChildren().addAll(label, btnPlay, btnHelp, btnExit);
            
            // play menu
            menu1.getChildren().addAll(btnJaG, btnBack);
            
            //help menu (implement how to connect + rules of game)
            menurules.getChildren().addAll(ruleText, btnBack2);
            
            //join a game screen
            menujoin.getChildren().addAll(IP, Port, Name, enter, btnBack3);
            
            Rectangle bg = new Rectangle(800, 600);
            bg.setFill(Color.GREY);
            bg.setOpacity(0.4);
            
            getChildren().addAll(bg, menu0);   
        }
    }
  
    private static final String WORDS = 
            "The 52 cards are shuffled and are distributed evenly between the four players (13 cards per player). \n" +
            "The game of Hearts allows for cards to be exchanged in a certain order at the start of each round. \n" + 
            "They are as follows: \n" + 
            "Round 1: Each player must select 3 cards to give to the player on their left. \n" + 
            "Round 2: Each player must select 3 cards to give to the player on their right.\n" + 
            "Round 3: Each player must select 3 cards to give to the player opposite them.\n" + 
            "Round 4: The round starts without exchanging cards.\n" + 
            "Round 5: Same as round 1 and the pattern follows. \n" + 
            " After the cards are exchanged, the user with 2♣ plays that card in the middle of the table." + 
            " The next player is the player to the left and so on until all 4 players have played a card." + 
            " This is known as a ‘trick’. The person who plays the highest ranked card, takes the cards." + 
            " For example, if player 1 plays ‘2♣’, player 2 plays ‘K♣’," + 
            " player 3 follows with ‘10♣’ and player 4 plays ‘A♣’, then player 4 takes the cards." + 
            " Players must follow suit of the card that leads off the trick unless the player does not possess that card." + 
            " The cards at the end of the trick are not usable for the rest of the round, but their point values are added to the user who collects them." + 
            " Since Clubs ♣, Diamonds ♦ and Spades ♠ (except for Q♠) don’t have any points attributed to them, collecting these cards will not increase a player score." +
            " The player who wins the trick, leads off the next trick and so on until all 52 cards are used. \n" +
            " No point cards allowed in the first round: At the start of the game, none of the users can play the Q♠ or any Hearts ♥ cards." +
            " For example, if a round starts and one of the 4 players does not have the clubs," +
            " they can play any other suit cards which do not have any point values. \n " + 
            " Hearts cannot lead till broken: After the first trick is completed," + 
            "the player who wins the trick leads the next one with any card except for Hearts." + 
            "If another player who is not leading the trick cannot follow suit," + 
            "they can break the trick with any hearts card." + 
            " For example, if the player leading the trick plays ‘9♣’, the next user plays ‘6♣’ " + 
            "and if the third player cannot follow suit (does not have a ♣ clubs card to continue the trick)," + 
            " they can play a card from any suit, even a Hearts ♥ card." +
            "Shoot the moon: If at the end of the round, a player collects all the point value cards (All the ♥ cards and the Q♠)," + 
            " then the player has shot the moon. At the end of this round, the player who shot the moon has the choice to either" + 
            "remove 26 points from his score (if his score is above 26), or to increase the score of all the other players by 26. ";
    
    private static class MenuButton extends StackPane {
        
       private Text text;
       
       public MenuButton(String name) {
           text = new Text(name);
           text.setFont(text.getFont().font(20));
           text.setFill(Color.WHITE);
           
           Rectangle bg = new Rectangle(250, 30);
           bg.setOpacity(0.6);
           bg.setFill(Color.BLACK);
           bg.setEffect(new GaussianBlur(3.5));
           
           setAlignment (Pos.CENTER);
           getChildren().addAll(bg, text);
           
           setOnMouseEntered(event -> {
           //    bg.setTranslateX(10);
           //    text.setTranslateX(10);
             bg.setFill(Color.WHITE);
             text.setFill(Color.BLACK);   
           });
            
          setOnMouseExited(event -> {
        //   bg.setTranslateX(0);
        //   text.setTranslateX(0);
           bg.setFill(Color.BLACK);
           text.setFill(Color.WHITE);
          });
           
           DropShadow drop = new DropShadow(50, Color.WHITE);
           drop.setInput(new Glow());
           
           setOnMousePressed(event -> setEffect(drop));
           setOnMouseReleased(event -> setEffect(null));    
       }   
    }

    public static void main(String[] args) {
        launch(args);
                // The default port.
        int portNumber = 8000;
        // The default host.
        String host = "10.2.147.44";

        if (args.length < 2) {
            System.out.println("Usage: java MultiThreadChatClient <host> <portNumber>\n" + "Now using host=" + host + ", portNumber=" + portNumber);
        } else {
            host = args[0];
            portNumber = Integer.valueOf(args[1]).intValue();
        }

        //Open a socket on a given host and port. Open input and output streams.
        try {
            //clientSocket = new Socket(host, portNumber);
            clientSocket = new Socket(InetAddress.getByName(host),portNumber);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host "
                    + host);
        }

        //If everything has been initialized then we want to write some data to the socket we have opened a connection to on the port portNumber.
        if (clientSocket != null && os != null && is != null) {
            try {

                // Create a thread to read from the server. 
                new Thread((Runnable) new Hearts()).start();
                while (!closed) {
                    os.println(inputLine.readLine().trim());
                }
                //Close the output stream, close the input stream, close the socket.

                os.close();
                is.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }

    //Create a thread to read from the server. 
    public void run() {
        //Keep on reading from the socket till we receive "Bye" from the server. Once we received that then we want to break.

        String responseLine;
        try {
            while ((responseLine = is.readLine()) != null) {
                System.out.println(responseLine);
                if (responseLine.indexOf("*** Bye") != -1) {
                    break;
                }
            }
            closed = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }

    }
    