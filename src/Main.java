import java.util.Scanner;

public class Main {
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

}
