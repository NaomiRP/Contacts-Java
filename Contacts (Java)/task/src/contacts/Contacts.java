package contacts;

import java.util.ArrayList;
import java.util.List;

public class Contacts {
    private final List<Record> records = new ArrayList<>();

    public void addRecord(Record record) {
        records.add(record);
    }

    public Record getRecord(int index) {
        return records.get(index);
    }

    public void removeRecord(int index) {
        records.remove(index);
    }

    public int countRecords() {
        return records.size();
    }
}
