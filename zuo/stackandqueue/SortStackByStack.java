package zuo.stackandqueue;

import java.util.Stack;

/**
 * Sort a stack by another stack in descending order. Only one stack is allowed
 * to use.
 * 
 * 1.Initialize a help Stack for sortion<br>
 * 2.Store the head of the Stack needed to sort as currentValue<br>
 * 3.If currentValue less than or equals with the head of help Stack, push
 * currentValue to help Stack<br>
 * 4.if currentValue greater than the head of help Stack, pop elements of help
 * Stack until it meets the element less than or equals with currentValue. Then
 * push currentValue into help Stack 5.Now the help Stack is sorted and the
 * original stack is empty. Push elements from help stack to the original stack.
 * 
 * @author Alex
 *
 */
public class SortStackByStack {

	public static void sortStackByStack(Stack<Integer> stack) {
		Stack<Integer> help = new Stack<>();
		while (!stack.isEmpty()) {
			Integer currentValue = stack.pop();
			while (!help.isEmpty() && currentValue > help.peek()) {
				stack.push(help.pop());
			}
			help.push(currentValue);
		}
		while (!help.isEmpty()) {
			stack.push(help.pop());
		}
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(43);
		stack.push(4);
		stack.push(233);
		stack.push(6);
		stack.push(53);
		stack.push(93);
		stack.forEach(item -> System.out.print(item + " "));
		System.out.println();
		SortStackByStack.sortStackByStack(stack);
		stack.forEach(item -> System.out.print(item + " "));
	}

}
