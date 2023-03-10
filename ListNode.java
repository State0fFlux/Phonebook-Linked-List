
/*
 * This defines a class of ListNodes that
 * store contact information within nodes,
 * and are capable of forming a linked list resembling a phonebook of contacts.
 * @author Brady Manske
 * @version 2023/01/31
 */
public class ListNode {
    // declaring fields
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public String phoneNumber;
    public ListNode next;
    
    // Given contact information such as name and location,
    // constructs a node to hold such information
    public ListNode(String firstName, String lastName,
        String address, String city, String phoneNumber, ListNode next) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.next = next;
    } // end of constructor
} // end of ListNode class
