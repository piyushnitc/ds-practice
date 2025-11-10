package practice.linkedlist;


import static practice.linkedlist.ReverseLinkedList.printLinkedList;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 *
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 *
 * Input: head = []
 * Output: []
 */
public class SortLinkedList {
    public static void main(String args[]) {
        Node head1 = new Node(1);   //6
        head1.next = new Node(4);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(2);
        head1.next.next.next.next = new Node(16);
        head1.next.next.next.next.next = new Node(9);

        Node head2 = new Node(-5);   //6
        head2.next = new Node(10);
        head2.next.next = new Node(20);
        head2.next.next.next = new Node(3);
        head2.next.next.next.next = new Node(31);

        System.out.println("Original List");
        printLinkedList(head1);
        System.out.println("After modification List");
        printLinkedList(mergeSort(head1));

        System.out.println("Original List");
        printLinkedList(head2);
        System.out.println("After modification List");
        printLinkedList(mergeSort(head2));
    }

    /**
     * Approach 1 -
     * dump all the elements in an array list and sort the array.  T.C = O(nlogn)  S.C = O(n)
     *
     * Approach 2
     * How can we sort the ll without extra space
     *  -- think?  can we swap the elements in place
     *  -- try merge sort -- divide the list in two halves and recursively call divide function untill there is nothing
     *  left. after that merge the list
     *  T.C = O(nlogn) and S.C = O(logn) -- because of recursion stack
     *  for t.c --> tasks are subdivided into two subproblems -- logn
     *  In each level of the recursion, all n elements have to be looked at to merge the sublists, contributing to the n factor.
     */
    public static Node mergeSort(Node head) {
        // Base case
        if(head == null || head.next == null) {
            return head;
        }
        // use two pointer to divide the list
        Node slow = head;
        Node fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // at this point slow will point one less to middle node
        Node mid = slow.next;
        slow.next = null;

        //recursively sort the two halves
        Node leftSide = mergeSort(head);
        Node rightSide = mergeSort(mid);

        return merge(leftSide, rightSide);
    }

    private static Node merge(Node left, Node right) {
        Node dummy = new Node(-1);
        Node current = dummy;

        while(left != null && right != null) {
            if (left.data <= right.data) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        // if there are any elements left on left side add them up
        current.next = (left == null) ? right : left;
        return dummy.next;
    }

}

