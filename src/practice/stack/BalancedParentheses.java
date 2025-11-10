package practice.stack;

import java.util.Stack;

/**
 * Given an expression string containing opening and closing parentheses, write a program to check if the expression is
 * a balanced expression or not. An expression is balanced if each opening parenthesis is closed by the same type of
 * closing parenthesis in the exact same order.
 *
 * Input: exp[] = "[ ( ) ] { } { ( ) ( ) }", Output: Balanced
 * Explanation: The expression is balanced because all the parentheses are well-formed.
 *
 * Input: exp[] = "[ ( ] )" Output: Not Balanced
 * Explanation: The expression is not balanced because there is a closing parenthesis ']' after the opening
 * parenthesis '('.
 */
public class BalancedParentheses {
    public static void main(String args[]) {
        String[] s1 = {"[", "(", ")", "]", "{","}","{", "(",")","(",")","}"};
        String[] s2 = {"[", "(", "]", ")"};

        System.out.println(valid(s1));
        System.out.println(valid(s2));
    }

    /**
     * Observation - there are three parenthesis  [], {}, ()
     * In a correct expression - we can visualize LIFO order. the opening parenthesis that comes last will have the
     * corresponding closing parenthesis comes first in the expression.
     *
     * So we can use stack.
     * Also, the opening parenthisis can not be ], }, )
     */

    public static boolean valid(String[] str) {
        int n = str.length;
        Stack<String> stack = new Stack<>();

        for(int i=0; i<n; i++) {
            if(str[i].equals("[") || str[i].equals("{") || str[i].equals("(")) {
                stack.push(str[i]);
            }
            if(str[i].equals("]") || str[i].equals("}") || str[i].equals(")")) {
                if(stack.isEmpty()) {
                    return false;
                } else if((stack.peek().equals("[") && !str[i].equals("]")) ||
                        (stack.peek().equals("{") && !str[i].equals("}")) ||
                        (stack.peek().equals("(") && !str[i].equals(")"))) {
                            return false;
                } else {
                    stack.pop();
                }
            }
        }
        return true;    // T.C = O(n)
    }

    /**
     * The above method can be rewritten in a different way. If we get "(" -- push ")" in stack
     */

    public static boolean valid2(String[] str) {
        int n = str.length;
        Stack<String> stack = new Stack<>();

        for(int i=0; i<n; i++) {
            if(str[i].equals("[")) {
                stack.push("]");
            } else if(str[i].equals("{")) {
                stack.push("}");
            } else if(str[i].equals("(")) {
                stack.push(")");
            } else if(!stack.isEmpty() && stack.peek().equals(str[i])) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }

    public static boolean usingCount(String[] str) {
        return false;
    }
}
