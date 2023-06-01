import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

        //contact list
        //TODO load this from file
        ArrayList<Contact> contacts = new ArrayList<>();

        //testing creating contacts, display, and adding to list
        Contact testContact = addContact();
        testContact.displayContacts();
        contacts.add(testContact);
        System.out.println(contacts);


    }

    public static Contact addContact() {
        //TODO check that there isn't already a contact with this name
        //TODO phone number validation
        System.out.println("What is the name of your contact?");
        String nameInput = in.nextLine();
        System.out.println("What is their phone number?");
        //TODO wrap string to int conversion in try/catch
        int numberInput = Integer.parseInt(in.nextLine());

        return new Contact(nameInput, numberInput);
    }
}
