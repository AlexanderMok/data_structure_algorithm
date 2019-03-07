package algorithm.structure.table;

import algorithm.structure.queue.Queue;

/**
 * Separate chaining to hashing collision resolution with nested Linked list.
 * Array chain with Linked list
 * @author Alex
 *
 */
public class SeparateChainingLiteHashST<K, V> {
	// number of key-value pairs
	private int n;
	// hash table size
	private int m;
	private Node<K, V>[] st;

	private class Node<K, V> {
		private K key;
		private V value;
		private Node<K, V> next;

		public Node(K key, V value, Node<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	public SeparateChainingLiteHashST(int capacity) {
		this.m = capacity;
		st = (Node<K, V>[]) new Node[capacity];
	}

	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	private void checkKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(K key) {
		return get(key) != null;
	}

	public V get(K key) {
		checkKey(key);
		int i = hash(key);
		for (Node<K, V> x = st[i]; x != null; x = x.next) {
			if (x.key.equals(key)) {
				return x.value;
			}
		}
		return null;
	}

	public void put(K key, V value) {
		checkKey(key);
		int i = hash(key);
		for (Node<K, V> x = st[i]; x != null; x = x.next) {
			if (x.key.equals(key)) {
				x.value = value;
				return;
			}
		}
		n++;
		st[i] = new Node<>(key, value, st[i]);
	}
	
	public V delete(K key) {
		checkKey(key);
		int i = hash(key);
		Node<K, V> x = st[i];
		//TODO deletion on single linked list
		return null;
	}
	

	public Iterable<K> keys() {
		Queue<K> queue = new Queue<K>();
		for (int i = 0; i < m; i++) {
			for (Node<K, V> x = st[i]; x != null; x = x.next) {
				queue.enqueue(x.key);
			}
		}
		return queue;
	}
}
