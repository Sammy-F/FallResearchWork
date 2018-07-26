package rough;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    ObservableList<DataEntry> entries;
    Dataset mData;
    ListView<DataEntry> dataList;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Map<String, Object> characteristics = new HashMap<String, Object>();

        characteristics.put("Hello", "World");
        characteristics.put("Hello", "World2");
        characteristics.put("Hellop", "World");
        characteristics.put("Hello2", "World2");

        mData = new Dataset();
        mData.addEntry(new DataEntry(0, true, characteristics));
        mData.addEntry(new DataEntry(0, true));
        mData.addEntry(new DataEntry(0, true, characteristics));
        mData.addEntry(new DataEntry(0, true));
        mData.addEntry(new DataEntry(0, true));
        entries = FXCollections.observableArrayList(mData.getData());
        dataList = new ListView<DataEntry>(entries);
        dataList.setPrefWidth(800);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        ((GridPane) root).getChildren().add(dataList);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
