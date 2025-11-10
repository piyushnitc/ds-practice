package practice.linkedlist;


import static practice.linkedlist.ReverseLinkedList.printLinkedList;

/**
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
 * Return the linked list sorted as well.
 *
 * Input: head = [1,1,2]
 * Output: [1,2]
 *
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 */
public class RemoveDuplicatesFromSortedList {
    public static void main(String args[]) {
        Node head1 = new Node(1);   //6
        head1.next = new Node(1);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(1);
        head1.next.next.next.next = new Node(2);
        head1.next.next.next.next.next = new Node(3);

        Node head2 = new Node(1);   //6
        head2.next = new Node(1);
        head2.next.next = new Node(2);
        head2.next.next.next = new Node(3);
        head2.next.next.next.next = new Node(3);

        System.out.println("Original List");
        printLinkedList(head1);
        System.out.println("After modification List");
        printLinkedList(iterative(head1));

        System.out.println("Original List");
        printLinkedList(head2);
        System.out.println("After modification List");
        printLinkedList(iterative(head2));


    }

    /**
     * Approach 1 - iterative
     * need to check if curr.data == curr.next.data
     *      -- if yes -- skip the node
     *      -- if no -- continue
     */

    public static Node iterative(Node head) {
        if(head == null) {
            return null;
        }

        Node curr = head;
        // Traverse the list
        while(curr != null && curr.next != null) {
            if(curr.data == curr.next.data) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;

        // 1,1, 2,3,3

    }
}
