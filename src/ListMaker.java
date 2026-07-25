import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    // Global ArrayList to hold list items
    private static ArrayList<String> myArrList = new ArrayList<>();
    private static Scanner pipe = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false;
        String command = "";

        do {
            //Display current list and menu
            displayList();
            displayMenu();

            // Get a valid menu choice using RegEx matching A, D, I, P, or Q (case-insensitive)
            command = SafeInput.getRegExString(pipe, "Enter your menu choice", "[AaDdIiPpQq]").toUpperCase();

            switch (command) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "P":
                    // displayList() runs automatically at the start of the loop,
                    // Call here explicitly if they just want to print.
                    break;
                case "Q":
                    quit = SafeInput.getYNConfirm(pipe, "Are you sure you want to quit?");
                    break;
            }
            System.out.println(); // spacing between loops
        } while (!quit);
        System.out.println("Goodbye!");
    }

    //Displays the current items in the list with 1-based indexing.
    private static void displayList() {
        System.out.println("\n---------------------------------");
        System.out.println("          CURRENT LIST           ");
        System.out.println("---------------------------------");
        if (myArrList.isEmpty()) {
            System.out.println("[ List is currently empty ]");
        } else {
            for (int i = 0; i < myArrList.size(); i++) {
                // Display 1-based index to the user
                System.out.printf("%d. %s\n", (i + 1), myArrList.get(i));
            }
        }
        System.out.println("=================================");
    }

    //Displays the menu options to the user.L
    private static void displayMenu() {
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("I - Insert an item into the list");
        System.out.println("P - Print (display) the list");
        System.out.println("Q - Quit the program");
    }


    //Adds an item to the very end of the ArrayList.
    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(pipe, "Enter the item to add");
        myArrList.add(item);
        System.out.println("Item successfully added.");
    }

    //Prompts the user for an item number and deletes it.
    private static void deleteItem() {
        if (myArrList.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        // Prompt user for a 1-based index within the current valid range
        int targetIndex = SafeInput.getRangedInt(pipe, "Enter the item number to delete", 1, myArrList.size());
        // Convert user's 1-based choice back to 0-based index for ArrayList
        String removedItem = myArrList.remove(targetIndex - 1);
        System.out.println("Successfully removed: \"" + removedItem + "\"");
    }

    //Inserts an item at a specific numbered location choice.
    private static void insertItem() {
        String item = SafeInput.getNonZeroLenString(pipe, "Enter the item to insert");

        int targetPosition;
        if (myArrList.isEmpty()) {
            System.out.println("List is empty. Inserting at position 1.");
            myArrList.add(item);
        } else {
            // Maximum slot choice is size + 1 (SafeInput appending at the very end)
            targetPosition = SafeInput.getRangedInt(pipe, "Enter the position number to insert at", 1, myArrList.size() + 1);
            myArrList.add(targetPosition - 1, item);
            System.out.println("Item successfully inserted.");
        }
    }
}
