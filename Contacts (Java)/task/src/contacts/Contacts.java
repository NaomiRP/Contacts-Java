package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Contacts {
    private final List<Record> records = new ArrayList<>();

    public void addRecord(Record record) {
        records.add(record);
    }

    public Record getRecord(int index) {
        return records.get(index);
    }

    public void removeRecord(Record record) {
        int index = 0;
        for (int i = 0; i < records.size(); i++) {
            Record r = records.get(i);
            if ((record instanceof Person && r instanceof Person)
                    || (record instanceof Organization && r instanceof Organization)) {
                if (record.getSummary().equals(r.getSummary())) {
                    index = i;
                    break;
                }
            }
        }
        records.remove(index);
    }

    public int countRecords() {
        return records.size();
    }

    public List<Record> searchRecords(String query) {
        Pattern p = Pattern.compile(query.toLowerCase());
        List<Record> results = new ArrayList<>();
        for (Record r : records) {
            if (p.matcher(r.getSearchData().toLowerCase()).find())
                results.add(r);
        }
        return results;
    }
}
