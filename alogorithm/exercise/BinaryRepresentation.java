package algorithm.exercise;

import java.util.Scanner;

import algorithm.structure.stack.Stack;

/**
 * Use a stack to compute the binary form of positive integer N
 * @author Alex
 *
 */
public class BinaryRepresentation {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		Stack<Integer> stack = new Stack<>();
		while (n > 0) {
			stack.push(n % 2);
			n = n / 2;
		}
		
		while(!stack.isEmpty()) {
			System.out.print(stack.pop());
		}
	}
}
