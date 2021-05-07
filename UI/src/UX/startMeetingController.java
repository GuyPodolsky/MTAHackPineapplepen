package UX;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import java.net.URL;
import java.util.ResourceBundle;

public class startMeetingController implements Initializable {


    public Button startNewMeetingButton;
    public Button joinMeetingButton;
    public Button scheduleButton;
    public Label userNameLabel;
    public Button joinButton;
    public TextField meetingID;
    public Text getNewMeetingID;
    public Button reallyStartNewMeetingButton;
    public ImageView userImageView;
    private BooleanProperty buttonClicked = new SimpleBooleanProperty(this, "Button clicked");
    private BooleanProperty HostbuttonClicked = new SimpleBooleanProperty(this, "Button clicked");
    private String saveID;



    public String getSaveID() {
        return saveID;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startNewMeetingClicked(ActionEvent actionEvent) throws UnknownHostException {
        if(getNewMeetingID.getText().equals("Meeting ID : ")) {
            // set an meeting id

            saveID =getSysIp();
            getNewMeetingID.setText(getNewMeetingID.getText() + saveID);
            getNewMeetingID.getStyleClass().add("copyable-label");
            getNewMeetingID.setVisible(true); // show it to the user
            reallyStartNewMeetingButton.setVisible(true);
            startNewMeetingButton.setDisable(true);
            joinMeetingButton.setDisable(true);
            scheduleButton.setDisable(true);
        }
    }

    public void joinMeetingClicked(ActionEvent actionEvent) {
        meetingID.setVisible(true);
        joinButton.setVisible(true);
        startNewMeetingButton.setDisable(true);
        joinMeetingButton.setDisable(true);
        scheduleButton.setDisable(true);
    }

    public void scheduleClicked(ActionEvent actionEvent) {
        startNewMeetingButton.setDisable(true);
        joinMeetingButton.setDisable(true);
        scheduleButton.setDisable(true);
        setButtonClicked(true);
    }

    public boolean isButtonClicked() {
        return buttonClicked.get();
    }

    public BooleanProperty buttonClickedProperty() {
        return buttonClicked;
    }

    public void setButtonClicked(boolean value) {
        this.buttonClicked.set(value);
    }

    public void setUserNameLabel(String name){
        userNameLabel.setText(name);
    }

    public void joinButtonClicked(ActionEvent actionEvent) {
        // save the meeting ID and connect
        saveID = meetingID.getText();
        setButtonClicked(true);
    }

    public void reallyStartNewMeetingButtonClicked(ActionEvent actionEvent) {
        // move to the next scene
        setHostbuttonClicked(true);
    }

    public void setUserImageView(Image photo){
        userImageView.setImage(photo);
    }

    public boolean isHostbuttonClicked() {
        return HostbuttonClicked.get();
    }

    public BooleanProperty hostbuttonClickedProperty() {
        return HostbuttonClicked;
    }

    public void setHostbuttonClicked(boolean hostbuttonClicked) {
        this.HostbuttonClicked.set(hostbuttonClicked);
    }

    public static String getSysIp() {
        String systemipaddress = "";
        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");

            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));

            // reads system IPAddress
            systemipaddress = sc.readLine().trim();
        } catch (Exception e) {
            systemipaddress = "Cannot Execute Properly";
        }

        return systemipaddress;
    }

    public static void copyToClipboard(String str){
        StringSelection stringSelection = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

}
