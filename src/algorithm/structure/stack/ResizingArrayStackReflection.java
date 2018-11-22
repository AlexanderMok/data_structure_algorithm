package algorithm.structure.stack;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Use reflection to create array type
 * 
 * @author Alex
 *
 * @param <T>
 */
public class ResizingArrayStackReflection<T> implements Iterable<T> {
	private Class<T[]> itemArrayClass; // array type class
	private T[] array;
	private int size;
	private static final int INIT_CAPCITY = 10;

	public ResizingArrayStackReflection(Class<T[]> itemArrayClass) {
		this.itemArrayClass = itemArrayClass;
		size = 0;
		array = this.itemArrayClass.cast(Array.newInstance(this.itemArrayClass.getComponentType(), INIT_CAPCITY));
	}

	public ResizingArrayStackReflection(Class<T[]> itemArrayClass, int capacity) {
		this.itemArrayClass = itemArrayClass;
		size = 0;
		array = itemArrayClass.cast(Array.newInstance(itemArrayClass, capacity));
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void resize(int capacity) {
		assert capacity >= size;
		T[] newarray = itemArrayClass.cast(Array.newInstance(itemArrayClass.getComponentType(), capacity));
		for (int i = 0; i < size; i++)
			newarray[i] = array[i];
		array = newarray;
		System.out.println("new array length " + array.length);
	}

	public void push(T item) {
		if (item == null) {
			throw new IllegalArgumentException("Cannot add null item.");
		}
		if (size == array.length) {
			resize(array.length * 2);
		}
		array[size++] = item;
	}

	public T pop() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T item = array[--size];
		array[size] = null;
		if (size > 0 && size < array.length / 4) {
			resize(array.length / 2);
		}
		return item;
	}

	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return array[size - 1];
	}

	@Override
	public Iterator<T> iterator() {
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<T> {
		private int i = size - 1;

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
		algorithm.structure.stack.ResizingArrayStackReflection<Integer> stack = new ResizingArrayStackReflection<>(
				Integer[].class);
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
