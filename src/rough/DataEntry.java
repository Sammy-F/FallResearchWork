package rough;

import java.util.*;

/**
 * Model class for a data entry
 */
public class DataEntry {

    private int dbIndex;
    private boolean status;
    private Map<String, Object> characteristics;

    public DataEntry(int dbIndex, boolean status, Map<String, Object> characteristics) {
        this.dbIndex = dbIndex;
        this.status = status;
        this.characteristics = characteristics;
    }

    public DataEntry(int dbIndex, boolean status) {
        this.dbIndex = dbIndex;
        this.status = status;
        this.characteristics = new HashMap<String, Object>();
    }

    public int getDbIndex() { return dbIndex; }
    public void setDbIndex(int newIndex) { dbIndex = newIndex; }
    public boolean getStatus() { return status; }
    public void flipStatus() {
        status = !status;
    }

    @Override
    public String toString() {
        String returnStr = Integer.toString(dbIndex) + ": " + Boolean.toString(status);

        Set<String> keys = characteristics.keySet();
        Set<Map.Entry<String, Object>> items = characteristics.entrySet();
        for (Map.Entry<String, Object> item: items) {
            returnStr += ", " + item.getKey() + ": " + item.getValue().toString();
        }

        return returnStr;
    }
}
