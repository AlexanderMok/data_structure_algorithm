package algorithm.exercise;

import java.util.Scanner;

import algorithm.structure.stack.Stack;

/**
 * input ( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )
 * output 2 3 4 + 5 6 * * + 
 * @author Alex
 *
 */
public class InfixToPostfix {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	while(scanner.hasNext()) {
    		String line = scanner.nextLine();
    		String[] elements = line.split(" ");
    		String result = infixToPostfix(elements);
    		System.out.println(result);
    	}
    	scanner.close();
	}

	public static String infixToPostfix(String[] elements) {
        StringBuilder operand = new StringBuilder();
		Stack<String> operatorStack = new Stack<>();
		for (String element : elements) {
			switch (element) {
			case "(":
				break;
			case ")":
				String str = operatorStack.pop();
				operand.append(str).append(" ");
				break;
			case "+":
				operatorStack.push(element);
				break;
			case "-":
				operatorStack.push(element);
				break;
			case "*":
				operatorStack.push(element);
				break;
			case "/":
				operatorStack.push(element);
				break;
			default:
				operand.append(element).append(" ");
				break;
			}
		}
		return operand.toString();
	}
}
