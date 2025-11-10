package practice.trackUpdateReset;

import java.util.*;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * Could you solve the problem in linear time and in O(1) space
 * Input: nums = [3,2,3]
 * Output: [3]
 *
 * Input: nums = [1]
 * Output: [1]
 *
 * Input: nums = [1,2]
 * Output: [1,2]
 */
public class MajorityElement2 {
    public static void main(String args[]) {
        int[] x1 = {3,2,3};
        int[] x2 = {1};
        int[] x3 = {1,2};
        int[] x4 = {2,2,2,3,3,3,1,1};
        int[] x5 = {1,2,3,4,5,6};

        System.out.println(hashMapApproach(x1));
        System.out.println(hashMapApproach(x2));
        System.out.println(hashMapApproach(x3));
        System.out.println(hashMapApproach(x4));
        System.out.println(hashMapApproach(x5));

        System.out.println(sortingApproach(x1));
        System.out.println(sortingApproach(x2));
        System.out.println(sortingApproach(x3));
        System.out.println(sortingApproach(x4));
        System.out.println(sortingApproach(x5));

        System.out.println(modifiedVotingAlgo(x1));
        System.out.println(modifiedVotingAlgo(x2));
        System.out.println(modifiedVotingAlgo(x3));
        System.out.println(modifiedVotingAlgo(x4));
        System.out.println(modifiedVotingAlgo(x5));
    }

    /**
     * Approach 1 - use hash map
     * 2 -> sort the array O(nlogn) and then count
     * 3 -> modified voting algorithm
     */

    public static List<Integer> hashMapApproach(int[] x) {
        int n = x.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> out = new ArrayList<>();

        for(int num: x) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(entry.getValue() > n/3) {
                out.add(entry.getKey());
            }
        }
        return out;
    }

    public static List<Integer> sortingApproach(int[] x) {
        int n = x.length;
        List<Integer> out = new ArrayList<>();

        //T.C = O(nlogn)
        Arrays.sort(x);
        int count = 1;
        for(int i=1; i<n; i++) {
            if(x[i] == x[i-1]) {
                count++;
            } else {
                if(count > n/3) {
                    out.add(x[i-1]);
                }
                count = 1;
            }
        }
        if(count > n/3) {
            out.add(x[n-1]);
        }
        return out;
    }

    /**
     * Idea behind this approach is -> no more than 2 elements can have count > n/3;
     * so we need to find two majority elements
     * once we found two majority elements -- we need to check their actual count.
     */
    public static List<Integer> modifiedVotingAlgo(int[] x) {
        int n = x.length;
        int element1 = 0, element2 = 1, count1 =0, count2=0;
        List<Integer> out = new ArrayList<>();

        for(int i=0; i<n; i++) {
            if(x[i] == element1) {
                count1++;
            } else if(x[i] == element2) {
                count2++;
            } else if(count1 ==0) {
                element1 = x[i];
                count1++;
            } else if(count2 ==0) {
                element2 = x[i];
                count2++;
            } else {
                count1 --;
                count2 --;
            }
        }
        System.out.println("First majority element "+element1);
        System.out.println("Second majority element "+element2);

        count1 =0;
        count2 =0;
        // now do the actual count
        for(int num: x) {
            if(num == element1) {
                count1++;
            } else if(num == element2) {
                count2++;
            }
        }

        System.out.println("First majority element "+element1 +" occurs "+count1+" times");
        System.out.println("Second majority element "+element2+" occurs "+count2+" times");

        if(count1 > n/3) {
            out.add(element1);
        }
        if(count2 > n/3) {
            out.add(element2);
        }

        return out;
    }
}
