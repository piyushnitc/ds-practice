package practice.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static practice.linkedlist.ReverseLinkedList.printLinkedList;


/**
 * You are given the head of a linked list.
 * Remove every node which has a node with a greater value anywhere to the right side of it.
 *
 * Return the head of the modified linked list.
 *
 * Input: head = [5,2,13,3,8]
 * Output: [13,8]
 * Explanation: The nodes that should be removed are 5, 2 and 3.
 * - Node 13 is to the right of node 5.
 * - Node 13 is to the right of node 2.
 * - Node 8 is to the right of node 3.
 * Example 2:
 *
 * Input: head = [1,1,1,1]
 * Output: [1,1,1,1]
 * Explanation: Every node has value 1, so no nodes are removed.
 */
public class RemoveNodesFromLinkedList {
    public static void main(String args[]) {
        Node head1 = new Node(5);
        head1.next = new Node(2);
        head1.next.next = new Node(13);
        head1.next.next.next = new Node(3);
        head1.next.next.next.next = new Node(26);

        Node head2 = new Node(1);
        head2.next = new Node(1);
        head2.next.next = new Node(1);
        head2.next.next.next = new Node(1);
        head2.next.next.next.next = new Node(1);

        Node head3 = new Node(5);
        head3.next = new Node(2);
        head3.next.next = new Node(13);
        head3.next.next.next = new Node(3);
        head3.next.next.next.next = new Node(2);

/*        printLinkedList(head1);
        Node newList1 = removeNodes(head1);
        printLinkedList(newList1);

        printLinkedList(head2);
        Node newList2 = removeNodes(head2);
        printLinkedList(newList2);

        printLinkedList(head3);
        Node newList3 = removeNodes(head3);
        printLinkedList(newList3);*/

/*        printLinkedList(head1);
        Node newList1 = usingArrayList(head1);
        printLinkedList(newList1);

        printLinkedList(head2);
        Node newList2 = usingArrayList(head2);
        printLinkedList(newList2);

        printLinkedList(head3);
        Node newList3 = usingArrayList(head3);
        printLinkedList(newList3);*/

        printLinkedList(head1);
        Node newList1 = efficientApproach(head1);
        printLinkedList(newList1);

        printLinkedList(head2);
        Node newList2 = efficientApproach(head2);
        printLinkedList(newList2);

        printLinkedList(head3);
        Node newList3 = efficientApproach(head3);
        printLinkedList(newList3);


    }

    /**
     * using stack
     * push all the elements to stack. lets assume that the last one is the maximum. create a result node with this.
     * keep iterating stack for any values which are greater than max.
     */
    public static Node removeNodes(Node head) {
        Stack<Integer> stack  = new Stack<>();

        // This effectively reverses the ll data.
        while(head != null) {
            stack.push(head.data);
            head = head.next;
        }

        // create a result head from the top of the stack
        int max = stack.pop();
        Node resultHead = new Node(max);

        // iterate through the stack
        while(!stack.isEmpty()) {
            if(stack.peek() >= max) {
                max = stack.pop();
                Node newResultHead = new Node(max);
                newResultHead.next = resultHead;
                resultHead = newResultHead;
            } else {
                stack.pop();
            }
        }
        return resultHead;
    }

    /**
     * using arrayList
     */

    public static Node usingArrayList(Node head) {
        List<Integer> arr = new ArrayList<>();      // S.C = O(n)
        //arr.add(new Node(0,head));

        while(head != null) {
            arr.add(head.data);
            head = head.next;
        }
        // Array is populated with all the elements
        // Now traverse the array from end to start
        int max = arr.get(arr.size()-1);
        Node resultHead = new Node(max);
        for(int i= arr.size()-2; i>=0; i--) {           // T.C = O(n)
            if(max <= arr.get(i)) {
                max = arr.get(i);
                Node newHead = new Node(max);
                newHead.next = resultHead;
                resultHead = newHead;
            }
        }
        return resultHead;
    }
    /**
     * what if we iterate the nodes in reverse order and tracking max
     * the last node will always be the part of the modified list
     */
    public static Node efficientApproach(Node head) {

        //The list is reversed and the head is pointing to the rightmost element
        Node reverseList = reverseList(head);

        int max = reverseList.data;
        Node resultHead = new Node(max);
        reverseList = reverseList.next;

        while(reverseList != null) {
            if (max <= reverseList.data) {
                max = reverseList.data;
                Node newResultHead = new Node(max);
                newResultHead.next = resultHead;
                resultHead = newResultHead;
            }
            reverseList = reverseList.next;
        }
        return resultHead;
    }

    private static Node reverseList(Node head) {
        Node prev=null, curr=head, next = null;
        while(curr!= null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
