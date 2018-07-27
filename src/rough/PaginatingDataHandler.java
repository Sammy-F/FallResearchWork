package rough;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaginatingDataHandler {

    boolean initialized = false;
    private int numPages;
    private int currentPage;
    private int currentIndex;
    private final int ENTRIES_PER_PAGE = 15;
    private String tableName;

    private Map<Integer, Dataset> dataByPage;   // Each time we load data for a page, store it

    private Connection conn;

    private String username;

    private static PaginatingDataHandler dataHandler;

    private PaginatingDataHandler() {}

    public static PaginatingDataHandler getInstance() {
        if (dataHandler == null) {
            dataHandler = new PaginatingDataHandler();
        }

        return dataHandler;
    }

    public void initialize(String username, int numPages, String tableName, Connection conn) {
        dataHandler.username = username;
        dataHandler.numPages = numPages;
        dataHandler.currentIndex = 0;
        dataHandler.currentPage = 0;
        dataHandler.conn = conn;
        dataHandler.dataByPage = new HashMap<Integer, Dataset>();
        dataHandler.tableName = tableName;
        dataHandler.initialized = true;

    }

    private Dataset loadPage(int page) {
        if (dataByPage.containsKey(page)) {
            return dataByPage.get(page);
        } else {
            loadNewPage(page);
            return dataByPage.get(page);
        }
    }

    private void loadNewPage(int page) {
        Statement stmt = null;
        int indexStart = page * ENTRIES_PER_PAGE;
        int indexEnd = indexStart + ENTRIES_PER_PAGE;
        Dataset newPageData = new Dataset();

        // Get the entries
        String sql = "SELECT * FROM " + tableName + " LIMIT " + Integer.toString(indexStart) + ","
                + Integer.toString(indexEnd) + ";";
        if (conn != null) {
            try {
                //Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                //Extract data from result set
                while(rs.next()){
                    //Retrieve by column name
                    int id  = rs.getInt("id");

                    newPageData.addEntry(new DataEntry(id, true));

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

        if (newPageData.size() > 0) {
            dataByPage.put(page, newPageData);
        }
    }

    public String getUsername() { return dataHandler.username; }
    public String getTableName() { return dataHandler.tableName; }
    public Connection getConnection() { return dataHandler.conn; }
    public PaginatingDataHandler getDataHandler() { return dataHandler; }

}
