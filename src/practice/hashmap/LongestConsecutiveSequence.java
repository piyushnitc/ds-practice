package practice.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array X[] of n integers, write a program to find the length of the longest consecutive sequence. In other
 * words, we need to find the length of the longest subsequence such that the elements in the subsequence are consecutive integers.
 *
 * The consecutive integers can be in any order.
 * There can be more than one consecutive subsequence of the longest length. So we just need to return the length of it.
 * Input integers can be positive, negative, or zero.
 * Input values can be repeated.
 *
 * Input: X[] = [4, 7, 1, 2, 8, 10, 3], Output: 4
 * Explanation: [1, 2, 3, 4] is the longest subsequence of consecutive elements.
 *
 * Input: X[] = [0, -3, 5, -1, 7, -2, -4, 1, 3], Output: 6
 * Explanation: There are two longest consecutive sequences of length 6: [-4, -3, -2, -1, 0, 1] and [-2, -1, 0, 1, 2, 3].
 * So, we return 6 as an output.
 *
 * Input: X[] = [0, 3, 7, 2, 5, 8, 4, 6, 0, 2, 1], Output: 9
 * Explanation: Here 2 and 3 are repeated, but all the unique integers are part of the longest consecutive sequence i.e.
 * 0, 1, 2, 3, 4, 5, 6, 7, 8.
 */
public class LongestConsecutiveSequence {
    public static void main(String args[]) {
        int[] x1 = {4, 7, 1, 2, 8, 10, 3};              // 4
        int[] x2 = {0, -3, 5, -1, 7, -2, -4, 1, 3};     // 6
        int[] x3 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 2, 1};   // 9

        System.out.println(bruteForce(x1));
        System.out.println(bruteForce(x2));
        System.out.println(bruteForce(x3));

        System.out.println(sortingApproach(x1));
        System.out.println(sortingApproach(x2));
        System.out.println(sortingApproach(x3));

        System.out.println(hashMapApproach(x1));
        System.out.println(hashMapApproach(x2));
        System.out.println(hashMapApproach(x3));
    }

    /**
     * Observation
     * 1. use brute force. for every element find the next element in the array and continue to track the longest length
     *
     * 2. Use sorting [4, 7, 1, 2, 8, 10, 3] --> [1,2,3,4,7,8,10] --> now iterate and track the longest sequence
     *
     * 3. use hash map to store the element T.C = O(n) SC = O(n)
     * Now there are two cases ---> the element is an intermediate element of some other sequence
     *                          --> the element is the starting element, and then we count the longest sequence for this
     */

    public static int bruteForce(int[] x) {
        int n = x.length;
        int maxLength = 0;

        for(int i=0; i<n; i++) {    // T.C = O(n)
           int currentElement = x[i];
           int currentLength = 1;

           while(linearSearch(x, n, currentElement+1) == true) { // T.C = O(n) -- linear search & O(n) -- while loop
               currentElement++;
               currentLength++;
           }
           if(currentLength > maxLength) {
               maxLength = currentLength;
           }
;        }
        return maxLength; // T.C = O(n)*O(n)*O(n) = O(n^3)
    }

    private static boolean linearSearch(int[] x, int n, int currElement) {
        for(int i=0; i<n; i++) {
            if(x[i] == currElement) {
                return true;
            }
        }
        return false;
    }

    //[4, 7, 1, 2, 8, 10, 3] --> [1,2,3,4,7,8,10]
    public static int sortingApproach(int[] x) {
        int n = x.length;
        Arrays.sort(x); // T.C = O(nlogn) SC = O(1) -- in place sorting
        int currElement = x[0];
        int currLength = 1;
        int maxLength = 0;

        for(int i=1; i<n; i++) {    // T.C = O(n)
            if(x[i] == currElement+1) {
                currLength++;
                currElement++;
            }
            maxLength = Math.max(currLength, maxLength);
        }
        return maxLength; // T.C = O(n*nlogn)
    }

    public static int hashMapApproach(int[] x) {
        int n = x.length;
        int maxLength = 0;
        Map<Integer, Boolean> map = new HashMap<>();

        //Populate map T.C = O(n)
        for(int num: x) {
            map.put(num, true);
        }

        for(int i=0; i<n; i++) {
            int currentElement = x[i];
            int currentLength = 1;

            while(map.get(currentElement+1) != null) {
                currentElement++;
                currentLength++;
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }

}
