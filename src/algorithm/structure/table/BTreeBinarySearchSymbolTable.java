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
		return null;
	}

	public void put(K key, V value) {
		checkKey(key);
	}
}
