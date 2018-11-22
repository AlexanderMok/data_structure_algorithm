package algorithm.exercise;

import algorithm.structure.queue.Queue;
import algorithm.structure.stack.Stack;
/**
 * Use a Stack to reverse a Queue
 * @author Alex
 *
 */
public class ReverseQueue {
	public static void main(String[] args) {
    	Queue<Integer> queue = new Queue<>();
    	queue.enqueue(1);
    	queue.enqueue(2);
    	queue.enqueue(3);
    	queue.enqueue(4);
    	queue.enqueue(5);
    	System.out.println(queue);
    	reverseQueue(queue);
    	System.out.println(queue);
	}
    
    public static <T> Queue<T> reverseQueue(Queue<T> queue) {
    	Queue<T> re = queue;
    	Stack<T> stack = new Stack<>();
    	while (!re.isEmpty()) {
            stack.push(re.dequeue());			
		}
    	while(!stack.isEmpty()) {
    		re.enqueue(stack.pop());
    	}
    	return re;
    }
}
