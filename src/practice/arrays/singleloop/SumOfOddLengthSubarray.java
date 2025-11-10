package practice.arrays.singleloop;

/**
 * Given an array of positive integers arr, return the sum of all possible odd-length subarrays
 * of arr.
 * A subarray is a contiguous subsequence of the array.
 *
 * Input: arr = [1,4,2,5,3]
 * Output: 58
 * Explanation: The odd-length subarrays of arr and their sums are:
 * [1] = 1
 * [4] = 4
 * [2] = 2
 * [5] = 5
 * [3] = 3
 * [1,4,2] = 7
 * [4,2,5] = 11
 * [2,5,3] = 10
 * [1,4,2,5,3] = 15
 * If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
 *
 * Input: arr = [1,2]
 * Output: 3
 * Explanation: There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.
 *
 * Input: arr = [10,11,12]
 * Output: 66
 */

public class SumOfOddLengthSubarray {

    public static void main(String args[]) {
        int[] nums1 = {1,4,2,5,3};
        int[] nums2 = {1,2};
        int[] nums3 = {10,11,12};
        System.out.println(bruteForce(nums1));
        System.out.println(bruteForce(nums2));
        System.out.println(bruteForce(nums3));

        System.out.println(bruteForce2(nums1));
        System.out.println(bruteForce2(nums2));
        System.out.println(bruteForce2(nums3));
    }

    // fix left index and iterate over all the elements. if the length is odd
    //sum it up. T.C = O(n*n*n)
    // Approach - loop from left to right and whenever it is an odd sub array -- then sum from left to right
    public static int bruteForce(int[] x) {
        int n = x.length;
        int sum =0;
        for(int left=0; left<n; left++) {
            for(int right=left; right<n; right++){
                if((right-left+1) %2 == 1) {
                    for(int start=left; start<right+1; start++) {
                        sum = sum+x[start];
                    }
                }
            }
        }
        return sum;
    }

    // T.C = O(n*n)
    // Better approach - scan from left to right  and keep taking sum. if this is part of odd array subarray then add it to the sum
    public static int bruteForce2(int[] x) {
        int n = x.length;
        int sum = 0;

        for(int left =0; left <n; left++) {
            int tempSum = 0;
            for(int right=left; right<n; right++) {
                tempSum = tempSum + x[right];
                if((right-left+1) %2 == 1) {
                    sum = sum+tempSum;
                }
            }
        }
        return sum;
    }

   //TODO: go in detail
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length, answer = 0;

        for (int i = 0; i < n; ++i) {
            int left = i, right = n - i - 1;
            answer += arr[i] * (left / 2 + 1) * (right / 2 + 1);
            answer += arr[i] * ((left + 1) / 2) * ((right + 1) / 2);
        }

        return answer;
    }
}
