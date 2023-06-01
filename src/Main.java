import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

        //contact list
        //TODO load this from file
        ArrayList<Contact> contacts = new ArrayList<>();
//        List<Contact> contacts = new ArrayList<>(List.of());

        //testing creating contacts, display, and adding to list
        Contact testContact = addContact();
        testContact.displayContacts();
        contacts.add(testContact);
        System.out.println(contacts);


        //write to file
        List<String> fileStrings = getFileStringsFromFighters(contacts);
        Path filepath = Paths.get("contacts.txt");
        try {
            Files.write(filepath, fileStrings);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static List<String> getFileStringsFromFighters(List<Contact> contacts) {
        // 0. make a new empty list of strings
        List<String> contactStrings = new ArrayList<>();

        // 1. for each fighter
        for (Contact contact : contacts) {
            // 2. get the string version of the fighter
            String contactString = contact.toCSV();

            // 3. add that string to a list of strings
            contactStrings.add(contactString);
        }

        // 4. return the list of strings
        return contactStrings;
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
