package practice.linkedlist;

import java.util.LinkedHashMap;
import java.util.Map;

import static practice.linkedlist.ReverseLinkedList.printLinkedList;


/**
 * In this problem, we are given the head of a linked list that is already sorted. Our task is to delete all nodes that
 * have duplicate numbers, leaving only those numbers that appear exactly once in the original list.
 * It is important to note that the returned linked list must be sorted as well, which is naturally maintained as we
 * only remove duplicates from the already sorted list.
 * For example,
 *
 * I/P: 1->2->3->3->3->4->4->5,
 * O/P: 1->2->5
 *
 * since 3 and 4 are duplicates and should be removed.
 */
public class RemoveDuplicatesFromSortedList2 {
    public static void main(String args[]) {
        Node head1 = new Node(1);   //6
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(3);
        head1.next.next.next.next = new Node(3);
        head1.next.next.next.next.next = new Node(4);
        head1.next.next.next.next.next.next = new Node(4);


/*        System.out.println("Original List");
        printLinkedList(head1);
        System.out.println("After modification List");
        printLinkedList(usingHash(head1));*/

        System.out.println("Original List");
        printLinkedList(head1);
        System.out.println("After modification List");
        printLinkedList(iterative(head1));
    }

    /**
     * Approach 1 - use Linked Hash map
     *
     * Approach 2 - iterative. iterate the list and keep deleting
     */

    public static Node usingHash(Node head) {
        if(head == null || head.next == null) {
            return head;
        }

        // use linked hash map to retain ordering
        LinkedHashMap<Integer, Boolean> linkedHashMap = new LinkedHashMap<>();
        int deletedData = 0;

        while(head != null) {
            if(!linkedHashMap.isEmpty() && linkedHashMap.containsKey(head.data)) {
                // delete this data and do not add to hash map if you see this data again.
                deletedData = head.data;
                linkedHashMap.remove(head.data);
            } else {
                if(head.data != deletedData) {
                    linkedHashMap.put(head.data, true);
                }
            }
            head = head.next;
        }

        // now loop the hash set and create the new list
        Node dummyNode = new Node(-1);
        Node curr = dummyNode;
        for(Map.Entry<Integer, Boolean> node : linkedHashMap.entrySet()) {
            curr.next = new Node(node.getKey());
            curr = curr.next;
        }

        return dummyNode.next;
    }

    /**
     * How to do it iteratively
     * two pointers prev and next
     * if next == next.next ---> delete next
     */

    public static Node iterative(Node head) {
        if(head == null || head.next == null) {
            return head;
        }

        Node dummy = new Node(-1, head);
        Node prev = dummy;

        // This pointer will traverse the original list
        Node curr = head;

        while(curr != null) {

            // skip all nodes that has the same value as curr
            while (curr.next != null && curr.next.data == curr.data) {
                curr = curr.next;
            }

            // if prev.next == curr --> this mean there were no duplicates. and hence prev == curr
            if(prev.next == curr) {
                prev = curr;
            } else {
                // there were duplicates. prev.next will point to curr. next as curr points to duplicate number.
                prev.next = curr.next;

            }
            // move curr pointer by 1
            curr = curr.next;
        }
        return dummy.next;
    }
}
