package algorithm.structure.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> implements Iterable<T> {
	private int size;
	private Node first;

	public LinkedStack() {
		size = 0;
		first = null;
	}

	private class Node {
		private T item;
		private Node next;
	}

	public int size() {
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

	public void push(T item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
	}

	public T pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("stack underflow");
		}
		T item = first.item;
		first = first.next;
		size--;
		return item;
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
		algorithm.structure.stack.LinkedStack<Integer> stack = new algorithm.structure.stack.LinkedStack<>();
		System.out.printf("Stack size %s, Stack element %s \n", stack.size(), stack);
		System.out.println(stack.isEmpty());
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.printf("Stack size %s, Stack element %s \n", stack.size(), stack);
		System.out.println(stack.isEmpty());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.printf("Stack size %s, Stack element %s \n", stack.size(), stack);
	}
}
