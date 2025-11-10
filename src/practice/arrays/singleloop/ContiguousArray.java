package practice.arrays.singleloop;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal
 * number of 0 and 1.
 *
 * Example 1:
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 * Example 2:
 *
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Example 3:
 *
 * Input: nums = [0,1,1,1,1,1,0,0,0]
 * Output: 6
 * Explanation: [1,1,1,0,0,0] is the longest contiguous subarray with equal number of 0 and 1.
 */
public class ContiguousArray {

    public static void main(String args[]) {
        int[] nums1 = {0,1};
        int[] nums2 = {0,1,0};
        int[] nums3 = {0,1,1,1,1,1,0,0,0};

        System.out.println(bruteForce(nums1));
        System.out.println(bruteForce(nums2));
        System.out.println(bruteForce(nums3));

        System.out.println(hashMapApproach(nums1));
        System.out.println(hashMapApproach(nums2));
        System.out.println(hashMapApproach(nums3));

    }

    //Observation - consider every possible subarray and count the number of 0's and 1s
    // if they are equal - find the max of this subarray and track
    public static int bruteForce(int[] x) {
        int n = x.length;
        int maxsize = 0;
        for(int i=0; i<n; i++) {
            int zeros=0, ones = 0;
            for(int j=i; j<n; j++) {
                if(x[j] ==0) {
                    zeros++;
                } else {
                    ones++;
                }
                if(zeros == ones) {
                    maxsize = Math.max(maxsize, j-i+1);
                }
            }
        }
        return maxsize;
    }

    /**
     * Observation
     *
     * 1. consider every contiguous array -- T.C = O(n*n)
     *
     * 2. convert 0s to -1 and the problem becomes largest zero sum sub array problem
     */

    public static int hashMapApproach(int[] x) {
        int n = x.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);

        int maxLength = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum = sum + (x[i] ==0 ? -1 : 1);

            if (map.containsKey(sum)) {
                int prevIndex = map.get(sum);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(sum, i);
            }
        }
        return maxLength;
    }
}
