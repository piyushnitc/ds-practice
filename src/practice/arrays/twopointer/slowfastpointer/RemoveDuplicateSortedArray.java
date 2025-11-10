package practice.arrays.twopointer.slowfastpointer;

/**
 * We are given a sorted array and need to write a program to remove duplicate elements so that there
 * is a single occurrence of each element in the array. Note: After removing duplicates, we need to
 * return the length of the array containing unique elements.
 *
 * Input: X[] = [2, 2, 2, 2, 2, 3, 3, 3], Output: 2, X[] = [2, 3]
 * Input: X[] = [1, 2, 2, 3, 4, 4, 4, 5, 5], Output: 5, X[] = [1, 2, 3, 4, 5]
 */
public class RemoveDuplicateSortedArray {
    public static void main(String args[]) {
        int[] x1 = {2, 2, 2, 2, 2, 3, 3, 3};
        int[] x2 = {1, 2, 2, 3, 4, 4, 4, 5, 5};

        System.out.println(bruteForce(x1));
        System.out.println(bruteForce(x2));

        System.out.println(twoPointers(x1));
        System.out.println(twoPointers(x2));
    }

    // obs - scan from left to right and count the unique elements since the array is already sorted
    public static int bruteForce(int[] x) {
        int uniqueElements = 1;
        int n = x.length;
        int previous = x[0];
        for(int i=1; i<n; i++) {
            if(previous != x[i]) {
                uniqueElements++;
                previous = x[i];
            }
        }
        return uniqueElements;
    }

    //obs - we use two pointers slow and fast.
    // slow starts at 0 and fast starts at 1;
    // while fast < n and if x[slow] != x[fast] -> we have hit a unique element. we increase the count
    // of slow by 1 and reassign new slow to x[fast]
    // finally return slow+1 (because slow is pointer. so there is one unique element - slow would be 0)
    public static int twoPointers(int[] x) {
        int slow = 0;
        int fast = 1;
        int n = x.length;

        while(fast < n) {
            if(x[fast] != x[slow]) {
                slow++;
                x[slow] = x[fast];
            }
            fast++;
        }
        return slow+1;
    }

}
