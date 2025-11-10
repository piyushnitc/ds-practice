package practice.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array X[] of n integers, write a program to find the length of longest subarray with sum equal to 0.
 * In general, for all j > i, find max (j - i + 1) among all subarray with zero-sum. Note: Length of subarray starting
 * from index i and ending at index j will be j - i + 1.
 *
 * Input: X[] = [14, -1, 1, -6, 1, 5, 12, 17], Output: 5
 * Explanation: The longest sub-array with elements summing up to 0 is [-1, 1, -6, 1, 5].
 *
 * Input: X[] = [1, 5, 10], Output: 0
 * Explanation: There is no subarray with 0 sum.
 *
 * Input: X[] = [1, 0, 2], Output: 1
 * Explanation: The longest sub-array with elements summing up to 0 is [0]
 */
public class LargestSubArrayWIthZeroSum {
    public static void main(String args[]) {
        int[] x1 = {14, -1, 1, -6, 1, 5, 12, 17};
        int[] x2 = {1, 5, 10};
        int[] x3 = {1, 0, 2};
        int[] x4 = {15, -2, 2, -8, 1, 7, 10, 23};

        System.out.println(bruteForce(x1));
        System.out.println(bruteForce(x2));
        System.out.println(bruteForce(x3));
        System.out.println(bruteForce(x4));

        System.out.println(hashMapApproach(x1));
        System.out.println(hashMapApproach(x2));
        System.out.println(hashMapApproach(x3));
        System.out.println(hashMapApproach(x4));
    }

    /**
     * Observation -
     *
     * 1. brute force - for every element check if the sum is zero or not . T.C = O(n*n)
     * 2. hash table approach
     *      --> idea is keep track of the cumulative sum
     *      -- if the same sum occurs again --> the sum between those elements must be 0.
     */

    public static int bruteForce(int[] x) {
        int n = x.length;
        int maxLength = 0;

        for(int i=0; i<n; i++) {
            int subArraySum = 0;

            for(int j=i; j<n; j++) {
                subArraySum = subArraySum+x[j];
                if(subArraySum == 0) {
                    maxLength = Math.max(j-i+1, maxLength);
                }
            }
        }
        return maxLength;
    }

    public static int hashMapApproach(int[] x) {
        int n = x.length;
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int maxLength = 0;

        for(int i=0; i<n; i++) {
            sum = sum+x[i];

            if(sum == 0) {
                maxLength = i+1;
            } else if(map.containsKey(sum)) {
                int prevIndex = map.get(sum);
                maxLength = Math.max(maxLength, i-prevIndex);
            } else {
                map.put(sum, i);
            }
        }
        return maxLength;
    }


}
