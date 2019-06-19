package algorithm.structure.table;

/**
 * The {@code BTree} class represents an ordered symbol table of generic
 * key-value pairs.
 * <P>
 * This implementation uses a B-tree.
 * 
 * @author Alex
 *
 */
public class BTreeBinarySearchSymbolTable<K extends Comparable<K>, V> {
	/* */
	private static final int M = 4;

	/* root of B-tree */
	private Node root;
	/* height of B-tree */
	private int height;
	/* number of k-v pairs in the B-tree */
	private int n;

	private static final class Node {
		private int m;
		private Entry[] children = new Entry[M];

		private Node(int k) {
			m = k;
		}
	}

	/**
	 * Represent data that a {@code Node} associates.
	 * <P>
	 * Can be separated as internal nodes and external nodes.
	 * <P>
	 * Internal nodes only use key and next pointer.
	 * <P>
	 * External nodes only use key and value.
	 * 
	 * @author Alex
	 *
	 */
	private static class Entry {
		private Comparable key;
		private final Object value;
		private Node next;

		private Entry(Comparable key, Object value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	/**
	 * Initializes an empty B-tree with 0 children
	 */
	public BTreeBinarySearchSymbolTable() {
		root = new Node(0);
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int height() {
		return height;
	}

	private void checkKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
	}

	public V get(K key) {
		checkKey(key);
		return get(root, key, height);
	}
    
	/**
	 * B-tree grows from bottom up
	 * @param x
	 * @param key
	 * @param height
	 * @return
	 */
	private V get(Node x, K key, int height) {
		Entry[] children = x.children;
		// external node
		if (height == 0) {
			// external node has data. so traverse children and retrieve item.
			for (int j = 0; j < x.m; j++) {
				if (eq(key, children[j].key)) {
					return (V) children[j].value;
				}
			}
		}
		// internal node
		else {
			for (int j = 0; j < x.m; j++) {
				if (j + 1 == x.m || less(key, children[j + 1].key)) {
					//search upper level
					return get(children[j].next, key, height - 1);
				}
			}
		}
		return null;
	}

	public void put(K key, V value) {
		checkKey(key);
	}

	private boolean less(Comparable k1, Comparable k2) {
		return k1.compareTo(k2) < 0;
	}

	private boolean eq(Comparable k1, Comparable k2) {
		return k1.compareTo(k2) == 0;
	}

}
