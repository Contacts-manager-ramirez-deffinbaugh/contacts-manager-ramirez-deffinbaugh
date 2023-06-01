
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

        Scanner scanner = new Scanner(System.in);
        int userOption = scanner.nextInt();
        userSelection(userOption);



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
        }
    }


    public static void view() {
        System.out.println("view");
        menu();

    }

    public static void  add() {
        System.out.println("add");
        menu();
    }

    public static void search() {
        System.out.println("search");
        menu();
    }

    public static void delete() {
        System.out.println("delete");
        menu();
    }

    public static void exit() {
        System.out.println("exit");
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
