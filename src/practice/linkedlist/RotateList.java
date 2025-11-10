package practice.linkedlist;


import static practice.linkedlist.ReverseLinkedList.printLinkedList;

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 */
public class RotateList {
    public static void main(String args[]) {
        Node head1 = new Node(1);                   // k = 2
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        Node head2 = new Node(0);               // k = 4
        head2.next = new Node(1);
        head2.next.next = new Node(2);

//        System.out.println("Original List");
//        printLinkedList(head1);
//        System.out.println("After modification List");
//        printLinkedList(rotate(head1,2));

        System.out.println("Original List");
        printLinkedList(head1);
        System.out.println("After modification List");
        printLinkedList(rotateByN(head1,2));

        System.out.println("Original List");
        printLinkedList(head2);
        System.out.println("After modification List");
        printLinkedList(rotateByN(head2,4));


    }

    /**
     * Approach 1- one rotation means prev of last element = null -- last becomes head.
     * then continue this rotation in loop
     */

    public static Node rotateByOne(Node head) {
        Node curr = head;

        // find the last node.  this way curr will point to one element before last element.
        while(curr.next.next != null) {
            curr = curr.next;
        }
        //here curr is pointing to 1. hence last node will be 1.next  = 2
        Node lastNode = curr.next;

        // set last node to null
        curr.next = null;

        // set last node as next to dummy
        lastNode.next = head;

        // return last node
        return lastNode;
    }

    public static Node rotate(Node head, int n) {
        for(int i=0; i<n; i++) {
            Node rotated = rotateByOne(head);
            head = rotated;
        }
        return head;
    }

    /**
     * Approach 2 - for n=2 take second element from the end. that will be head.
     * add rest of the head to it
     * This method will work only when the count > length. put logic when count > length
     */

    public static Node rotateByN(Node head, int n) {
        Node curr = head;
        int count = 0;
        while(curr != null) {
            count++;
            curr = curr.next;
        }

        if(n > count) {
            n = n-count;
        }
        // set curr to head
        curr = head;

        int i = 1;
        while(i< count-n) {
            curr = curr.next;
            i++;
        }

        // at this point curr points to 3
        Node firstPart = curr.next;
        // set last pointer to null
        curr.next = null;

        // now first part has 4 and 5. iterate till pointer reaches at end.
        Node secCurr = firstPart;
        while(secCurr.next != null) {
            secCurr = secCurr.next;
        }
        // repoint to head.
        secCurr.next = head;
        return firstPart;
    }
}
