package algorithm.structure.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * In a heap, the parent of the node in position k is in position k/2; and,
 * conversely, the two children of the node in position k are in positions 2k
 * and 2k + 1. We can travel up and down by doing simple arithmetic on array
 * indices: to move up the tree from a[k] we set k to k/2; to move down the tree
 * we set k to 2*k or 2*k+1.
 * <p>
 * MaxHeap data structure
 * 
 * @author Alex
 *
 * @param <Key>
 */
public class PriorityQueueMax<Key extends Comparable<Key>> implements Iterable<Key> {
	// Array to store elements
	private Key[] pq;
	private int n;

	public PriorityQueueMax(int size) {
		pq = (Key[]) new Comparable[size + 1];
		n = 0;
	}

	public PriorityQueueMax() {
		this(1);
	}

	public void insert(Key v) {
		// insert v to the end of the heap
		// test if need to resize
		if (n >= pq.length - 1) {
			resize(2 * pq.length);
		}
		n++;
		pq[n] = v;
		swim(n);
	}

	public Key delMax() {
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow");
		Key max = pq[1];
		// exchange with last element
		exchange(1, n--);
		// avoid loitering
		pq[n + 1] = null;
		// reheapify
		sink(1);
		// test if need to reduce size
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

	/**
	 * Bottom-up reheapify
	 * 
	 * when node at k becomes larger than its parent move the larger one up the
	 * tree
	 * 
	 * @param k
	 */
	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exchange(k / 2, k);
			k = k / 2;
		}
	}

	/**
	 * Top-down reheapify
	 * 
	 * when node at k becomes smaller than its children move the smaller one
	 * down the tree
	 * 
	 * @param k
	 */
	private void sink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			// find the smaller child among the two
			if (j < n && less(j, j + 1))
				j++;
			// when k is larger then stop
			if (!less(k, j))
				break;
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
		Key[] temp = (Key[]) new Comparable[capacity];
		// copy to temp
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

	@Override
	public Iterator<Key> iterator() {
		return new HeapIterator();
	}

	private class HeapIterator implements Iterator<Key> {
		// create a copy
		private PriorityQueueMax<Key> copy;

		// takes linear time since already in heap order
		public HeapIterator() {
			copy = new PriorityQueueMax<>(size());
			for (int i = 1; i <= n; i++) {
				copy.insert(pq[i]);
			}
		}

		@Override
		public boolean hasNext() {
			return !copy.isEmpty();
		}

		@Override
		public Key next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return copy.delMax();
		}

	}

	public static void main(String[] args) {
		PriorityQueueMax<String> pq = new PriorityQueueMax<>();
		pq.insert("a");
		pq.insert("b");
		pq.insert("c");
		pq.insert("d");
		pq.insert("e");
		pq.forEach(System.out::println);
	}

}
