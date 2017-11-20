package algorithm.structure.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A fixed-capacity generic stack using an array
 * 
 * @author Alex
 *
 */
public class FixedCapacityStack<T> implements Iterable<T> {
	private int N; // number of elements in array
	private T[] array; // array holder of elements

	public FixedCapacityStack(int capacity) {
		N = 0;
		array = (T[]) new Object[capacity]; // no generic array creation
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public boolean isFull() {
		return N == array.length;
	}

	public void push(T item) {
		if (isFull()) {
			throw new UnsupportedOperationException("stack is full");
		}
		array[N++] = item;
	}

	public T pop() {
		if (isEmpty()) {
			throw new UnsupportedOperationException("stack is empty");
		}
		T item = array[--N];
		array[N] = null; // avoid loitering
		return item;
	}

	public T peek() {
		return array[N - 1];
	}

	@Override
	public Iterator<T> iterator() {
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<T> {
		private int i = N - 1;

		@Override
		public boolean hasNext() {
			return i >= 0;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[i--];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(T item : this) {
			s.append(item + "").append(" ");
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		algorithm.structure.stack.FixedCapacityStack<Integer> stack = new FixedCapacityStack<Integer>(4);
		System.out.printf("Stack size %s, Stack element %s \n", stack.size(), stack);
		System.out.println(stack.isEmpty());
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.printf("Stack size %s, Stack element %s \n", stack.size(), stack);
		System.out.println(stack.isEmpty());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		stack.pop();
		System.out.printf("Stack size %s, Stack element %s \n", stack.size(), stack);
	}

}
