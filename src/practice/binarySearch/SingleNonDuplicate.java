package practice.binarySearch;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one
 * element which appears exactly once. Return the single element that appears only once.
 *
 * Your solution must run in O(log n) time and O(1) space.
 *
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 *
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 */

public class SingleNonDuplicate {

    /**
     * Obs - scan linearly and keep a track where elements appear only once. T.C = O(n)
     *
     */
    public static void main(String args[]) {
        int[] x1 = {1,1,2,3,3,4,4,8,8}; // o/p = 2
        int[] x2 = {3,3,7,7,10,11,11};  // o/p = 10
        int[] x3 = {1,2,2,3,3}; // o/p = 1

        System.out.println(linearSearch(x1));
        System.out.println(linearSearch(x2));
        System.out.println(linearSearch(x3));

        System.out.println(binarySearch(x1));
        System.out.println(binarySearch(x2));
        System.out.println(binarySearch(x3));
    }

    //T.C = O(n). However the problem statement asks for T.C = O(logn) ==> it means we need to apply binary search
    public static int linearSearch(int[] x) {
        int num = -1;
        int n = x.length;

        for(int i=0; i<n; i= i+2) {
            if(x[i] == x[i+1]) {
                continue;
            } else {
                return x[i];
            }
        }
        return 0;
    }

    /**
     * Two ideas are there
     * 1. Since there is exactly one number without a pair --> this means that the size of the array is odd length
     * and if we remove a pair from it ---> the size would still be odd.  at this point we need to look for the array
     * which is odd and we keep doing it till we find the single element.
     *
     * eg - 1,1,2,3,3,4,4,8,8  -- no of elements = 9
     * lets define low = 0, high = 8  hence mid = low + (high-low)/2 = 0+ 9/2 = 4
     * so if we remove 4th element --> we will have equal halves (1,1,2,3) and (4,4,8,8)
     * haveEqualHalves = true
     *
     * eg - 1,1,2,3,3,4,4,8,8  -- no of elements = 9
     * lets define low = 0, high = 8  hence mid = low + (high-low)/2 = 0+ 9/2 = 5
     * so if we remove 5th element --> we will not have equal halves (1,1,2,3,3) and (4,8,8)
     * haveEqualHalves = false
     *
     * if x[mid] = x[mid+1] -->
     *  ----> haveEqualHalves
     *          --> yes -- low = mid +2
     *          --> no  -- high = mid -1
     * if x[mid] = x[mid-1]
     *  ----> haveEqualHalves
     *          --> yes -- high = mid - 2
     *          --> no  -- low = mid + 1
     * else -- return mid
     *
     */

    public static int binarySearch(int[] x) {
        int n = x.length;
        int low =0, high = n-1;

        while(low < high) {
            int mid = low + (high-low)/2;
            boolean halvesAreEven = mid %2 == 0;

            if(x[mid] == x[mid+1]) {
                if(halvesAreEven) {
                    low = mid +2;
                } else {
                    high = mid-1;
                }
            } else if(x[mid] == x[mid-1]) {
                if(halvesAreEven) {
                    high = mid -2;
                } else {
                    low = mid+1;
                }
            } else {
                return x[mid];
            }
        }
        return x[low];
    }
}
