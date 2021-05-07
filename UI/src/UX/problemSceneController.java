package UX;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import server.User;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class problemSceneController implements Initializable {

    @FXML
    Button sendButton;

    @FXML
    TextField userCharTextField;

    @FXML
    TextArea serverChatTextArea;

    @FXML
    TextField userSolution;

    @FXML
    FlowPane solutionBox; ///**********8

    @FXML
    TextArea solutionTextArea;

    @FXML
    AnchorPane solutionBubble;

    @FXML
    TabPane AllTabs;
    Tab currentTab;
    private User user;

    private Client client;


    private boolean isAdmin; // For Delete
    private boolean isUser; /// For Delete
    private AnchorPane currentDiscussion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AllTabs = new TabPane();

    }


    public void setUser(User user){
        this.user = user;
    }

    public void sendChatMessage(ActionEvent actionEvent) {
        serverChatTextArea.appendText(user.getName()+ " : " + userCharTextField.getText() + "\n");
        userCharTextField.clear();
    }

    public void addSolutionText(ActionEvent actionEvent) throws IOException {
        if (userSolution.getText().length() != 0) {
            FXMLLoader bubble = new FXMLLoader();
            URL url = getClass().getResource("bubbleSolution.fxml");
            bubble.setLocation(url);
            solutionBubble = new AnchorPane();
            solutionBubble = bubble.load(url.openStream());
            TextArea text = (TextArea) solutionBubble.getChildren().get(0);
            text.setText(userSolution.getText());
            solutionBubble.setPrefSize(userSolution.getText().length(),5*userSolution.getFont().getSize());
            solutionBox.getChildren().add(solutionBubble);
            /// ADD SORT FOR LIKES
            /// ADD LIKES INCREMENT
        }

    }

    public void addTab(ActionEvent actionEvent) {
        Tab addTab = new Tab("+");
        addTab.setClosable(false);
        AllTabs.getTabs().add(addTab);
    }

    public void deleteChild(ActionEvent actionEvent) {
    }


    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
