package practice.stack;

import java.util.Stack;

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * Evaluate the expression. Return an integer that represents the value of the expression.
 *
 * Note that:
 *
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class ReversePolishNotation {
    public static void main(String args[]) {
        String[] token1 = {"2","1","+","3","*"};                                        // (2+1)*3 = 9
        String[] token2 = {"4","13","5","/","+"};                                       // (4+(13/5) = 6
        String[] token3 = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};    // 22

        System.out.println(usingStack(token1));
        System.out.println(usingStack(token2));
        System.out.println(usingStack(token3));
    }

    /**
     * Observation
     * scan the array and keep adding to stack. when we encounter symbol --> evaluate and push inside and continue doing
     * so untill the end of stack.
     */

    // {"10","6","9","3","+","-11","*","/","*","17","+","5","+"}
    public static int usingStack(String[] token) {
        int n = token.length;
        Stack<Integer> intStack = new Stack<>();

        for(int i=0; i<n; i++) {
            if(isNotation(token[i])) {
                int b = intStack.pop();
                int a = intStack.pop();
                int result = evaluate(a, b, token[i]);
                intStack.push(result);
            } else {
                intStack.push(Integer.valueOf(token[i]));
            }
        }
        return intStack.pop();
    }

    private static int evaluate(int a, int b, String op) {
        switch (op) {
            case "+": return  a+b;
            case "*": return  a*b;
            case "-": return  a-b;
            case "/": return  a/b;
        }
        return 0;
    }

    private static boolean isNotation(String s) {
        if((s.equals("+")) || (s.equals("-")) || (s.equals("*")) || (s.equals("/"))) {
            return true;
        }
        return false;
    }
}
