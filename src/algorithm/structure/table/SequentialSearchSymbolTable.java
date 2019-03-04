package algorithm.structure.table;

import java.util.NoSuchElementException;

/**
 * an unordered symbol table
 * <p>
 * This implementation uses a singly-linked list and sequential search.
 * 
 * put,delete O(n) get,contains O(n) worst size,isEmpty O(1)
 * 
 * @author Alex
 *
 * @param <K>
 * @param <V>
 */
public class SequentialSearchSymbolTable<K, V> {
	// number of key-value pairs
	private int n;
	// the linked-list of key-value pairs
	private Node first;

	public SequentialSearchSymbolTable() {

	}

	public int size() {
		return n;
	}

	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		if (isEmpty()) {
			return null;
		}
		Node traverse = first;
		while (traverse != null) {
			System.out.print(traverse.value + " ");
			if (key.equals(traverse.key)) {
				return traverse.value;
			}
			traverse = traverse.next;
		}
		return null;
	}

	public void put(K key, V value) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		if (value == null) {
			delete(key);
			return;
		}
		// key in table
		Node traverse = first;
		while (traverse != null) {
			if (key.equals(traverse.key)) {
				traverse.value = value;
				return;
			}
			traverse = traverse.next;
		}
		// key not in table
		Node newFirst = new Node(key, value, first);
		first = newFirst;
		n++;
	}

	public V delete(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		if (isEmpty()) {
			throw new NoSuchElementException("Symbol table is empty");
		}

		if (first != null && key.equals(first.key)) {
			first = first.next;
			return first.value;
		}

		Node p = first;
		for (Node x = first.next; x != null; x = x.next) {
			if (key.equals(x.key)) {
				n--;
				p.next = x.next;
				return x.value;
			}
		}
		return null;
	}

	public V delte(K key) {
		first = delete(first, key);
		return first.value;
	}

	private Node delete(Node x, K key) {
		if (x == null) {
			return null;
		}
		if (key.equals(x.key)) {
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	public boolean contains(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		return get(key) != null;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	private class Node {
		private K key;
		private V value;
		private Node next;

		public Node(K key, V value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public Node() {

		}
	}

	public static void main(String[] args) {
		SequentialSearchSymbolTable<String, Integer> st = new SequentialSearchSymbolTable<String, Integer>();
		st.put("a", 1);
		st.put("b", 2);
		st.put("c", 3);
		System.out.println(st.contains("a"));
		System.out.println(st.get("b"));
		System.out.println(st.delte("b"));
		System.out.println(st.get("b"));
		System.out.println(st.get("c"));
	}
}
