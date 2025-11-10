package practice.linkedlist.twoPointer;

import practice.linkedlist.Node;

import static practice.linkedlist.ReverseLinkedList.printLinkedList;


/**
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 *
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * Input: list1 = [], list2 = []
 * Output: []
 *
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 */
public class MergeSortedList {
    public static void main(String args[]) {
        Node list1 = new Node(1);
        list1.next = new Node(2);
        list1.next.next = new Node(4);

        Node list2 = new Node(1);
        list2.next = new Node(3);
        list2.next.next = new Node(4);

        Node list3 = null;
        Node list4 = null;

        Node list5 = null;
        Node list6 = new Node(0);

        printLinkedList(iterativeMerge(list1, list2));

        /**
         * Approach 1
         * - dump all the elements to array. sort the array and create a linked list from sorted array
         * T.C = O(m+n) * log(m+n)  S.C = O(m+n)
         *
         * Approach 2
         * - use iterative merge
         * - traverse the first ll and start creating the linked list till you find smaller number in list2
         * - shift the pointer to list 2 and iterate list2
         */

    }

    public static Node iterativeMerge(Node listA, Node listB) {
        Node nodeA = listA;
        Node nodeB = listB;
        Node dummy = new Node(0);
        Node resultHead = dummy;

        // 1, 2, 4   1, 3, 4
        while(nodeA != null && nodeB != null) {
            if(nodeA.data <= nodeB.data) {
                resultHead.next = nodeA;
                nodeA = nodeA.next;
            } else if(nodeB.data <= nodeA.data){
                resultHead.next = nodeB;
                nodeB = nodeB.next;
            }
            resultHead = resultHead.next;
        }

        while(nodeA != null) {
            resultHead.next = nodeA;
            nodeA = nodeA.next;
        }

        while(nodeB != null) {
            resultHead.next = nodeB;
            nodeB = nodeB.next;
        }
        return dummy.next;
    }
}
