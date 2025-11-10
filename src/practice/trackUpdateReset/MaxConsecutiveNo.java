package practice.trackUpdateReset;

/**
 * An input array X[] is given where all elements are either 0 or 1. Write a program to find the maximum number of
 * consecutive 1's in the binary array.
 *
 * Input: X[] = [1, 1, 0, 1, 1, 1, 0, 0, 1], Output: 3
 * Input: X[] = [0, 1, 1, 1, 1, 0, 0, 1, 1], Output: 4
 * Input: X[] = [1, 1, 1, 1], Output: 4
 * Input: X[] = [0, 0, 1, 0, 1], Output: 1
 */
public class MaxConsecutiveNo {
    public static void main(String args[]) {
        int[] x1 = {1, 1, 0, 1, 1, 1, 0, 0, 1};
        int[] x2 = {0, 1, 1, 1, 1, 0, 0, 1, 1};
        int[] x3 = {1, 1, 1, 1};
        int[] x4 = {0, 0, 1, 0, 1};

        System.out.println(bruteForce(x1));
        System.out.println(bruteForce(x2));
        System.out.println(bruteForce(x3));
        System.out.println(bruteForce(x4));

        System.out.println(slidingWindow(x1));
        System.out.println(slidingWindow(x2));
        System.out.println(slidingWindow(x3));
        System.out.println(slidingWindow(x4));

        System.out.println(linearSearch(x1));
        System.out.println(linearSearch(x2));
        System.out.println(linearSearch(x3));
        System.out.println(linearSearch(x4));
    }

    /**
     * Approach 1 - brute force -- consider all the possible sub array and count the ones and return the max
     * Approach 2 - sliding window --
     * Approach 3 - linear scanning and counting and keeping a track of 1s. T.C = O(n)  S.C = O(1)
     */

    public static int bruteForce(int[] x) {
        int n = x.length;
        int maxCount = 0;
        for(int i=0; i<n; i++) {
            int oneCount = 0;
            for(int j=i; j<n; j++) {
                if(x[j] == 1) {
                    oneCount++;
                } else {
                    break;
                }
            }
            if(oneCount > maxCount) {
                maxCount = oneCount;
            }
        }
        return maxCount;  //T.C = O(n*n)
    }


    public static int slidingWindow(int[] x) {
        int n = x.length;
        int leftEnd = 0;
        int maxCount = 0;
        while (leftEnd < n) {
            if(x[leftEnd] ==0) {
                leftEnd++;
            } else {
                int rightEnd = leftEnd;
                while(rightEnd < n-1 && x[rightEnd+1] ==1) {
                    rightEnd++;
                }
                maxCount = Math.max(maxCount, rightEnd-leftEnd+1);
                leftEnd = rightEnd+1;
            }
        }
        return maxCount;
    }

    /**
     *      *  * Input: X[] = [1, 1, 0, 1, 1, 1, 0, 0, 1], Output: 3
     *      *  * Input: X[] = [0, 1, 1, 1, 1, 0, 0, 1, 1], Output: 4
     *      *  * Input: X[] = [1, 1, 1, 1], Output: 4
     *      *  * Input: X[] = [0, 0, 1, 0, 1], Output: 1
     */
    public static int linearSearch(int[] x) {
        int n = x.length;
        int maxCount = 0;
        int oneCount = 0;

        for(int i=0; i<n; i++) {
            if(x[i] ==1) {
                oneCount++;
            } else {
                oneCount = 0;
            }

            if(oneCount > maxCount) {
                maxCount = oneCount;
            }
        }
        return maxCount;
    }
}
