package practice.arrays.singleloop;

/**
 * Given an array X[] of size n, we need to find the maximum and minimum elements present in the array.
 * Our algorithm should make the minimum number of comparisons.
 * Examples
 * Input: X[] = [4, 2, 0, 8, 20, 9, 2], Output: max = 20, min = 0.
 * Input: X[] = [-8, -3, -10, -32, -1], Output: max = -1, min = -32.
 */

public class MaxMinArray {

    public static void main(String args[]) {

        int[] nums1 = {4, 2, 0, 8, 20, 9, 2};
        int[] nums2 = {-8, -3, -10, -32, -1};

        maxMinArray(nums1);
        maxMinArray(nums2);

        maxMinArrayEfficient(nums1);
        maxMinArrayEfficient(nums2);
    }

    /**
     * brute force - scan all the array and track max and min
     * considering no duplicates
     * @param nums
     * T.C = O(n)
     * Number of comparison = 2*n
     */
    public static void maxMinArray(int[] nums) {
        int max = nums[0];
        int min = nums[0];

        for(int i=1; i<nums.length; i++) {
            if(nums[i] > max) {
                max = nums[i];
            }
            if(nums[i] < min) {
                min = nums[i];
            }
        }
        System.out.println(max+"---"+min);
    }

    /**
     * solve it when you understand recursion.
     * @param nums
     */
    public static void maxMinArrayRecursive(int[] nums) {

    }

    /**
     * compare elements in pair
     * if number of elements even - no issues
     * if number of elements odd - then max = min = x[0]
     *
     * Count the total number of comparison here. It would be 3n/2-1
     * @param nums
     */
    public static void maxMinArrayEfficient(int[] nums) {
        int max , min, i = 0;
        if(nums.length %2 != 0) {
            max = nums[0];
            min = nums[0];
            i = 1;
        } else {
            if(nums[0] > nums[1]) {
                max = nums[0];
                min = nums[1];
            } else {
                max = nums[1];
                min = nums[0];
            }
            i = 2;
        }

        while(i < nums.length) {
            if (nums[i] > nums[i + 1]) {
                if (max < nums[i]) {
                    max = nums[i];
                }
                if (min > nums[i + 1]) {
                    min = nums[i + 1];
                }
            } else {
                if (max < nums[i + 1]) {
                    max = nums[i + 1];
                }
                if (min > nums[i]) {
                    min = nums[i];
                }
            }
            i = i+2;
        }
        System.out.println(max +","+min);
    }
}
