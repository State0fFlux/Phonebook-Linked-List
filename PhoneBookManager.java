/*
 * This defines a PhoneBook Manager class that provides
 * many methods for linking and unlinking ListNodes to form a dynamic phonebook,
 * as well as storing information about the phonebook such as its size
 * and String appearance.
 * @author Brady Manske
 * @version 2023/01/31
 */
import java.util.*; // for Exceptions

public class PhoneBookManager {
    // declaring field
    private ListNode front;
    
    // Given a valid index and contact information such as name and location,
    // adds a contact entry at the given index to the phonebook
    public void add(int index, String firstName, String lastName,
        String address, String city, String phoneNumber) {
        if (index == 0) { // adding to front
            front = new ListNode(firstName, lastName,
                address, city, phoneNumber, front);
        } else { // adding to middle or back
            ListNode current = front; // duplicating phonebook
            for (int i = 1; i < index; i++) { // flipping through duplicate
                current = current.next;
            } // end of for loop
            current.next = new ListNode(firstName, lastName, 
                address, city, phoneNumber, current.next);
        } // end of if/else
    } // end of add method
    
    // Given a valid index and contact information such as name and location,
    // edits an existing contact at the given index
    public void edit(int index, String firstName, String lastName,
        String address, String city, String phoneNumber) {
        ListNode current = front; // duplicating phonebook
        for (int i = 0; i < index; i++) { // flipping through duplicate
            current = current.next;
        } // end of for loop
        current.firstName = firstName;
        current.lastName = lastName;
        current.address = address;
        current.city = city;
        current.phoneNumber = phoneNumber;
    } // end of edit method
    
    // Given a valid index,
    // deletes an existing contact at the given index
    public void delete(int index) {
        ListNode current = front; // duplicating phonebook
        if (index == 0) { // deleting from front
            front = front.next;
        } else if (index == (getSize() - 1)) { // deleting from back
            for (int i = 2; i < getSize(); i++) { // flipping through duplicate
                current = current.next;
            } // end of for loop
            current.next = null;
        } else { // deleting from middle
            for (int i = 1; i < index; i++) { // flipping through duplicate
                current = current.next;
            } // end of for loop
            current.next = current.next.next;
        } // end of if/elses
    } // end of delete method
    
    // Returns a String representation of the phonebook,
    // and throws a NoSuchElement exception if phonebook is empty
    public String toString() {
        if (front == null) { // phonebook is empty
            throw new NoSuchElementException();
        } else { // phonebook has at least one entry
            ListNode current = front; // duplicating phonebook
            int space = maxLength();
            String list = "";
            while (current != null) { // flipping through duplicate
                list += String.format("Contact " + (indexOf(current) + 1) +
                    "%nName:%" + (space + 10) + "s%nAddress:%" + (space + 7) +
                    "s%nCity:%" + (space + 10) + "s%nPhone Number:%" +
                    (space +2) + "s%n%n", fullName(current), current.address,
                    current.city, current.phoneNumber);
                current = current.next;
            } // end of while loop
            return list;
        } // end of if/else
    } // end of toString method
    
    // Returns the number of contacts stored within the phonebook
    public int getSize() {
        ListNode current = front; // duplicating phonebook
        int size = 0;
        while (current != null) { // flipping through duplicate
            current = current.next;
            size++;
        } // end of while loop
        return size;
    } // end of getSize method
    
    // Given a phone number,
    // returns the index of the phonebook node storing the phone number,
    // and returns -1 if the phonebook does not contain
    // a node with given phone number
    public int indexOf(String phoneNumber) {
        // initializing variables & objects
        int indexCounter = 0;
        ListNode current = front; // duplicating phonebook
        while (indexCounter < (getSize() - 1)
            && current.phoneNumber != phoneNumber) {
            // searching duplicate for phone number
            current = current.next;
            indexCounter++;
        } // end of while loop
        if (indexCounter == (getSize() - 1)
            && !current.phoneNumber.equals(phoneNumber)) {
            // search was unsuccessful
            return -1;
        } else { // phone number was found
            return indexCounter;
        } // end of if/else
    } // end of public indexOf method
    
    // Given a contact node from the phonebook,
    // returns the index of node in relation to the phonebook
    private int indexOf(ListNode node) {
        ListNode current = front; // duplicating phonebook
        int indexCounter = 0;
        while (indexCounter < (getSize() - 1) && current != node) {
        // searching duplicate for node
            current = current.next;
            indexCounter++;
        } // end of while loop
        return indexCounter;
    } // end of private indexOf method
    
    // Given a contact node from the phonebook,
    // returns the contact's full name (combined first and last)
    private String fullName(ListNode node) {
        if (node.lastName.equals("")) { // no last name
            return (node.firstName);
        } else { // node stores a last name
        return (node.firstName + " " + node.lastName);
        } // end of if/else
    } // end of fullName method
    
    // Returns the length of the longest String stored in the entire phonebook
    private int maxLength() {
        ListNode current = front; // duplicating phonebook
        int length = 0;
        while (current != null) {
        // searching duplicate for increasingly longer Strings
            if (fullName(current).length() > length) {
                length = fullName(current).length();
            } // end of if
            if (current.address.length() > length) {
                length = current.address.length();
            } // end of if
            if (current.city.length() > length) {
                length = current.city.length();
            } // end of if
            if (current.phoneNumber.length() > length) {
                length = current.phoneNumber.length();
            } // end of if
            current = current.next; // flipping through phonebook
        } // end of while loop
        return length;
    } // end of maxLength method
} // end of PhoneBookManager class
