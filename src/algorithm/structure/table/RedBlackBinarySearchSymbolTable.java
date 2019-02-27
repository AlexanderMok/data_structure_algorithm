package algorithm.structure.table;

import java.awt.RenderingHints.Key;
import java.util.NoSuchElementException;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * <a href='http://www.cs.princeton.edu/~rs/talks/LLRB/RedBlack.pdf'>
 * RedBlackTree</a>
 * 
 * @author Alex
 *
 * @param <K>
 * @param <V>
 */
public class RedBlackBinarySearchSymbolTable<K extends Comparable<K>, V> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	// root of BST
	private Node root;

	/**
	 * Helper node data type in red black BST
	 * 
	 * @author Alex
	 *
	 */
	private class Node {
		private K key;
		private V value;
		private Node left, right;
		// color of link from parent to this node
		private boolean color;
		private int size;

		public Node(K key, V value, boolean color, int size) {
			this.key = key;
			this.value = value;
			this.color = color;
			this.size = size;
		}
	}

	public RedBlackBinarySearchSymbolTable() {
	}

	private boolean isRed(Node x) {
		if (x != null) {
			return x.color == RED;
		}
		return false;
	}

	private int size(Node x) {
		if (x == null) {
			return 0;
		}
		return x.size;
	}

	private void checkKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
	}

	public int size() {
		return size(root);
	}

	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Red-black tree search/get. Same as BST O(logn)
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		checkKey(key);
		return get(root, key);
	}

	private V get(Node x, K key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp > 0) {
			return get(x.right, key);
		} else if (cmp < 0) {
			return get(x.left, key);
		} else
			return x.value;
	}

	public boolean contains(K key) {
		return get(key) != null;
	}

	/**
	 * Red-black tree insert/put. O(logn)
	 * 
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		checkKey(key);
		if (value == null) {
			delete(key);
			return;
		}
		root = put(root, key, value);
		root.color = BLACK;
	}

	private Node put(Node h, K key, V value) {
		if (h == null) {
			return new Node(key, value, RED, 1);
		}
		int cmp = key.compareTo(h.key);
		if (cmp > 0) {
			h.right = put(h.right, key, value);
		} else if (cmp < 0) {
			h.left = put(h.left, key, value);
		} else {
			h.value = value;
		}
		// enforce left leaning
		if (isRed(h.right) && !isRed(h.left)) {
			h = rotateLeft(h);
		}
		// balance a 4-node
		if (isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}
		// split a 4-node and ensure current is not a 4-node
		if (isRed(h.left) && isRed(h.right)) {
			flipColors(h);
		}
		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}

	/* Rotation Operations */

	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		// color link from parent to h change to x, as h color is red and x now
		// is root.
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	// flip the colors of a node and its two children
	private void flipColors(Node h) {
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	/**
	 * Fix right-leaning reds and eliminates 4-nodes on the way up.
	 * 
	 * @param h
	 * @return
	 */
	private Node balance(Node h) {
		if (isRed(h.right)) {
			h = rotateLeft(h);
		}
		if (isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}
		// split 4-node
		if (isRed(h.right) && isRed(h.left)) {
			flipColors(h);
		}
		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}

	public void deleteMin() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		root = deleteMin(root);
		root.color = BLACK;
	}

	/**
	 * keep h or h.left is {@code RED}
	 * 
	 * @param h
	 * @return
	 */
	private Node deleteMin(Node h) {
		if (h.left == null) {
			return null;
		}
		// 2-node cases
		if (!isRed(h.left) && !isRed(h.left.left)) {
			// borrow nodes from right siblings
			h = moveRedLeft(h);
		}
		h.left = deleteMin(h.left);
		return balance(h);
	}

	private Node moveRedLeft(Node h) {
		// h.right.left is BLACK
		flipColors(h);
		if (isRed(h.right.left)) {
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	public void deleteMax() {
		root = deleteMax(root);
		root.color = BLACK;
	}

	/**
	 * keep h or h.right is {@code RED} so that it is not a 2-node
	 * 
	 * @param h
	 * @return
	 */
	private Node deleteMax(Node h) {
		// lean 3-nodes to the right as we are looking for the max
		if (isRed(h.left)) {
			h = rotateRight(h);
		}
		// remove node on bottom level and h must be RED by invariant.
		if (h.right == null) {
			return null;
		}
		// h is a 2-node and parent of h is 3-node or 4-node
		if (!isRed(h.right) && !isRed(h.right.left)) {
			h = moveRedRight(h);
		}
		h.right = deleteMax(h.right);
		// fix right-leaning red links and eliminate 4-nodes
		return balance(h);
	}

	private Node moveRedRight(Node h) {
		// h.left.left is BLACK. sibling of h is 2-node
		flipColors(h);
		// sibling of h is non-2-node
		if (isRed(h.left.left)) {
			// borrow from left
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}

	public void delete(K key) {
		checkKey(key);
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}
		root = delete(root, key);
		if (!isEmpty()) {
			root.color = BLACK;
		}
	}

	/**
	 * keep h or one of its children is RED
	 * 
	 * @param h
	 * @param key
	 * @return
	 */
	private Node delete(Node h, K key) {
		int cmp = key.compareTo(h.key);
		if (cmp < 0) {
			if (!isRed(h.left) && !isRed(h.left.left)) {
				h = moveRedLeft(h);
			}
			h.left = delete(h.left, key);
		} else {
			if (isRed(h.left)) {
				h = rotateRight(h);
			}
			// equal at bottom
			if (cmp == 0 && (h.right == null)) {
				return null;
			}
			if (!isRed(h.right) && !isRed(h.right.left)) {
				h = moveRedRight(h);
			}
			// equal not at bottom
			if (cmp == 0) {
				Node x = min(h.right);
				h.key = x.key;
				h.value = x.value;
				h.right = deleteMin(h.right);
			} else {
				h.right = delete(h.right, key);
			}
		}
		return balance(h);
	}

	/***************************
	 * Ordered symbol table methods
	 **************************/

	public K min() {
		if (isEmpty()) {
			throw new NoSuchElementException("empty symbol table");
		}
		return min(root).key;
	}

	private Node min(Node h) {
		if (h.left == null) {
			return h;
		} else {
			return min(h.left);
		}
	}

	public K max() {
		if (isEmpty()) {
			throw new NoSuchElementException("empty symbol table");
		}
		return max(root).key;
	}

	private Node max(Node h) {
		if (h.right == null) {
			return h;
		} else {
			return max(h.right);
		}
	}

	/**
	 * largest key less than or equals to {@code key}}
	 * 
	 * @param key
	 * @return
	 */
	public K floor(K key) {
		checkKey(key);
		if (isEmpty()) {
			throw new NoSuchElementException("empty symbol table");
		}
		Node x = floor(root, key);
		if (x == null) {
			return null;
		} else {
			return x.key;
		}
	}

	private Node floor(Node x, K key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			return x;
		}
		if (cmp < 0) {
			floor(x.left, key);
		}
		Node t = floor(x.right, key);
		if (t != null) {
			return t;
		} else {
			return x;
		}
	}

	/**
	 * smallest key greater than or equals to {@code key}
	 * 
	 * @param key
	 * @return
	 */
	public K ceiling(K key) {
		checkKey(key);
		if (isEmpty()) {
			throw new NoSuchElementException("empty symbol table");
		}
		Node x = ceiling(root, key);
		if (x == null) {
			return null;
		} else {
			return x.key;
		}
	}

	private Node ceiling(Node x, K key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			return x;
		}
		if (cmp > 0) {
			ceiling(x.right, key);
		}
		Node t = ceiling(x.left, key);
		if (t != null) {
			return t;
		} else {
			return x;
		}
	}

	public K select(int k) {
		if (k < 0 || k >= size()) {
			throw new IllegalArgumentException();
		}
		Node x = select(root, k);
		return x.key;
	}

	private Node select(Node x, int k) {
		int t = size(x.left);
		if (t > k) {
			return select(x.left, k);
		} else if (t < k) {
			select(x.right, k - t - 1);
		} else {
			return x;
		}

	}
}
