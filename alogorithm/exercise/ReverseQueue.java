package algorithm.exercise;

import algorithm.structure.queue.Queue;
import algorithm.structure.stack.Stack;

public class ReverseQueue {
    public static void main(String[] args) {
    	Queue<Integer> queue = new Queue<>();
    	queue.enqueue(1);
    	queue.enqueue(2);
    	queue.enqueue(3);
    	queue.enqueue(4);
    	queue.enqueue(5);
    	System.out.println(queue.toString());
    	Stack<Integer> stack = new Stack<>();
    	while (!queue.isEmpty()) {
            stack.push(queue.dequeue());			
		}
    	while(!stack.isEmpty()) {
    		queue.enqueue(stack.pop());
    	}
    	System.out.println(queue.toString());
	}
}
