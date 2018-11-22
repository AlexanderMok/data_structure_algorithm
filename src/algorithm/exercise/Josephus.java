package algorithm.exercise;

import java.util.ArrayDeque;

import algorithm.structure.queue.Queue;
/**
 * Josephus problem. In the Josephus problem from antiquity, 
 * N people are in dire straits and agree to the following 
 * strategy to reduce the population. They arrange themselves 
 * in a circle (at positions numbered from 0 to N-1) and 
 * proceed around the circle, eliminating every Mth person 
 * until only one person is left. Legend has it that 
 * Josephus figured out where to sit to avoid being eliminated.
 * <p>
 * takes M and N from the command line and prints out the order 
 * in which people are eliminated (and thus would show Josephus 
 * where to sit in the circle) 
 * @author Alex
 *
 */
public class Josephus {
    public static void main(String[] args) {
		killQueue(3, 6);
		System.out.println();
		jdkQueue(3, 6);
	}
    
    /**
     * O(n*m)
     * @param m
     * @param n
     */
    public static void killQueue(int m, int n) {
    	Queue<Integer> queue = new Queue<>();
    	// round a circle O(n)
    	for (int i = 0; i < n; i++) {
    		queue.enqueue(i);
    	}
    	while (!queue.isEmpty()) {
    		// kill mth position
    		for (int i = 0; i < m - 1; i++) {
    			queue.enqueue(queue.dequeue());
    		}
    		System.out.print(queue.dequeue() + " ");
    	}
    }
    
    /**
     * O(n*m)
     * {@code ArrayDeque} can also be {@code java.util.LinkedList}
     * @param m
     * @param n
     */
    public static void jdkQueue(int m, int n) {
    	java.util.Queue<Integer> queue = new ArrayDeque<>();
    	for (int i = 0; i < n; i++) {
    		queue.add(i);
    	}
    	while(!queue.isEmpty()){
    		for (int i = 0; i < m - 1; i++) {
    			Integer t = queue.remove();
    			queue.add(t);
    		}
    		Integer r = queue.remove();
    		System.out.print(r + " ");
    	}
    }    
}
