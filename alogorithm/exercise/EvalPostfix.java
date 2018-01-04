package algorithm.exercise;

import java.util.Scanner;

import algorithm.structure.stack.Stack;
/**
 * input 2 3 4 + 5 6 * * +
 * output 212 
 * @author Alex
 *
 */
public class EvalPostfix {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	while(scanner.hasNext()) {
    		String line = scanner.nextLine();
    		String[] elements = line.split(" ");
    		System.out.println(eval(elements));
    	}
    	scanner.close();
	}
    
    public static Integer eval(String[] postfixExpr) {
    	Stack<Integer> operandStack = new Stack<>();
    	for (String element : postfixExpr) {
    		switch (element) {
			case "+":
				operandStack.push(operandStack.pop() + operandStack.pop());
				break;
			case "-":
				operandStack.push(operandStack.pop() - operandStack.pop());
				break;
			case "*":
				operandStack.push(operandStack.pop() * operandStack.pop());
				break;
			case "/":
				operandStack.push(operandStack.pop() / operandStack.pop());
				break;
			default:
				operandStack.push(Integer.parseInt(element));
				break;
			}
		}
    	return operandStack.peek();
    }
}
