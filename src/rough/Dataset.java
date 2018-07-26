package rough;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class for a Dataset.
 *
 * Includes trivial methods for modifying the data.
 */
public class Dataset {

    private ArrayList<DataEntry> mData;

    public Dataset() {
        mData = new ArrayList<DataEntry>();
    }

    public ArrayList<DataEntry> getData() { return mData; }

    public void addEntry(DataEntry entry) { mData.add(entry); }

    public void addEntries(List<DataEntry> newData) { mData.addAll(newData); }

    public void removeEntry(DataEntry entry) { mData.remove(entry); }

    public void removeEntry(int eIndex) { mData.remove(eIndex); }

    public int size() { return mData.size(); }
}
