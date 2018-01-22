package algorithm.structure.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A bounded stack is a stack that has a capacity of at most N. (Applications:
 * undo or history with finite buffer.)
 * 
 * @author Alex
 *
 */
public class BoundedStack<T> implements Iterable<T> {
	private int size;
	private int capacity;
	private Node<T> first;

	private static class Node<E> {
		private E item;
		private Node<E> next;
	}

	public BoundedStack(int capacity) {
		this.size = 0;
		this.capacity = capacity;
		this.first = null;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == capacity;
	}

	private T getAndRemoveLast(BoundedStack<T> stack) {
		T result = stack.pop();
		if (isEmpty()) {
			return result;
		} else {
			T last = getAndRemoveLast(stack);
			stack.push(result);
			return last;
		}
	}

	public void push(T item) {
		if (isFull()) {
			getAndRemoveLast(this);
		}
		Node<T> oldfirst = first;
		first = new Node<T>();
		first.item = item;
		first.next = oldfirst;
		size++;
	}

	public T pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack underflow");
		}
		T item = first.item;
		first = first.next;
		size--;
		return item;
	}

	public T peek() {
		return first.item;
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator<T>(first);
	}

	private class ListIterator<E> implements Iterator<E> {
		private Node<E> current;

		public ListIterator(Node<E> first) {
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
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
		BoundedStack<Integer> stack = new BoundedStack<>(3);
		stack.push(1);
		stack.push(2);
		System.out.println(stack);
		stack.push(3);
		System.out.println(stack);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		stack.push(10);
		stack.push(11);
		stack.push(12);
		System.out.println(stack);
	}
}
