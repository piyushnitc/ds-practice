package practice.arrays.singleloop;


import java.util.Arrays;

/**
 * Given an integer array nums, you need to find one continuous subarray such that if you only sort this
 * subarray in non-decreasing order, then the whole array will be sorted in non-decreasing order.
 * Return the shortest such subarray and output its length.
 *
 * nums = [2,6,4,8,10,9,15]             o/p = 5 [6,4,8,10,9]
 * nums = [1,2,3,4]                     o/p = 0
 * nums = [1]                           o/p = 0
 * nums = [2,4,6,8,10,15,9]             o/p = 3 [9,10,15]
 * nums = [6,2,4,8,9,10,15]             o/p = 3 [6,2,4]
 */
public class ShortestSubArray {
    public static void main(String[] args) {
        int[] x1 = {2,6,4,8,10,9,15};
        int[] x2 = {1,2,3,4};
        int[] x3 = {1};
        int[] x6 = {4,3,2,1};
        int[] x4 = {2,4,6,8,10,15,9};
        int[] x5 = {6,2,4,8,9,10,15};
        int[] x7 = {4,2,6,8,9,15,10};

        System.out.println(sortedApproach(x1));
        System.out.println(twoPointer(x1));
        System.out.println(sortedApproach(x2));
        System.out.println(twoPointer(x2));
        System.out.println(sortedApproach(x3));
        System.out.println(twoPointer(x3));
        System.out.println(sortedApproach(x4));
        System.out.println(twoPointer(x4));
        System.out.println(sortedApproach(x5));
        System.out.println(twoPointer(x5));
        System.out.println(sortedApproach(x6));
        System.out.println(twoPointer(x6));
        System.out.println(sortedApproach(x7));
        System.out.println(twoPointer(x7));

    }

    /**
     * Observations
     * 1. There can be only one such subarray which when sorted results in whole sorted array
     * 2. we need to find the boundaries of this subarray
     * Idea 1 - if we sort the original array and compare with sorted array - it wil give us the positions of the array
     * which we need to sort
     *
     * idea2 - from left track the max. if the next element is smaller than max - this means that this element should be part of
     * unsorted array (l)
     *
     * similarly - from right track the min and if we find the next element to be greater than minimum encounterd so far - this
     * means that this element should be part of unsorted array (r)
     *
     * hence, the unsorted array would be from l - r and length would be (r-l)+1;
     *
     * if l does not change - this means that array is sorted
     * if r does not change - this means that array is sorted
     */

    // Total Complexity = O(nlogn) -- sorting of array + O(n) - traversing of array = O(nlogn)+O(n)
    // Space Complexity = O(n)
    public static int sortedApproach(int[] x) {
        int n = x.length;
        int[] sorted = Arrays.copyOf(x,n);
        Arrays.sort(sorted);
        boolean left = true, right=true;

        int leftIndex = -1, rightIndex = -1;

        for(int i=0; i<n; i++) {
            if(sorted[i] != x[i] && left) {
                leftIndex = i;
                left = false;
            }
            if(sorted[n-1-i] != x[n-1-i] && right) {
                rightIndex = n-1-i;
                right = false;
            }
        }
        if(leftIndex == -1 && rightIndex ==-1) {
            return 0;
        }
        return rightIndex-leftIndex+1;
    }

    // Total T.C = O(n) + O(n)
    public static int twoPointer(int[] nums) {
        // Initialize variables
        final int MAX_INT = Integer.MAX_VALUE; // Use MAX_VALUE constant for clarity
        int n = nums.length; // Length of the input array
        int leftIndex = -1, rightIndex = -1; // Track the left and right boundaries of the subarray
        int minUnsorted = MAX_INT, maxUnsorted = Integer.MIN_VALUE; // Set initial values for min and max of the unsorted subarray

        // Loop through the array to find the unsorted subarray's boundaries
        for (int i = 0; i < n; ++i) {

            // If current max is greater than the current element, it might belong to the unsorted part
            if (maxUnsorted > nums[i]) {
                rightIndex = i; // Update the right boundary
            } else {
                maxUnsorted = nums[i]; // Update the current max for the sorted part
            }

            // Simultaneously, check the unsorted subarray from the end of the array
            if (minUnsorted < nums[n - i - 1]) {
                leftIndex = n - i - 1; // Update the left boundary
            } else {
                minUnsorted = nums[n - i - 1]; // Update the current min for the sorted part
            }
        }

        // If rightIndex is not updated, the array is fully sorted, return 0
        // Otherwise, return the length of the subarray that must be sorted
        return rightIndex == -1 ? 0 : rightIndex - leftIndex + 1;
    }
}
