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
    
	/**
	 * Traverse the list to find index of {@code item}
	 * @param item
	 * @return return index of {@code item}. return -1 if not found.
	 */
	public int indexOf(T item) {
		Objects.requireNonNull(item);
		int index = 0;
		for (Node head = first; head != null; head = head.next) {
			if (head.item.equals(item)) {
				return index;
			}
			index++;
		}
		return -1;
	}
    
	/**
	 * Links {@code item} as first element.
	 * @param item
	 */
	public void addFirst(T item) {
		final Node oldFirst = first;
		final Node newFirst = new Node(null, item, oldFirst);
		first = newFirst;
		if (oldFirst == null) {
			last = newFirst;
		} else {
			oldFirst.prev = newFirst;
		}
		size++;
	}

	/**
	 * Links {@code item} as last element.
	 * 
	 * @param item
	 */
	public void addLast(T item) {
		final Node oldLast = last;
		final Node newLast = new Node(oldLast, item, null);
		if (oldLast == null) {
			first = newLast;
		} else {
			oldLast.next = newLast;
		}
		size++;
	}
    
	/**
	 * Unlinks non-null first node
	 */
	public T removeFirst() {
		final Node firstNode = first;
        Objects.requireNonNull(firstNode);
        final T item = firstNode.item;
        final Node next = firstNode.next;
        firstNode.item = null;
        firstNode.next = null;
        first = next;
        if (next == null) {
        	last = null;
        } else {
        	next.prev = null;
        }
        size--;
        return item;
        
	}
    
	/**
	 * Unlinks non-null last node
	 */
	public T removeLast() {
        final Node lastNode = last;
        Objects.requireNonNull(lastNode);
        final T item = lastNode.item;
        final Node prev = lastNode.prev;
        lastNode.item = null;
        lastNode.prev = null;
        last = prev;
        if (prev == null) {
        	first = null;
        } else {
			prev.next = null;
		}
        size--;
        return item;
	}
	
	
	/**
	 * Unlink non-null node x
	 * @param x
	 * @return
	 */
	private T unlink(Node x) {
		Objects.requireNonNull(x);
		final T item = x.item;
		final Node prev = x.prev;
		final Node next = x.next;
		
		if (prev == null) {
			// means unlink a first node and update first node.
			first = next;
		} else {
			//update prev next
			prev.next = next;
			//delete node x's prev pointer
			x.prev = null;
		}
		
		if (next == null) {
			//means unlink a last node and update last node
			last = prev;
		} else {
			next.prev = prev;
			x.next = null;
		}
		x.item = null;
		size--;
		return item;
	}
	
	private boolean checkElementIndex(int index) {
		return index >= 0 && index < size; 
	}
	
	/**
	 * Return non-null Node at {@code index}
	 * @param index
	 * @return
	 */
	private Node node(int index) {
		if (!checkElementIndex(index)) {
			throw new IndexOutOfBoundsException();
		}
		// apply binary search idea to search from first
		if (index < (size >> 1)) {
			Node x = first;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;
		} else {
			// search from last
			Node x = last;
			for (int i = size - 1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}
	}
	
	/**
	 * Remove non-null node at {@code index} and return the removed item
	 * @param index
	 * @return
	 */
	public T remove(int index) {
		Node x = node(index);
		return unlink(x);
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
