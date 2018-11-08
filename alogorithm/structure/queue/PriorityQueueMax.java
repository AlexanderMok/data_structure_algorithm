package alogorithm.structure.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityQueueMax<Key extends Comparable<Key>> implements Iterable<Key> {
    //Array to store elements
	private Key[] pq;
    private int n;
    
	public PriorityQueueMax(int size) {
		pq = (Key[]) new Comparable[size + 1];
		n = 0;
	}
	
	
	public void insert(Key v) {
		//insert v to the end of the heap
		//test if need to resize
		if (n >= pq.length - 1) {
			resize(2 * pq.length);
		}
		n++;
		pq[n] = v;
		swim(n);
	}
	
	public Key delMax() {
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		Key max = pq[1];
		// exchange with last element
		exchange(1, n--);
		// avoid loitering
		pq[n+1] = null;
		//reheapify
		sink(1);
		//test if need to reduce size
		if (n > 0 && (n == (pq.length - 1) / 4)) {
			resize(pq.length / 2);
		}
		return max;
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public Key max() {
		return pq[1];
	}
	
	public int size() {
		return n;
	}
	
	@Override
	public Iterator<Key> iterator() {
		return null;
	}
	
	/**
	 * Bottom-up reheapify
	 * 
	 * when node at k becomes larger than its parent
	 * move the larger one up the tree
	 * @param k
	 */
	private void swim(int k) {
		while(k > 1 && less(k/2, k)) {
			exchange(k/2, k);
			k = k/2;
		}
	}
	
	/**
	 * Top-down reheapify
	 * 
	 * when node at k becomes smaller than its children
	 * move the smaller one down the tree
	 * @param k
	 */
	private void sink(int k) {
		while(2*k <= n) {
			int j = 2*k;
			// find the smaller child among the two
			if(j < n && less(j, j + 1)) j++;
			// when k is larger then stop
			if(!less(k, j)) break;
			exchange(k, j);
			k = j;
		}
	}
	
	
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exchange(int i, int j) {
		Key key = pq[i];
		pq[i] = pq[j];
		pq[j] = key;
	}
	
	private void resize(int capacity) {
		Key[] temp = (Key[])new Object[capacity];
		//copy to temp
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

}
