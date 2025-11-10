package practice.binarySearch;

/**
 * A sorted and rotated array of size n is given. Write a program to find the minimum element in this sorted and rotated array.
 * Rotation by k means: The first k elements of the array will move to the last k positions, and the last n - k elements
 * will move to the first n - k positions (in an anti-clockwise fashion). For example, rotating an array
 * X[0], X[1], X[2], ..., X[n-1] three-time results in the array X[3], X[4], X[5], ..., X[n-2], X[n-1], X[0], X[1], X[2].
 *
 * We don’t know how many times array is rotated in the given problem.
 * Assume that all elements in the array are unique.

 * Input: X[] = [5, 6, 7, 8, 9, 1, 2, 3, 4], Output: 1
 * Explanation: Array is rotated by 4, so minimum element is present at index 5.
 *
 * Input: X[] = [8, 9, 3, 4, 5, 6, 7], Output: 3
 * Explanation: Array is rotated by 5, so minimum element is present at index 2.
 *
 * Input: X[] = [5, 6, 7, 8, 9], Output: 5
 * Explanation: Array is not rotated at all, so minimum element is present at index 0.
 *
 * Input: X[] = [8, 7], Output: 7
 * Explanation: Array is not rotated by 1, so minimum element is present at index 1.
 */
public class MinInRotatedAndSortedArray {
    public static void main(String args[]) {
        int[] x1 = {5, 6, 7, 8, 9, 1, 2, 3, 4}; // o/p = 1
        int[] x2 = {5, 6, 7, 8, 9};     // o/p = 5
        int[] x3 = {8, 9, 3, 4, 5, 6, 7};   // o/p = 3

        System.out.println(binarySearchIterative(x1));
        System.out.println(binarySearchIterative(x2));
        System.out.println(binarySearchIterative(x3));

        System.out.println(binarySearchRecursive(x1, 0, 8));
        System.out.println(binarySearchRecursive(x2, 0, 4));
        System.out.println(binarySearchRecursive(x3, 0, 6));

    }

    public static int binarySearchIterative(int[] x) {
        int n = x.length;
        int left = 0, right = n-1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if(x[mid] > x[n-1]) {
                // look into the right sub array
                left = mid+1;
            } else if(x[mid] < x[n-1]) {
                // look into the left sub array
                right = mid-1;
            } else {
                return x[mid];
            }
        }
        return x[left];
    }

    public static int binarySearchRecursive(int[] x, int left, int right) {
        int n = x.length;

        if(left < right) {
            if(x[left] < x[right]) {
                return x[left];
            }

            int mid = left + (right-left)/2;
            // look into the right sub array
            if(x[mid] > x[right]) {
                left = mid + 1;
                return binarySearchRecursive(x, left, right);
            } else {
               // look into the left sub array
                right = mid - 1;
                return binarySearchRecursive(x, left, right);
            }
        }
        return x[left];
    }
}
