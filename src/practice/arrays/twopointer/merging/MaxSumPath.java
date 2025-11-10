package practice.arrays.twopointer.merging;

/**
 * Given two sorted arrays having some elements in common. Find the sum of the maximum sum path to reach
 * from the beginning of any array to the end of the two arrays. We can switch from one array
 * to another array only at common elements.
 *
 * Note: The common elements do not have to be at the same indexes. And individual arrays have
 * distinct elements only (no repetition within the array).
 *
 * Input: ar1[] = {2, 3, 7, 10, 12}, ar2[] = {1, 5, 7, 8}
 * Output: 35
 * Explanation: 35 is sum of 1 + 5 + 7 + 10 + 12.
 * Start from the first element of ar2 which is 1, then move to 5, then 7.  From 7 switch to
 * ar1 (as 7 is common) and traverse 10 and 12.
 *
 * Input: ar1[] = {10, 12}, ar2 = {5, 7, 9}
 * Output: 22
 * Explanation: 22 is the sum of 10 and 12.
 * Since there is no common element, take all elements from the array with more sum.
 *
 * Input: ar1[] = {2, 3, 7, 10, 12, 15, 30, 34}, ar2[] = {1, 5, 7, 8, 10, 15, 16, 19}
 * Output: 122
 * Explanation: 122 is sum of 1, 5, 7, 8, 10, 12, 15, 30, 34
 * Start from the first element of ar2 which is 1, then move to 5, then 7. From 7 switch to
 * ar1 (as 7 is common), then traverse the remaining ar1.
 */
public class MaxSumPath {
    public static void main(String args[]) {
        int[] x1 = {2, 3, 7, 10, 12}; //op = 35
        int[] y1 = {1, 5, 7, 8};

        int[] x2 = {10, 12}; //op = 22
        int[] y2 = {5, 7, 9};

        int[] x3 = {2, 3, 7, 10, 12, 15, 30, 34}; //op = 122
        int[] y3 = {1, 5, 7, 8, 10, 15, 16, 19};

        System.out.println(maxSum(x1, y1));
    }

    /**
     * idea is we can switch only when we have a common element and also array is sorted.
     * so we iterate both arrays taking sum and when we hit the pivot element or the common element
     * we take the max sum.
     *
     * if x[i] > y[j] --> take sum and increment j
     * if x[i] < y[j] --> take sum and increment i
     * if x[i] = y[j] --> take max of sum so far. and increment i and j.
     *
     * Now i and j can be of different length and can end up differently. That means that from the last pivot encountered
     * we need to take sum from 1st array and 2nd array and take the max of it.
     *
     */

    public static int maxSum(int[] x, int[] y) {
        int maxSum = 0;
        int i=0, j=0, sum1=0, sum2=0; // initiate two pointers i and j {for arr x and arr y}
        // sum1 for arr1 and sum2 for arr2.

        while(i < x.length && j < y.length) {
            if(x[i] > y[j]) {
                sum2 = sum2 + y[j];
                j++;
            } else if(x[i] < y[j]) {
                sum1 = sum1 + x[i];
                i++;
            } else {
                maxSum = maxSum+ Math.max(sum1, sum2) + x[i];
                sum1 = 0;
                sum2 = 0;
                i++;
                j++;
            }
        }

        while(i < x.length) {
            sum1 = sum1 + x[i];
            i++;
        }
        while(j < y.length) {
            sum2 = sum2 + y[j];
            j++;
        }

        maxSum = maxSum + Math.max(sum1, sum2);
        return maxSum;

        //Total T.C = O(m) + O(n) - as we are iterating the whole array once = O(m+n)
    }
}
