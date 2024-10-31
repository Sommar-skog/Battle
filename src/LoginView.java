import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LoginView extends Application{//Sara har skapat klassen och kodat in det i

    public Stage window;
    private Button clientButton;
    private Button serverButton;
    private Scene loginView;
    private final Text choosePlayer = new Text("Choose player");
    private final Text titel = new Text("Battleship");

    //@Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setResizable(false);
        window.setTitle("Login View");

        choosePlayer.setFill(Color.WHITE);
        choosePlayer.setFont(Font.font("Arial", 30));

        titel.setFill(Color.WHITE);
        titel.setFont(Font.font("Arial",30));


        clientButton = new Button();
        clientButton.setText("Player 1");
        clientButton.setTextFill(Color.BLUE);
        clientButton.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        clientButton.setFont(Font.font("Courier New", 14));

        serverButton = new Button();
        serverButton.setText("Player 2");
        serverButton.setTextFill(Color.GREEN);
        serverButton.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        serverButton.setFont(Font.font("Courier New",14));


/*
        clientButton.setOnAction(e->{
            System.out.println("Creating Socket");
            try{
                Socket socketClient = new Socket("localhost", 8886);

                //Communication clientCom = new Communication(socketClient);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        serverButton.setOnAction(e->{
            System.out.println("Creating serverSocket");
            try(ServerSocket serverSocket = new ServerSocket(8886)){
                Socket socketServer = serverSocket.accept();
                //Communication clientCom = new Communication(socketServer);

            }catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
*/

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(choosePlayer, clientButton, serverButton, titel);
        Image startScreen = new Image(getClass().getResourceAsStream("sprite_0.png"));
        //Kan inte lägga till någon annan bild alls, då kraschar det....
        //Image startScreen = new Image(getClass().getResourceAsStream("sprite_1.png"));




        anchorPane.setBackground(
                new Background(
                        new BackgroundImage(
                                startScreen,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(500,500, false,false,false,false)
                        )
                )
        );



        titel.setLayoutX(180);
        titel.setLayoutY(100);

        choosePlayer.setLayoutX(150);
        choosePlayer.setLayoutY(140);

        clientButton.setLayoutX(160);
        clientButton.setLayoutY(200);

        serverButton.setLayoutX(260);
        serverButton.setLayoutY(200);


        loginView = new Scene(anchorPane, 500,500);
        window.setScene(loginView);
        window.show();


    }

   
}
