package practice.hashmap;

/**
 * Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
 *
 * answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
 * answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
 * Note that the integers in the lists may be returned in any order.
 *
 * Input: nums1 = [1,2,3], nums2 = [2,4,6]
 * Output: [[1,3],[4,6]]
 * Explanation:
 * For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
 * For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums1. Therefore, answer[1] = [4,6].
 *
 * Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
 * Output: [[3],[]]
 * Explanation:
 * For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
 * Every integer in nums2 is present in nums1. Therefore, answer[1] = [].
 */
public class DiffOfTwoArrays {
    public static void main(String args[]) {
        int[] x1 = {1,2,3};
        int[] y1 = {2,4,6};

        int[] x2 = {1,2,3,3};
        int[] y2 = {1,1,2,2};
    }

    /**
     * 1. Put x1 in map1  and y1 in map2
     * iterate x1 and look for values in map2
     * iterate x2 and look for values in map1
     *
     * T.C = O(n)  Space Complexity = O(n)
     * Use set to remove duplicates
     *
     * also use set2.removeAll(set1) --> will give all the values in set2 which are not in set 1
     */
}
