package alogorithm.structure.queue;

/**
 * Simple implementation of priority queue using elementary sort.
 * 
 * not practical for huge input
 * 
 * add code for insert to move larger entries one position to the right, thus
 * keeping the entries in the array in order (as in insertion sort). Insertion
 * is stable
 * 
 * @author Alex
 *
 * @param <E>
 */
public class PriorityQueueOrderedMax<E extends Comparable<E>> {
	// array to store elements
	private E[] pq;
	// number of elements
	private int n;
	
	public PriorityQueueOrderedMax(int capacity) {
		pq = (E[])new Comparable[capacity];
		n = 0;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	public void insert(E key) {
		int i = n - 1;
		// find index to insert
		while (i >= 0 && less(key, pq[i])) {
			// move those larger than key to right
			pq[i + 1] = pq[i];
			i--;
		}
		pq[i + 1] = key;
		n++;
	}

	public E delMax() {
		// largest one always on the right
		return pq[--n];
	}

	private boolean less(E v, E w) {
		return v.compareTo(w) < 0;
	}

	public static void main(String[] args) {

	}

}
