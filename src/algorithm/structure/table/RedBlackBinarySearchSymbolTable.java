package algorithm.structure.table;

import java.util.NoSuchElementException;

public class RedBlackBinarySearchSymbolTable<K extends Comparable<K>, V> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	//root of BST
	private Node root;
	
	/**
	 * Helper node data type in red black BST
	 * @author Alex
	 *
	 */
	private class Node {
		private K key;
		private V value;
		private Node left, right;
		//color of link from parent to this node
		private boolean color;
		private int size;
		
		public Node(K key, V value, boolean color, int size) {
			super();
			this.key = key;
			this.value = value;
			this.color = color;
			this.size = size;
		}
	}
	
	public RedBlackBinarySearchSymbolTable(){}
	
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
	
	public int size(){return size(root);}
	
	public boolean isEmpty(){return root == null;}
	
	/**
	 * Red-black tree search/get. Same as BST
	 * O(logn)
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
		if (cmp > 0) { return get(x.right, key);}
		else if (cmp < 0) {return get(x.left, key);}
		else return x.value;
	}
	
	public boolean contains(K key) {
		return get(key) != null;
	}
	
	/**
	 * Red-black tree insert/put.
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
		//check red black links
		if (isRed(h.right) && !isRed(h.left)) {
			h = rotateLeft(h);
		}
		if (isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}
		if (isRed(h.left) && isRed(h.right)) {
			flipColors(h);
		}
		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}
	
	/*Rotation Operations*/
	
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		//color link from parent to h change to x, as h color is red and x now is root.
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
	
	private Node balance(Node h) {
		if (isRed(h.right)) {
			h= rotateLeft(h);
		}
		if (isRed(h.left) && isRed(h.left.left)) {
			h= rotateRight(h);
		}
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
	}
	
	private Node deleteMin(Node h) {
		if (h.left == null) {
			return null;
		}
		//ensure we do not end up on a 2-link-node(Red node)
		if (!isRed(h.left) && !isRed(h.left.left)) {
			//borrow nodes from right siblings
			h= moveRedLeft(h);
		}
		h.left = deleteMin(h.left);
		return balance(h);
	}
	
	private Node moveRedLeft(Node h) {
		flipColors(h);
		if (isRed(h.left.left)) {
			h.right = rotateRight(h.right);
			h = rotateLeft(h); 
			flipColors(h);
		}
		return h;
	}
	
	
	
	
	public V delete(K key) {
		checkKey(key);
	}
	
	
}
