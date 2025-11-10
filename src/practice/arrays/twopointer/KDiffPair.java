package practice.arrays.twopointer;

import java.util.*;

/**
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 * 0 <= i, j < nums.length
 * i != j
 * |nums[i] - nums[j]| == k
 *
 * Input: nums = [3,1,4,1,5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 *
 * Input: nums = [1,2,3,4,5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 *
 * Input: nums = [1,3,1,5,4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 */
public class KDiffPair {

    public static void main(String args[]) {
        int[] x1 = {3,1,4,1,5}; // k=2 o/p = 2
        int[] x2 = {1,2,3,4,5}; // k=1 o/p = 4
        int[] x3 = {1,3,1,5,4}; // k=0 o/p =1

        System.out.println(bruteForce(x1,2));
        System.out.println(bruteForce(x2,1));
        System.out.println(bruteForce(x3,0));

        System.out.println(sortingTwoPointer(x1,2));
        System.out.println(sortingTwoPointer(x2,1));
        System.out.println(sortingTwoPointer(x3,0));

        System.out.println(usingHashMap(x1,2));
        System.out.println(usingHashMap(x2,1));
        System.out.println(usingHashMap(x3,0));
    }

    // T.C = O(n^2)
    public static int bruteForce(int[] x, int k) {
        int n = x.length;
        int uniquePairs = 0;
        Set<Integer> numSet = new HashSet<>();
        for(int i=0; i<n-1; i++) {
            for(int j=i+1; j<n; j++) {
                if(Math.abs(x[i]-x[j]) == k) {
                    if(!(numSet.contains(x[i]) && numSet.contains(x[j]))) {
                        uniquePairs++;
                        numSet.add(x[i]);
                        numSet.add(x[j]);
                    }
                }
            }
        }
        return uniquePairs;
    }

    public static int sortingTwoPointer(int[] x, int k) {
        int n = x.length;
        int uniquePairs = 0;

        Arrays.sort(x); // T.C = O(nlogn)  S.C = O(n) -> occurred during sorting.
        int left = 0, right = 1;

        //T.C = O(n)
        while(left < n-1 && right < n) {
            if((Math.abs(x[left] - x[right])) == k) {
                uniquePairs++;
                left++;
                right++;
            } else if(Math.abs(x[left] - x[right]) > k) {
                left++;
                right++;
            } else {
                right++;
            }
        }
        return uniquePairs; // Total T.C = O(nlogn) + O(n)
    }

    //Obs - Build a frequency map
    public static int usingHashMap(int[] x, int k) {
        int n = x.length;
        int uniqueCount = 0;

        Map<Integer, Integer> counterMap = new HashMap<>();
        for(int i=0; i<n; i++) {
            counterMap.put(x[i], counterMap.getOrDefault(x[i],0)+1);
        }

        for(Map.Entry<Integer,Integer> entry: counterMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if(k > 0 && counterMap.containsKey(key+k)) {
                uniqueCount++;
            } else if(k ==0 && value>1) {
                uniqueCount++;
            }
        }
        return uniqueCount;
    }
}
