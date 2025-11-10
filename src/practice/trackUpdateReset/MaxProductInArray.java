package practice.trackUpdateReset;

import java.util.Arrays;

/**
 * Given the array of integers nums, you will choose two different indices i and j of that array. Return the
 * maximum value of (nums[i]-1)*(nums[j]-1).
 *
 * Input: nums = [3,4,5,2]
 * Output: 12
 * Explanation: If you choose the indices i=1 and j=2 (indexed from 0), you will get the maximum value, that is,
 * (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12.
 *
 * Input: nums = [1,5,4,5]
 * Output: 16
 * Explanation: Choosing the indices i=1 and j=3 (indexed from 0), you will get the maximum value of (5-1)*(5-1) = 16.
 *
 * Input: nums = [3,7]
 * Output: 12
 */
public class MaxProductInArray {
    public static void main(String args[]) {
        int[] x1 = {3,4,5,2};
        int[] x2 = {1,5,4,5};
        int[] x3 = {3,7};
        int[] x4 = {-3, 4, 6,-8}; // {-8, -3, 4, 6}

        System.out.println(sortingApproach(x1));
        System.out.println(sortingApproach(x2));
        System.out.println(sortingApproach(x3));
        System.out.println(sortingApproach(x4));

        System.out.println(findSmallestAndLargest(x1));
        System.out.println(findSmallestAndLargest(x2));
        System.out.println(findSmallestAndLargest(x3));
        System.out.println(findSmallestAndLargest(x4));
    }

    /**
     * Observation
     * 1. find the max product of two elements
     *      -- if all elements are positive find two biggest number
     *      -- if all elements are negative find two smallest number
     *      -- if there are positive and negative -- we need to find the max product
     *
     * 2. sort the array and take the last two elements (in case of positive)
     * or first two and last two in case of mixed sign elements
     *
     * 3. find two largest and two smallest element
     *
     * 3.
     */

    public static int sortingApproach(int[] x) {
        int n = x.length;

        Arrays.sort(x); // T.C = O (nlogn)

        int product1 = x[0] * x[1];
        int product2  = x[n-1] * x[n-2];

        if(product1 > product2) {
            return (x[0]-1) * (x[1]-1);
        } else if(product1 < product2){
            return (x[n-1]-1) * (x[n-2]-1);
        } else {
            return (x[0]-1) * (x[1]-1);
        }
    }

    public static int findSmallestAndLargest(int[] x) {
        int n = x.length;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        // int[] x4 = {-3, 4, 6,-8}
        for(int i=0; i<n; i++) {
            if(x[i] > max1) {
                max2 = max1;
                max1 = x[i];
            } else if(x[i] > max2) {
                max2 = x[i];
            }

            if (x[i] < min1) {
                min2 = min1;
                min1 = x[i];
            } else if(x[i] < min2) {
                min2 = x[i];
            }
        }

        return Math.max((max1-1) * (max2-1), (min1-1)*(min2-1));
    }
}
