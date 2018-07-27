package rough;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class Controller implements Initializable {

    // VARIABLES
    private Connection conn;

    // MODELS
    private ObservableList<DataEntry> entries;
    private Dataset mData;
    private ListView<DataEntry> dataList;

    // VIEWS
    @FXML
    private HBox mBox;
    private TextField tfUser;
    private TextField tfPass;

    // CONSTANTS
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    // TYPES
    private final int LOAD_DATA = 1;
    private final int UPDATE_ROW = 2;

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

    private boolean validateLogin(String user, String pass) {
        boolean condition = true;

        if (condition) {
            return true;
        } else {
            return false;
        }
    }

    private String constructStatement(Map<String, String> args, int type) {
        if (type == LOAD_DATA) {
            return "Load";
        } else if (type == UPDATE_ROW) {
            return "Update";
        } else {
            return "Invalid";
        }
    }

    public void executeQuery(Map<String, String> args, int type) {
        Statement stmt = null;
        String sql = constructStatement(args, type);
        if (conn != null && !sql.equals("INVALID")) {
            try {
                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                //STEP 5: Extract data from result set
                while(rs.next()){
                    //Retrieve by column name
                    int id  = rs.getInt("id");
                    int age = rs.getInt("age");
                    String first = rs.getString("first");
                    String last = rs.getString("last");

                    //Display values
                    System.out.print("ID: " + id);
                    System.out.print(", Age: " + age);
                    System.out.print(", First: " + first);
                    System.out.println(", Last: " + last);
                }
                //STEP 6: Clean-up environment
                rs.close();
                stmt.close();
                conn.close();
            } catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
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

    public void connect() {

        conn = null;

        final String USER = tfUser.getText();
        final String PASS = tfPass.getText();

        if (validateLogin(USER, PASS)) {
            try{
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
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

}



