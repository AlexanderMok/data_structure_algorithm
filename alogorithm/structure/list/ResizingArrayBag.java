package algorithm.structure.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayBag<T> implements Iterable<T> {
	private int size;
	private T[] array;
	private int INIT_CAPACITY = 2;

	public ResizingArrayBag() {
		size = 0;
		array = (T[]) new Object[INIT_CAPACITY];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	private void resize(int capacity) {
		assert capacity >= size;
		// T[] newArray = (T[]) new Object[capacity];
		array = Arrays.copyOf(array, capacity);
		System.out.println("new array length " + array.length);
	}

	public void add(T item) {
		if (size == array.length) {
			resize(size * 2);
		}
		array[size++] = item;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator<T> {
		private int i;

		@Override
		public boolean hasNext() {
			return i < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[i++];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) {
		ResizingArrayBag<String> bag = new ResizingArrayBag<String>();
        bag.add("Hello");
        bag.add("World");
        bag.add("how");
        bag.add("are");
        bag.add("you");

        for (String s : bag)
            System.out.println(s);
	}

}
