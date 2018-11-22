package algorithm.structure.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This implementation uses a resizing array, which double the underlying array
 * when it is full and halves the underlying array when it is one-quarter full.
 * The <em>enqueue</em> and <em>dequeue</em> operations take constant amortized
 * time. The <em>size</em>, <em>peek</em>, and <em>is-empty</em> operations
 * takes constant time in the worst case.
 * 
 * @author Alex
 *
 * @param <T>
 */
public class ResizingArrayQueue<T> implements Iterable<T> {
	private int n; // number of items in queue
	private T[] array;
	private int first; // index of first element of queue
	private int last; // index of next available slot
	private static final int INIT_CAPCITY = 5;

	public ResizingArrayQueue() {
		n = 0;
		array = (T[]) new Object[INIT_CAPCITY];
		first = 0;
		last = 0;
	}

	public ResizingArrayQueue(int capacity) {
		n = 0;
		array = (T[]) new Object[capacity];
		first = 0;
		last = 0;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public void enqueue(T item) {
		// double size of array if necessary and recopy to front array
		if (n == array.length) {
			resize(2 * array.length);
		}
		array[last++] = item; // add item and increment index of last
		if (last == array.length) {
			// when this condition is satisfied,
			// next call of enqueue should call resize()
			last = 0;
		}
		n++; // increment size
	}

	public T dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue underflow/empty");
		}
		T item = array[first];
		array[first] = null; // to avoid loitering
		n--; // decrement size
		first++; // increment index of first
		if (first == array.length) {
			first = 0;
		}
		// shrink size of array if necessary
		if (n > 0 && n == array.length / 4) {
			resize(array.length / 2);
		}
		return item;
	}

	public T peek() {
		return array[first];
	}

	private void resize(int capacity) {
		assert capacity >= n;
		T[] newArray = (T[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			newArray[i] = array[(first + i) % array.length];
		}
		array = newArray;
		first = 0;
		last = n;
		System.out.println("new length of array " + array.length);
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (T item : this) {
			s.append(item + "").append(" ");
		}
		return s.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator<T> {
        private int i = 0;
		@Override
		public boolean hasNext() {
			return i < n;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T item = array[(i + first) % array.length];
			i++;
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) {
		ResizingArrayQueue<String> queue = new algorithm.structure.queue.ResizingArrayQueue<>();
		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");
		queue.enqueue("D");
		queue.enqueue("E");
		queue.enqueue("F");
		queue.enqueue("G");
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.peek());
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.peek());
	}
}
