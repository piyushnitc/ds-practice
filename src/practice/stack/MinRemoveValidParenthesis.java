package practice.stack;

import java.util.Stack;

/**
 *  Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 */
public class MinRemoveValidParenthesis {
    public static void main(String args[]) {
        String input1 = "lee(t(c)o)de)";
        String input2 = "a)b(c)d";
        String input3 = "))((";
        String input4 = "lee(t(co)de";
        String input5 = "a))b((c";

        System.out.println(usingStack(input1));
        System.out.println(usingStack(input2));
        System.out.println(usingStack(input3));
        System.out.println(usingStack(input4));
        System.out.println(usingStack(input5));

        System.out.println(withoutStack(input1));
        System.out.println(withoutStack(input2));
        System.out.println(withoutStack(input3));
        System.out.println(withoutStack(input4));
        System.out.println(withoutStack(input5));
    }

    /**
     * observation --> scan string and whenever you encounter ) push it in openParenthesis stack and character stack
     * -- if ( exists in character stack --> pop and continue. else mark for removal
     * -- if stack top has ( -- remove
     */

    public static String usingStack(String str) {
        int n = str.length();
        Stack<Character> stack = new Stack<>();
        Stack<Character> openParenthesis = new Stack<>();

        for(int i=0; i<n; i++) {
            if(str.charAt(i) == ')') {
                if(!openParenthesis.isEmpty()) {
                    openParenthesis.pop();
                    stack.push(')');
                }
            } else if(str.charAt(i) == '(') {
                openParenthesis.push('(');
                stack.push(str.charAt(i));
            } else {
                stack.push(str.charAt(i));
            }
        }

        // now reconstruct the string
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            if(!openParenthesis.isEmpty() && stack.peek() == '(') {
                stack.pop();
                openParenthesis.pop();
            } else {
                sb.append(stack.pop());
            }
        }
        return sb.reverse().toString();
    }

    /**
     * observation 2 - without stack
     * scan from left to right and if you encounter ( ---> scan from right to left and remove open parenthesis.
     * closed parenthesis is accepted
     *
     * - if left to right ) --> delete this
     * - if left to right ( --> check from right to left.. if its ) -- ok else delete (
     */
    //"a)b(c)d"
    public static String withoutStack(String str) {
        int n = str.length();
        int balance = 0;
        StringBuilder sb = new StringBuilder(str);

        // remove invalid closed parenthesis.
        for(int i=0; i<sb.length(); i++) {
            if (str.charAt(i) == '(') {
                balance++;
            } else if (str.charAt(i) == ')') {
                if (balance == 0) {
                    sb.deleteCharAt(i);
                    i--;
                } else {
                    balance--;
                }
            }
        }

        // remove invalid open parenthesis
        balance = 0;
        for(int i=sb.length()-1; i>=0; i--) {
            if(str.charAt(i) == ')') {
                balance++;
            } else if(str.charAt(i) == '(') {
                if(balance == 0) {
                    sb.deleteCharAt(i);
                } else {
                    balance--;
                }
            }
        }
        return sb.toString();
    }
}
