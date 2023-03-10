/* This program will assist the user in creating,
 * using, and managing a phonebook.
 * @author Brady Manske
 * @version 2023/02/07
 */
import java.util.*; // for Exceptions and Scanner

public class Test {
    public static void main(String[] args) {
        // initializing variables & objects
        char command = 'z';
        PhoneBookManager phoneBook = new PhoneBookManager();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to your digital phonebook.");
        do { // maintains program until user wants to quit
            System.out.println("Please select an option:\n");
            System.out.println("[p]rint phonebook\n[a]dd contact\n" +
                "[e]dit contact\n[d]elete contact\n[q]uit\n");
            command = commandPrompt(scanner);
            switch (command) {
                case 'p':
                    try {
                        System.out.println(phoneBook.toString());
                    } catch (NoSuchElementException e) {
                    // phonebook is nodeless
                        System.out.println("Your phonebook is currently " +
                        "empty. Try adding a contact first.\n");
                    }
                    break;
                case 'a':
                    add(scanner, phoneBook);
                    break;
                case 'e':
                    edit(scanner, phoneBook);
                    break;
                case 'd':
                    delete(scanner, phoneBook);
                    break;
                case 'q':
                    System.out.println("Goodbye!");
                    break;
                default: // user input is not a valid command
                    System.out.println("Invalid command. Please try again.");
            } // end of switch/case
        } while (command != 'q'); // end of do/while loop
    } // end of main method
    
    // Given a Scanner for user input,
    // and a PhoneBookManager storing a phonebook,
    // Adds a new node for the phonebook,
    // and places it at the spot that the user requests
    public static void add(Scanner scanner,
        PhoneBookManager phoneBook) {
        // initializing variables & objects
        char command = '!';
        int index = -1;
        String[] info = setInfo(scanner);
        
        if (phoneBook.getSize() == 0) { // phonebook is currently empty
            index = 0;
        } else { // phonebook has at least one other entry
            do { // repeats until command is valid
                System.out.println("\nWhere would you like to enter " +
                    "this contact within your phonebook?\n");
                System.out.println("[b]eginning\n[e]nd\n" +
                    "[d]esignate a spot\n[c]ancel\n");
                command = commandPrompt(scanner);
                switch (command) {
                    case 'b':
                        index = 0;
                        break;
                    case 'e':
                        index = phoneBook.getSize();
                        break;
                    case 'd':
                        index = chooseIndex(phoneBook.getSize(),
                            scanner, phoneBook);
                        break;
                    case 'c':
                        break;
                    default: // user input is not a valid command
                        System.out.println("Invalid command. " +
                            "Please try again.");
                        command = 'z';
                } // end of switch/case
            } while (command == 'z'); // end of do/while loop
        } // end of if/else
        if (command != 'c') { // user wants to add a contact
            phoneBook.add(index, info[0], info[1], info[2], info[3], info[4]);
            System.out.println("\n- contact added -\n");
        } // end of if
    } // end of add method
    
    // Given a Scanner for user input
    // and a PhoneBookManager storing a phonebook,
    // Edits an existing node in the phonebook
    // at the location that the user chooses
    public static void edit(Scanner scanner, PhoneBookManager phoneBook) {
        int index = chooseContact(scanner, phoneBook);
        if (index != -1) { // phonebook is not empty and user wants to edit
            System.out.println("\n- contact selected -\n");
            String[] info = setInfo(scanner);
            phoneBook.edit(index, info[0], info[1],
                info[2], info[3], info[4]);
            System.out.println("\n- contact edited -\n");
        }
    } // end of edit method
    
    // Given a Scanner for user input
    // and a PhoneBookManager storing a phonebook,
    // Deletes an existing node in the phonebook
    // at the location that the user chooses
    public static void delete(Scanner scanner, PhoneBookManager phoneBook) {
        int index = chooseContact(scanner, phoneBook);
        if (index != -1) { // phonebook is not empty and user wants to delete
            phoneBook.delete(index);
            System.out.println("\n- contact deleted -\n");
        }
    } // end of delete method
    
