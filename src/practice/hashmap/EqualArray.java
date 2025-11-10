package practice.hashmap;

/**
 * Given two integer arrays X[] and Y[], write a program to check if both arrays are equal or not.
 * Two arrays are equal if they have the same elements in any order. If there are repeated elements, then counts of
 * repeated elements must be same in both arrays.
 *
 * Assume that elements in both arrays are non-negative.
 *
 * The size of both arrays may not be the same.
 *
 * Input: X[] = [1, 2, 8], Y[] = [2, 1, 8], Output: Yes
 *
 * Input: X[] = [0, 2, 5, 1, 2, 23], Y[] = [2, 0, 1, 23, 5, 2], Output: Yes
 *
 * Input: X[] = [2, 5, 1, 2], Y[] = [2, 0, 1, 2], Output: No
 */
public class EqualArray {
    public static void main(String args[]) {
        int[] x1 = {1,2,8};
        int[] y1 = {2,1,8};

        int[] x2 = {0,2,5,1,2,23};
        int[] y2 = {2,0,1,23,5,2};

        int[] x3 = {2,5,1,2};
        int[] y3 = {2,0,1,2};
    }

    /**
     * Obs -
     * 1. sort the array O(nlogn) and compare each element O(n)
     * Total T.C = O(nlogn) + O(n)
     *
     * 2. put the first array elements in hash table with frequency. for second array get the elements from hash table
     * and decrement the count by 1.
     * T.C = O(n)  S.C = O(n)
     */


}
