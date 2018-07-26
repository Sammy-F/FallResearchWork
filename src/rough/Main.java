package rough;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Node mBox = ((GridPane) root).getChildren().get(0);
        mBox = new HBox();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
