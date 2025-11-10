package practice.arrays.twopointer.merging;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given three sorted arrays in non-decreasing order, print all common elements in non-decreasing order
 * across these arrays. If there are no such elements return an empty array. In this case, t
 * he output will be -1.
 * Note: In case of duplicate common elements, print only once.
 *
 * Input: arr1[] = [1, 5, 10, 20, 30], arr2[] = [5, 13, 15, 20], arr3[] = [5, 20]
 * Output: 5 20
 * Explanation: 5 and 20 are common in all the arrays.
 *
 * Input: arr1[] = [2, 5, 10, 30], arr2[] = [5, 20, 34], arr3[] = [5, 13, 19]
 * Output: 5
 * Explanation: 5 is common in all the arrays.
 */
public class CommonElementsInThreeArrays {

    /**
     * How can we find intersection of 3 arrays
     * Suggested Approach
     * 1. FInd intersection of 1 & 2 say ans1. Find intersection of ans1 & 3 and return the output
     *
     * 2. use hashtable or hashmap or treemap to store elements and increment count if they have occurred more than once
     * finally iterate hash map and print all elements whose count = 3
     *
     * 4. Use three pointers (suggested approach)
     * {same as intersection of 2 arrays instead of 2 we have 3 here}
     */

    public static void main(String args[]) {
        int[] x1 = {1, 5, 10, 20, 30};
        int[] y1 = {5, 13, 15, 20};
        int[] z1 = {5, 20};

        int[] x2 = {2, 5, 92, 30};
        int[] y2 = {5, 20, 34};
        int[] z2 = {5, 13, 90};

        System.out.println(Arrays.toString(threePointers(x1,y1,z1)));
        System.out.println(Arrays.toString(threePointers(x2,y2,z2)));
    }

    public static int[] threePointers(int[] x, int[] y, int[] z) {
        // how to define the output array size? we do not know.
        // int[] ans = new int[10];
        // hence we store the output in a map.
        Map<Integer, Integer> ansMap = new HashMap<>();

        /**
         * idea here is to have three pointers i, j and k - all starting at 0.
         * find the array size and let's say we have n >= m >= p where n, m and p are sizes of the three arrays respectively
         * we need to loop on the smallest array say p.
         *
         * if z[k] = y[j]
         *      x[i] < y[j] --> increment i by 1
         *      x[i] > y[j] --> increment j and k by 1
         *      x[i] = y[j] --> increment i , j and k by 1
         *
         * if z[k] < y[j] --> increment k by 1
         * if z[k] > y[j] --> increment j by 1
         */
        int n = x.length;
        int m = y.length;
        int p = z.length;

        int i=0, j=0, k=0; // i for x, j for y and k for z.

        while(i < n && j < m && k < p) {
            if(z[k] == y[j]) {
                if(x[i] < y[j]) {
                    i++;
                } else if(x[i] > y[j]) {
                    j++;
                    k++;
                } else {
                    ansMap.put(x[i], i);
                    i++;
                    j++;
                    k++;
                }
            } else if(z[k] > y[j]) {
                j++;
            } else {
                k++;
            }
        }

        int ansLength = ansMap.size();
        int[] ans = new int[ansLength];

        int s = 0;
        for(Map.Entry<Integer, Integer> entry: ansMap.entrySet()) {
            ans[s] = entry.getKey();
            s++;
        }
        return ans;

    }
}
