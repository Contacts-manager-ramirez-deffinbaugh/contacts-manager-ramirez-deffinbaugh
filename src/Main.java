
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Scanner;

import static java.nio.file.Files.readAllLines;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        //create the contact list from the file
        try {
            getContactsFromList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //call menu
        menu();

    }

    public static void menu() {

        String options = "1. View contacts.\n"
                + "2. Add a new contact.\n"
                + "3. Search a contact by name.\n"
                + "4. Delete an existing contact.\n"
                + "5. Exit.\n"
                + "Enter an option (1, 2, 3, 4 or 5):";
        System.out.println(options);

        int userOption = getInt(1, 5);
        userSelection(userOption);

    }

    public static void userSelection(int x) {
        if (x == 1) {
            view();
        } else if (x == 2) {
            add();
        } else if (x == 3) {
            search();
        } else if (x == 4) {
            delete();
        } else if (x == 5) {
            exit();
        } else {
            System.out.println("Something went wrong.\n");
            menu();
        }
    }

    public static void view() {

        String listHeader = "Name                |  Phone number\n"
                + "----------------------------------------";
        System.out.println(listHeader);
        for (Contact contact : contacts) {
            contact.displayContacts();
        }
        System.out.println(" ");
        menu();
    }

    public static void add() {
        System.out.println("What is the name of the contact you would like to add?");
        String nameInput = scanner.nextLine();
        boolean contactExists = contactExists(nameInput);

        if (!contactExists) {
            System.out.println("What is their phone number?");
            long numberInput = getPhoneNumber();
            contacts.add(new Contact(nameInput, numberInput));
            System.out.println("Contact \"" + nameInput + "\" created successfully\n");
        }

        menu();
    }

    public static long getPhoneNumber() {
        long userIn = getLong();
        while (String.valueOf(userIn).length() != 10 && String.valueOf(userIn).length() != 7) {
            System.out.println("Please enter a valid 7 or 10 digit phone number.");

            userIn = getPhoneNumber();
        }
        return userIn;
    }

    public static boolean contactExists(String nameInput) {
        boolean exists = false;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(nameInput)) {
                exists = true;
                handleExists(i,nameInput);
            }
        }
        return exists;
    }

    public static void handleExists(int i, String nameInput){
        System.out.println("\nThere is already a contact named " + nameInput + ".");
        System.out.println("Would you like to overwrite it? (Type \"yes\" to continue)");
        String userIn = scanner.nextLine();
        if (userIn.equalsIgnoreCase("yes")) {
            System.out.println("What is their phone number?");
            long numberInput = getPhoneNumber();
            contacts.set(i, new Contact(nameInput, numberInput));
            System.out.println("Contact \"" + nameInput + "\" updated successfully\n");
        } else {
            System.out.println("Contact overwrite canceled.\n");
        }
    }

    public static void search() {
        System.out.println("Enter the name of the contact you are looking for: ");
        String contactName = scanner.nextLine();
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(contactName)) {
                printSingleContact(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Sorry, there are no contacts by that name.");
        }
        menu();
    }

    public static void printSingleContact(Contact contact) {
        System.out.println("Result:");
        String listHeader = "Name                |  Phone number\n"
                + "----------------------------------------";
        System.out.println(listHeader);
        contact.displayContacts();
        System.out.println();
    }

    public static void delete() {
        System.out.println("Enter the name of the contact you would like to delete: ");
        String contactName = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(contactName)) {
                contacts.remove(contacts.get(i));
                System.out.println("Contact \"" + contactName + "\" was deleted successfully\n");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Sorry, there are no contacts by that name.\n");
        }
        menu();
    }

    public static void exit() {
        try {
            saveContactList();
            System.out.println("Thank you for using our contact manager. Goodbye!");
        } catch (IOException e) {
            System.out.println("Contacts were unable to save. You now have no contacts. Wha Wha Wha");
        }
    }


    public static int getInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("That was not an integer. Try again:");
            return getInt();
        }
    }

    public static int getInt(int min, int max) {
        int userIn = getInt();
        while (userIn < min || userIn > max) {
            System.out.println("That wasn't an option, try again:");

            userIn = getInt();
        }
        return userIn;
    }

    public static long getLong() {
        long userNumber;
        try {
            userNumber = Long.parseLong(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please enter valid numbers:");
            userNumber = getLong();
        }
        return userNumber;
    }

    private static List<String> getFileStringsFromContacts(List<Contact> contacts) {
        List<String> contactStrings = new ArrayList<>();

        for (Contact contact : contacts) {
            String contactString = contact.toCSV();
            contactStrings.add(contactString);
        }

        return contactStrings;
    }

    public static List<String> getFileList() throws IOException {
        Path filepath = Paths.get("contacts.txt");
        //read from file
        List<String> contactList = Files.readAllLines(filepath);
        System.out.println(contactList);
        return contactList;
    }

    public static void getContactsFromList() throws IOException {
        List<String> contactList = getFileList();
        for (String contact : contactList) {
            String[] contactSplit = contact.split(",");
            long number = Long.parseLong(contactSplit[1]);
            Contact newContact = new Contact(contactSplit[0], number);
            contacts.add(newContact);
        }
    }

    public static void saveContactList() throws IOException {
        Path filepath = Paths.get("contacts.txt");
        //write to file
        List<String> fileStrings = getFileStringsFromContacts(contacts);
        Files.write(filepath, fileStrings);

    }

}
