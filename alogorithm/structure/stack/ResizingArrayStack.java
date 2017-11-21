package algorithm.structure.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<T> implements Iterable<T> {
	private int n; // number of elements in stack
	private T[] array; // holder of elements
	private static final int INIT_CAPCITY = 10;

	public ResizingArrayStack() {
		n = 0;
		array = (T[]) new Object[INIT_CAPCITY];
	}

	public ResizingArrayStack(int capacity) {
		n = 0;
		array = (T[]) new Object[capacity];
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public void push(T item) {
		// double size of array if necessary
		if (n == array.length) {
			resize(2 * array.length);
		}
		array[n++] = item;
	}

	public T pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack underflow/empty");
		}
		T item = array[--n];
		array[n] = null;
		// shrink size of array if necessary
		if (n > 0 && n == array.length / 4) {
			resize(array.length / 2);
		}
		return item;
	}

	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack underflow/empty");
		}
		return array[n - 1];
	}

	private void resize(int capacity) {
		assert capacity >= n;
		T[] newArray = (T[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
		System.out.println("new array length " + array.length);
		// array = Arrays.copyOf(array, capacity);
	}

	@Override
	public Iterator<T> iterator() {
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<T> {
		private int i = n - 1;

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
		for (T item : this) {
			s.append(item + "").append(" ");
		}
		return s.toString();
	}

	public static void main(String[] args) {
		algorithm.structure.stack.ResizingArrayStack<Integer> stack = new ResizingArrayStack<Integer>();
		System.out.printf("Stack size %s, Stack element %s \n", stack.size(), stack);
		System.out.println(stack.isEmpty());
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		stack.push(10);
		stack.push(11);
		System.out.printf("Stack size %s, Stack element %s \n", stack.size(), stack);
		System.out.println(stack.isEmpty());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		System.out.printf("Stack size %s, Stack element %s \n", stack.size(), stack);
	}

}
