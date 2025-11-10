package practice.slidingWindow;

/**
 * Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all
 * the elements in the subarray is strictly less than k.
 *
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 *
 * Input: nums = [1,2,3], k = 0
 * Output: 0
 */
public class SubArrayProductLessThanK {
    public static void main(String args[]) {
        int[] x1 = {10,5,2,6}; int target1 = 100;
        int[] x2 = {1,2,3};     int target2 = 0;

        System.out.println(slidingWindow(x1, target1));
    }

    /**
     * Observation 1 -- nested loops
     * 2,5,6,10
     *
     * Observation 2 - sliding window
     */

    public static int slidingWindow(int[] x, int target) {
        int n = x.length;
        int count = 0;
        int product = 1;

        for(int end=0, start=0; end<n; end++) {
            product = product * x[end];

            while(start <n && product >= target) {
                product = product / x[start];
                start++;
            }
            count = count+(end-start+1);

        }
        return count;
    }
}
