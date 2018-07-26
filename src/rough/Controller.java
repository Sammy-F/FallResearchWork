package rough;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    // MODELS
    private ObservableList<DataEntry> entries;
    private Dataset mData;
    private ListView<DataEntry> dataList;

    // VIEWS
    @FXML
    private HBox mBox;

    // CONSTANTS
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        System.out.println(mBox);

        mBox.getChildren().add(dataList);
    }

}



