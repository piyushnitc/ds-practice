package practice.linkedlist.twoPointer;


import practice.linkedlist.Node;

import static practice.linkedlist.ReverseLinkedList.printLinkedList;

/**
 * Write a program to remove the Nth node from the end of the linked list, i.e., when the node is traversed from the end,
 * delete the Nth node from there.
 *
 * Input: 1->2->3->4->5, N = 2, Output: 1->2->3->5.
 *
 * Input: 7->8->4->5->3, N = 1, Output: 7->8->4->5.
 */
public class DeleteNNode {
    public static void main(String args[]) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);

/*        System.out.println("Original List");
        printLinkedList(head1);
        System.out.println("After modification List");
        printLinkedList(twoPassApproach(head1, 2));

        System.out.println("Original List");
        printLinkedList(head2);
        System.out.println("After modification List");
        printLinkedList(twoPassApproach(head2, 1));*/

        System.out.println("Original List");
        printLinkedList(head1);
        System.out.println("After modification List");
        printLinkedList(twoPointers(head1, 2));

        System.out.println("Original List");
        printLinkedList(head2);
        System.out.println("After modification List");
        printLinkedList(twoPointers(head2, 1));

    }

    /**
     * Approach 1
     * - count the number of elements in the ll
     * - n from end = (count-n+1) from the beginning.
     *  - if its the last element -- set next pointer to null
     *  - if its not the last element -- set next pointer to head.next.next
     *
     */
    public static Node twoPassApproach(Node head, int n) {
        int length = 0;
        Node temp = head;

        // count the length of ll
        while(temp != null) {
            length++;
            temp = temp.next;
        }

        if(n <= 0 || n > length) {
            return head;
        }

        // delete the first element
        if(n == length) {
            return head.next;
        }

        // reset temp to head
        temp = head;

        for(int i=0; i<length-n; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;

        return head;
    }

    /**
     * Approach 2 - two pointer approach
     * introduce a dummy node 0--1--2--3--4--5
     * initialize slow pointer at 0 and fast pointer at 0+N+1 (for N=2) --> at 3
     * when fast reaches null --> slow would be at 3. slow.next = slow.next.next return head
     */
    public static Node twoPointers(Node head, int n) {
        // introduce a dummy node
        Node dummy = new Node(0, head);
        Node slow = dummy;
        Node fast = dummy;

        // initialize fast pointer with gap of n
        int i =0;
        while(i < n+1) {
            fast = fast.next;
            i++;
        }

        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
