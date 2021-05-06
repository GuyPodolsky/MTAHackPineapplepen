package UX;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    public Button guestButton;
    public Button userButton;
    public TextField guestName;
    public BorderPane signInPane;
    public Button continueButton;
    private BooleanProperty continueClicked = new SimpleBooleanProperty(this, "User connected");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void guestButtonClicked(ActionEvent actionEvent) {
        guestName.setVisible(true);
        continueButton.setVisible(true);
        // enter the new user to the system
        // start the next scene
    }

    public void continueClicked(ActionEvent actionEvent) {
        setContinueClicked(true);
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
}
