package practice.linkedlist;

import static practice.linkedlist.ReverseLinkedList.printLinkedList;

/**
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even
 * indices, and return the reordered list.
 * The first node is considered odd, and the second node is even, and so on.
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 *
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 */
public class OddEvenLinkedList {
    public static void main(String args[]) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        Node head2 = new Node(2);
        head2.next = new Node(1);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(5);
        head2.next.next.next.next = new Node(6);
        head2.next.next.next.next.next = new Node(4);
        head2.next.next.next.next.next.next = new Node(7);

/*        System.out.println("Original List");
        printLinkedList(head1);
        System.out.println("After modification List");
        printLinkedList(twoPointers(head1));

        System.out.println("Original List");
        printLinkedList(head2);
        System.out.println("After modification List");
        printLinkedList(twoPointers(head2));*/

        System.out.println("Original List");
        printLinkedList(head1);
        System.out.println("After modification List");
        printLinkedList(twoPointersInPlace(head1));

        System.out.println("Original List");
        printLinkedList(head2);
        System.out.println("After modification List");
        printLinkedList(twoPointersInPlace(head2));

    }

    /**
     * Approach 1
     * two pointers odd start at head and even starts at head.next;
     * loop odd till null followed by even till null
     * This is two pointer approach but not in place. Note that we are creating a new linked list here and hence space
     * complexity would be O(n)
     */
    public static Node twoPointers(Node head) {
        // Create a dummy node and assign it to odd
        Node oddDummy = new Node(0);
        Node odd = oddDummy;

        // odd start at head and even starts at head.next
        Node currOdd = head;
        Node currEven = head.next;

        // iterate currOdd pointer till currOdd.next becomes null
        while(currOdd != null) {

            // create a new node with value
            Node newNode = new Node(currOdd.data);

            // assign new node to odd.next
            odd.next = newNode;

            // increment odd pointer
            odd = odd.next;

            // if currOdd pointer reaches last - break from the loop
            if(currOdd.next == null) {
                break;
            } else {
                // move currOdd pointer by two steps.
                currOdd = currOdd.next.next;
            }
        }

        // Now iterate even indexes and follow the same as in odd case
        while(currEven != null) {
            Node newNode = new Node(currEven.data);
            odd.next = newNode;
            odd = odd.next;
            if(currEven.next == null) {
                break;
            } else {
                currEven = currEven.next.next;
            }
        }
        // return the head.
        return oddDummy.next;
    }

    /**
     * Create two pointers odd and even
     */
    public static Node twoPointersInPlace(Node head) {
        if(head == null || head.next == null) {
            return head;
        }

        Node odd = head;
        Node even = head.next;
        Node evenHead = even;

        // odd      1   3   5
        // even       2   4   6
        while(even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }

        // Append evenList after odd
        odd.next = evenHead;
        return head;
    }
}
