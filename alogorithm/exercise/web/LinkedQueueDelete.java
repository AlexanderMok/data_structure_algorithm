package algorithm.exercise.web;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueueDelete<T> implements Iterable<T>{
    private int size;
    private Node<T> first;
    private Node<T> last;
    
    private static class Node<E> {
    	private E item;
    	private Node<E> next;
    }
    
    public LinkedQueueDelete() {
    	size = 0;
    	first = null;
    	last = null;
    }
    
    public int size() {
    	return size;
    }
    
    public boolean isEmpty() {
    	return first == null;
    }
    
    public void insertLast(T item) {
    	linkLast(item);
    }
    
    private void linkLast(T item) {
    	Node<T> oldlast = last;
    	last = new Node<T>();
    	last.item = item;
    	last.next = null;
    	if (isEmpty()) {
    		first = last;
    	} else {
    		oldlast.next = last;
    	}
    	size++;
    }
    
    public T removeFirst() {
    	if (isEmpty()) {
			throw new NoSuchElementException();
		}
    	T item = first.item;
    	first = first.next;
    	size--;
    	return item;
    }
    
    
    public T remove(int index) {
    	checkElementIndex(index);
    	return unlink(node(index), pre(index));
    }
    /**
     * TODO bugs when removing first or last element
     * @param remove
     * @param pre
     * @return
     */
    private T unlink(Node<T> remove, Node<T> pre) {
    	//first index last
    	T item = remove.item;
    	pre.next = remove.next;
    	remove.next = null;
    	size--;
    	return item;
    }
    
    private Node<T> pre(int index) {
    	Node<T> x = first;
    	for(int i = 0; i < index - 1; i++) {
    		x = x.next;
    	}
    	return x;
    }
    
    private Node<T> node(int index) {
    	Node<T> x = first;
    	for(int i = 0; i < index; i++) {
    		x = x.next;
    	}
    	return x;
    }

	private void checkElementIndex(int index) {
        if(!(index >= 0 && index < size)) {
        	throw new IndexOutOfBoundsException();
        }		
	}
	
	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("queue underflow");
		}
		return first.item;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (T item : this)
			s.append(item + " ");
		return s.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<T> {
        private Node<T> current = first;
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T item = current.item;
			current = current.next;
			return item;
		}
	}
	
	public static void main(String[] args) {
		LinkedQueueDelete<String> queue = new LinkedQueueDelete<>();
		queue.insertLast("A");
		queue.insertLast("B");
		queue.insertLast("C");
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.peek());
		queue.removeFirst();
		System.out.println(queue);
		queue.insertLast("D");
		queue.insertLast("E");
		queue.insertLast("F");
		queue.remove(4);
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.peek());
	}
}
