package practice.arrays.prefixarray;

import java.util.Arrays;

/**
 * Given an array X[] of n integers where n > 1, write a program to return an array product[] such that
 * product[i] is equal to the product of all the elements of X except X[i].
 *
 * Input: X[] = [2, 1, 3, 4], Output: product[] = [12, 24, 8, 6]
 * Input: X[] = [5, 2, 8, 4, 5], Output: product[] = [320, 800, 200, 400, 320]
 * Input: X[] = [1, 0, 4, 3, 5], Output: product[] = [0, 60, 0, 0, 0]
 * Input: X[] = [0, 4, 0, 3], Output: product[] = [0, 0, 0, 0]
 *
 * Observations
 *  1. if there is one zero - product array is zero except for that position
 *  2. if there are two zeros - product array is 0
 *  3. if there are no zeros - all elements product / self
 *  4. calculate prefixProduct and suffixProduct and multiply
 *  5. brute force
 */
public class SelfProduct {

    public static void main(String args[]) {
        int[] nums1 = {2,1,3,4};
        int[] nums2 = {5,2,8,4,5};
        int[] nums3 = {1,0,4,3,5};
        int[] nums4 = {0,4,0,3};

        Arrays.stream(bruteForce(nums1)).forEach(e->System.out.print(e+","));
        Arrays.stream(bruteForce(nums2)).forEach(e->System.out.print(e+","));
        Arrays.stream(bruteForce(nums3)).forEach(e->System.out.print(e+","));
        Arrays.stream(bruteForce(nums4)).forEach(e->System.out.print(e+","));
        System.out.println();
        Arrays.stream(prefixSuffix(nums1)).forEach(e->System.out.print(e+","));
        Arrays.stream(prefixSuffix(nums2)).forEach(e->System.out.print(e+","));
        Arrays.stream(prefixSuffix(nums3)).forEach(e->System.out.print(e+","));
        Arrays.stream(prefixSuffix(nums4)).forEach(e->System.out.print(e+","));
        System.out.println();
        Arrays.stream(prefixSuffixLessSpace(nums1)).forEach(e->System.out.print(e+","));
        Arrays.stream(prefixSuffixLessSpace(nums2)).forEach(e->System.out.print(e+","));
        Arrays.stream(prefixSuffixLessSpace(nums3)).forEach(e->System.out.print(e+","));
        Arrays.stream(prefixSuffixLessSpace(nums4)).forEach(e->System.out.print(e+","));
        System.out.println();

    }

    //T.C = O(n) +O(n) = 2*O(n)
    public static int[] bruteForce(int[] x) {
        int zeroCount = 0;
        int zeroIndex = -1;
        int[] productArray = new int[x.length];
        int totalProduct = 1;

        for(int i=0; i<x.length; i++) {
            if(x[i] ==0) {
                zeroCount++;
                zeroIndex = i;
                continue;
            }
            totalProduct = totalProduct * x[i];
        }

        // there is one zero
        if(zeroCount ==1 && zeroIndex!= -1) {
            Arrays.fill(productArray, 0);
            productArray[zeroIndex] = totalProduct;
            return productArray;
        }

        // if there is more than one zero
        if(zeroCount > 1) {
            Arrays.fill(productArray,0);
            return productArray;
        }

        // if there is no zero
        for(int i=0; i<x.length; i++) {
            productArray[i] = totalProduct/x[i];
        }
        return productArray;
    }

    public static int[] prefixSuffix(int[] x) {
        int n = x.length;
        int[] productArray = new int[n];
        int[] prefixArray = new int[n];
        int[] suffixArray = new int[n];

        Arrays.fill(prefixArray, 1);
        Arrays.fill(suffixArray, 1);
        Arrays.fill(productArray, 1);

        //prefixArray[0] = x[0];
        for(int i=1; i<n; i++) {
            prefixArray[i] = prefixArray[i-1] * x[i-1];
        }

        //suffixArray[x.length-1] = x[x.length-1];
        for(int i=n-2; i>=0; i--) {
            suffixArray[i] = suffixArray[i+1] * x[i+1];
        }

        for(int i=0; i<x.length; i++) {
            productArray[i] = prefixArray[i] * suffixArray[i];
        }
        return productArray;
    }

    public static int[] prefixSuffixLessSpace(int[] x) {
        int n = x.length;
        int[] productArray = new int[n];

        Arrays.fill(productArray, 1);
        for(int i=1; i<n; i++) {
            productArray[i] = productArray[i-1] * x[i-1];
        }

        int suffixProduct = 1;
        for(int i=n-2; i>=0; i--) {
            suffixProduct = suffixProduct * x[i+1];
            productArray[i] = productArray[i]*suffixProduct;
        }

        return productArray;
    }
}
