package hearts;

import java.net.*;
import java.io.*;

import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javafx.animation.*;
import javafx.application.*;
import static javafx.application.Application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.util.*;

/**
 *
 * @author lonewolf
 */
//public class GameUI {
//    //  private List<CardUI> my_list = new ArrayList<CardUI>();
//    // private List<CardUI> play_list = new ArrayList<CardUI>();
//
//    private Boolean waiting_user = true;
//    //  private GAME_MODE pick_card_mode;
//    private int picked_card_count;
//
//    private Stage frmHeartsGame;
//
//    private ScrollPane scrollPaneMessages;
//
//    private TextField textFieldMessage;
//    //   private List<String> listChat;
//
//    private Panel panelServer;
//    private Button btnLogin;
//    private Button btnStartServer;
//    private TextField textFieldPort;
//    private TextField textFieldAddress;
//    private TextField textFieldName;
//
//    private int card_width = 75;
//    private int card_height = 108;
//    private Button btnAvatarA; // just for fun
//    private Label lblNameA, lblScoreA, lblNameB, lblScoreB, lblNameC, lblScoreC, lblNameD, lblScoreD, lblNoti;
//    private Button btnCardA, btnCardB, btnCardC, btnCardD;
//
//    private String my_id; // A B C D
//    private Hearts client;
//    // private Server server;
//
//    private Boolean is_server = false;
//    private Boolean is_connected = false;
//
//    // private SpringLayout springLayout;
//    private BorderPane border = new BorderPane();
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//    public GameUI(){
//        initialize();
//      
//    }
//
//    private void initialize() {
//        frmHeartsGame = new Stage();
//        frmHeartsGame.setTitle("Hearts Gmae");
//       //  GridPane root = new GridPane();
//        root.setPrefSize(800, 600);
//        root.setAlignment(Pos.CENTER);        
//    }
//
//}
public class GameUI extends Application implements Runnable {

    Stage window;
    Scene scene, scene2;
    // private Menu Menu;
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
    private String my_id;
    private Boolean waiting_user = true;
    private Button player;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        GridPane root = new GridPane();
        root.setPrefSize(800, 600);
        root.setAlignment(Pos.CENTER);

        //background image
        InputStream is = Files.newInputStream(Paths.get("images/hearts bg1.jpg"));
        javafx.scene.image.Image img = new javafx.scene.image.Image(is);
        is.close();
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(800);
        imgView.setFitHeight(600);

        //     Menu = new .Menu();
        javafx.scene.control.Label label1 = new javafx.scene.control.Label("Hearts");

        root.getChildren().addAll(imgView);

        scene = new Scene(root);

        primaryStage.setTitle("Hearts");
        primaryStage.setScene(scene);
        primaryStage.show();
        


        initA();
        initB();
        initC();
        initD();
//
//        initNoti();
//
//        initChatPanel();
//        initServerPanel();

    }

//    public String my_id() {
//        return my_id;
//    }
//
//    public void my_id(String my_id) {
//        this.my_id = my_id;
//    }
//
//    public Boolean waiting_user() {
//        return waiting_user;
//    }
//
//    public void waiting_user(Boolean waiting_user) {
//        this.waiting_user = waiting_user;
//    }

//    public void ChatPanel() {
//
//        VBox messenger = new VBox();
//        messenger.setStyle("-fx-background-color: #fff;");
//        ScrollPane messages = new ScrollPane();
//
//    }

    @Override
    public void run() {

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

    public static void main(String[] args) {
        launch(args);

    }

    private void initA() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initC() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
