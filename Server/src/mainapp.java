import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

public class mainapp extends Application {

    @Override
    public void start(Stage primaryStage) {

        String avatarPath = new String();
        String name;


        primaryStage.setTitle("Welcome!");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName,0,2);

        TextField userNameField = new TextField();
        grid.add(userNameField,1,2);

        Label path = new Label("Avatar Path:");
        grid.add(path, 0, 3);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        Button openButton = new Button("Choose file");
        grid.add(openButton, 1, 3);

        openButton.setOnAction(e ->{
                        Stage fileChooseStage = new Stage();
                        File file = fileChooser.showOpenDialog(fileChooseStage);
                        if (file != null) {
                            avatarPath.concat(file.getAbsolutePath());
                        }
                });

        Button btn = new Button("Enter");
        HBox buttonsHBox = new HBox(10);
        buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonsHBox.getChildren().add(btn);
        grid.add(buttonsHBox, 1, 4);


        btn.setOnAction(e -> {
            User u= new User(userNameField.getText(),avatarPath);

        });

        grid.add(u.getPic(),0,1);
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("main");
        launch(args);
    }

}