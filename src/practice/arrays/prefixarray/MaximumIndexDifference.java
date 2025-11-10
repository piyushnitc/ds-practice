package practice.arrays.prefixarray;

import java.util.Arrays;

/**
 * Given an array of n  integers, find the max j-i such that X[j] > X[i]
 * Input X[] = [40,20,70,10,-20,80,30,-10]
 * Output = 5
 * Explanation (40,80) at index (0,5) = 5
 * (20,30) at index (1,6) = 5
 *
 * X[] = [1,2,3,4,5,6]
 * Output = 5
 * max index diff (1,6) = 5
 *
 * X[] = [7,6,5,4,3,2]
 * Output = -1
 * condition X[j]>X[i] is not satisfied
 */
public class MaximumIndexDifference {

    public static void main(String args[]) {
        int[] x1 = {40,20,70,10,-20,80,30,-10};
        int[] x2 = {1,2,3,4,5,6};
        int[] x3 = {7,6,5,4,3,2};
        System.out.println(bruteForce(x1));
        System.out.println(bruteForce(x2));
        System.out.println(bruteForce(x3));

        System.out.println(binarySearch(x1));
        System.out.println(binarySearch(x2));
        System.out.println(binarySearch(x3));

        System.out.println(sortedApproach(x1));
        System.out.println(sortedApproach(x2));
        System.out.println(sortedApproach(x3));

        System.out.println(twoPointers(x1));
        System.out.println(twoPointers(x2));
        System.out.println(twoPointers(x3));

    }

    //T.C = O(n*n)
    public static int bruteForce(int[] x) {
        int maxDiff = -1;
        int n = x.length;
        for(int i=0; i<n-1; i++) {
            for(int j=i+1; j<n; j++) {
                if(x[j] > x[i]) {
                    maxDiff = Math.max(maxDiff,j-i);
                }
            }
        }
        return maxDiff;
    }

    // we need to find max (j-i) where X[j]>X[i]. i.e for every element from the left
    // we need to find the first element from right which is greater than x[i].
    // we can pre-process this  and store in an array x[maxfromend]
    public static int binarySearch(int[] x) {
        int n = x.length;
        int maxSoFar = Integer.MIN_VALUE;
        int[] maxFromEnd = new int[n];
        Arrays.fill(maxFromEnd,0);

        //T.C = O(n) for populating maxFromEnd[] array
        for(int i=n-1; i>=0; i--) {
            if (x[i] > maxSoFar) {
                maxFromEnd[i] = x[i];
                maxSoFar = x[i];
            } else {
                maxFromEnd[i] = maxSoFar;
            }
        }

        //Now for every x[i] we need to find first element from maxFromEnd[] from right
        // which is greater than or equal to x[i]
        // if we do linear search on maxFromEnd[] - we would end up with O(n) T.C for searching
        // If we do binary search (since elements are sorted) - we would end up with O(logn) T.C
        int maxIndexDiff = -1;

        // T.C for this for loop = o(n)
        for(int i=0; i<n; i++) {
            int leftIndex = i+1;
            int rightIndex = n-1;

            int farthestIndex = i;

            // This is binary search. T.C = o(logn)
            while(leftIndex <= rightIndex) {
                // below code is right in finding mid-index, but for very large arrays
                // it can cause array flow if left+right > 2^31. so a better way to do is
                // mid-index = left+(right-left)/2
                int mid = leftIndex+(rightIndex-leftIndex)/2;
                if(x[i] <= maxFromEnd[mid]) {
                    farthestIndex = Math.max(mid, farthestIndex);
                    leftIndex = mid+1;
                } else {
                    rightIndex = rightIndex-1;
                }
            }
            maxIndexDiff = Math.max(maxIndexDiff, farthestIndex-i);
        }

        return maxIndexDiff;
        // Total T.C = o(n) + n*O(logn) = O(n)+O(nlogn)
        // Space Complexity = O(n) - for using an extra array.
    }

    public static int sortedApproach(int[] x) {
        int n = x.length;
        Pair[] pairs = new Pair[n];

        //Populate pairs
        for(int i=0; i< n; i++) {
            pairs[i] = new Pair(x[i], i);
        }
        //array is sorted on value and hence x[j] > x[i] is already met. we need to track the max diff of indexes.
        // values: -20  -10 10  20  30  40  70  80
        // index:   4   7   3   1   6   0   2   5
        // values are already sorted. so if we traverse from right - we need to find the maxDiff between indexes and track
        // the farthest indexes seen so far
        // (70, 80) -->(2,5) --maxDiff = 5-2 = 3 --farthestIndex = 5;
        // (40,0) -->        --maxDiff = 5-0 = 5 --farthestIndex = 5;
        // (30,6) -->        --maxDiff = max(5-6, 5) = 5 --farthestIndex = 6;
        // This will take O(n) time complexity
        Arrays.sort(pairs);

        int farthestIndex = Integer.MIN_VALUE;
        int maxDiff = -1;
        for(int i=n-1; i>=0; i--) {
            int currentIndex = pairs[i].getIndex();
            if(currentIndex > farthestIndex) {
                farthestIndex = currentIndex;
            } else {
                maxDiff = Math.max(maxDiff, farthestIndex-currentIndex);
            }
        }
        return maxDiff;
    }

    //Obs1 - for every element of x[i] if there is an element smaller than x[i] on the left side
    // - we do not need to consider  x[i] for left index.

    // Obs2 - for every element of x[j] if there is an element greater than x[j] on the right side
    // we do not need to consider x[j] for the right index.

    // X[] = {40,20,70,10,-20,80,30,-10}
    // leftMin[] = {40,20,20,10,-20,-20,-20,-20} - min from left
    // rightMax[] = {80,80,80,80,80,80,30,-10} - max from right

    // we traverse both the arrays and if leftMin[i] < rightMax[j] - we need to move j
    // to look for higher j-i value. we also track the maxDiff

    public static int twoPointers(int[] x) {
        int n = x.length;
        int[] leftMin = new int[n];
        int[] rightMax = new int[n];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<n; i++) {
            if(x[i] < min) {
                leftMin[i] = x[i];
                min = x[i];
            } else {
                leftMin[i] = min;
            }
        }

        for(int i=n-1; i>=0; i--) {
            if(x[i] > max) {
                rightMax[i] = x[i];
                max = x[i];
            } else {
                rightMax[i] = max;
            }
        }

        int left =0, right=0, maxDiff = 0;

        while(left < n && right < n) {
            if(leftMin[left] <= rightMax[right]) {
                maxDiff = Math.max(maxDiff, right-left);
                right++;
            } else {
                left++;
            }
        }
        if(maxDiff ==0) {
            return -1;
        }
        return maxDiff;
    }
}
