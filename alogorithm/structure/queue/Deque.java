package algorithm.structure.queue;

import java.util.Iterator;

public class Deque<T> implements Iterable<T> {
	public Deque() {
	}                           // construct an empty deque

	public boolean isEmpty() {
		return false;
	}                 // is the deque empty?

	public int size() {
		return 0;
	}                        // return the number of items on the deque

	public void addFirst(T item) {
	}          // add the item to the front

	public void addLast(T item) {
	}           // add the item to the end

	public T removeFirst() {
		return null;
	}                // remove and return the item from the front

	public T removeLast() {
		return null;
	}                 // remove and return the item from the end

	public Iterator<T> iterator() {
		return null;
	}         // return an iterator over items in order from front to end

	public static void main(String[] args){
		
	}   // unit testing (required)
}
