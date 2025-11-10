package practice.linkedlist;


import static practice.linkedlist.ReverseLinkedList.printLinkedList;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the
 * list from position left to position right, and return the reversed list.
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 */
public class ReverseLinkedList2 {
    public static void main(String args[]) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.print("Original Linked list ");
        printLinkedList(head);
        Node reverseList3 = reverseLinkedList(head, 2,4);
        System.out.print("Reversed Linked list ");
        printLinkedList(reverseList3);
    }

    /**
     * Observation --> extract the list from original and reverse it.
     * iterate the original list and combine with the reverse list
     */

    private static Node reverseLinkedList(Node head, int left, int right) {
        if(head == null || left == right) {
            return head;
        }

        // Introduce a dummy node before the starting of the node to handle edge cases and always return
        // dummy node. head
        Node dummyNode = new Node(0, head);
        Node nodeBeforeReversal = dummyNode;

        for(int i=0; i<left-1; i++) {
            nodeBeforeReversal = nodeBeforeReversal.next;
        }

        // This will become the last node after the sublist reversal
        Node firstReversed = nodeBeforeReversal.next;

        // now reverse the list
        Node current = nodeBeforeReversal.next;
        Node prev = null;
        Node next = null;

        /**
         * node = 1-->2;  current = 1, next = null, prev = null
         * next = current.nxt = 1.next = 2
         * now i want to reverse the pointer between 1 & 2 so that current.nxt becomes previous. current.nxt = 1.next = prev = null;
         * prev = current = 1
         * current = next = 2
         * so the order is 1 <-- 2
         *
         * after this iterative process current would become null and previous would become 2
         */
        for(int i= 0; i<right-left+1; i++) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        // prev will give the head of the reversed list. 1, 2, [3,4,5], 6, 7
        // after reversal  1,2,[5,4,3], 6, 7
        // prev = [5,4,3] current = 6
        // node before reversal = 1,2
        // connect -- > node before reversal + reversed list + remaining list  (3.next = current)
        // 3 is nothing but first reversed list which becomes the last node and next of this node would be the rest of the nodes
        // 1,2,[5,4,3], 6
        nodeBeforeReversal.next = prev;

        // connect rest of the nodes with nodeBeforeReversal
        firstReversed.next = current;
        return dummyNode.next;

    }


}
