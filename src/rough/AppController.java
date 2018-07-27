package rough;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class AppController implements Initializable {

    // VARIABLES
    private Connection conn;
    private String username;
    private PaginatingDataHandler dataHandler;

    // MODELS
    private ObservableList<DataEntry> entries;
    private Dataset mData;
    private ListView<DataEntry> dataList;

    // VIEWS
    @FXML
    private Pagination paginator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username = PaginatingDataHandler.getInstance().getUsername();
        conn = PaginatingDataHandler.getInstance().getConnection();
        dataHandler = PaginatingDataHandler.getInstance().getDataHandler();
    }

}