package practice.arrays.twopointer.merging;

/**
 * Given two unsorted arrays X[] and Y[] of sizes n and m respectively, write a program to find the
 * intersection of these two arrays. Suppose n > m and the elements in both arrays are distinct.
 *
 * The intersection represents a list of common elements present in both arrays.
 * The elements in the output can be in any order.
 *
 * Input: X[] = [3, 4, 6, 2, 8, 5, 9], Y[] = [6, 3, 2, 7, 5, 1]
 * Output: [3, 6, 2, 5]
 *
 * Input: X[] = [3, 4, 6, 7, 10, 12, 5], Y[] = [7, 11, 18, 15, 12]
 * Output: [7, 12]
 *
 * Input: X[] = [3, 4, 6, 10, 5], Y[] = [7, 11, 18, 15, 12]
 * Output: There are no common elements. So the output is an empty array.
 */
public class CommonElementInArrays {
    public static void main(String args[]) {
        int[] x1 = {3, 4, 6, 2, 8, 5, 9};
        int[] y1 = {6, 3, 2, 7, 5, 1};

        int[] x2 = {3, 4, 6, 7, 10, 12, 5};
        int[] y2 = {7, 11, 18, 15, 12};

        int[] x3 = {3, 4, 6, 10, 5};
        int[] y3 = {7, 11, 18, 15, 12};
    }

    /**
     * 1. brute force approach --> compare each element of y with x and if found --> add to the output
     * array
     * 2. sort both arrays and do binary search. if found add to the output array
     * 3. sort both arrays and use two pointers to see if the element is found in target array
     * if yes -> add to the output array
     * 4. use hash table.
     *
     * This question is similar to array subset of another array problem.
     */
}
