package practice.trackUpdateReset;

/**
 * Given an array A[] of integers, write a program to find the maximum difference between any two elements such that
 * the larger element appears after the smaller element. In other words, we need to find max(A[j] - A[i]),
 * where A[j] > A[i] and j > i.
 *
 * Input: A[] = [1, 4, 9, 5, 3, 7], Output: 8
 * Explanation: The maximum difference is between 9 and 1.
 *
 * Input: A[] = [9, 8, 1, 6, 3, 2], Output: 5
 * Explanation: The maximum difference is between 6 and 1.
 *
 * Input: A[] = [9, 8, 6, 3, 2], Output: -1
 * Explanation: The input elements are in decreasing order, meaning no larger element appears after the smaller element.
 */
public class MaxDifference {
    public static void main(String args[]) {
        int[] x1 = {1, 4, 9, 5, 3, 7};  //8
        int[] x2 = {9, 8, 1, 6, 3, 2};  //5
        int[] x3 = {9, 8, 6, 3, 2};     // -1

        System.out.println(bruteForce(x1));
        System.out.println(bruteForce(x2));
        System.out.println(bruteForce(x3));

        System.out.println(linearSearch(x1));
        System.out.println(linearSearch(x2));
        System.out.println(linearSearch(x3));

        System.out.println(efficientSearch(x1));
        System.out.println(efficientSearch(x2));
        System.out.println(efficientSearch(x3));
    }

    /**
     * approach
     * 1. brute force --> consider every element -- T.C = O(n*n)
     *
     * Alt1 - divide and conquer ??
     * Alt2 - traansform and conquer ??
     *
     * 2. find the position of min and max
     *      if index(max) < index(min) --> start loop from min to end and find the diff
     *      if index(max) > index(min) --> start loop from min to max
     *
     * 3. efficient approach using single loop -->
     *      traverse the loop and keep updating min and maxdiff
     */

    public static int bruteForce(int[] x) {
        int n = x.length;
        int maxDiff = -1;

        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {
                if(x[j] > x[i]) {
                    maxDiff = Math.max(maxDiff, x[j]-x[i]);
                }
            }
        }
        return maxDiff; // T.C = O(n*n)
    }


    public static int linearSearch(int[] x) {
        int n = x.length;
        int maxDiff = -1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int minIndex = -1, maxIndex =-1;

        for(int i=0; i<n; i++) {   // T.C = O(n)
            if(x[i] < min) {
                min = x[i];
                minIndex = i;
            }
            if(x[i] > max) {
                max = x[i];
                maxIndex = i;
            }
        }

        if(maxIndex < minIndex) {  // T.C = O(n)
            for(int i=minIndex; i<n-1; i++) {
                maxDiff = Math.max(maxDiff, x[minIndex+1]-x[minIndex]);
            }
        } else {
            maxDiff = max - min;
         }
        return maxDiff;  // T.C = 2O(n) = O(n)
    }

    public static int efficientSearch(int[] x) {
        int n = x.length;
        int min = x[0];
        int maxDiff = -1;

        for(int i=1; i<n; i++) {
            if(x[i] < min) {
                min = x[i];
            }

            if(x[i]-min > maxDiff) {
                maxDiff = x[i] - min;
            }
        }
        return maxDiff == 0 ? -1 : maxDiff;
    }

    /**
     * Read the blog https://www.enjoyalgorithms.com/blog/maximum-difference-between-two-elements
     * @param x
     * @return
     */
    public static int divideAndConquer(int[] x) {
        return 0;
    }

    public static int transformAndConquer(int[] x) {
        return 0;
    }
}
