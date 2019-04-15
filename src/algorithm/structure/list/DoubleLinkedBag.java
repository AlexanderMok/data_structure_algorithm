package algorithm.structure.list;

import java.util.Iterator;
import java.util.Objects;

public class DoubleLinkedBag<T> implements Iterable<T> {
	private int size;
	private Node first;
	private Node last;

	// helper double-linked list class
	private class Node {
		private T item;
		private Node next;
		private Node prev;

		Node(Node prev, T item, Node next) {
			this.prev = prev;
			this.item = item;
			this.next = next;
		}
	}

	public DoubleLinkedBag() {
		size = 0;
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	public boolean contains(T item) {
		return indexOf(item) != -1;
	}

	public int indexOf(T item) {
		Objects.nonNull(item);
		int index = 0;
		for (Node head = first; head != null; head = head.next) {
			if (head.item.equals(item)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public void addFirst(T item) {
		final Node oldFirst = first;
		final Node newFirst = new Node(null, item, oldFirst);
		first = newFirst;
		if (oldFirst == null) {
			last = newFirst;
		} else {
			first.prev = newFirst;
		}
		size++;
	}

	/**
	 * Appends item to the end of this list
	 * 
	 * @param item
	 */
	public void addLast(T item) {
		final Node oldLast = last;
		final Node newNode = new Node(oldLast, item, null);
		last = newNode;
		if (oldLast == null) {
			first = newNode;
		} else {
			oldLast.next = newNode;
		}
		size++;
	}

	public void removeFirst() {

	}

	public void RemoveLast() {

	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
