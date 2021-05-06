package UX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import server.User;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;



public class app extends Application {

    private Stage primaryStage;
    private Scene signInScene;
    private Scene startMeetingScene;
    private Scene disccushScene;
    private SignInController signInController;
    private startMeetingController startMeetingController;
    //private I user; //TODO:
    User user;




    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initSignInScene();
        this.primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }


    private void initSignInScene() throws IOException{
        this.primaryStage.setTitle("Disccush Sign In");
        FXMLLoader fl = new FXMLLoader();
        URL url = getClass().getResource("signInScene.fxml");
        fl.setLocation(url);
        fl.setBuilderFactory(new JavaFXBuilderFactory());
        Parent load = fl.load(url.openStream());
        signInController = fl.getController();
        signInScene = new Scene(load, 600, 600);
        this.primaryStage.setScene(signInScene);
        signInController.continueClickedProperty().addListener((source)->{
            if(signInController.isContinueClicked()){
                try {
                    initStartMeetingScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//

        });
        signInController.signUpClickedProperty().addListener((source)->{
            if(signInController.isSignUpClicked()){
                try {
                    initStartMeetingScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initStartMeetingScene() throws IOException{
        this.primaryStage.setTitle("Disccush Start Meeting");
        FXMLLoader fl = new FXMLLoader();
        URL url = getClass().getResource("startMeeting.fxml");
        fl.setLocation(url);
        fl.setBuilderFactory(new JavaFXBuilderFactory());
        Parent load = fl.load(url.openStream());
        startMeetingController = fl.getController();
        startMeetingScene = new Scene(load, 600, 600);
         startMeetingController.setUserNameLabel(signInController.getSaveGuestName()); //TODO:
        this.primaryStage.setScene(startMeetingScene);
        startMeetingController.buttonClickedProperty().addListener((source)->{
            if(startMeetingController.isButtonClicked()){

            }
        });

    }


}
