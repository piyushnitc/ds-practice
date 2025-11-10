package practice.slidingWindow;

import java.util.Arrays;
import java.util.HashMap;

/**
 * You are given an array X[] of size n and an integer k, write a program to count the distinct elements present in every k sized window of the array.
 *
 * The size of the array will always be greater than or equal to k.
 * A k sized window is a series of continuous k elements present in the sequence.
 * We need to return an array with the count of distinct elements in each k size window.
 * Examples
 *
 * Input: X[ ] = [1, 1, 1, 3, 4, 2, 3], k = 4, Output: [2, 3, 4, 3]
 * Input: X[ ] = [1, 2, 4, 4, 2], k = 3, Output: [3, 2, 2]
 */
public class DistinctElementKWindow {
    public static void main(String args[]) {
        int[] x1 = {1, 1, 1, 3, 4, 2, 3};   // k=4
        int[] x2 = {1, 2, 4, 4, 2};         // k = 3

        System.out.println(Arrays.toString(nestedLoop(x1, 4)));
        System.out.println(Arrays.toString(nestedLoop(x2, 3)));
    }

    public static int[] nestedLoop(int[] x, int k) {
        int n = x.length;
        int n1 = n-k+1;
        int[] out = new int[n1];

        HashMap<Integer, Boolean> hashSet = new HashMap<>();
        for(int i=0; i<n-k+1; i++) {
            hashSet.clear();
            for(int j=i; j<i+k; j++) {
                hashSet.putIfAbsent(x[j], true);
            }
            out[i] = hashSet.size();
        }
        return out;     // T.C = O(k*n)  S.C = O(K)
    }

    /**
     * Efficient approach using the sliding window approach
     * can we use the same left and right pointer and count the unique elements
     */

    public static int[] slidingWindow(int[] x, int k) {
        int n = x.length;
        if (k <= 0 || k > n) return new int[0];

        int[] out = new int[n - k + 1];
        HashMap<Integer, Integer> freq = new HashMap<>();

        // Build the first window
        for (int i = 0; i < k; i++) {
            freq.put(x[i], freq.getOrDefault(x[i], 0) + 1);
        }
        out[0] = freq.size();

        // Slide the window
        for (int i = k; i < n; i++) {
            int add = x[i];
            int remove = x[i - k];

            freq.put(add, freq.getOrDefault(add, 0) + 1);

            int cnt = freq.get(remove) - 1;
            if (cnt == 0) {
                freq.remove(remove);
            } else {
                freq.put(remove, cnt);
            }

            out[i - k + 1] = freq.size();
        }

        return out;
    }
}
