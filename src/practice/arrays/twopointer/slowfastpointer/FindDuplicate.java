package practice.arrays.twopointer.slowfastpointer;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n]
 * inclusive.
 * There is only one repeated number in nums, return this repeated number.
 * You must solve the problem without modifying the array nums and using only constant extra space.
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * Input: nums = [3,3,3,3,3]
 * Output: 3
 */
public class FindDuplicate {
    public static void main(String args[]) {
        int[] x1 = {1,3,4,2,2};
        int[] x2 = {3,1,3,4,2};
        int[] x3 = {3,3,3,3,3};

//        System.out.println(sortingAndCounting(x1));
//        System.out.println(sortingAndCounting(x2));
//        System.out.println(sortingAndCounting(x3));
//
//        System.out.println(usingHashTable(x1));
//        System.out.println(usingHashTable(x2));
//        System.out.println(usingHashTable(x3));
//
//        System.out.println(negativeMarking(x1));
//        System.out.println(negativeMarking(x2));
//        System.out.println(negativeMarking(x3));

//        System.out.println(cyclicSort(x1));
//        System.out.println(cyclicSort(x2));
//        System.out.println(cyclicSort(x3));
//
//        System.out.println(recursive(x1));
//        System.out.println(recursive(x2));
//        System.out.println(recursive(x3));

        System.out.println(findDuplicate(x1));
//        System.out.println(findDuplicate(x2));
//        System.out.println(findDuplicate(x3));

        //System.out.println(binarySearch(x1));
        //System.out.println(findDuplicate(x2));
        //System.out.println(findDuplicate(x3));
    }

    //obs - if we sort the array the duplicate numbers will be adjacent to each other
    // compare the adjacent numbers and if they are equal - return that number
    // this approach modifies the array which we are not supposed to do
    public static int sortingAndCounting(int[] x) {
        int n = x.length;
        int duplicateNum = 0;
        Arrays.sort(x); //T.C = O(nlogn)

        //T.C = O(n)
        for(int i=1; i<n; i++) {
            if(x[i] == x[i-1]) {
                duplicateNum = x[i];
            }
        }
        return duplicateNum; //Total T.C = O(n)+O(nlogn)
    }

    // obs - add all numbers to hash table. if the key is already present that means
    // it's a duplicate number
    public static int usingHashTable(int[] x) {
        int n = x.length;
        int duplicate = 0;
        Hashtable<Integer, Integer> numTable = new Hashtable<>();

        for(int i =0; i<n; i++) {
            if(!numTable.containsKey(x[i])) {
                numTable.put(x[i], 0);
            } else {
                duplicate = x[i];
                break;
            }
        }
        return duplicate; //T.C = O(n) ; S.C = O(n)
    }

    //obs - scan the array from left to right. these numbers will be from [1..n]
    // i,e all numbers are positive in array
    // we iterate and we flip the number (with negative sign) for that index
    // eg - [1,3,3,2] --> first element is 1 ---> go to index 1 and flip the number
    // array would become [1,-3,3,2]... for second number 3 ---> go to index 3 and flip the number
    // array would become [1,-3,3,-2]... for third element 3 ---> go to index 3 and try flipping the
    // number. its already negative and hence 3 is duplicate

    //Note - this approach temporarily modify the array
    public static int negativeMarking(int[] x) {
        int n = x.length;
        int duplicate = 0;

        for(int i=0; i<n; i++) {
            int j = Math.abs(x[i]);
            if(x[j] < 0) {
                duplicate = j;
                break;
            } else {
                x[j] = -x[j];
            }
        }
        return duplicate; // Total T.C = O(n). No space complexity as we are modifying the same array
    }

    //obs - this approach is known as array as a hash map (iterative) or also called cyclic sort
    // idea is to always map the number at index 0 to its equivalent index
    // eg:- [3,3,5,4,1,3]; num[0] = 3, check the value at position 3 i.e num[3] = 4
    // num[0] != num[3] - swap the elements [4,3,5,3,1,3]
    // check num[0] = 4 and num[4] = 1 -- not equal swap --> [1,3,5,3,4,3]
    // check num[0] = 1 and num[1] = 3 -- not equal swap --> [3,1,5,3,4,3]
    // check num[0] = 3 and num[3] = 3 -- equal ---> found the duplicate = 3
    public static int cyclicSort(int[] x) {
        while(x[0] != x[x[0]]) {
            int temp = x[x[0]];
            x[x[0]] = x[0];
            x[0] = temp;
        }
        return x[0]; // T.C = O(n) - each elements needs to be swapped at least once. S.C = O(1)
    }

    /**
     * array as hash map (recursive)
     *
     * Idea is to map every element to its equivalent index. since there are 1..n elements one of the element wil be out
     * of place. lets start with index 0 for array [3,3,5,4,1,3]. at index 0 --> 3 is out of place. so we introduce 0 at
     * index 0 and back up 3 for repositioning
     * after first swap array would become [0,3,5,4,1,3]  with number 3 still to be repositioned.
     * call the function in recursive way ---> after 3 [0,3,5,3,1,3] with number 4 to be repositioned
     * call the function in recursive way ---> after 4 [0,3,5,3,4,3] with number 1 to be repositioned
     * call the function in recursive way ---> after 1 [0,1,5,3,1,3] with number 3 to be repositioned
     * 3 already exists at position 3. stop the recursion and return 3 as duplicate
     */

    public static int recursive(int[] x) {
        return store(x,0);
    }

    private static int store(int[] x, int curr) {
        if(x[curr] == curr) {
            return curr;
        }
        int temp = x[curr];
        x[curr] = curr;
        return  store(x,temp);
        //Time Complexity: O(n)
        //The function (store) makes a single recursive call at every instance. Each index is visited at
        // most once, resulting in O(n) time complexity.

        //Space Complexity: O(n)
        //Since this is a recursive function with up to n self-invocations (i.e. depth of the recursive
        // function = n), the space complexity is O(n) as there can be up to n function calls in memory
        // at some point. Note that due to the recursive nature of the solution, there is some additional
        // overhead on each invocation (such as the function variables and a return function pointer that
        // are stored on the system executable stack).
    }

    //obs - how can we apply binary search to find duplicate. we apply binary search to find the elements
    // one idea is to store the count of every element in the array
    // count([3,3,5,4,1,3]) - n = 5 {since the elements are in range of 1..n}. so we need to fill the
    // count array starting from 1 to 5
    //[count(1), count(2), count(3), count(4), count(5)] = [1,0,3,1,1]
    // now traverse this array and wherever count>1 --return the index. that's the duplicate.

    // question is -how do we find count using binary search.

    public static int findDuplicate(int[] nums) {
        // 'low' and 'high' represent the range of values of the target
        int low = 1, high = nums.length - 1;
        int duplicate = -1;

        while (low <= high) {
            int cur = (low + high) / 2;

            // Count how many numbers in 'nums' are less than or equal to 'cur'
            int count = 0;
            for (int num : nums) {
                if (num <= cur)
                    count++;
            }

            if (count > cur) {
                duplicate = cur;
                high = cur - 1;
            } else {
                low = cur + 1;
            }
        }
        return duplicate;
    }

}
