package practice.stack;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed)
 * parentheses substring.
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 *
 * Input: s = ""
 * Output: 0
 *
 * Input: s = ((())
 * Output: 0
 */
public class LongestValidParentheses {
    public static void main(String args[]) {
        String[] s1 = {"(","(",")"};
        String[] s2 = {")","(",")","(",")",")"};
        String[] s3 = {};

        System.out.println(usingStack(s1));
        System.out.println(usingStack(s2));
        System.out.println(usingStack(s3));

    }

    /**
     * Observation - since only ( and ) parenthese are allowed -  use stack and keep counting the valid ones
     * return 2* valid ones.
     */

    public static int usingStack(String[] str) {
        int n = str.length;
        Stack<String> stack = new Stack<>();
        int count = 0;

        if(n ==0) {
            return 0;
        }

        for(int i=0; i<n; i++) {
            if(str[i].equals("(")) {
                stack.push(")");
            } else {
                if(!stack.isEmpty() && stack.peek().equals(str[i])) {
                    count++;
                    stack.pop();
                }
            }
        }
        return 2*count;
    }
}
