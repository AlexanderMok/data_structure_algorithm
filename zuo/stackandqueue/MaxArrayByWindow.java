package zuo.stackandqueue;

import java.util.LinkedList;

/**
 * An int array and a window in w length. Window will slide from left to right one unit per move.
 * <p>
 * Example [4,3,5,23,6,13,58,5], window length is 3
 * <pre>
 *     [4,3,5],23,6,13,58,5  --> 5
 *     4,[3,5,23],6,13,58,5  --> 23
 *     4,3,[5,23,6],13,58,5  --> 23
 *     4,3,5,[23,6,13],58,5  --> 23
 *     4,3,5,23,[6,13,58],5  --> 58
 *     4,3,5,23,6,[13,58,5]  --> 58
 *  Now output, the MaxArray is [5,23,23,23,58,58]
 *  </pre>
 *  
 *  array length is n, window's is w, then length of MaxArray is n-w+1
 *  <p>
 *  key: use Dequeue to keep and update max value
 *  <p>
 *  While looping the array in position i, queueMax rule:<pre>
 *  enqueue rule:
 *  if queueMax is empty, put i into queue
 *  if queueMax is not empty, poll the tail value j, which is an index of array
 *    if array[j] > array[i], add i to the tail of queueMax
 *    if array[j] <= array[i], we have bigger value and so dequeue j
 *    
 *  dequeue rule:
 *        
 * @author Alex
 *
 */
public class MaxArrayByWindow {
    
	public static int[] getMaxWindow(int[] array, int window) {
		//defensive code
		if(array == null || window < 1 || array.length < window){
			return null;
		}
		//create a Dequeue to store index of array
		LinkedList<Integer> queueMax = new LinkedList<>();
		int[] result = new int[array.length - window + 1];
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			while(!queueMax.isEmpty() && array[queueMax.pollLast()] <= array[i]) {
				queueMax.pollLast();
			}
			//update index of Max within this window movement
			queueMax.addLast(i);
			//head of the queue is out of date. not in this window
			if(queueMax.peekFirst() == i - window) {
				queueMax.pollFirst();
			}
			if(i >= window - 1) {
				result[index++] = array[queueMax.peekFirst()]; 
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] array = {4,3,5,23,6,13,58,5};
		int window = 3;
		
		int[] res = getMaxWindow(array, window);
		for (int i : res) {
			System.out.print(i + " ");
		}

	}

}
