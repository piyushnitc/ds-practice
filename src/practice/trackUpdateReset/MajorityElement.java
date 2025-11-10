package practice.trackUpdateReset;

import java.util.Random;

/**
 * You are given an array X[] consisting of n elements, write a program to find the majority element in an array i.e.
 * return the number which appears more than n/2 times.
 *
 * Assume that array is non-empty and the majority element always exists.
 * A majority element appears more than n/2 times, so there is at most one such element.
 * Examples
 *
 * Input: X[] = [2, 12, 2, 2, 2, 4, 2], Output: 2
 * Explanation: 2 is the majority element which appears 5 times.
 *
 * Input: A[]= [3, 3, 4, 2, 4, 4, 2, 4, 4], Output: 4
 * Explanation: The frequency of 4 is 5 which is greater than half of the size of the array.
 *
 * Input: X[] = [4, 3, 4], Output: 4
 *
 * Input: X[] = [1, 1, 1, 1, 1, 1], Output: 1
 */
public class MajorityElement {
    public static void main(String args[]) {
        int[] x1 = { 2, 12, 12, 12, 2, 4, 12 };        // 2
        int[] x2 = { 3, 3, 4, 2, 4, 4, 2, 4, 4 };   // 4
        int[] x3 = { 4, 3, 4 };                     // 4
        int[] x4 = { 1, 1, 1, 1, 1, 1 };            // 1

       System.out.println(getBit(2,0));
        System.out.println(bitManipulation(x1));
        System.out.println(bitManipulation(x2));
        System.out.println(bitManipulation(x3));
        System.out.println(bitManipulation(x4));

        System.out.println(divideAndConquer(x1, 7));
        System.out.println(divideAndConquer(x2, 9));
        System.out.println(divideAndConquer(x3, 3));
        System.out.println(divideAndConquer(x4, 6));

        System.out.println(randomizedApproach(x1));
        System.out.println(randomizedApproach(x2));
        System.out.println(randomizedApproach(x3));
        System.out.println(randomizedApproach(x4));

        System.out.println(efficientApproach(x1));
        System.out.println(efficientApproach(x2));
        System.out.println(efficientApproach(x3));
        System.out.println(efficientApproach(x4));
    }

        /**
         * Observation 1
         * - brute force approach --> count every element and find the max count of the element -- O(n*n)
         * - sort the array O(nlogn). Since majority is >n/2 --> element will be present at n/2 index (true for both odd and even array
         * - use hashTable  T.C = O(n) S.C = O(n)
         *
         * The below approaches are awesome and must be implemented.
         * - binarySearch ??
         * - divide and conquer ??
         * - using bit manipulation ??
         *      let's convert all the integers into their bit
         *      2   0010
         *      12  1100
         *      2   0010
         *      2   0010
         *      2   0010
         *      4   0100
         *      2   0010
         *  Identify the majority bits at each position and reconstruct the number
         *  eg:- 0010 -- 1 is in majority at 3rd position and hence 0 will be majority at rest of the positions = 2
         *
         * - using randomized algorithm  ??
         * - using efficient O(n) approach ??
         */
        
        public static int bitManipulation(int[] x) {
            int majority = 0;
            int n = x.length;

            for(int currBit=0; currBit<32; currBit++) {
                int onesCount = 0;

                for(int i=0; i<n; i++) {
                    if((x[i] != 0) && getBit(x[i], currBit) != 0) {
                        onesCount++;
                    }
                }
                if(onesCount > n/2) {
                    majority = majority + (int) Math.pow(2,currBit);
                }
            }
            return majority;
     }

    public static int getBit(int value, int position) {
        return (value >> position) & 1;
    }

    /**
     * divide and conquer - how can we implement this
     * [2, 12, 2, 2, 2, 4, 2]
     * divide this in two equal parts left and right array and find the majority.
     * if left and right has the same majority --> then its a global majority
     * or else divide further and check left and right majority
     *
     * T.C for divide part = O(1)
     * T.C for conquer part = 2T(n/2)
     * T.C for combine part = O(n) + O(n) = O(n)
     *
     * T(n)= 2T(n/2) + O(n)
     * Using master theorem T(n) = O(nlogn)
     *
     * Space Complexity = O(logn) --> that many recursion stack;
     */

    public static int divideAndConquer(int[] x, int n) {
        return majorityElement(x, 0, n-1);
    }

    private static int majorityElement(int[] x, int l, int r) {
        if(l == r) {
            return x[l];
        }

        int mid = l + (r-l)/2;
        int leftMajority = majorityElement(x, 0, mid);
        int rightMajority = majorityElement(x,mid+1,r);

        if(leftMajority == rightMajority) {
            return leftMajority;
        }

        int leftCount = countFrequency(x, l, r, leftMajority);
        int rightCount = countFrequency(x, l, r, rightMajority);

        if(leftCount > (r-l+1)/2) {
            return leftMajority;
        } else if(rightCount > (r-l+1)/2) {
            return rightMajority;
        } else {
            return -1;
        }
    }

    private static int countFrequency(int[] x, int l, int r, int value) {
        int count = 0;
        for(int i=l; i<=r; i++) {
            if(x[i] == value) {
                count = count+1;
            }
        }
        return count;
    }

    /**
     * using randomized approach
     * lets say that we choose a random number and count the frequency of this number. if count > n/2 --> then its a majority
     * element.  But what if its not a majority element --> we need to take another random number from list
     * so what would be the T.C in such case
     * since there are majority elements (n/2+1) there is more than 50% chance that we would select a majority element at some point
     *
     * there are n choices of choosing an element . so success prob = (n/2+1)/n  = 1/2 + 1/n > 1/2
     * so prob of failure would be at most 1/2
     *
     * if we repeat the algorithm logn times - at most prob of failure = (1/2)^logn = 1/n
     * and prob of success = (1-1/n)
     *
     * each randomized algo takes O(n) and if we repeat logn times -- T.C = O(nlogn)
     *
     * for very large n failure = (1/2)^n  and success = 1-(1/2^n)
     */

    public static int randomizedApproach(int[] x) {
        Random rand = new Random();
        int n = x.length;
        int counter = 0;
        while (counter < n) {
            int random = rand.nextInt(0, n-1);
            counter++;
            int count = countMajority(x, random);
            if(count > n/2) {
                return x[random];
            }
        }
        return -1;
    }

    private static int countMajority(int[] x, int random) {
        int count = 0;
        for(int i=0; i<x.length; i++) {
            if(x[i] == x[random]) {
                count++;
            }
        }
        return count;
    }

    /**
     * Boyer-Moore Voting algorithm
     * [2, 12, 12, 2, 4, 2, 2]
     * The intuition behind Boyer-Moore Voting algorithm: Since the majority element appears more than n/2 times, its
     * frequency is greater than the combined frequency of all the other elements. So, if we mark the occurrence of the
     * majority element as +1 and the occurrence of any other element as -1, then the overall sum of these markings
     * would definitely be greater than zero.
     *
     * Note: As long as the majority element occurs with a frequency more than n/2, we can guarantee that this approach
     * will always find the majority element.
     */

    public static int efficientApproach(int[] x) {
        int count = 0, majorityElement = 0, n = x.length;

        for(int i=0; i<n; i++) {
            if(count == 0) {
                majorityElement = x[i];
            }

            if(x[i] == majorityElement) {
                count ++;
            } else {
                count --;
            }
        }
        return majorityElement;
    }
}
