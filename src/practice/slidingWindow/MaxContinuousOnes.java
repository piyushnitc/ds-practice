package practice.slidingWindow;

/**
 * You are given an array of 1s and 0s, along with an integer k that represents the maximum number of allowed flips.
 * Write a program to return the count of the maximum consecutive 1s in the array, considering that you can flip at most k 0s.
 *
 * Input: X[] = [1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1], k = 2, Output: 8.
 * Explanation: We are allowed to flip a maximum of 2 zeroes. Flipping X[5] and X[7] results in 8 consecutive 1’s,
 * which is the maximum possible under the given constraints.
 *
 * Input: X[] = [1, 1, 0, 1, 0, 1, 0, 0, 1], k = 1, Output: 4.
 * Explanation: We are allowed to flip a maximum of 1 zero. Flipping X[2] gives us 4 consecutive 1's, which is the
 * maximum possible under the given constraints.
 */
public class MaxContinuousOnes {
    public static void main(String args[]) {
        int[] x1 = {1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1}; // k=2    op = 8
        int[] x2 = {1, 1, 0, 1, 0, 1, 0, 0, 1};     // k = 1    op = 4

        System.out.println(nestedLoops(x1, 2));
    }

    /**
     * Approach 1 - use nested loops
     * start with first element and keep a count of zeros which has been flipped. also track max.
     * do the same for every element. return max
     * T.C = O(n) *O(n)
     */
    public static int nestedLoops(int[] x, int k) {
        int n = x.length;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            int tempCount = 0;
            int flipZeros = k;
            for (int j = i; j < n; j++) {
                if (x[j] == 0 && flipZeros == 0) {
                    break;
                } else if (x[j] == 0 && flipZeros > 0) {
                    flipZeros--;
                    tempCount++;
                } else if (x[j] == 1) {
                    tempCount++;
                }
            }
            maxCount = Math.max(tempCount, maxCount);
        }
        return maxCount;
    }

    /**
     * Can it be done in a better way ? O(N) ---> sliding window approach
     * Obs1 -> if the no of zeros > k --> then we need to track max subarray size with k zeros
     * --> if the no of zeros < k --> then the output will be the size of the array
     * lets use two pointers l= 0 and run a loop from r=0 to n-1
     * whenever x[r] == 0  --> we increment zero count by 1
     * if zerocount > k
     *  --> we slide the left window. if x[l] == 0 ---> we reduce the zero count and take the max count
     *
     */

    public static int slidingWindow(int[] x, int k) {
        int n = x.length;
        int l=0, maxCount = 0, zeroCount = 0;

        for(int r=0; r<n; r++) {
            if(x[r] == 0) {
                zeroCount++;
            }

            if(zeroCount > k) {
                if(x[l] == 0) {
                    zeroCount --;
                }
                l = l+1;
            }
            maxCount = Math.max(maxCount, r-l+1);
        }
        return maxCount;
    }
}
