package algorithm.structure.stack;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Pushdown stack. 
 * 
 *  A pushdown stack is a collection that is based on the last-in-first-out (LIFO) policy. 
 *  When you click a hyperlink, your browser displays the new page (and pushes onto a stack). 
 *  You can keep clicking on hyperlinks to visit new pages, but you can always revisit the previous page 
 *  by clicking the back button (popping it from the stack)
 *  先进先出，模拟现实浏览网页，DFS,Dijkstra's two-stack algorithm
 *  This implementation uses a singly-linked list with a static nested class for
 *  linked-list nodes.
 * @author Alex
 *
 * @param <T>
 */
public class Stack<T> implements Iterable<T> {
    private int size;       // size of the stack
	private Node<T> first;  // top of stack
	private int modCount = 0;
    
	// helper singly-linked list class
	private static class Node<E> {
		private E item;
		private Node<E> next;
		
		public Node() {}
		
		//Recursive solution
		public Node(Node<E> first, int recursive) {
			this.item = first.item;
			if (first.next != null) this.next = new Node<E>(first.next, recursive);
		}
		
		public Node(Node<E> first, float nonerecursive) {
			this.item = first.item;
			this.next = first.next;
		}
	}
	
	/**
	 * Initialized an empty stack
	 */
	public Stack() {
		size = 0;
		first = null;
	}
	
	/**
	 * create a copy constructor for a linked list starting at a given Node 
	 * and use this to create the new stack. 
	 * reference a new and independent copy of the Stack stack 
	 * @param stack
	 */
	public Stack(Stack<T> stack, int recursive) {
		first = new Node<>(stack.first, recursive);
		
	}
	
	/**
	 * create a copy constructor for a single Node object.  
	 * @param stack
	 * @param nonerecursive
	 */
	public Stack(Stack<T> stack, float nonerecursive) {
		if (stack != null && stack.first != null) {
			first = stack.first;
			for(Node<T> x = first; x != null; x = x.next) {
				x.next = new Node<T>(x.next, nonerecursive);
			}
		}
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
    	modCount++;
    	return item;
    }
    
    /**
     * insert new node at the beginning
     * node <- node <- node(new)
     * @param item
     */
    public void push(T item) {
    	Node<T> oldfirst = first;
    	first = new Node<T>();
    	first.item = item;
    	first.next = oldfirst;
    	size++;
    	modCount++;
    }

	@Override
	public Iterator<T> iterator() {
		return new ListIterator<T>(first);
	}
	
	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<E> implements Iterator<E> {
        private Node<E> current;
        private int expectedModCount;
        
        public ListIterator(Node<E> first) {
        	this.current = first;
        	this.expectedModCount = modCount;
		}
        
		@Override
		public boolean hasNext() {
			checkModification();
			return current != null;
		}

		@Override
		public E next() {
			checkModification();
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
		
		final void checkModification() {
			if(expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}
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
