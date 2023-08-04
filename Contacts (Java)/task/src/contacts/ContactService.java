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
        out.print("\n[list] Enter action ([number], back): ");
        if (in.hasNextInt()) {
            int num = in.nextInt();
            in.nextLine();
            if (num - 1 <= count) {
                displayRecord(contacts, contacts.getRecord(num - 1));
            }
        } else {
            in.nextLine();
        }
    }

    public void searchRecords(Contacts contacts) {
        out.print("Enter search query: ");
        List<Record> results = contacts.searchRecords(in.nextLine());
        int count = results.size();
        out.printf("Found %d result%s:", count, count == 1 ? "" : "s");
        for (int i = 0; i < count; i++) {
            out.printf("%d. %s\n", i + 1, results.get(i).getSummary());
        }
        out.print("\n[search] Enter action ([number], back, again): ");
        if (in.hasNextInt()) {
            int num = in.nextInt();
            in.nextLine();
            if (num - 1 <= count) {
                displayRecord(contacts, results.get(num - 1));
            }
        } else {
            if ("again".equals(in.nextLine()))
                searchRecords(contacts);
        }
    }

    private void displayRecords(Contacts contacts, int count) {
        for (int i = 0; i < count; i++) {
            out.printf("%d. %s\n", i + 1, contacts.getRecord(i).getSummary());
        }
    }

    private void displayRecord(Contacts contacts, Record record) {
        out.println(record);
        List<String> actions = List.of("edit", "delete", "menu");
        out.printf("\n[record] Enter action (%s): ", actions);
        String action = in.nextLine();
        switch (action) {
            case "edit" -> editRecord(contacts, record);
            case "delete" -> removeRecord(contacts, record);
        }
    }

    private void editRecord(Contacts contacts, Record record) {
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
        out.println("Saved");
        displayRecord(contacts, record);
    }

    public void removeRecord(Contacts contacts, Record record) {
        contacts.removeRecord(record);
        out.println("\nThe record removed!");
    }
}
