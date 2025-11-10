package practice.linkedlist;

/**
 * You are given the head of a linked list.
 *
 * The nodes in the linked list are sequentially assigned to non-empty groups whose lengths form the sequence of the
 * natural numbers (1, 2, 3, 4, ...). The length of a group is the number of nodes assigned to it. In other words,
 *
 * The 1st node is assigned to the first group.
 * The 2nd and the 3rd nodes are assigned to the second group.
 * The 4th, 5th, and 6th nodes are assigned to the third group, and so on.
 * Note that the length of the last group may be less than or equal to 1 + the length of the second to last group.
 *
 * Reverse the nodes in each group with an even length, and return the head of the modified linked list.
 * The groups which are reversed are shown in bracket
 *
 * Input: head = [5,{2,6},3,9,1,{7,3,8,4}]
 * Output: [5,6,2,3,9,1,4,8,3,7]
 * Explanation:
 * - The length of the first group is 1, which is odd, hence no reversal occurs.
 * - The length of the second group is 2, which is even, hence the nodes are reversed.
 * - The length of the third group is 3, which is odd, hence no reversal occurs.
 * - The length of the last group is 4, which is even, hence the nodes are reversed.
 *
 * Input: head = [1,{1,0},6]
 * Output: [1,0,1,6]
 * Explanation:
 * - The length of the first group is 1. No reversal occurs.
 * - The length of the second group is 2. The nodes are reversed.
 * - The length of the last group is 1. No reversal occurs.
 *
 * Input: head = [1,{1,0},{6,5}]
 * Output: [1,0,1,5,6]
 * Explanation:
 * - The length of the first group is 1. No reversal occurs.
 * - The length of the second group is 2. The nodes are reversed.
 * - The length of the last group is 2. The nodes are reversed.
 */
public class ReverseInEvenLengthGroup {
    public static void main(String args[]) {
        Node head1 = new Node(5);
        head1.next = new Node(2);
        head1.next.next = new Node(6);
        head1.next.next.next = new Node(3);
        head1.next.next.next.next = new Node(9);
        head1.next.next.next.next.next = new Node(1);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next.next= new Node(3);
        head1.next.next.next.next.next.next.next.next= new Node(8);
        head1.next.next.next.next.next.next.next.next.next= new Node(4);



        Node head2 = new Node(1);
        head2.next = new Node(1);
        head2.next.next = new Node(0);
        head2.next.next.next = new Node(6);

        Node head3 = new Node(1);
        head3.next = new Node(1);
        head3.next.next = new Node(0);
        head3.next.next.next = new Node(6);
        head3.next.next.next.next = new Node(5);

    }

    /**
     * Observation -
     * group        #of elements        reversal
     * 1            1                   no
     * 2            2                   yes
     * 2            1                   no
     * 3            1,3                 no
     * 3            2                   yes
     * 4            1,3                 no
     * 4            2,4                 yes
     *
     * so in every group except first -- if there are even no of elements -- reversal is required.
     */

    public static Node reverseGroup(Node head) {
        // Head will always be the first node
        Node resultHead = new Node(head.data);
        // Now head points to the next element
        head = head.next;

        int group = 2;
        while(head != null) {
            // if the group is even - check if there are even no of elements
            if(group % 2 == 0) {
                // check if even no of elements are possible here
                int count = 0;
                Node curr = head;
                while(curr != null) {
                    if (count == 2) {
                        break;
                    } else {
                        count++;
                        curr = curr.next;
                    }
                }
                // check count here. if even - go for reversal. if odd

                } else {
                // group is odd.
                // check if there are odd no of elements. if yes  no need to reverse
            }
        }
        return null;
    }
}
