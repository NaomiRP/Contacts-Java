package contacts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Contact person = new Contact();
        System.out.println("Enter the name of the person:");
        person.setName(in.nextLine());
        System.out.println("Enter the surname of the person:");
        person.setSurname(in.nextLine());
        System.out.println("Enter the number:");
        person.setNumber(in.nextLine());

        System.out.println("A record created!");

        Contacts contacts = new Contacts();
        contacts.addContact(person);
        System.out.println("A Phone Book with a single record created!");
    }
}
