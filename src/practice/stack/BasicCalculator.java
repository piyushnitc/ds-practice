package practice.stack;

import java.util.Stack;

/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Input: s = "1 + 1"
 * Output: 2
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * There will be no two consecutive operators in the input.
 * Every number and running calculation will fit in a signed 32-bit integer.
 */
public class BasicCalculator {
    public static void main(String args[]) {
        String input1 = "1 + 1";
        String input2 = " 2-1 + 2 ";
        String input3 = "(1+(4+5+2)-3)+(6+8)";
        String input4 = "11+(14+15+12)-13+(16+18)";
        String input5 = "(1+(4+5+2)-3)";

//        System.out.println(usingStack(input1));
//        System.out.println(usingStack(input2));
//        System.out.println(usingStack(input3));
//        System.out.println(usingStack(input4));
//
//        System.out.println(usingStackAndSign(input1));
//        System.out.println(usingStackAndSign(input2));
//        System.out.println(usingStackAndSign(input3));
//        System.out.println(usingStackAndSign(input4));

        //System.out.println(recursive(input1));
        System.out.println(recursive(input5));

    }

    /**
     * Observation
     * - '' - is allowed. skip this whenever it is encountered. remove all the whitespaces
     * - extract the digits and sign from string and put it in stack.
     * - if closed bracket ) is encountered --> evaluate the expression and put it in stack
     * - whenever notation is encountered - evaluate the expression and put it in stack.
     */

    public static int usingStack(String input) {
        input = input.replace(" ", "");
        int n = input.length();
        Stack<String> stack = new Stack<>();
        StringBuilder temp = new StringBuilder();

        for(int i=0; i<n; i++) {

            if(String.valueOf(input.charAt(i)).equals(")")) {
                temp.setLength(0);
                // pop out elements till we encounter '('
                while(!stack.isEmpty() && !stack.peek().equals("(")) {
                    temp.append(stack.pop());
                }
                // remove '('
                stack.pop();
                StringBuilder tempReversed = reversed(temp); // get the expression right

                // evaluate this expression
                int result = evaluate(tempReversed);

                // convert int to character and push this to stack
                stack.push(String.valueOf(result));
            } else {
                String tempDigit = "";
                if(Character.isDigit(input.charAt(i))) {
                    while(i< n && Character.isDigit(input.charAt(i))) {
                        tempDigit = tempDigit+input.charAt(i);
                        i++;
                    }
                    i--;
                } else {
                    tempDigit = String.valueOf(input.charAt(i));
                }
                stack.push(tempDigit);
            }
        }

        temp.setLength(0);
        while(!stack.isEmpty()) {
            temp.append(stack.pop());
        }

        // get the expression right. can not do reverse. need to tokenize them and do reverse.
        StringBuilder tempReversed = reversed(temp);

        // evaluate the expression
        int result = evaluate(tempReversed);
        stack.push(String.valueOf(result));

        return Integer.valueOf(stack.pop());
    }

    public static StringBuilder reversed(StringBuilder sb) {
        String[] numbers = sb.toString().split("(?=[+-])|(?<=[+-])");
        StringBuilder reversed = new StringBuilder();

        for(int i=0; i<numbers.length; i++) {
            reversed.append(numbers[numbers.length-1-i]);
        }
        return reversed;
    }

    private static int evaluate(StringBuilder temp) {
        int result = 0;
        String[] numbers = temp.toString().split("(?=[+-])|(?<=[+-])");  // numbers = [1,+, 11, -, 3]

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].equals("+")) {
                result = (Integer.valueOf(numbers[i - 1]) + Integer.valueOf(numbers[i + 1]));
                numbers[i + 1] = String.valueOf(result);
            } else if (numbers[i].equals("-")) {
                result = (Integer.valueOf(numbers[i - 1]) - Integer.valueOf(numbers[i + 1]));
                numbers[i + 1] = String.valueOf(result);
            }
        }
        return result;
    }

    /**
     * Observation - 2
     * - using stack and sign
     * - trim all the whitespaces
     * - if digit -- read all the characters and store  the result with current sign in stack
     * - ( --> push sign into stack
     * - ) -- pop elements from stack
     * - + --> set sign = +1
     * - - --> set sign = -1
     */
    //Input: s = "(1+(4+5+2)-3)+(6+8)" -- (1(5
    public static int usingStackAndSign(String input) {
        input = input.replaceAll(" ", "");
        int n = input.length();
        Stack<String> stack = new Stack<>();

        int i=0;
        int sign = 1;
        int result = 0;

        while(i< n) {
            if(input.charAt(i) == '(') {
                stack.push(String.valueOf(result));
                stack.push(String.valueOf(sign));
                result = 0;
                sign = 1;
                i++;
            } else if(input.charAt(i) == '+') {
                sign = 1;
                i++;
            } else if(input.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if(Character.isDigit(input.charAt(i))) {
                // build the string with all the characters
                String temp = "";
                while(i<n && Character.isDigit(input.charAt(i))) {
                    temp = temp+String.valueOf(input.charAt(i));
                    i++;
                }
                result = result+ sign * Integer.valueOf(temp);
            } else if(input.charAt(i) == ')') {
                // pop the elements
                result = result * Integer.valueOf(stack.pop()) + Integer.valueOf(stack.pop());
                i++;
            }
        }
        return result;
    }

    /**
     * using recursion
     * - call the function recursively when ever we encounter '(' and return to the caller when we encounter ')'
     */

    // (1+(4+5+2)-3)
    public static int recursive(String input) {
        input = input.replaceAll(" ", "");
        int n = input.length();
        int result = 0;
        int i = 0;
        int sign = 1;
        int num = 0;
        StringBuilder sb = new StringBuilder();

        while(i < n) {
            if(Character.isDigit(input.charAt(i))) {
                sb.setLength(0);
                while (i<n && Character.isDigit(input.charAt(i))) {
                    sb.append(input.charAt(i));
                    i++;
                }
                result = result + sign * Integer.valueOf(sb.toString());
            } else if((input.charAt(i) == '+') || (input.charAt(i) == '-')){
                sign = input.charAt(i) == '+' ? 1: -1;
                i++;
            } else if(input.charAt(i) == '(') {
                num = recursive(input.substring(i+1));
            } else if(input.charAt(i) == ')') {
                result = result + num * sign;
                return result;
            }
            result = result + num * sign;
        }
        return result;
    }
}
