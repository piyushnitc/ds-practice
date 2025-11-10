package practice.linkedlist.twoPointer;


import practice.linkedlist.Node;

/**
 * Given a singly linked list, write a program to find the middle node of the linked list. If the number of nodes is
 * even, we need to return the second middle node.
 *
 * Example 1: Input: 5->4->3->2->1, Output: 3
 * Explanation: Here the number of nodes is 5, so there is one middle node which is 3.
 *
 * Example 2: Input: 6->5->4->3->2->1, Output: 3
 * Explanation: The number of nodes is 6, where the first middle node is 4 and the second middle node is 3.
 * So we need to return the pointer to the node 3.
 */
public class MiddleLinkedList {

    /**
     * Approach
     * 1. length of ll = odd then find odd/2 element
     * if even -- find (even/2)+1 element
     *
     * while traversing ll -- keep adding values to array and get the count. return the element.
     * T.C = O(n)  S.C = O(n)-- because of array
     *
     * 2. extra memory
     *  -- can use stack or array list to put all the elements and do the counting. if using array list
     *  get arr[n/2+1]
     *
     * 3. double traversal (better approach than extra memory)
     * -- traverse and find the count. then traverse till count/2 and return head.next
     *
     * 4. single traversal (good approach)
     *  -- We initialize a variable count to track the node count.
     *  -- Also, we initialize a pointer middle to track the middle node
     *  -- return middle.next
     *
     * 5. two pointers (slow and fast pointer)
     *  --
     */
    public static void main(String args[]) {
        Node head1 = new Node(5);
        head1.next = new Node(4);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(2);
        head1.next.next.next.next = new Node(1);

        Node head2 = new Node(6);
        head2.next = new Node(5);
        head2.next.next = new Node(4);
        head2.next.next.next = new Node(3);
        head2.next.next.next.next = new Node(2);
        head2.next.next.next.next.next = new Node(1);

//        System.out.println(doubleTraversal(head1));
//        System.out.println(doubleTraversal(head2));
//
//        System.out.println(singleTraversal(head1));
//        System.out.println(singleTraversal(head2));

        System.out.println(twoPointerApproach(head1));
        System.out.println(twoPointerApproach(head2));
    }
    public static int doubleTraversal(Node head) {
        Node middle = head;
        int i=0, count=0;

        while(head != null) {       // T.C = O(n)
            count++;
            head = head.next;
        }
        // count = 5 count/2 = 2 i=0,1 middle = 3rd element
        // count = 6 count/2 = 3 i=0,1,2 middle = 4th element
        while(i <count/2) {     // T.C = O(n)
            i++;
            middle = middle.next;
        }
        return middle.data;     // S.C = O(1)
    }

    public static int singleTraversal(Node head) {
        int count=0;
        Node middle = head;

        while(head.next != null) {
            if(count % 2 == 0) {
                middle = middle.next;
            }
            count++;
            head = head.next;
        }
        return middle.data;
    }

    public static int twoPointerApproach(Node head) {
        /**
         * create two pointer slow and fast. increment slow by 1 and fast by 2 untill fast!= null
         */
        Node slow = head, fast=head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }
}
