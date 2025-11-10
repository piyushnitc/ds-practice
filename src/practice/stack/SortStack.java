package practice.stack;

import java.util.Stack;

/**
 * Given a stack, write a program to sort the stack in ascending order. We are not allowed to make any assumptions
 * about how the stack is implemented. To implement this, we need to use following stack operations:
 *
 * push(s, x): Insert a new element to the stack.
 * pop(s): Remove the top element from the stack.
 * top(s): Return the top element from the stack.
 * isEmpty(s): Check whether the stack is empty or not.
 * Example 1
 *
 * Input stack: [4, 3, 6, 5, 1, 2] -> top, Output stack: [6, 5, 4, 3, 2, 1] -> top
 *
 * Input stack: [4, 3, 2, 1] ->top, Output stack: [4, 3, 2, 1] -> top
 *
 * Input stack: [5, 2, 8, 1, 9] ->top, Output stack: [9, 8, 5, 2, 1] -> top
 */
public class SortStack {
    public static void main(String args[]) {
        Stack<Integer> s1 =  new Stack<>();
        s1.push(2);
        s1.push(1);
        s1.push(5);
        s1.push(6);
        s1.push(3);
        s1.push(4);
        // [4,3,6,5,2,1] - stack elements
        System.out.println(useAnotherStack(s1));
    }

    /**
     * Observation
     * 1. using another stack
     *      -- pop out the element from stack and put it in temp stack
     *      -- 4  tempstack = [4]
     *      -- 3 tempStack.peek() > 3
     *          -- pop out 4 and put it in input stack  i/p stack = [4,6,5,1,2]
     *          -- put 3 in temp stack  tempStack = [3]
     *
     * continue doing this
     *
     * 2. using recursion
     *      -- pop out first element and sort rest n-1 element
     *      -- keep popping out element and use recursion
     *      -- for inserting top element
     */

    public static Stack<Integer> useAnotherStack(Stack<Integer> inputStack) {
        Stack<Integer> tempStack = new Stack<>();

        while(!inputStack.empty()) {
            int currElement = inputStack.pop();

            while(!tempStack.empty() && tempStack.peek() > currElement) {
                inputStack.push(tempStack.pop());
            }
            tempStack.push(currElement);
        }
        return tempStack;
    }

    public static void recursion(Stack<Integer> inputStack) {
        if(!inputStack.empty()) {
            int top = inputStack.pop();
            recursion(inputStack);
            insertTop(inputStack, top);
        }
    }

    private static void insertTop(Stack<Integer> stack, int top) {
        if(stack.empty() || stack.peek() > top) {
            stack.push(top);
            return;
        }
        int temp = stack.pop();
        insertTop(stack, temp);
        stack.push(temp);
    }

}
