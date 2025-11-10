package practice.linkedlist;

import static practice.linkedlist.ReverseLinkedList.printLinkedList;

/**
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a
 * multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 */
public class ReverseNodesInKGroup {
    public static void main(String args[]) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        int i =0, j=2, k=3;

        Node dummyNode   = new Node(0);
        Node curr = dummyNode;
        while(i<5) {
            i++;
            curr.next = new Node(i);
            curr = curr.next;
        }

        System.out.print("Original Linked list ");
        printLinkedList(head);
        Node reversedList1 = reverseInKGroup(head, j);
        printLinkedList(reversedList1);

        System.out.print("Original Linked list ");
        printLinkedList(dummyNode.next);
        Node reversedList2 = reverseInKGroup(dummyNode.next, k);
        printLinkedList(reversedList2);
    }

    /**
     * Observation - write a method to reverse linked list.
     * 1->2->3->4->5->null
     * 1,2 ---> 2->1    head = 2    firstReversed = 1   firstreversed.next = head of next one
     * 3,4 ---> 4->3    head = 4    firstreversed = 4   firstreversed.next = head of next one
     * 5   ---> 5
     * 2-->1-->4-->3-->5
     */
    private static Node reverseInKGroup(Node head, int k) {
        if(head == null || k ==1) {
            return head;
        }
        Node dummyNode = new Node(0, head);
        Node predecessor = dummyNode, current = dummyNode;

        while(current.next != null) {

            // check if there are j nodes to reverse
            for(int i=0; i<k && current!= null; i++) {
                current = current.next;
            }

            // if less than j nodes remain - no more reversal is required
            if(current == null) {
                return dummyNode.next;
            }

            // There are j nodes to reverse. Do the reversal
            // Temporary store the next segment to be addressed for reversal
            Node temp = current.next;

            // Detach the k nodes from the rest of the list
            current.next = null;

            //start will be the end node after reversal
            Node start = predecessor.next;

            //Reverse the k nodes
            predecessor.next = reverseList(start);

            //connect the new tail
            start.next = temp;

            //Move the predecessor and current pointer k nodes ahead
            predecessor = start;
            current = predecessor;
        }
        return dummyNode.next;
    }

    /**
     * Helper method to reverse the node
     */
    private static Node reverseList(Node head) {
        Node previous = null, current = head;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }
}
