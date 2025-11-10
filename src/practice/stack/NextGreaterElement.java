package practice.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an array, find the Next Greater Element for every element. The Next Greater Element for an element is the first
 * greater element on the right side of the array. Elements for which no greater element exist, consider the next
 * greater element as -1.
 *
 * Input: A[] = [3, 2, 8, 7, 9, 17, 12], Output: [8, 8, 9, 9, 17, -1, -1]
 * Explanation: Traversing through the array for each element, we find the next greater element on its right side
 * i.e., NGE of 3 is 8, NGE of 2 is 8, NGE of 8 is 9, NGE of 7 is 9, and so on.
 *
 * Input: A[] = [4, 5, 2, 25, 10], Output: [5, 25, 25, -1, -1]
 */
public class NextGreaterElement {
    public static void main(String args[]) {
        int[] x1 = {3, 2, 8, 7, 9, 17, 12};
        int[] x2 = {4, 5, 2, 25, 10};

        System.out.println(Arrays.toString(bruteForce(x1)));
        System.out.println(Arrays.toString(bruteForce(x2)));

        System.out.println(Arrays.toString(usingStack(x1)));
        System.out.println(Arrays.toString(usingStack(x2)));
    }

    /**
     * Obs - Brute force
     *  - scan the array and if you find element greater than current element --> store o/p as greater element
     */

    public static int[] bruteForce(int[] x) {
        int n = x.length;
        int[] output = new int[n];
        int k = 0;
        int currElement = x[0];
        for(int i=1; i<n; i++) {        //T.C = O(n)
            if(currElement < x[i]) {
                while(k<i) {            //T.C = O(n) -- worst case
                    output[k] = x[i];
                    k++;
                }
                currElement = x[i];
            }
        }
        while(k<n) {
            output[k] = -1;
            k++;
        }
        return output;      //T.C = O(n^2)   S.C = O(n) for output array.
    }

    /**
     * Input: A[] = [3, 2, 8, 7, 9, 17, 12], Output: [8, 8, 9, 9, 17, -1, -1]
     * start pushing elements to stack 2,3. when it comes to 8 > top of stack keep popping out untill stack is empty
     * or 8 < top of stack   now push 8 into stack as many we popped out.  8 8. currentElement becomes 8
     * repeat the same procedure
     */
    public static int[] usingStack(int[] x) {
       int n = x.length;
       int k = 0;
       Stack<Integer> s = new Stack<>();
       int[] output = new int[n];

       s.push(x[0]);

       for(int i=1; i<n; i++) {                     // T.C = O(n)
           while(!s.empty() && x[i] > s.peek()) {
               output[k] = x[i];
               k++;
               s.pop();
           }
           s.push(x[i]);
       }

       while(!s.empty()) {
           output[k] = -1;
           k++;
           s.pop();
       }
        return output;
    }
}
