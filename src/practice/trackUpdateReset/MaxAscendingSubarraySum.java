package practice.trackUpdateReset;

/**
 * Given an array of positive integers nums, return the maximum possible sum of an strictly increasing subarray in nums.
 * A subarray is defined as a contiguous sequence of numbers in an array.
 *
 * Input: nums = [10,20,30,5,10,50]
 * Output: 65
 * Explanation: [5,10,50] is the ascending subarray with the maximum sum of 65.
 *
 * Input: nums = [10,20,30,40,50]
 * Output: 150
 * Explanation: [10,20,30,40,50] is the ascending subarray with the maximum sum of 150.
 *
 * Input: nums = [12,17,15,13,10,11,12]
 * Output: 33
 * Explanation: [10,11,12] is the ascending subarray with the maximum sum of 33.
 */
public class MaxAscendingSubarraySum {
    public static void main(String args[]) {
        int[] x1 = {10,20,30,5,10,50};
        int[] x2 = {10,20,30,40,50};
        int[] x3 = {12,17,15,13,10,11,12};

        System.out.println(linearScan(x1));
        System.out.println(linearScan(x2));
        System.out.println(linearScan(x3));

        System.out.println(linearScan2(x1));
        System.out.println(linearScan2(x2));
        System.out.println(linearScan2(x3));
    }

    /**
     * Obs 1 -> linear scan the array and store the sum. restart the sum where the element is not in ascending order
     */

    public static int linearScan(int[] x) {
        int n = x.length;
        int currsubarraySum = 0;
        int tempSum = x[0];

        for(int i=1; i<n; i++) {
            if(x[i] > x[i-1]) {
                tempSum = tempSum + x[i];
            } else {
                tempSum = x[i];
            }

            if(tempSum > currsubarraySum) {
                currsubarraySum = tempSum;
            }
        }
        return currsubarraySum;
    }

    public static int linearScan2(int[] x) {
        int n = x.length;
        int maxSum = 0;
        int currSubSum = x[0];

        for(int i=1; i<n; i++) {
            if(x[i] <= x[i-1]) {
                maxSum = Math.max(maxSum, currSubSum);
                currSubSum = 0;
            }
            currSubSum = currSubSum + x[i];
        }
        return Math.max(currSubSum, maxSum);
    }
}
