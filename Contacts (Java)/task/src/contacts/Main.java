package contacts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ContactService contactService = new ContactService(in, System.out);

        System.out.println("open phonebook.db");
        Contacts contacts = new Contacts();

        boolean exit = false;
        do {
            System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
            switch (in.nextLine().toLowerCase()) {
                case "add" -> contactService.addRecord(contacts);
                case "count" -> contactService.countRecords(contacts);
                case "list" -> contactService.listRecords(contacts);
                case "search" -> contactService.searchRecords(contacts);
                case "exit" -> exit = true;
                default -> System.out.println("Please select a listed action.");
            }
        } while (!exit);

    }
}
