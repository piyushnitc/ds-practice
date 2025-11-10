package practice.arrays.prefixarray;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k, return the total number of subarrays
 * whose sum equals to k.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 */
public class SubArraySum {

    public static void main(String args[]) {
        int[] nums1 = {1,1,1};
        int[] nums2 = {1,2,3};
        int[] nums3 = {-1,1,2,-2,3,-3,0};
        System.out.println(bruteForce(nums1, 2));
        System.out.println(bruteForce(nums2, 3));
        System.out.println(bruteForce(nums3, 0));

        System.out.println(prefixSumApproach(nums1, 2));
        System.out.println(prefixSumApproach(nums2, 3));
        System.out.println(prefixSumApproach(nums3, 0));

    }

    // consider every element and every sub array.
    // T.C = O(n*n)
    public static int bruteForce(int[] x, int k) {
        int count = 0;
        for(int i = 0; i < x.length; i++) {
            int sum = 0;
            for(int j = i; j < x.length; j++) {
                    sum = sum + x[j];
                    if(sum == k) {
                        count++;
                    }
                }
            }
        return count;
    }

    /**
     * Idea is if the difference between two prefix sum is equal to k this implies that the
     * sum of the subarray is K
     *
     * sum[i] - denotes sum of all elements till ith element
     * sum[j] - denotes sum of all elements till jth element
     *
     * if sum[j]-sum[i] = k  ==> this subarray contains element whose sum is k
     * also, sum[j] - k = sum[i]
     *
     * i.e whenever we reach cumulative sum "sum" we check if there is a corresponding sum-k element
     */

    public static int prefixSumApproach(int[] x, int k) {
        int sum =0;
        int count = 0;
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0,1);

        for(int i=0; i<x.length; i++) {
            sum = sum + x[i];
            if(prefixSumMap.containsKey(sum-k)) {
                count = count + prefixSumMap.get(sum-k);
            }
            prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum,0)+1);
        }
        return count;
    }
}
