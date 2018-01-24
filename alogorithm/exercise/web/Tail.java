package algorithm.exercise.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import algorithm.structure.stack.Stack;

/**
 * Write a program Tail so that Tail k < file.txt 
 * prints the last k lines of the file file.txt. 
 * Use StdIn.readLine(). Which data structure should you use? 
 * @author Alex
 *
 */
public class Tail {
    public static void main(String[] args) {
		int k = 5;
		Scanner scanner = new Scanner(System.in);
		Stack<String> stack = new Stack<>();
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			if (line.equals("stop")) {
				break;
			}
			stack.push(line);
		}
		List<String> lines = tail(k, stack);
		lines.forEach(System.out::print);
		scanner.close();
	}
    
    public static List<String> tail(int k, Stack<String> stack) {
    	List<String> list = new ArrayList<>();
    	for(; k > 0; k--) {
    		String line = stack.pop();
    		list.add(line);
    	}
    	return list;
    }
}
