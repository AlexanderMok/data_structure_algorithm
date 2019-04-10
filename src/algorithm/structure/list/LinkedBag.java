package algorithm.structure.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code LinkedBag} class represents a bag (or multiset) of 
 *  generic items. It supports insertion, deletion and iterating over the 
 *  items in arbitrary order.
 *  <p>
 *  This implementation uses a singly-linked list with a non-static nested class Node.
 *  See {@link Bags} for a version that uses a static nested class.
 * @author Alex
 *
 */
public class LinkedBag<T> implements Iterable<T>{
	private int size;    //number of elements in bag
	private Node first;  //head of bag
	
	// helper singly-linked list class
	private class Node {
		private T item;
		private Node next;
	}
	
	/**
	 * Initializes an empty bag
	 */
	public LinkedBag() {
		size = 0;
		first = null;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return size;
	}
	
	public void add(T item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
	}
	
	public void delete(T item) {
		Node head = first;
		for(Node pre = null; head != null; pre=head, head = head.next) {
			if (head.item.equals(item)) {
				if (pre!=null) {
					pre.next = head.next;
				} else {
					head = head.next;
				}
				size--;
			}
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<T> {
		private Node current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T item = current.item;
			current = current.next;
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) {
		LinkedBag<Integer> bags = new LinkedBag<>();
		System.out.println(bags.isEmpty() + ":" + bags.size());
		bags.add(1);
		bags.add(2);
		bags.add(3);
		System.out.println(bags.isEmpty() + ":" + bags.size());
		bags.delete(2);
		System.out.println(bags.isEmpty() + ":" + bags.size());
		bags.forEach(System.out::print);
	}

}
