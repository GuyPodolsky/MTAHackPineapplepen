package UX;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class BubbleSolutionController implements Initializable {
    // ** bubbleSolution
    public Button disLikeButton;
    public Button likeButton;
    private String ID;
    private int likes;
    // ** bubbleSolution

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addDiscussion(MouseEvent mouseEvent) {


    }

    public void disLikeButtonClicked(ActionEvent actionEvent) {
        // update server
    }

    public void likeButtonClicked(ActionEvent actionEvent) {
        // update server

    }
}
