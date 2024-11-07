package com.battleship.graphic;

import com.battleship.CommunicationHandler;
import com.battleship.Game;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//GB-15-SA
public class SceneClient {
    public static Scene scene;

    private static TextField host;
    private static TextField port1;
    private static Button submit1;
    private static Button back1;
    private static Text player1Label;


    //GB-15-SA
    public static Scene getScene(Stage primaryStage) {
        LoginView login = new LoginView();

        //Skapat textField där användaren kan skriva in host och port
        host = new TextField();
        host.setPromptText("host");
        host.setPrefSize(100,20);

        port1 = new TextField();
        port1.setPromptText("port");
        port1.setPrefSize(100,20);

        //Knappar för att samla infon från TextFields eller om man vill gå tillbaka
        submit1 = new Button("Submit");
        submit1.getStyleClass().add("button-standard");

        back1 = new Button();
        back1.setText("Back");
        back1.getStyleClass().add("button-standard");

        //Action för submit och back knapparna
        submit1.setOnAction(e->{

            System.out.println("Submit");

            if(login.isInt(port1, port1.getText())){
                //Denna CommunicationHandler ska ha vilken spelare det är, host och port

                CommunicationHandler communicationHandler = new CommunicationHandler(login.whichPlayer(1), host.getText(), Integer.parseInt(port1.getText()));
                host.clear();
                port1.clear();
                Game game = new Game(communicationHandler, true);
                game.startGame();

            }else if (!login.isInt(port1, port1.getText())){
                System.out.println("Can't play at that port");
            }



        });
        //Går tillbaka till start
        back1.setOnAction(e->{
           goBack(login, primaryStage);

        });



        //Scene-Client
        //Skapar gridpane
        GridPane gridPane1 = new GridPane();
        gridPane1.setPadding(new Insets(10));
        gridPane1.setVgap(8);
        gridPane1.setHgap(10);

        //Skapar text för vad användaren ska skriva in
        player1Label = new Text();
        player1Label.setText("Enter host and port");
        player1Label.getStyleClass().add("titel-small");

        //Sätter alla nodes på gridpane
        gridPane1.getChildren().addAll(player1Label,host, port1, submit1, back1);

        //Sätter ut placering på allt
        GridPane.setConstraints(player1Label, login.COLUMN, 15);
        GridPane.setConstraints(host, login.COLUMN, 17);
        GridPane.setConstraints(port1, login.COLUMN, 19);
        GridPane.setConstraints(submit1, login.COLUMN, 21);
        GridPane.setConstraints(back1, login.COLUMN,23);

        //Sätter bakgrund
        gridPane1.setBackground(
                new Background(
                        new BackgroundImage(
                                login.startBackground,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(login.windowSizeWidth,login.windowSizeHeight, false,false,false,false)
                        )
                )
        );

        //Skapar scenen och ger den Stylesheet
        scene = new Scene(gridPane1, login.windowSizeWidth, login.windowSizeHeight);
        scene.getStylesheets().add("com/battleship/graphic/BattleShip.css");


        return scene;
    }

    //GB-15-SA
    //Om man vill gå tillbaka
    private static void goBack(LoginView login, Stage window) {
        try{
            //Tar klassen LoginView, metoden "start" och sätter igång "window" vilket är primaryStage medskickat från LoginView
            login.start(window);
            window.setFullScreen(true);

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
