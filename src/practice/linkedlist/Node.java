package practice.linkedlist;

public class Node {
    // Data stored in the node
    public int data;
    // pointer to the next node in the list
    public Node next;

    //pointer to store visit
    public boolean visited;

    // constructor with both data and node
    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    // Constructor with only data
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}
