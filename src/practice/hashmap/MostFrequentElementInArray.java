package practice.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array X[] of size n, write a program to find the most frequent element in the array, i.e., the element that
 * occurs the maximum number of times.
 *
 * Assumed that at least one element is repeated.
 * If there are multiple elements with the maximum frequency, return the smallest of them.

 * Input: X[] = [2, 12, 1, 2, 8, 2, 2, 1, 8, 2], Output: 2
 * Explanation: 2 is the most frequent element, which appears 4 times.
 *
 * Input: X[] = [1, 9, 1, 1, 2], Output: 1
 * Explanation: 1 is a single repeated element that appears 3 times.
 *
 * Input: X[] = [3, 8, 2, 3, 2], Output: 2
 * Explanation: 2 and 3 are repeated two times each. So we return the smallest of them, which is 2.
 */
public class MostFrequentElementInArray {
    public static void main(String args[]) {
        int[] x1 = {2, 12, 1, 2, 8, 2, 2, 1, 8, 2};         // 2
        int[] x2 = {1, 9, 1, 1, 2};                         // 1
        int[] x3 = {3, 8, 2, 3, 2};                         // 2 & 3 -- return 2

        /**
         * Observation
         * 1. sort the array and linear scan {store the max frequency and element}
         * 2. use a hash set and retrieve the elements with highest frequency
         */

        System.out.println(sortingApproach(x1));
        System.out.println(sortingApproach(x2));
        System.out.println(sortingApproach(x3));

        System.out.println(hashMapApproach(x1));
        System.out.println(hashMapApproach(x2));
        System.out.println(hashMapApproach(x3));
    }

    // {1,1,2,2,2,2,2,8,8,12}
    public static int sortingApproach(int[] x) {
        int n = x.length;
        Arrays.sort(x);
        int mostFrequent = -1;
        int maxFreq = 0;

        int i=0;
        while(i < n) {
            int countFreq = 1;

            while(i+1 < n && x[i] == x[i+1]) {
                countFreq++;
                i++;
            }
            if(countFreq > maxFreq) {
                mostFrequent = x[i];
                maxFreq = countFreq;
            } else if (countFreq == maxFreq) {
                mostFrequent = Math.min(mostFrequent, x[i]);
            }
            i++;
        }
        return mostFrequent;

    }

    public static int hashMapApproach(int[] x) {
        int n = x.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int num: x) {
            map.put(num, map.getOrDefault(num,0)+1);
        }

        int maxFrequency = 0;
        int mostFrequent = -1;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int countFrequency = entry.getValue();

            if(countFrequency > maxFrequency) {
                maxFrequency = countFrequency;
                mostFrequent = entry.getKey();
            } else if(countFrequency == maxFrequency) {
                mostFrequent = Math.min(mostFrequent, entry.getKey());
            }
        }
        return mostFrequent;
    }
}
