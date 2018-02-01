package algorithm.exercise.web;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Is only Dequeue support remove ith element?
 * @author Alex
 *
 * @param <T>
 */
public class LinkedQueueDelete<T> implements Iterable<T>{
    private int size;
    private Node<T> first;
    private Node<T> last;
    
    private static class Node<E> {
    	private E item;
    	private Node<E> next;
    	
    	@Override
    	public String toString() {
    		return "Data: " + item + ", Pointer: " + next;
    	}
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
    	return unlink(node(index));
    }
    
    void unlink(int index) {
    	Node<T> pre = first;
    	Node<T> cur = first;
    	for (int i = 0; i < index && cur != null; i++) {
    		cur = cur.next;
    	}
    	pre.next = cur.next;
    	cur.next = null;
    	
    }
    /**
     * delete the ith least recently added
     * @param remove
     * @param pre
     * @return
     */
    public T unlink(Node<T> preOfDel) {
    	Node<T> pre = first;
    	if (preOfDel.equals(pre)) {
    		return removeFirst();
    	} else {
    		preOfDel.next = preOfDel.next.next;
    		Node<T> del = preOfDel.next;
    		size--;
    		return del.item;
    	}
    }
    
    /**
     * Traverse to find 
     * @param index
     * @return
     */
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
		queue.remove(0);
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.peek());
	}
}
