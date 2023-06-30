package contacts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ContactService contactService = new ContactService(in, System.out);

        Contacts contacts = new Contacts();

        boolean exit = false;
        do {
            System.out.print("Enter action (add, remove, edit, count, list, exit): ");
            switch (in.nextLine().toLowerCase()) {
                case "add" -> contactService.addRecord(contacts);
                case "count" -> contactService.countRecords(contacts);
                case "list" -> contactService.listRecords(contacts);
                case "edit" -> contactService.editRecord(contacts);
                case "remove" -> contactService.removeRecord(contacts);
                case "exit" -> exit = true;
                default -> System.out.println("Please select a listed action.");
            }
        } while (!exit);

    }
}
