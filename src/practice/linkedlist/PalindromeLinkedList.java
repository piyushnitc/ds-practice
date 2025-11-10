package practice.linkedlist;

import java.util.Stack;

/**
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 * Input: head = [1,2,2,1]
 * Output: true
 *
 * Input: head = [1,2]
 * Output: false
 *
 * Input: head = [1,2,3,2,1]
 * Output: true
 */
public class PalindromeLinkedList {
    public static void main(String args[]) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(2);
        head1.next.next.next = new Node(1);

        Node head2 = new Node(1);
        head2.next = new Node(2);

        Node head3 = new Node(1);
        head3.next = new Node(2);
        head3.next.next = new Node(3);
        head3.next.next.next = new Node(2);
        head3.next.next.next.next = new Node(1);

        System.out.println(isPalindromArray(head1));
        System.out.println(isPalindromArray(head2));
        System.out.println(isPalindromArray(head3));

        System.out.println(usingStack(head1));
        System.out.println(usingStack(head2));
        System.out.println(usingStack(head3));

        System.out.println(usingRecursion(head1));
        System.out.println(usingRecursion(head2));
        System.out.println(usingRecursion(head3));

        System.out.println(efficientApproach(head1));
        System.out.println(efficientApproach(head2));
        System.out.println(efficientApproach(head3));

        System.out.println(isPalindrome(head1));
        System.out.println(isPalindrome(head2));
        System.out.println(isPalindrome(head3));

    }
    /**
     * Observation - for palindrome - first element = last element
     * if i keep all these elements in an array - i can scan the array from left to right
     * and right to left
     */

    public static boolean isPalindromArray(Node head) {
        // get the size of the linked list
        Node dummy = head;          // S.C = O(n)
        int count = 0;
        while(dummy != null) {      // T.C = O(n)
            count++;
            dummy = dummy.next;
        }

        int[] arr = new int[count]; // S.C = O(n)
        int j = 0;
        // populate array
        while(head != null) {   // T.C = O(n)
            arr[j] = head.data;
            j++;
            head = head.next;
        }

        for(int i=0; i<count; i++) {    // T.C = O(n)
            if(arr[i] != arr[count-1-i]) {
                return false;
            }
        }
        return true; // T.C = 3*O(n)  SpaceComplexity = 2*O(n)
    }

    /**
     * use stack to pop all the elements in stack and compare with linked list
     */
    public static boolean usingStack(Node head) {
        Node curr = head;
        Stack<Integer> stack = new Stack<>();

        // T.C = O(n) S.C = O(n)
        while(curr != null) {
            stack.push(curr.data);
            curr = curr.next;
        }

        // pop out from stack
        while(!stack.isEmpty()) {       // T.C = O(n)
            if(stack.pop() != head.data) {
                return false;
            }
            head = head.next;
        }
        return true;        // T.C = 2O(n) , S.C = O(n)
    }

    /**
     * using recursion - The idea is to initialize a pointer start, which will initially point to the head of the node.
     * Then, recursively traverse the list. At each node end, first recursively check if the right side of the list is
     * palindrome. If yes, then compare the values of the start and end node.
     * If they are equal, then set start = start.next and return true. Otherwise return false.
     */
    public static boolean usingRecursion(Node head) {
        // Set starting node to head
        // Create a single-element array of type Node, where the only element in the array is the variable head.
        Node[] start = new Node[]{head};

        // recursively check the linked list and return
        return isPalindrom(head, start);
    }

    private static boolean isPalindrom(Node head, Node[] start) {
        // whats the base case? when head becomes null
        if(head == null) {
            return true;
        }

        // recursively check the right side
        boolean right = isPalindrom(head.next, start);
        boolean ans = right && start[0].data == head.data;
        // update the start node
        start[0] = start[0].next;


        return ans;
    }

    /**
     * reverse the list from middle and compare elements
     * 1--2--3--2--1 --> take two pointes slow and fast both pointing at the start. let slow move by 1 and fast by 2
     * if fast.next == null ---> reverse the list from slow pointer till the end.
     * start comparing head1 to reverse string
     *
     * explanation
     * slow = 1 fast =1
     * slow = 2 fast = 3
     * slow = 3 fast =1 (after this fast.next = null)
     *
     * we need to reverse the list 2-->1 (slow.next)
     * set slow.next = null // to disconnect slow
     * this will return 1-->2. head2 will be pointing to 1
     *
     * now compare head1 (start at 1) with head2(point at 1)
     */

    public static boolean efficientApproach(Node head) {
        Node start = head, slow = head, fast=head;

        // we get the slow node pointer which points to middle of the node
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node head2 = reverseLinkedList(slow.next);
        // exclusively set the end to head1
        slow.next = null;

        // now compare head2 with head 1
        return isIdentical(start, head2);
    }

    private static boolean isIdentical(Node head1, Node head2) {
        while(head1 != null && head2!=null) {
            if(head1.data != head2.data) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }

    private static Node reverseLinkedList(Node head) {
        Node prev = null, next = null, curr = head;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    public static boolean isPalindrome(Node head) {
        // Use two pointers: slow moves one step at a time and fast moves two steps at a time.
        Node slow = head;
        Node fast = head;

        // Move fast pointer to the end, and slow to the middle of the list
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half of the list
        Node prev = null;
        Node current = slow;
        while (current != null) {
            Node temp = current.next; // Stores the next node
            current.next = prev; // Reverses the link
            prev = current; // Moves prev to current node
            current = temp; // Move to the next node in the original list
        }

        // Compare the reversed second half with the first half
        Node firstHalfIterator = head;
        Node secondHalfIterator = prev;
        while (secondHalfIterator != null) {
            // If values are different, then it's not a palindrome
            if (secondHalfIterator.data != firstHalfIterator.data) {
                return false;
            }

            // Move to the next nodes in both halves
            secondHalfIterator = secondHalfIterator.next;
            firstHalfIterator = firstHalfIterator.next;
        }

        // All values matched, so it's a palindrome
        return true;
    }

}
