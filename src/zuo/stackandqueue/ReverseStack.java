package zuo.stackandqueue;

import java.util.Stack;

/**
 * Reverse a stack. Use only one stack. Use recursive function.
 * @author Alex
 *
 */
public class ReverseStack {
	
	/**
	 * Return and remove the last item of a stack. Keep others in place.
	 * @param stack
	 * @return the last item of a stack
	 */
    public static int getAndRemoveLast(Stack<Integer> stack) {
    	int result = stack.pop();
    	if (stack.isEmpty()) {
			return result;
		} else {
			int last = getAndRemoveLast(stack);
			stack.push(result);
			return last;
		}
    }
    
    /**
     * Reverse a stack 
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {
    	if (stack.isEmpty()) {
			return;
		}
    	int item = getAndRemoveLast(stack);
    	reverse(stack);
    	stack.push(item);
    }
    
    public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(3);
		stack.push(2);
		stack.push(1);
		System.out.println(stack);
		ReverseStack.reverse(stack);
		System.out.println(stack);
	}
}
