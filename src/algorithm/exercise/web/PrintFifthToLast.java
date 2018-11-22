package algorithm.exercise.web;

import java.util.Scanner;

import algorithm.structure.queue.Queue;
import algorithm.structure.stack.Stack;

/**
 * Interview question. Given a stack of an unknown number of strings, print out
 * the 5th to the last one. It's OK to destroy the stack in the process. Hint:
 * use a queue of 5 elements.
 * input ksdfojwdsf output ojwdsf
 * @author Alex
 *
 */
public class PrintFifthToLast {
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Stack<String> stack = new Stack<>();
        while(input.hasNext()){
        	String oneLetter = input.nextLine();
        	if(oneLetter.length() != 1) {
        	    System.out.println("");
        		continue;
        	}
        	if(oneLetter.equals("1")) {
        		break;
        	} 
            stack.push(oneLetter);        	
        }
        System.out.println(printFifthToLast2(stack));
        input.close();
	}
	
	public static String printFifthToLast(Stack<String> stack) {
		Queue<String> queue = new Queue<>();
		//remove or just get the bottom of the stack
		// put 5 to queue and print
		while(stack.Size() >= 5) {
			queue.enqueue(stack.pop());
		}
		return queue.toString();
	}
	
	public static String printFifthToLast2(Stack<String> stack) {
		for(int i = 0; i < 5; i++) {
			getAndRemoveLast(stack);
		}
		return stack.toString();
	}
	
	
	
	public static String getAndRemoveLast(Stack<String> stack) {
		String result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		} else {
			String last = getAndRemoveLast(stack);
			stack.push(result);
			return last;
		}
	}
}
