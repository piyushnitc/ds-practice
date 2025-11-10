package practice.linkedlist.twoPointer;

import practice.linkedlist.Node;

import java.util.HashSet;

/**
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the
 * two linked lists have no intersection at all, return null.
 *
 * For example, the following two linked lists begin to intersect at node c1
 * Note that the linked lists must retain their original structure after the function returns.
 *
 * Custom Judge:
 *
 * The inputs to the judge are given as follows (your program is not given these inputs):
 *
 * intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 * The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your
 * program. If you correctly return the intersected node, then your solution will be accepted.
 *
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Intersected at '8'
 * Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before
 * the intersected node in A; There are 3 nodes before the intersected node in B.
 * - Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd
 * node in B) are different node references. In other words, they point to two different locations in memory, while
 * the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.
 *
 * Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * Output: Intersected at '2'
 * Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the
 * intersected node in A; There are 1 node before the intersected node in B.
 *
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * Output: No intersection
 * Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do
 * not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 * Explanation: The two lists do not intersect, so return null.
 */
public class IntersectionOfTwoLL {
    public static void main (String args[]) {
        Node listA = new Node(4);
        listA.next = new Node(1);
        listA.next.next = new Node(8);
        listA.next.next.next = new Node(4);
        listA.next.next.next.next = new Node(5);

        Node listB = new Node(5);
        listB.next = new Node(6);
        listB.next.next = new Node(1);
        listB.next.next.next = listA.next.next;
        listB.next.next.next.next = listA.next.next.next;
        listB.next.next.next.next.next = listA.next.next.next.next;

        Node listC = new Node(1);
        listC.next = new Node(9);
        listC.next.next = new Node(1);
        listC.next.next.next = new Node(2);
        listC.next.next.next.next = new Node(4);

        Node listD = new Node(3);
        listD.next = listC.next.next.next;
        listD.next.next = listC.next.next.next.next;

        Node listE = new Node(2);
        listE.next = new Node(6);
        listE.next.next = new Node(4);

        Node listF = new Node(1);
        listF.next = new Node(5);

/*        System.out.println(nestedApproach(listA, listB));
        System.out.println(nestedApproach(listC, listD));
        System.out.println(nestedApproach(listE, listF));

        System.out.println(usingHashSet(listA, listB));
        System.out.println(usingHashSet(listC, listD));
        System.out.println(usingHashSet(listE, listF));

        System.out.println(countDiff(listA, listB));
        System.out.println(countDiff(listC, listD));
        System.out.println(countDiff(listE, listF));*/

        Node list1 = new Node(4);
        list1.next = new Node(1);
        list1.next.next = new Node(8);

        Node list2 = new Node(5);
        list2.next = new Node(7);
        list2.next.next = new Node(9);

        System.out.println(twoPointerApproach(list1, list2));
        System.out.println(twoPointerApproach(listA, listB));
        System.out.println(twoPointerApproach(listC, listD));
        System.out.println(twoPointerApproach(listE, listF));

    }

    /**
     * Approach 1
     * - two nested loops - run outer loop on listA and check if there are any references on listB
     * T.C = O(m*n) S.C = O(1)
     *
     * Approach 2
     * - use HashSet. put all the listA in hash map and check when listB can be found in Map
     * T.C = O(m+n)  S.C = O(m)
     *
     * Approach 3
     * - count the length of both the list. l1 and l2.
     * - if l1> l2 --> d= l1-l2  else d = l2-l1;
     * - start pointer of l1 = d end start pointer of l2 = 1
     * - else start pointer of l2 = d end start pointer of l1 = 1
     * - now start a for loop from here and check the elements in both list if they are equal anywhere
     *
     * Approach 4
     * - two pointer approach
     *
     * -
     */

    public static int nestedApproach(Node listA, Node listB) {
        Node nodeA = listA;
        Node nodeB = listB;

        if(listA == null || listB == null) {
            return 0;
        }

        while(nodeA != null) {
            // set nodeB pointer to head
            nodeB = listB;
            while(nodeB != null) {

                if(nodeA == nodeB) {
                    return nodeA.data;
                }
                nodeB = nodeB.next;
            }
            nodeA = nodeA.next;
        }
        return 0;
    }

    public static int usingHashSet(Node listA, Node listB) {
        HashSet<Node> nodeSet = new HashSet<>();
        Node nodeA = listA;
        Node nodeB = listB;

        while(nodeA != null) {
            nodeSet.add(nodeA);
            nodeA = nodeA.next;
        }

        while(nodeB != null) {
            if(nodeSet.contains(nodeB)) {
                return nodeB.data;
            }
            nodeB = nodeB.next;
        }
        return 0;
    }

    public static int countDiff(Node listA, Node listB) {
        int length1 = 0, length2 = 0;
        Node nodeA = listA;
        Node nodeB = listB;
        int startPointerA = 0;
        int startPointerB = 0;

        while(nodeA != null) {
            length1++;
            nodeA = nodeA.next;
        }

        while(nodeB != null) {
            length2++;
            nodeB = nodeB.next;
        }

        //reset pointers to head
        nodeA = listA;
        nodeB = listB;

        if(length1 >= length2) {
            startPointerA = length1 - length2;
            startPointerB = 0;
        } else {
            startPointerA = 0;
            startPointerB = length2 - length1;
        }

        // Pointers aligned
        if(startPointerA == 0) {
            for(int i=0; i<startPointerB; i++) {
                nodeB = nodeB.next;
            }
        } else {
            for(int i=0; i<startPointerA; i++) {
                nodeA = nodeA.next;
            }
        }

        Node curr1 = nodeA;
        Node curr2 = nodeB;

        int loopLength = Math.min(length1,length2);

        for(int j=0; j<loopLength; j++) {
            if(curr1 == curr2) {
                return curr1.data;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return 0;
    }

    /**
     * idea is tow initialize two pointers one for list A and another for listB
     * when one pointer reaches end --> reassign to the other list head
     * when another pointer reaches end --> reassign to the other list head.
     *
     * if both intersect in the second pass --> return value else 0;
     */
    public static int twoPointerApproach(Node listA, Node listB) {
        Node ptr1 = listA;
        Node ptr2 = listB;

        if(ptr1 == null || ptr2 == null) {
            return 0;
        }

        while(ptr1 != ptr2) {
            ptr1 = (ptr1 != null) ? ptr1.next : listB;
            ptr2 = (ptr2 != null) ? ptr2.next : listA;
        }
        return ptr1 != null ? ptr1.data : 0 ;
    }
}
