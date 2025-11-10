package practice.arrays.twopointer;

import java.util.*;

/**
 * Given an array X[] of distinct elements, write a program to find all triplets in array whose sum is
 * equal to zero. For example, suppose triplets that sum to zero are X[i], X[j] and X[k] then
 * X[i] + X[j] + X[k] = 0.
 *
 * Input: X[] = [0, -2, 5, -3, 2], Output: [0, -2, 2] and [-2, 5, -3]
 * Explanation: Among several triplets, two triplets have zero-sum i.e. [0, -2, 2] and [-2, 5, -3].
 *
 * Input: X[] = [-2, 6, 1, 2, -1, -4, 0], Output: [-2, 6, -4], [-1, 1, 0] and [-2, 2, 0]
 * Explanation: Among several triplets, three triplets have zero-sum i.e.
 * [-2, 6, -4], [-1, 1, 0] and [-2, 2, 0].
 *
 * Input: X[] = [-3, 6, -1, 1], Output: "triplet with 0 sum does not exist"
 */
public class ZeroSumTriplet {
    public static void main(String args[]) {
        int[] x1 = {0, -2, 5, -3, 2};
        int[] x2 = {-2, 6, 1, 2, -1, -4, 0};
        int[] x3 = {-3, 6, -1, 1};

        System.out.println(bruteForce(x1));
        System.out.println(bruteForce(x2));
        System.out.println(bruteForce(x3));

        System.out.println(bruteForceMap(x1));
        System.out.println(bruteForceMap(x2));
        System.out.println(bruteForceMap(x3));

        sortingTwoPointer(x1);
        sortingTwoPointer(x2);
        sortingTwoPointer(x3);

    }

    //Obs - since we are asked about triplet sum - we run three for loops. t.C = O(n^3)
    public static List<List<Integer>> bruteForce(int[] x) {
        int n = x.length;
        List<List<Integer>> output = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                for(int k=j+1; k<n; k++) {
                    if(x[i]+x[j]+x[k] == 0) {
                        output.add(Arrays.asList(x[i],x[j],x[k]));
                    }
                }
            }
        }
        return output;
    }

    public static List<List<Integer>> bruteForceMap(int[] x) {
        int n = x.length;
        int targetSum = 0;

        List<List<Integer>> output = new ArrayList<>();
        //T.C = O(n^2)
        for(int i=0; i<n; i++) {
            Map<Integer, Integer> numMap = new HashMap(); // Space Complexity = O(n)
            for(int j=i+1; j<n; j++) {
                if(numMap.containsKey(targetSum - x[i]- x[j])) {
                    output.add(Arrays.asList(x[i],targetSum - x[i]- x[j], x[j]));
                } else {
                    numMap.put(x[j], j);
                }
            }
        }
        return output;
    }

    public static void sortingTwoPointer(int[] x) {
        int n = x.length;
        Arrays.sort(x);

        for(int i=0; i<n-2; i++) {
            int left = i+1;
            int right = n-1;

            while(left < right) {
                int sum = x[i]+x[left]+x[right];
                if(sum > 0) {
                    right--;
                } else if(sum <0) {
                    left++;
                } else {
                    System.out.println(x[i]+","+x[left]+","+x[right]);
                    left = left+1;
                    right = right-1;
                }
            }
        }
    }
}
