package practice.arrays.twopointer.merging;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * Given two unsorted arrays X[] and Y[] of size m and n respectively, write a program to check whether
 * array Y[] is a subset of array X[] or not. Y[] will be a subset of X[] if each element of Y[] is
 * present in X[]. Assume that there are no repeated elements in both arrays and n <= m.
 *
 * Input: X[] = [2, 8, 12, 6, 10, 11], Y[] = [8, 2, 6, 11], Output: true
 * Explanation: All elements of Y[] are present in X[]. So Y[] is a subset of X[].
 *
 * Input: X[] = [6, 4, 8, 3, 2], Y[] = [4, 7, 3, 9], Output: false
 * Explanation: 7 and 9 of Y[] are not present in X[]. So Y[] is not a subset of X[].
 */
public class ArraySubsetOfAnotherArray {
    public static void main(String args[]) {
        int[] x1 = {2, 8, 12, 6, 10, 11}; // [2,6,8,10,11,12]
        int[] y1 = {8, 2, 6, 11};         // [2,6,8,11]

        int[] x2 = {6, 4, 8, 3, 2};
        int[] y2 = {4, 7, 3, 9};

        System.out.println(bruteForce(x1, y1));
        System.out.println(bruteForce(x2, y2));

        System.out.println(sortingAndBinarySearch(x1, y1));
        System.out.println(sortingAndBinarySearch(x2, y2));

        System.out.println(sortingAndTwoPointers(x1, y1));
        System.out.println(sortingAndTwoPointers(x2, y2));

        System.out.println(usingHashTable(x1, y1));
        System.out.println(usingHashTable(x2, y2));
    }

    /**
     * How many approaches we can think of?
     * 1. Brute Force --> search every element of y into x --> T.C = O(n*m)
     * 2. sort both the arrays. then do binary search on bigger array
     *  --> T.C = sorting two arrays + search m elements in an array of size n using binary serach
     *          = 2 * O(nlogn) + m * O(logn)
     *          = O(nlogn) + mO(logn)
     * 3. sort both the arrays and search using two pointers
     *
     * 4. use hashTable
     */

    public static boolean bruteForce(int[] x, int[] y) {
        boolean isFound = true;
        for(int i=0; i<y.length; i++) { // T.C = O(m)
             isFound = false;
            for(int j=0; j<x.length; j++) { // T.C = O(n)
                if(x[j] == y[i]) {
                    isFound = true;
                    break;
                }
            }
        }
        return isFound; //Total T.C = O(m*n)
        // Best T.C = O(m^2) --> both array
    }

    public static boolean sortingAndBinarySearch(int[] x, int[] y) {
        //Total T.C = O(nlogn)
        Arrays.sort(x); //[2,6,8,10,11,12]

        //If we do linear search for each element in y to array x -> T.C = O(m*n)
        // we did not benefit. its like first brute force approach only
        // what if we do binary search for m elements in n array -- > T.C = m * O(logn). better

        for(int i=0; i<y.length; i++) {
            int left = 0, right = x.length;
            int result = binarySearch(x,left,right,y[i]);
            if(result == -1) {
                return false;
            }
        }
        return true; //Total T.C = O(nlogn) + m*O(logn)  = O(nlogn+mlogn)
        // since n> m  --> nlogn > mlognn
        // hence T.C = O(nlogn)
    }

    private static int binarySearch(int[] x, int left, int right, int key) {

        while(left <= right) {
            int mid = left+(right-left)/2;

            if(x[mid] == key) {
                return mid;
            } else if(x[mid] > key) {
                right = mid -1;
            } else {
                left = mid+1;
            }
        }
        return -1;
    }

    public static boolean sortingAndTwoPointers(int[] x, int[] y) {
        Arrays.sort(x); //[2,6,8,10,11,12] T.C = O(nlogn)
        Arrays.sort(y); //[2,6,8,11] T.C = O(mlogm)

        //let's use one pointer(i) for array x and another pointer(j) for array y.
        // both starts at 0
        // if x[i] > y[j] --> return false;
        // if x[i] < y[j] --> increment i and check again;
        // if x[i] = y[j] --> increment i and j;
        // now what would be the loop condition? Think?
        // since n > m --> pointer j must be less than or equal to m --> this could be loop condition
        int i=0, j=0;
        //T.C = O(m+n) --> though we are looping only m times --> we are also incrementing n's pointer
        // hence total T.C = o(m+n)
        while(i < x.length && j < y.length) {
            if(x[i] == y[j]) {
                i++;
                j++;
            } else if(x[i] < y[j]) {
                i++;
            } else if(x[i] > y[j]) {
                return false;
            }
        }
        return true; // Total T.C = O(mlom) + O(nlogn) + O(m+n)
        // since n>m T.C = O(nlogn) + O(n)  === > O(nlogn)
        // there is no space complexity in the program but sorting arrays use space internally.
        // Hence, space complexity = O(m+n)
    }

    //obs - idea is put the bigger array in hash table. then search the smaller array in hash map
    public static boolean usingHashTable(int[] x, int[] y) {
        Hashtable<Integer, Integer> numMap = new Hashtable<>();

        //T.C = O(n)  S.C = O(n)
        for(int i=0; i<x.length; i++) {
            numMap.putIfAbsent(x[i], numMap.getOrDefault(x[i],0)+1);
        }

        //T.C = O(m) No space complexity
        for(int j=0; j<y.length; j++) {
            if(!numMap.containsKey(y[j])) {
                return false;
            }
        }
        return true; // Total T.C = O(m) + O(n) ==> O(n) since n>m
        // Space Complexity = O(n)
    }

}
