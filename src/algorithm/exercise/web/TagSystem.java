package algorithm.exercise.web;

import java.util.Scanner;

import algorithm.structure.queue.Queue;

/**
 * Tag systems. Write a program that reads in a binary string from the command
 * line and applies the following (00, 1101) tag-system: if the first bit is 0,
 * delete the first three bits and append 00; if the first bit is 1, delete the
 * first three bits and append 1101. Repeat as long as the string has at least 3
 * bits. Try to determine whether the following inputs will halt or go into an
 * infinite loop: 10010, 100100100100100100. Use a queue.
 * 
 * @author Alex
 *
 */
public class TagSystem {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String binaryString = input.nextLine();
		TagSystem.tag(binaryString);
		input.close();
	}

	private static void tag(String binaryString) {
		char[] chars = binaryString.toCharArray();
		Queue<Character> queue = new Queue<>();
		for (int i = 0; i < chars.length; i++) {
			queue.enqueue(chars[i]);
		}

		 while(queue.size() >=3){
			if (queue.peek().equals('0')) {
				queue.dequeue();
				queue.dequeue();
				queue.dequeue();
				queue.enqueue('0');
				queue.enqueue('0');
			} else if (queue.peek().equals('1')) {
				queue.dequeue();
				queue.dequeue();
				queue.dequeue();
				queue.enqueue('1');
				queue.enqueue('1');
				queue.enqueue('0');
				queue.enqueue('1');
			}
		}
		System.out.println(queue);
	}
}
