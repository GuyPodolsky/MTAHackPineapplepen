package UX;

import client.Client;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import server.User;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    public Button guestButton;
    public Button userButton;
    public TextField guestName;
    public BorderPane signInPane;
    public Button continueButton;
    public TextField firstTimeName;
    public Button choosePhotoButton;
    public Button signUpButton;
    private BooleanProperty continueClicked = new SimpleBooleanProperty(this, "User connected");
    private BooleanProperty photoChooserClicked = new SimpleBooleanProperty(this, "User connected");
    private BooleanProperty signUpClicked = new SimpleBooleanProperty(this, "User connected");
    private String saveGuestName;
    private File savePhotoFilePath;
    private User user;
    private Client client;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //guestName.selectedTextProperty().addListener((source)->{continueButton.setDisable(false);});
        //guestName.getProperties().addListener((source)->{if(!guestName.getText().isEmpty()){continueButton.setDisable(false);});
     //guestName.textProperty()
    }
    public void guestButtonClicked(ActionEvent actionEvent) {
        userButton.setDisable(true);
        guestButton.setDisable(true);
        guestName.setVisible(true);
        continueButton.setDisable(true);
        guestName.textProperty().addListener((source)->{if(!guestName.getText().isEmpty()) continueButton.setDisable(false);
        else continueButton.setDisable(true);});
        continueButton.setVisible(true);
        // enter the new user to the system
        // start the next scene
    }

    public void continueClicked(ActionEvent actionEvent) {
        // save the user name
        if(!guestName.getText().isEmpty()) {
            saveGuestName = guestName.getText();
            setContinueClicked(true);
        }
    }

    public boolean isContinueClicked() {
        return continueClicked.get();
    }

    public BooleanProperty continueClickedProperty() {
        return continueClicked;
    }

    public void setContinueClicked(boolean continueClicked) {
        this.continueClicked.set(continueClicked);
    }

    public void userButtonClicked(ActionEvent actionEvent) throws IOException {
        try {
            InputStream stream = getClass().getResource("Server\\src\\resource\\saver.bin").openStream();
            user = new User(null);
        }catch (Exception e){
            // TODO: the first time entering to the system
            firstTimeName.setVisible(true);
            choosePhotoButton.setVisible(true);
            signUpButton.setDisable(true);
            firstTimeName.textProperty().addListener((source)->{if(!firstTimeName.getText().isEmpty()) signUpButton.setDisable(false);
            else signUpButton.setDisable(true);});
            signUpButton.setVisible(true);
        } finally {
            userButton.setDisable(true);
            guestButton.setDisable(true);
        }


    }

    public String getSaveGuestName(){return saveGuestName;}

    public void choosePhotoButtonClicked(ActionEvent actionEvent) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                //new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            ///
            setSavePhotoFilePath(selectedFile);
        }

        setPhotoChooserClicked(true);
    }

    public void signUpButtonClicked(ActionEvent actionEvent) {
        if(!firstTimeName.getText().isEmpty()) {
            // here I save the File???????
            user = new User(firstTimeName.getText(),savePhotoFilePath);
            setSignUpClicked(true);
        }
    }

    public boolean isPhotoChooserClicked() {
        return photoChooserClicked.get();
    }

    public BooleanProperty photoChooserClickedProperty() {
        return photoChooserClicked;
    }

    public void setPhotoChooserClicked(boolean photoChooserClicked) {
        this.photoChooserClicked.set(photoChooserClicked);
    }

    public boolean isSignUpClicked() {
        return signUpClicked.get();
    }

    public BooleanProperty signUpClickedProperty() {
        return signUpClicked;
    }

    public void setSignUpClicked(boolean signUpClicked) {
        this.signUpClicked.set(signUpClicked);
    }

    public void setSavePhotoFilePath(File savePhotoFilePath) {
        this.savePhotoFilePath = savePhotoFilePath;
    }

    public User getUser(){return user;}

    public int getFromMeetIDPort(String meetID)
    {
        String strArr[]  = meetID.split(":");
        return Integer.parseInt(strArr[1]);
    }

    public String getFromMeetIDIP(String meetID){
        String strArr[] = meetID.split(":");
        return strArr[0];
    }
}
