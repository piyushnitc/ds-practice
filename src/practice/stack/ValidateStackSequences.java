package practice.stack;

import java.util.Stack;

/**
 * Given two integer arrays pushed and popped each with distinct values, return true if this could have been the result
 * of a sequence of push and pop operations on an initially empty stack, or false otherwise.
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following sequence:
 * push(1), push(2), push(3), push(4),
 * pop() -> 4,
 * push(5),
 * pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * Example 2:
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * Output: false
 * Explanation: 1 cannot be popped before 2.
 */
public class ValidateStackSequences {
    public static void main(String args[]) {
        int[] pushed1 = {1,2,3,4,5}; int[] popped1 = {4,5,3,2,1};
        int[] pushed2 = {1,2,3,4,5}; int[] popped2 = {4,3,5,1,2};
        int[] pushed3 = {1,3,5,7,9}; int[] popped3 = {5,3,9};
        int[] pushed4 = {1,3,5,7,9}; int[] popped4 = {5,9,3};

        System.out.println(validate(pushed1, popped1));
        System.out.println(validate(pushed2, popped2));
        System.out.println(validate(pushed3, popped3));
        System.out.println(validate(pushed4, popped4));

        System.out.println(validateWithoutStack(pushed1, popped1));
        System.out.println(validateWithoutStack(pushed2, popped2));
        System.out.println(validateWithoutStack(pushed3, popped3));
        System.out.println(validateWithoutStack(pushed4, popped4));
    }

    /**
     * Observation
     * 1. push arrays to stack and at every push check if the top element is equal to the popped array.
     * if yes - pop out the element and increase the popped index to check another elements
     *
     * T.C = O(n)  S.C = O(n) -- as we are using stack
     *
     */

    public static boolean validate(int[] pushed, int[] popped) {
        int m = pushed.length;
        int n = popped.length;
        int j = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < m; i++) {
            stack.push(pushed[i]);

            while (!stack.isEmpty() && j < n && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return j == n;
    }

    /**
     * Idea is to use the pushed array itslef as stack by keeping a pointer
     */
    public static boolean validateWithoutStack(int[] pushed, int[] popped) {
        int m = pushed.length;
        int n = popped.length;
        int i = 0, j = 0;

        for(int val: pushed) {
            pushed[i] = val;
            i++;

            while(i-1 >0 && j<n && pushed[i-1] == popped[j]) {
                i--;
                j++;
            }
        }
        return j == n-1;
    }

}
