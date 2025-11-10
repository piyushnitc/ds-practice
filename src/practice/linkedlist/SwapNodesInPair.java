package practice.linkedlist;

/**
 * Given a singly linked list, write a program to swap every two adjacent nodes and return its head. If the number of
 * nodes is odd, then we need to pair-wise swap all the elements except the last element.
 *
 * Input: 12->42->9->30->56->20->NULL
 * Output: 42->12->30->9->20->56->NULL
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 2->1->4->3->5->NULL
 */
public class SwapNodesInPair {
    public static void main(String args[]) {
        Node head1 = new Node(12);
        head1.next = new Node(42);
        head1.next.next = new Node(9);
        head1.next.next.next = new Node(30);
        head1.next.next.next.next = new Node(56);
        head1.next.next.next.next.next = new Node(20);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);


    }

    /**
     * Approach 1 -
     * iteratively changing the node pointers
     *
     * Approach 2-
     * recursively changing the node pointers
     *
     * Apprach 3 -  T.C = o(n*n)
     * iteratively changing the node values
     *
     * Approach 4   T.C = O(n*n)
     * recursively changing the node values
     *
     */

    public static Node iterativeChangeNodePointers(Node head) {
        return null;
    }

     // Input: 1->2->3->4->5->6
     // Output: 2->1->4->3->6->5
    public static Node recursiveNodePointer(Node head) {
        // base case - either one node or null
        if(head == null || head.next == null) {
            return head;
        }

        Node remainingListHead = head.next.next;
        Node newHead = head.next;
        newHead.next = head;
        head.next = recursiveNodePointer(remainingListHead);
        return newHead;
    }
}
