package UX;

import client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import server.DisscusionEngine;
import server.User;
import client.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;



public class app extends Application {

    private Stage primaryStage;
    private Scene signInScene;
    private Scene startMeetingScene;
    private Scene disccushScene;
    private problemSceneController disccushController;
    private SignInController signInController;
    private startMeetingController startMeetingController;
    User user;
    private DisscusionEngine de;
    private Client client;
    String hostIP;




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
                    if(signInController.getSaveGuestName()==null)
                        user = signInController.getUser();
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
        if(user== null)
            user = new User(signInController.getSaveGuestName(),new File("Server/src/resource/default_pic.jpg"));
        startMeetingController.setUserNameLabel(user.getName());
        startMeetingController.setUserImageView(user.getPic());
        this.primaryStage.setScene(startMeetingScene);
        hostIP = startMeetingController.getSaveID();

        startMeetingController.buttonClickedProperty().addListener((source)->{
            if(startMeetingController.isButtonClicked()){
                try {
                    de = new DisscusionEngine();
                    client = new Client(de,hostIP,12212);
                    initThirdWindow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        startMeetingController.hostbuttonClickedProperty().addListener((source)->{
            if(startMeetingController.isHostbuttonClicked()){
                try {
                    user.setHost(true);
                    de = new DisscusionEngine();
                    client = new Client(de,hostIP,12212);
                    initThirdWindow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void initThirdWindow() throws IOException{
        this.primaryStage.setTitle("Disccush!");
        FXMLLoader fl = new FXMLLoader();
        URL url = getClass().getResource("problemScene.fxml");
        fl.setLocation(url);
        fl.setBuilderFactory(new JavaFXBuilderFactory());
        Parent load = fl.load(url.openStream());
        disccushController = fl.getController();
        disccushController.setUser(user);
        disccushController.setClient(client);
        disccushScene = new Scene(load, 600, 600);
        this.primaryStage.setScene(disccushScene);
    }


}
