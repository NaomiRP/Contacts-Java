package contacts;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class ContactService {

    Scanner in;
    PrintStream out;

    public ContactService(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }


    public void addRecord(Contacts contacts) {
        out.printf("Enter the type (%s): ", Record.getTypes());
        String type = in.nextLine().toLowerCase();
        Record record;
        if (Record.getTypes().get(0).equals(type)) {
            record = new Person();
        } else {
            record = new Organization();
        }
        for (String field : record.getFields()) {
            out.printf("Enter the %s: ", field);
            String value = in.nextLine();
            if (!record.setField(field, value))
                out.println("Bad " + field + "!");
        }
        contacts.addRecord(record);
        out.println("The record added.");
    }

    public void countRecords(Contacts contacts) {
        int count = contacts.countRecords();
        out.printf("The Phone Book has %d record%s.\n", count, count == 1 ? "" : "s");
    }

    public void listRecords(Contacts contacts) {
        int count = contacts.countRecords();
        if (count == 0) {
            out.println("No records to list!");
            return;
        }
        displayRecords(contacts, count);
        out.print("Enter index to show info: ");
        int num = in.nextInt();
        in.nextLine();
        if (num - 1 <= count) {
            out.println(contacts.getRecord(num - 1));
        }
    }

    private void displayRecords(Contacts contacts, int count) {
        for (int i = 0; i < count; i++) {
            out.printf("%d. %s\n", i + 1, contacts.getRecord(i).getSummary());
        }
    }

    public void editRecord(Contacts contacts) {
        int count = contacts.countRecords();
        if (count == 0) {
            out.println("No records to edit!");
            return;
        }
        displayRecords(contacts, count);
        out.print("Select a record: ");
        int num = in.nextInt();
        while (num > count) {
            in.nextLine();
            out.print("Please enter the number of the record to edit: ");
            num = in.nextInt();
        }
        in.nextLine();
        Record record = contacts.getRecord(num - 1);
        List<String> fields = record.getFields();
        out.printf("Select a field (%s): ", fields);
        String option = in.next().toLowerCase();
        while (!fields.contains(option)) {
            in.nextLine();
            out.print("Please select a listed field: ");
            option = in.next().toLowerCase();
        }
        in.nextLine();
        out.printf("Enter %s: ", option);
        String data = in.nextLine();
        if (!record.setField(option, data)) {
            out.println("Wrong " + option + " format!");
            record.setNumber(null);
        }
        out.println("The record updated!");
    }

    public void removeRecord(Contacts contacts) {
        int count = contacts.countRecords();
        if (count == 0) {
            out.println("No records to remove!");
            return;
        }
        listRecords(contacts);
        out.print("Select a record: ");
        int num = in.nextInt();
        while (num > count) {
            in.nextLine();
            out.print("Please enter the number of the record to delete: ");
            num = in.nextInt();
        }
        contacts.removeRecord(num - 1);
        out.println("\nThe record removed!");
    }
}
