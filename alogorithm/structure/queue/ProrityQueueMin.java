package alogorithm.structure.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MinHeap Data Structure
 * @author Alex
 *
 * @param <E>
 */
public class ProrityQueueMin<E extends Comparable<E>> implements Iterable<E> {

	private E[] pq;
	private int n;

	public ProrityQueueMin(int capcaticy) {
		pq = (E[]) new Comparable[capcaticy + 1];
		n = 0;
	}

	public ProrityQueueMin() {
		this(1);
	}

	public void insert(E e) {
		if (n == pq.length - 1)
			resize(pq.length * 2);
		pq[++n] = e;
		swim(pq, n, n);
	}

	public E delMin() {
		if (isEmpty())
			throw new NoSuchElementException();
		E min = pq[1];
		exchange(pq, 1, n--);
		sink(pq, 1, n);
		pq[n + 1] = null;
		if (n > 0 && (n == (pq.length) / 4))
			resize(pq.length / 2);
		return min;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	private void resize(int capacity) {
		E[] temp = (E[]) new Comparable[capacity];
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

	private void swim(Comparable[] pq, int k, int n) {
		while (k > 1 && greater(pq, k / 2, k)) {
			exchange(pq, k / 2, k);
			k = k / 2;
		}
	}

	private void sink(Comparable[] pq, int k, int n) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && greater(pq, j, j + 1))
				j++;
			if (!greater(pq, k, j))
				break;
			exchange(pq, k, j);
			k = j;
		}
	}

	private boolean greater(Comparable[] pq, int i, int j) {
		return pq[i].compareTo(pq[j]) > 0;
	}

	private void exchange(Comparable[] pq, int i, int j) {
		Comparable key = pq[i];
		pq[i] = pq[j];
		pq[j] = key;
	}

	@Override
	public Iterator<E> iterator() {
		return new HeapIterator();
	}

	private class HeapIterator implements Iterator<E> {
        
		private ProrityQueueMin<E> copy;
		
		public HeapIterator() {
			copy = new ProrityQueueMin<>(size());
			for (int i = 1; i <= n; i++) {
				copy.insert(pq[i]);
			}
		}
		
		@Override
		public boolean hasNext() {
			return !copy.isEmpty();
		}

		@Override
		public E next() {
			if(!hasNext()) throw new NoSuchElementException();
			return copy.delMin();
		}
	}
	
	public static void main(String[] args) {
		ProrityQueueMin<Integer> pq = new ProrityQueueMin<>();
		pq.insert(6);
		pq.insert(2);
		pq.insert(9);
		pq.insert(5);
		pq.insert(1);
		pq.forEach(System.out::println);
	}
}
