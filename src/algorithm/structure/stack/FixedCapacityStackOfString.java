package algorithm.structure.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A fixed-capacity stack of strings using an array
 * 
 * @author Alex
 *
 */
public class FixedCapacityStackOfString implements Iterable<String> {
	private int N; // number of items in stack
	private String[] array; // holds the items

	// create an empty stack with given capacity
	public FixedCapacityStackOfString(int capacity) {
		N = 0;
		array = new String[capacity];
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

	// [0,1,2,3] -> [0,1,2,3,4,5] forward
	public void push(String item) {
		if (isFull()) {
			throw new UnsupportedOperationException("Stack is full.");
		}
		array[N++] = item;
	}

	// [0,1,2,3,4,5] -> [0,1,2,3] backward
	public String pop() {
		if (isEmpty()) {
			throw new UnsupportedOperationException("Stack is empty.");
		}
		// array index starts with 0, so we must decrement N first
		String item = array[--N];
		array[N] = null; // avoid loitering. No need to keep the reference as it
							// is popped.
		return item;
	}

	public String peek() {
		// array index starts with 0, so we've got to subtract one
		return array[N - 1];
	}

	@Override
	public Iterator<String> iterator() {
		// to iterates the stack in FILO order, reverse the array
		return new ReverseArrayIterator();
	}

	/**
	 * Iterates the array in reverse order
	 * 
	 * @author Alex
	 *
	 */
	private class ReverseArrayIterator implements Iterator<String> {
		private int i = N - 1; // current item index

		@Override
		public boolean hasNext() {
			return i >= 0;
		}

		@Override
		public String next() {
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
        for (String item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
	
	public static void main(String[] args) {
		algorithm.structure.stack.FixedCapacityStackOfString stack = new FixedCapacityStackOfString(10);
		System.out.printf("Stack size %s, Stack element %s \n", stack.size(), stack);
		System.out.println(stack.isEmpty());
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		System.out.printf("Stack size %s, Stack element %s \n", stack.size(), stack);
		System.out.println(stack.isEmpty());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.printf("Stack size %s, Stack element %s \n", stack.size(), stack);
	}

}
