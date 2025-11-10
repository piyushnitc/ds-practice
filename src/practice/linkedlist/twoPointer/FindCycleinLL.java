package practice.linkedlist.twoPointer;

import practice.linkedlist.Node;

import java.util.HashMap;

/**
 * Given the head of a linked list, write a program to find if linked list has a cycle or not. Return true if there is
 * a cycle, otherwise, return false.
 */
public class FindCycleinLL {
    public static void main(String args[]) {
        Node head1 = new Node(5);
        head1.next = new Node(4);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(2);
        head1.next.next.next.next = new Node(1);

        Node head2 = new Node(5);
        head2.next = new Node(4);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(2);
        head2.next.next.next.next = new Node(1);
        head2.next.next.next.next.next = new Node(9);
        head2.next.next.next.next.next.next = head2.next.next;

        //System.out.println(usingHashTable(head1));
        //System.out.println(usingHashTable(head2));

        //System.out.println(usingNodeStruct(head1));
        //System.out.println(usingNodeStruct(head2));

        //System.out.println(floydDetection(head1));
        //System.out.println(floydDetection(head2));

        //System.out.println(floydDetectionStartCycle(head1));
        System.out.println(floydDetectionStartCycle(head2));

    }

    /**
     * Approaches
     * 1. use a hash table to keep a note to visited node. if node is found in the hash map -- then cycle exist
     * 2. use a flag inside node structure itself
     * 3. use floyd cycle detection algorithm
     */

    public static boolean usingHashTable(Node head) {
        HashMap<Node, Boolean> nodeMap = new HashMap<Node, Boolean>();
        Node currNode = head;

        while (currNode != null) {
            if(nodeMap.containsKey(currNode)) {
                return true;
            } else {
                nodeMap.put(currNode, true);
            }
            currNode = currNode.next;
        }
        return false;
    }

    public static boolean usingNodeStruct(Node head) {
        Node curr = head;

        while(curr != null) {
            if(Boolean.valueOf(curr.visited)) {
                return true;
            } else {
                curr.visited = true;
            }
            curr = curr.next;
        }
        return false;
    }

    /**
     * FLyod detection algo tells us that if slow pointer moves by 1 and fast pointer moves by 2
     * eventually slow and fast pointer would meet at some point in some time - if cycle exists.
     */
    public static boolean floydDetection(Node head) {
        Node slow = head, fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find the start of the cycle.
     */
    public static Node floydDetectionStartCycle(Node head) {
        Node slow = head, fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                // reset one pointer to head
                slow = head;

                // the next time slow and fast pointer will meet at start of the cycle
                while(slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow; // start of the cycle
            }
        }
        return null; // no cycle
    }
}
