package algorithm.structure.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Pushdown stack. 
 * 
 *  A pushdown stack is a collection that is based on the last-in-first-out (LIFO) policy. 
 *  When you click a hyperlink, your browser displays the new page (and pushes onto a stack). 
 *  You can keep clicking on hyperlinks to visit new pages, but you can always revisit the previous page 
 *  by clicking the back button (popping it from the stack)
 *  先进先出，模拟现实浏览网页，DFS
 *  This implementation uses a singly-linked list with a static nested class for
 *  linked-list nodes.
 * @author Alex
 *
 * @param <T>
 */
public class Stack<T> implements Iterable<T> {
    private int size;       // size of the stack
	private Node<T> first;  // top of stack
    
	// helper singly-linked list class
	private static class Node<E> {
		private E item;
		private Node<E> next;
	}
	
	/**
	 * Initialized an empty stack
	 */
	public Stack() {
		size = 0;
		first = null;
	}
	
    public int Size() {
    	return size;
    }
    
    public boolean isEmpty() {
    	return first == null;
    }
    
    public T peek() {
    	if (isEmpty()) {
			throw new NoSuchElementException();
		}
    	return first.item;
    }
    
    public T pop() {
    	if (isEmpty()) {
			throw new NoSuchElementException("Stack underflow");
		}
    	//Node<T> oldfirst = first;
    	T item = first.item;   //save item and return
    	//first.next = null;
    	//first = oldfirst.next;
    	first = first.next;    // delete first node
    	size--;
    	return item;
    }
    
    /**
     * node <- node <- node
     * @param item
     */
    public void push(T item) {
    	Node<T> oldfirst = first;
    	first = new Node<T>();
    	first.item = item;
    	first.next = oldfirst;
    	size++;
    }

	@Override
	public Iterator<T> iterator() {
		return new ListIterator<T>(first);
	}
	
	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<E> implements Iterator<E> {
        private Node<E> current;
        
        public ListIterator(Node<E> first) {
        	this.current = first;
		}
        
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if (!hasNext()) {
			    throw new NoSuchElementException();
			}
			E item = current.item;
			current = current.next;
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	@Override
	public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
	
	public static void main(String[] args) {
		algorithm.structure.stack.Stack<Integer> stack = new algorithm.structure.stack.Stack<>();
		System.out.printf("Stack size %s, Stack element %s \n", stack.Size(), stack);
		System.out.println(stack.isEmpty());
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.printf("Stack size %s, Stack element %s \n", stack.Size(), stack);
		System.out.println(stack.isEmpty());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.printf("Stack size %s, Stack element %s \n", stack.Size(), stack);
	}
}