    // Given a scanner for user input,
    // Converts user input into a character command,
    // and returns the command
    public static char commandPrompt(Scanner scanner) {
        try {
            return scanner.nextLine().charAt(0);
            // returns a command, regardless of validity
        } catch (StringIndexOutOfBoundsException e) {
        // user does not provide a command
            return 'z'; // returns an invalid command
        } // end of try/catch
    } // end of commandPrompt method
    
    
    // Given a Scanner for user input and a PhoneBookManager storing a phonebook,
    // Prompts the user to select an existing contact in the phonebook,
    // and returns the contact's corresponding index
    // or returns -1 if the phonebook is empty or user wants to back out
    public static int chooseContact(Scanner scanner, PhoneBookManager phoneBook) {
        // initializing variables
        int index = -1;
        char command = '!';
        
        if (phoneBook.getSize() <= 0) { // phonebook is currently empty
            System.out.println("Your phonebook is currently empty. " +
                "Try adding a contact first.\n");
        } else { // phonebook has at least one entry
            do { // repeats until command is valid
                System.out.println("How would you like to " +
                    "select the contact?\n");
                System.out.printf("by [p]hone number%nby [l]ocation " +
                    "in phonebook%n%9s%n%n", "[b]ack");
                command = commandPrompt(scanner);
                switch (command) {
                    case 'p':
                        System.out.println("Here is your current " +
                            "phonebook for reference:");
                        System.out.println(phoneBook.toString());
                        do { // repeats until phone number is valid
                            System.out.print("Please enter the phone number " +
                                "of the contact you wish to select: ");
                            index = phoneBook.indexOf(scanner.nextLine());
                            if (index == -1) { // not a valid phone number
                                System.out.println("This phone number " +
                                    "does not exist in your phonebook.\n" +
                                    "Please try again.\n");
                            } // end of if
                        } while (index == -1); // end of do/while loop
                        break;
                    case 'l':
                        index = chooseIndex((phoneBook.getSize() - 1),
                            scanner, phoneBook);
                        break;
                    case 'b':
                        return -1;
                    default: // user input is not a valid command
                        System.out.println("Invalid command." +
                            "Please try again.");
                        command = 'z';
                } // end of switch/case
            } while (command == 'z'); // end of do/while loop
        } // end of if/else
        return index;
    } // end of chooseContact method
    
    // Given an integer representing the highest possible index,
    // a Scanner for user input, and a PhoneBookManager storing a phonebook
    // Prompts the user to select a valid position in the phonebook,
    // and returns the corresponding index
    public static int chooseIndex(int limit, Scanner scanner,
        PhoneBookManager phoneBook) {
        // initializing variables
        int index = -1;
        int gap = limit - (phoneBook.getSize() - 1);
        
        System.out.println("Here is your current phonebook for reference:");
        System.out.println(phoneBook.toString());
        do { // repeats until index is valid
            System.out.println("Please select a location in your phonebook:\n");
            System.out.println("[1] first contact");
            if ((phoneBook.getSize() + gap) > 1) {
            // contact 2 is a valid position
                System.out.println("[2] second contact");
                if ((phoneBook.getSize() + gap) > 3) {
                // there are more than 3 valid positions
                    System.out.println("...");
                } // end of if
                if ((phoneBook.getSize() + gap) > 2) {
                // the final position is beyond contact 2
                    System.out.println("[" + (limit + 1) + "] final contact");
                } // end of if
            } // end of if
            try {
                index = scanner.nextInt() - 1;
                if (limit < index || index < 0) {
                // user inputs an index outside of the valid range
                    System.out.println("Your phonebook is not that big!\n" +
                        "Try entering a value between 1 and " + 
                        (limit + 1) + ".\n");
                    index = -1;
                } // end of if
            } catch (InputMismatchException e) {
            // user inputs a non-integer value
                System.out.println("Invalid command. Please try again.");
                index = -1;
            } // end of try/catch
            scanner.nextLine();
        } while (index == -1); // end of do/while loop
        return index;
    } // end of chooseIndex method
    
    // Given a scanner for user input,
    // requests contact information such as name and location from the user,
    // and returns a String array storing user inputs
    public static String[] setInfo(Scanner scanner) {
        // declaring array
        String[] info = new String[5];
        
        System.out.println("Please fill out the following information:\n");
        System.out.print("Contact name: ");
        String[] name = scanner.nextLine().split(" ", 2);
        String firstName = name[0];
        String lastName;
        try {
            lastName = name[1];
        } catch (ArrayIndexOutOfBoundsException e) {
        // user did not provide a last name for their contact
            lastName = "";
        } // end of try/catch
        // initializing array values
        info[0] = firstName;
        info[1] = lastName;
        System.out.print("Address: ");
        info[2] = scanner.nextLine();
        System.out.print("City: ");
        info[3] = scanner.nextLine();
        System.out.print("Phone number: ");
        info[4] = scanner.nextLine();
        
        return info;
    } // end of setInfo method
} // end of Test class
