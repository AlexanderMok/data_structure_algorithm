package algorithm.structure.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

import algorithm.structure.Collection;

/**
 * Bags. A bag is a collection where removing items is not supported— its
 * purpose is to provide clients with the ability to collect items and then to
 * iterate through the collected items. 
 * 
 * 模拟收集和迭代
 * 
 * @author Alex
 *
 * @param <T>
 *            the generic type of an item in this bag
 */
public class Bags<T> implements Collection<T> {
	private Node<T> first; // pointer for the linked list
	private int n; // number of elements in bag

	/**
	 * Initializes an empty bag
	 */
	public Bags() {
		first = null;
		n = 0;
	}

	// helper linked list class to store items
	private static class Node<T> {
		private T item; // node item
		private Node<T> next; // pointer
	}

	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * 必須返回一個實現Iterator接口的類
	 */
	@Override
	public Iterator<T> iterator() {
		return new ListIterator<T>(first);
	}

	// node <- node <- node FIFO
	public void add(T item) {
		linkLast(item);
		n++;
	}

	/**
	 * link @param item to the tail of inner LinkedList
	 * 
	 * @param item
	 */
	private void linkLast(T item) {
		Node<T> oldFirst = first;
		first = new Node<T>();
		first.item = item;
		first.next = oldFirst;
	}

	private class ListIterator<T> implements Iterator<T> {
		private Node<T> current;

		public ListIterator(Node<T> first) {
			this.current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			T item = current.item;
			current = current.next;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		Bags<Integer> bags = new Bags<>();
		System.out.println(bags.isEmpty() + ":" + bags.size());
		bags.add(1);
		bags.add(2);
		bags.add(3);
		System.out.println(bags.isEmpty() + ":" + bags.size());
		bags.forEach(System.out::print);
	}
}
