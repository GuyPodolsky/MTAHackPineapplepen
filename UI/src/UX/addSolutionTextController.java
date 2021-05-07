import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class addSolutionTextController implements Initializable {
    @FXML
    TextArea solution;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addSolution(String text) {
        solution.setText(text);
    }
}
