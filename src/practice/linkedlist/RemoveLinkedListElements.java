package practice.linkedlist;

import static practice.linkedlist.ReverseLinkedList.printLinkedList;

/**
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val,
 * and return the new head.
 *
 * Input: head = [1,2,6,3,4,5,6], val = 6
 * Output: [1,2,3,4,5]
 *
 * Input: head = [], val = 1
 * Output: []
 *
 * Input: head = [7,7,7,7], val = 7
 * Output: []
 */
public class RemoveLinkedListElements {
    public static void main(String args[]) {
        Node head1 = new Node(1);   //6
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);

        Node head2 = null; //1

        Node head3 = new Node(7); //7
        head3.next = new Node(7);
        head3.next.next = new Node(7);
        head3.next.next.next = new Node(7);
        head3.next.next.next.next = new Node(7);

        System.out.println("Original List");
        printLinkedList(head1);
        System.out.println("After modification List");
        printLinkedList(iterativeApproach(head1, 6));

        System.out.println("Original List");
        printLinkedList(head2);
        System.out.println("After modification List");
        printLinkedList(iterativeApproach(head2, 1));

        System.out.println("Original List");
        printLinkedList(head3);
        System.out.println("After modification List");
        printLinkedList(iterativeApproach(head3, 7));


    }

    /**
     * Approach 1 - iterate the list and compare the value. if value is there delete from list and adjust the pointers
     * use dummy
     */

    public static Node iterativeApproach(Node head, int val) {
        if(head == null) {
            return head;
        }

        Node dummy = new Node(-1);
        dummy.next = head;

        Node prev = dummy;

        while(prev.next != null) {
            if(prev.next.data == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }
}
