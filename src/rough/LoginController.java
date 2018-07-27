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

public class LoginController implements Initializable {

    // VARIABLES
    private Connection conn;
    private String username;

    // VIEWS

    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfPass;

    // CONSTANTS
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    private boolean validateLogin(String user, String pass) {
        boolean condition = true;

        if (condition) {
            return true;
        } else {
            return false;
        }
    }

    private void connect() {

        conn = null;

        final String USER = tfUser.getText();
        final String PASS = tfPass.getText();

        if (validateLogin(USER, PASS)) {
            try{
                //STEP 2: Register JDBC driver
                Class.forName(JDBC_DRIVER);

                //STEP 3: Open a connection
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
                username = USER;

                PaginatingDataHandler.getInstance().initialize(username, 10, "cats", conn);
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("Goodbye!");
        }

    }

    @FXML
    public void loginClicked() {
        connect();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("App");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}



