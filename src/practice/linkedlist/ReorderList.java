package practice.linkedlist;

import java.util.Stack;

import static practice.linkedlist.ReverseLinkedList.printLinkedList;


/**
 * You are given the head of a singly linked-list. The list can be represented as:
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 *
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 */
public class ReorderList {
    public static void main(String args[]) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);

/*        System.out.println("Original List");
        printLinkedList(head1);
        System.out.println("After modification List");
        printLinkedList(usingStack(head1));

        System.out.println("Original List");
        printLinkedList(head2);
        System.out.println("After modification List");
        printLinkedList(usingStack(head2));*/

        System.out.println("Original List");
        printLinkedList(head1);
        System.out.println("After modification List");
        printLinkedList(divideAndMerge(head1));

        System.out.println("Original List");
        printLinkedList(head2);
        System.out.println("After modification List");
        printLinkedList(divideAndMerge(head2));

    }
    /**
     * Approach 1 -- keep the half part of ll in stack.
     * to find the mid point -> we will use two pointers slow and fast
     */
    public static Node usingStack(Node head) {
        Stack<Node> stack = new Stack<>();
        Node slow = head;
        Node fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // this will be the right side which goes to stack
        Node right = slow.next;

        // anything from slow would be the right part
        slow.next = null;

        // push all the right elements to stack.
        while(right != null) {
            stack.push(right);
            right = right.next;
        }

        Node dummy = new Node(-1);
        Node curr = dummy;
        // now iterate head and stack and keep adding nodes
        while(head!= null && !stack.isEmpty()) {
            curr.next = head;
            head = head.next;

            curr = curr.next;
            curr.next = stack.pop();

            curr = curr.next;
        }

        // add remaining elements
        while(head != null) {
            curr.next = head;
            head = head.next;
            curr = curr.next;
        }
        return dummy.next;
    }

    /**
     * Alternative approach
     * - find the mid point and reverse the right side of the list
     * - we will end up with 2 list. left and right
     * - merge the two halves together by taking alternte elements from the list
     */

    public static Node divideAndMerge(Node head) {
        Node slow = head;
        Node fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node mid = slow.next;

        // reverse the mid
        Node reverseList = reverse(mid);
        Node curr = head; // this should hold laf the elements.

        while(reverseList != null) {
            Node temp = reverseList.next;
            reverseList.next = curr.next;
            curr.next = reverseList;
            curr = reverseList.next;
            reverseList = temp;
        }
        return head;
    }

    private static Node reverse(Node head) {
        if(head == null || head.next == null) {
            return head;
        }
        Node prev = null, next = null, curr = head;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
