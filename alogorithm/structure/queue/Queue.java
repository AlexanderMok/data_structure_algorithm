package algorithm.structure.queue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FIFO queues. A FIFO queue is a collection that is based on the
 * first-in-first-out (FIFO) policy.
 * 
 * It supports the usual <em>enqueue</em> and <em>dequeue</em> operations, along
 * with methods for peeking at the first item, testing if the queue is empty,
 * and iterating through the items in FIFO order.
 * <p>
 * This implementation uses a singly-linked list with a static nested class for
 * linked-list nodes.
 * 
 * 模拟现实中的排队情况。基本操作有入队、出队、获取对头元素、是否空队列、顺序迭代 单链表存储队列元素
 * 
 * @author Alex
 *
 * @param <T>
 */
public class Queue<T> implements Iterable<T> {
	private int size; // number of elements on queue
	private Node<T> first; // head of queue
	private Node<T> last; // tail of queue
	private int modCount = 0;

	// helper singly-linked list class
	private static class Node<E> {
		private E item;
		private Node<E> next;
	}

	/**
	 * Initialize an empty queue
	 */
	public Queue() {
		size = 0;
		first = null;
		last = null;
	}

	public int size() {
		return size;
	}

	/**
	 * Returns true if this queue is empty
	 * 
	 * @return {@code true} if this queue is empty; {@code false} otherwise
	 */
	public boolean isEmpty() {
		return first == null;
	}
    
	/**
	 * insert new node at the end of a link list
	 * node <- node FIFO 
	 * node(new) <- node <- node 增長方向與stack相反
	 * @param item
	 */
	public void enqueue(T item) {
		Node<T> oldLast = last;
		last = new Node<T>();
		last.item = item;
		last.next = null; // 先将新节点包装好
		if (isEmpty()) {
			first = last; // 若队列为空，头即是尾
		} else {
			oldLast.next = last; // 新節點增加是指針指著的方向
		}
		size++;
	}
	
	public T dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T item = first.item; // 取出头节点元素
		first = first.next; // 新头节点为先现头节点的下一个节点
		size--;
		if (isEmpty())
			last = null;
		return item;
	}

	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return first.item;
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

	@Override
	public Iterator<T> iterator() {
		return new ListIterator<T>(first);
	}

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
		
		final void checkModification() {
			if(expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}
		}
	}

	public static void main(String[] args) {
		Queue<String> queue = new Queue<>();
		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.peek());
		queue.dequeue();
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.peek());
	}
}
