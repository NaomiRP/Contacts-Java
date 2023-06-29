package contacts;

import java.util.ArrayList;
import java.util.List;

public class Contacts {
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }
}
