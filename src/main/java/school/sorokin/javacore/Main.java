package school.sorokin.javacore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] name = new String[1000];
        String[] phone = new String[1000];
        int count = 0;
        while (true) {
            System.out.println();
            printMenu();
            String input = scanner.nextLine();
            switch (input.trim()) {
                // add new contact
                case "1" -> {
                    if (count < 1000) {
                        name[count] = validName().trim();
                        //validate phone number
                        phone[count] = validPhoneNumber().trim();
                        System.out.println("Contact has been saved.");
                        count++;
                    } else {
                        System.out.println("List of contacts is full");
                    }
                }
                // show all contacts
                case "2" -> {
                    if (count == 0) {
                        System.out.println("List of contacts is empty");
                    } else {
                        for (int i = 0; i <= count - 1; i++) {
                            System.out.println(i + 1 + "." + name[i] + " - " + phone[i]);
                        }
                    }
                }
                //find by name
                case "3" -> {
                    System.out.print("Enter contact name: ");
                    String str = scanner.nextLine();
                    int temp = 0;
                    for (int i = 0; i < count; i++) {
                        //to be sure that you didn't make name mistake
                        if (name[i].toLowerCase().equals(str.toLowerCase().trim())) {
                            System.out.println("The phone number of " + name[i] + " is : " + phone[i]);
                            temp++;
                        }
                    }
                    if (temp == 0) {
                        System.out.println("The contact with name " + str + " was not found.");
                    }
                }
                //delete contact
                case "4" -> {
                    System.out.print("Enter contact name for deleting: ");
                    String str = scanner.nextLine();
                    int temp = 0;
                    for (int i = 0; i < count; i++) {
                        if (name[i].toLowerCase().equals(str.toLowerCase().trim())) {
                            name[i] = null;
                            phone[i] = null;
                            temp++;
                        }
                    }
                    if (temp == 0) {
                        System.out.println("The contact with name " + str + " was not found.");
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

    // print menu
    private static void printMenu() {
        System.out.print(
                "   Menu:\n" +
                        " 1 - Add new contact\n" +
                        " 2 - Show all contacts\n" +
                        " 3 - Find by name\n" +
                        " 4 - Delete contact\n" +
                        " 5 - Exit program\n" +
                        "Enter number of command: "
        );
    }

    // validation phone number
    private static String validPhoneNumber() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter contact phone: ");
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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter contact name: ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                return name;
            } else {
                System.out.println("Invalid contact name try again");
            }
        }
    }
}