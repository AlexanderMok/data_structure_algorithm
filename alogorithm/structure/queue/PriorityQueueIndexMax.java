package alogorithm.structure.queue;

import java.util.NoSuchElementException;

/**
 * 
 * an indexed priority queue of generic keys
 * 
 * @author Alex
 *
 * @param <E>
 */
public class PriorityQueueIndexMax<E extends Comparable<E>> {
    //binary heap using 1-based indexing;存儲元素的index
	private int[] pq;
	//number of elements
	private int n;
	//inverse of pq; qp[pq[i]] = pq[qp[i]] = i;存儲元素的index，但是逆序的
	private int[] qp;
	//keys[i] = priority of i
	private E[] keys;
	
	public PriorityQueueIndexMax(int maxN) {
		if(maxN < 0) {
			throw new IllegalArgumentException();
		}
		n = 0;
		keys = (E[])new Comparable[maxN + 1];
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		//initialize inverse index
		for (int i = 0; i <= maxN; i++) {
			qp[i] = -1;
		}
	}
	
	/**
	 * insert item and associate it with k
	 * @param k
	 * @param item
	 */
	public void insert(int i, E item) {
		if(contains(i)) {
			throw new IllegalArgumentException();
		}
		n++;
		qp[i] = n;
		pq[n] = i;
		keys[i] = item;
		swim(n);
	}
	
	/**
	 * change the item associated with i to another item
	 * @param k
	 * @param item
	 */
	public void changeKey(int i, E item) {
		if(!contains(i)) {
			throw new NoSuchElementException("index is not in the priority queue");
		}
		keys[i] = item;
		swim(qp[i]);
		sink(qp[i]);
	}
	
	/**
	 * Returns the key associated with index {@code i}
	 * @param i
	 * @return the key associated with index {@code i}
	 */
	public E keyOf(int i) {
		if(!contains(i)) {
			throw new NoSuchElementException("index is not in the priority queue");
		} else {
			return keys[i];
		}
	}
	
	/**
	 * is k associated with some item
	 * @param k
	 * @return
	 */
	public boolean contains(int i) {
		return qp[i] != -1;
	}
	
	/**
	 * remove k and its associated item
	 * @param k
	 */
	public E delete(int i) {
		if(isEmpty()) {
			throw new NoSuchElementException("Priority queue underflow");
		}
		int index = qp[i];
		E key = keys[index];
		exchange(index, n--);
		swim(index);
		sink(index);
		keys[index] = null;
		qp[i] = -1;
		return key;
	}
	
	/**
	 * return a max item
	 * @return
	 */
	public E max() {
		if(isEmpty()) {
			throw new NoSuchElementException("Priority queue underflow");
		}
		return keys[pq[1]];
	}
	
	/**
	 * return a max item's index
	 * @return
	 */
	public int maxIndex() {
		if(isEmpty()) {
			throw new NoSuchElementException("Priority queue underflow");
		}
		return pq[1];
	}
	
	/**
	 * remove a max item and return its index
	 * @return index of the max item
	 */
	public int delMax() {
		if(isEmpty()) {
			throw new NoSuchElementException("Priority queue underflow");
		}
		
		int maxIndex = pq[1];
		exchange(1, n--);
		sink(1);
		
		qp[maxIndex] = -1;//delete index
		keys[maxIndex] = null;//delete item
		pq[n+1] = -1;
		return maxIndex;
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	private void swim(int k) {
		while(k > 1 && less(k/2, k)) {
			exchange(k/2, k);
			k = k/2;
		}
	}
	
	private void sink(int k) {
		while(2*k <= n) {
			int j = 2*k;
			if(j < n && less(j, j+1)) {
				j++;
			}
			if(!less(k, j)) {
				break;
			}
			exchange(k, j);
			k = j;
		}
	}
	
	private boolean less(int i, int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
	}
	
	private void exchange(int i, int j) {
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
}
