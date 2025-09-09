package school.sorokin.javacore;

import java.util.Scanner;

public class Main {
    private static int quantity = 1000;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String wasNotFound = "The contact with name %s  was not found.";
    private static final String enterName = "Enter contact name: ";
    private static String[] name = new String[quantity];
    private static String[] phone = new String[quantity];
    private static int count = 0;

    public static void main(String[] args) {

        while (true) {
            System.out.println();
            printMenu();
            String input = scanner.nextLine();
            switch (input.trim()) {
                // add new contact
                case "1" -> {
                    addContact();
                }
                // show all contacts
                case "2" -> {
                    shoAllContacts();
                }
                //find by name
                case "3" -> {
                    findByName();
                }
                //delete contact
                case "4" -> {
                    deleteContact();
                }
                // exit from program
                case "5" -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid input try again.");
                }
            }
        }
    }

    private static void addContact() {
        if (count < quantity) {
            name[count] = validName();
            //validate phone number
            phone[count] = validPhoneNumber().trim();
            System.out.println("Contact has been saved.");
            count++;
        } else {
            System.out.println("List of contacts is full");
        }
    }

    private static void shoAllContacts() {
        if (count == 0) {
            System.out.println("List of contacts is empty");
        } else {
            for (int i = 0; i <= count - 1; i++) {
                System.out.println(i + 1 + "." + name[i] + " - " + phone[i]);
            }
        }
    }

    private static void findByName() {
        System.out.print(enterName);
        String str = scanner.nextLine();
        int temp = 0;
        for (int i = 0; i < count; i++) {
            //to be sure that you didn't make name mistake
            if (name[i].equalsIgnoreCase(str.trim())) {
                System.out.println("The phone number of " + name[i] + " is : " + phone[i]);
                temp++;
            }
        }
        if (temp == 0) {
            System.out.printf(wasNotFound, str);
        }
    }

    private static void deleteContact() {
        System.out.print("Enter contact name for deleting: ");
        String str = scanner.nextLine();
        int temp = 0;
        for (int i = 0; i < count; i++) {
            if (name[i].equalsIgnoreCase(str.trim())) {
                name[i] = null;
                phone[i] = null;
                temp++;
            }
        }
        if (temp == 0) {
            System.out.printf(wasNotFound, str);
        } else {
            System.out.println("The contact with name " + str + " has been deleted.");
            // shift the array after removing contacts
            for (int i = 0; i < count - 1; i++) {
                for (int j = 0; j < count - 1; j++) {
                    if (name[j] == null) {
                        String tempName = name[j];
                        String tempPhone = phone[j];
                        name[j] = name[j + 1];
                        name[j + 1] = tempName;
                        phone[j] = phone[j + 1];
                        phone[j + 1] = tempPhone;
                    }
                }
            }
        }
        count = count - temp;
    }

    // print menu
    private static void printMenu() {
        System.out.print("   Menu:\n" + " 1 - Add new contact\n" + " 2 - Show all contacts\n" + " 3 - Find by name\n" + " 4 - Delete contact\n" + " 5 - Exit program\n" + "Enter number of command: ");
    }

    // validation phone number
    private static String validPhoneNumber() {
        while (true) {
            System.out.print(enterName);
            String number = scanner.nextLine();
            try {
                Integer.parseInt(number);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid phone number try again");
            }
        }
    }

    //validation name
    private static String validName() {
        while (true) {
            System.out.print("Enter contact name: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            } else {
                System.out.println("Invalid contact name try again");
            }
        }
    }
}