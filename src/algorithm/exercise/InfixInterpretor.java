package algorithm.exercise;

import java.io.IOException;
import java.util.Scanner;

import algorithm.structure.stack.Stack;

/**
 * 中缀表达式。使用双栈。
 * @author Alex
 *
 */
public class InfixInterpretor {
	public static void main(String[] args) throws IOException {
		Stack<Integer> valueStack = new Stack<>();
		Stack<String> operatorStack = new Stack<>();
		
		/**
		 * 左括弧忽略 
		 * 若 操作数，入栈1 
		 * 若 操作符，入栈2 
		 *   若 操作符为右括弧，pop one operator and two value
		 *   and compute and push result to value stack
		 */
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			if(input.equals("s")) break;
			switch (input) {
			case "+":
				operatorStack.push(input);
				break;
			case "-":
				operatorStack.push(input);
				break;
			case "*":
				operatorStack.push(input);
				break;
			case "/":
				operatorStack.push(input);
				break;
			case ")":
				String op = operatorStack.pop();
				if (op.equals("+")) {
					valueStack.push(valueStack.pop() + valueStack.pop());
				} else if (op.equals("-")) {
					valueStack.push(valueStack.pop() - valueStack.pop());
				} else if (op.equals("*")) {
					valueStack.push(valueStack.pop() * valueStack.pop());
				} else if (op.equals("/")) {
					valueStack.push(valueStack.pop() / valueStack.pop());
				}
			case "(":
				break;
			default:
				valueStack.push(Integer.parseInt(input));
				break;
			}
		}
		System.out.println(valueStack.peek());
	}
}
