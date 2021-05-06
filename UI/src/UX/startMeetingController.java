package UX;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
    private BooleanProperty buttonClicked = new SimpleBooleanProperty(this, "Button clicked");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startNewMeetingClicked(ActionEvent actionEvent) {
        if(getNewMeetingID.getText().equals("Meeting ID : ")) {
            // set an meeting id
            int saveID = new Random().nextInt(10000);       // TODO: I'll need to get a real meeting ID
            getNewMeetingID.setText(getNewMeetingID.getText() + saveID);
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
        setButtonClicked(true);
    }

    public void reallyStartNewMeetingButtonClicked(ActionEvent actionEvent) {
        // move to the next scene
        setButtonClicked(true);
    }
}
