import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

import static javafx.application.Application.launch;

public class main extends Application {
    private Stage primaryStage;
    private Scene scene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource("problemScene.fxml");
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent parent = fxmlLoader.load(url.openStream());
        primaryStage.setScene(new Scene(parent, 600, 600));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
