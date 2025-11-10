package practice.linkedlist;

import java.util.Stack;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * I/P: 1-->2-->3-->4-->5-->NULL
 * O/P: 5-->4-->3-->2-->1-->NULL
 *
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 * Input: head = [1,2]
 * Output: [2,1]
 *
 * Input: head = []
 * Output: []
 */
public class ReverseLinkedList {
    public static void main(String args[]) {
        // create test data here
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

//        System.out.print("Original Linked list ");
//        printLinkedList(head);
//        Node reverseList1 = usingStack(head);
//        System.out.print("Reversed Linked list ");
//        printLinkedList(reverseList1);
//
//        System.out.print("Original Linked list ");
//        printLinkedList(head);
//        Node reverseList2 = usingInPlace(head);
//        System.out.print("Reversed Linked list ");
//        printLinkedList(reverseList2);

        System.out.print("Original Linked list ");
        printLinkedList(head);
        Node reverseList3 = usingRecursion(head);
        System.out.print("Reversed Linked list ");
        printLinkedList(reverseList3);
    }

    public static void printLinkedList(Node head) {
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * Observation -- push all the elements to stack. and pop out elements from stack
     * and construct the linked list
     */
    public static Node usingStack(Node head) {
        Stack<Integer> stack = new Stack<>();

        Node temp = head;
        while(temp != null) {   // T.C = O(n)
            stack.push(temp.data);
            temp = temp.next;
        }

        // reset temp to head.
        temp = head;
        while(!stack.isEmpty()) {   // T.C = O(n)
            temp.data = stack.pop();
            temp = temp.next;
        }

        // return the new head of the reversed linked list
        return head;    // T.C = O(2n)  S.C = O(n) because of stack
    }

    /**
     * in place reversal. we initialize three pointers prev, curr and forward
     * prev = null, curr = head and forward = null
     * iterate linked list and change curr to prev and prev to curr and forward to next
     */

    public static Node usingInPlace(Node head) {
        if(head == null) {
            return null;
        }

        Node curr = head;
        Node prev = null;

        while(curr != null) {   // T.C = O(n)
            Node fowd = curr.next;

            // Reverse the direction of the current node's 'next' pointer to point to 'prev'
            curr.next = prev;

            // previous become curre
            prev = curr;

            // current becomes forward
            curr = fowd;
        }
        return prev; // S.C = O(1)
    }

    /**
     * using recursion
     * recursively call the reverse function untill head == null or head.next == null. return head
     */
    //[1,2,3,4,5]
    public static Node usingRecursion(Node head) {

        if(head == null || head.next == null) {
            return head;
        }

        /**
         * when head = 5 head.next = null and hence it would return 5
         * when the recursion unwinds --
         *       head = 4 and head.next = 5
         *       and we want 5.next = 4 (since we are reversing)
         *  Hence,
         *  head.next.next = head  (5.next = 4)
         *  head.next (4.next ) = null;
         */
        Node newHead = usingRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
