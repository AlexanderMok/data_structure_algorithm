package algorithm.structure.queue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code LinkedQueue} class represents a first-in-first-out (FIFO) queue of
 * generic items. This implementation uses a singly-linked list with a
 * non-static nested class for linked-list nodes. See {@link Queue} for a
 * version that uses a static nested class. The <em>enqueue</em>,
 * <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 * operations all take constant time in the worst case.
 * <p>
 * Maintain one pointer <em>first</em> to first node in asingly-linked list.
 * <p>
 * Maintain another pointer <em>last</em> to last node.
 * <p>
 * Dequeue from <em>first</em>.
 * <p>
 * Enqueue after <em>last</em>.
 * <p>
 * 
 * @author Alex
 * 
 *
 * @param <T>
 */
public class LinkedQueue<T> implements Iterable<T> {
	private int size; // number of elements in queue
	private Node first; // head of queue
	private Node last; // tail of queue
	private int modCount;

	public LinkedQueue() {
		size = 0;
		first = null;
		last = null;
	}

	private class Node {
		private T item;
		private Node next;
	}

	public void enqueue(T item) {
		linkLast(item);
	}

	private void linkLast(T item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) {
			first = last;
		} else {
			oldlast.next = last;
		}
		size++;
		modCount++;
	}

	public T dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("queue underflow");
		}
		final Node f = first;
		return unlinkFirst(f);
	}
	
	private T unlinkFirst(Node first) {
		T item = first.item;
		first = first.next;
		size--;
		modCount++;
		if (isEmpty()) {
			last = null;
		} // to avoid loitering
		return item;
	}

	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("queue underflow");
		}
		return first.item;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator(modCount);
	}

	private class ListIterator implements Iterator<T> {
		private Node current = first;
		private int expectedModCount;

		public ListIterator(int modCount) {
			this.expectedModCount = modCount;
		}

		@Override
		public boolean hasNext() {
			checkModification();
			return current != null;
		}

		@Override
		public T next() {
			checkModification();
			if (!hasNext()) {
				throw new NoSuchElementException("queue underflow");
			}
			T item = current.item;
			current = current.next;
			return item;
		}
		
		final void checkModification() {
			if(expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (T item : this)
			s.append(item + " ");
		return s.toString();
	}

	public static void main(String[] args) {
		LinkedQueue<String> queue = new LinkedQueue<>();
		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.peek());
		queue.dequeue();
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.peek());
	}

}
