package practice.trackUpdateReset;

import java.util.HashMap;

/**
 * An element x of an integer array arr of length m is dominant if more than half the elements of arr have a value of x.
 * You are given a 0-indexed integer array nums of length n with one dominant element.
 * You can split nums at an index i into two arrays nums[0, ..., i] and nums[i + 1, ..., n - 1], but the split is only valid if:
 *
 * 0 <= i < n - 1
 * nums[0, ..., i], and nums[i + 1, ..., n - 1] have the same dominant element.
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,2]
 * Output: 2
 * Explanation: We can split the array at index 2 to obtain arrays [1,2,2] and [2].
 * In array [1,2,2], element 2 is dominant since it occurs twice in the array and 2 * 2 > 3.
 * In array [2], element 2 is dominant since it occurs once in the array and 1 * 2 > 1.
 * Both [1,2,2] and [2] have the same dominant element as nums, so this is a valid split.
 * It can be shown that index 2 is the minimum index of a valid split.
 * Example 2:
 *
 * Input: nums = [2,1,3,1,1,1,7,1,2,1]
 * Output: 4
 * Explanation: We can split the array at index 4 to obtain arrays [2,1,3,1,1] and [1,7,1,2,1].
 * In array [2,1,3,1,1], element 1 is dominant since it occurs thrice in the array and 3 * 2 > 5.
 * In array [1,7,1,2,1], element 1 is dominant since it occurs thrice in the array and 3 * 2 > 5.
 * Both [2,1,3,1,1] and [1,7,1,2,1] have the same dominant element as nums, so this is a valid split.
 * It can be shown that index 4 is the minimum index of a valid split.
 * Example 3:
 *
 * Input: nums = [3,3,3,3,7,2,2]
 * Output: -1
 * Explanation: It can be shown that there is no valid split.
 */
public class MinIndexValidSplit {
    public static void main(String args[]) {
        int[] x1 = {1,2,2,2};               // index = 2    majorityElement = 2     count = 3   total = 4
        int[] x2 = {2,1,3,1,1,1,7,1,2,1};   // index = 4    majorityElement = 1     count = 6   total = 10
        int[] x3 = {3,3,3,3,7,2,2};         // index = NA   majorityElement = 3     count = 4   total = 7

        System.out.println(bruteForce(x1));
        System.out.println(bruteForce(x2));
        System.out.println(bruteForce(x3));

        System.out.println(hashMapApproach(x1));
        System.out.println(hashMapApproach(x2));
        System.out.println(hashMapApproach(x3));

        System.out.println(votingAlgorithm(x1));
        System.out.println(votingAlgorithm(x2));
        System.out.println(votingAlgorithm(x3));

    }


    /**
     * Obs 1 -> valid split index exists only if there is a majority element with count > n/2. else return -1;
     * if it exists
     *  --> we need to split index near the majority. keep increasing the index till we find same majority in left and
     *  right subarray
     *
     *  bruteForce --> keep finding majority for left and right and when we have found the same majority across
     *  both subarray --> return the index
     *
     *  T.C = O(n) --> for increasing the index split  and
     *  O(n) --> for finding left majority using hash table approach
     *  O(n) --> for finding right majority
     *
     *  T.C = O(n) * O(n) + O(n) * O(n) = O(n*n)
     *  Space Complexity = O(n)
     *
     *
     *  Appraoch 2 --> use two hashMap
     *  Approach 3 --> use Boyer-Moore Majority Voting Algorithm.
     */

    public static int bruteForce(int[] x) {
        int n = x.length;

        for(int i=0; i<n; i++) {        // T.C = O(n)
            int leftMajority = findMajority(x, 0, i);   //T.C = O(n)
            int rightMajority = findMajority(x, i+1, n-1);

            if(leftMajority == rightMajority) {
                return i;
            }
        }
        return -1; // T.C = O(n*n)
    }

    /**
     * use hash map approach to find the majority
     * T.C = O(n)
     */
    private static int findMajority(int[] x, int left, int right) {
        if(left == right) {
            return x[left];
        }

        HashMap<Integer, Integer> freqCountMap = new HashMap<>();
        for(int i=left; i<=right; i++) {
            int count = freqCountMap.getOrDefault(x[i], 0) +1 ;
            freqCountMap.put(x[i], count);

            if(count > (right-left+1)/2) {
                return x[i];
            }
        }
        return -1;
    }

    /**
     * Using hash map approach
     * lets use two hash map -> first hash map to contain first split and second to contain second split data
     * initially first hash map would be null and all the elements belong to second hash map
     * as we iterate --> we move the elements from second hash map to first hash map -->
     * decrease the frequency in second and increase the frequency in first and check if that's a valid split
     * freq * 2 > index +1
     *
     */

    public static int hashMapApproach(int[] x) {
        int n = x.length;
        HashMap<Integer, Integer> firstMap = new HashMap<>();
        HashMap<Integer, Integer> secondMap = new HashMap<>();

        // populate the second map. T.C = O(n)
        for(int num : x) {
            secondMap.put(num, secondMap.getOrDefault(num, 0)+1);
        }

        //Mainuplate both maps T.C = O(n)
        for(int currIndex =0; currIndex<n; currIndex++) {
            int num = x[currIndex];
            secondMap.put(num, secondMap.get(num)-1);
            firstMap.put(num, firstMap.getOrDefault(num,0)+1);

            //check if this is a valid split
            if((firstMap.get(num) *2 > currIndex+1) && (secondMap.get(num) *2 > n-currIndex-1)) {
                return currIndex;
            }
        }
        return -1; // T.C = O(n) + O(n) + O(n)
        // Space complexity = O(n) + O(n) = O(n)
    }

    /**
     * How can we use Boyer-Moore Majority Voting Algorithm which is O(n) for finding majority.
     * Find majority in O(n) in overall array.
     * Iterate through num array and at each index if current num = x increment count by 1
     * left majority count = count and right majority count = totalCount - count.
     *
     * Now check if these two are valid split. if not --> keep iterating.
     */

    public static int votingAlgorithm(int[] x) {
        int count =0, majorityElement = 0;
        int n = x.length;

        //T.C = O(n)
        for(int i=0; i<n; i++) {
            if(count == 0) {
                majorityElement = x[i];
            }

            if(majorityElement == x[i]) {
                count++;
            } else {
                count --;
            }
        }

        // get the total count of majority element T.C = O(n)
        int xCount = 0;
        for(int num: x) {
            if(num == majorityElement) {
                xCount++;
            }
        }

        if(xCount > n/2) {
            System.out.println("majority element exists "+majorityElement);
        } else {
            return -1;
        }

        // Now iterate through the index
        count = 0;
        for(int currIndex=0; currIndex<n; currIndex++) {
            if(x[currIndex] == majorityElement) {
                count ++;
            }
            int remainingCount = xCount - count;

            if((count * 2 > currIndex +1) && (remainingCount * 2 > n - currIndex -1)) {
                return currIndex;
            }
        }
        return -1;
    }
}
