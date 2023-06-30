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
        Record record = new Record();
        out.print("Enter the name: ");
        record.setName(in.nextLine());
        out.print("Enter the surname: ");
        record.setSurname(in.nextLine());
        out.print("Enter the number: ");
        if (!record.setNumber(in.nextLine()))
            out.println("Wrong number format!");
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
        for (int i = 0; i < count; i++) {
            out.printf("%d. %s\n", i + 1, contacts.getRecord(i));
        }
    }

    public void editRecord(Contacts contacts) {
        int count = contacts.countRecords();
        if (count == 0) {
            out.println("No records to edit!");
            return;
        }
        listRecords(contacts);
        out.print("Select a record: ");
        int num = in.nextInt();
        while (num > count) {
            in.nextLine();
            out.print("Please enter the number of the record to edit: ");
            num = in.nextInt();
        }
        in.nextLine();
        Record record = contacts.getRecord(num - 1);
        out.print("Select a field (name, surname, number): ");
        String option = in.next().toLowerCase();
        List<String> options = List.of("name", "surname", "number");
        while (!options.contains(option)) {
            in.nextLine();
            out.print("Please select a listed field: ");
            option = in.next().toLowerCase();
        }
        in.nextLine();
        out.printf("Enter %s: ", option);
        String data = in.nextLine();
        switch (option) {
            case "name" -> record.setName(data);
            case "surname" -> record.setSurname(data);
            case "number" -> {
                if (!record.setNumber(data)) {
                    out.println("Wrong number format!");
                    record.setNumber(null);
                }
            }
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
