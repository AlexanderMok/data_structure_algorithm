package algorithm.structure.table;

import java.util.NoSuchElementException;

/**
 * The {@code AVLBinarySearchSymbolTable} class represents an ordered symbol
 * table of generic key-value pairs.
 * <p>
 * This symbol table implementation uses internally an
 * <a href="https://en.wikipedia.org/wiki/AVL_tree"> AVL tree </a> (Georgy
 * Adelson-Velsky and Evgenii Landis' tree) which is a self-balancing BST. In an
 * AVL tree, the heights of the two child subtrees of any node differ by at most
 * one; if at any time they differ by more than one, rebalancing is done to
 * restore this property. 内部使用AVL树，类似红黑树，可以自平衡。子节点与父节点的高度最多为1.
 * <p>
 * The <em>put</em>, <em>get</em>, <em>contains</em>, <em>delete</em>,
 * <em>minimum</em>, <em>maximum</em>, <em>ceiling</em>, and <em>floor</em>
 * operations each take logarithmic time in the worst case.
 * 
 * @author Alex
 *
 */
public class AVLBinarySearchSymbolTable<K extends Comparable<K>, V> {
	/**
	 * The root node of AVL tree
	 */
	private Node root;

	private class Node {
		private final K key;
		private V value;
		// height of this subtree
		private int height;
		// number of nodes in subtree
		private int size;
		private Node left, right;

		public Node(K key, V value, int height, int size) {
			this.key = key;
			this.value = value;
			this.height = height;
			this.size = size;
		}

	}

	public AVLBinarySearchSymbolTable() {
	}

	/**
	 * O(1) Checks if the symbol table is empty.
	 * 
	 * @return {@code true} if the symbol table is empty.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Returns number of k-v pairs in the symbol table.
	 * 
	 * @return
	 */
	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		return x.size;
	}

	/**
	 * Returns the height of the internal AVL tree. -1 if it is an empty tree. 0
	 * if it has only one node.
	 * 
	 * @return
	 */
	public int height() {
		return height(root);
	}

	private int height(Node x) {
		if (x == null) {
			return -1;
		}
		return x.height;
	}

	private void checkKey(K key) {
		if (key == null)
			throw new IllegalArgumentException();
	}

	/**
	 * O(logN) Returns value associated with the given key
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		checkKey(key);
		Node x = get(root, key);
		if (x == null) {
			return null;
		} else {
			return x.value;
		}
	}

	private Node get(Node x, K key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return get(x.left, key);
		} else if (cmp > 0) {
			return get(x.right, key);
		} else {
			return x;
		}
	}

	public boolean contains(K key) {
		return get(key) != null;
	}

	public void put(K key, V value) {
		checkKey(key);
		if (value == null) {
			delete(key);
			return;
		}
		root = put(root, key, value);
	}

	private Node put(Node x, K key, V value) {
		if (x == null) {
			return new Node(key, value, 0, 1);
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key, value);
		} else if (cmp > 0) {
			x.right = put(x.right, key, value);
		} else {
			x.value = value;
			return x;
		}
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return balance(x);
	}

	/**
	 * Restores balance.
	 * <p>
	 * Scenarios: left left,left right, right right, right left
	 * 
	 * @param x
	 * @return
	 */
	private Node balance(Node x) {
		// left leaning
		if (balanceFactor(x) > 1) {
			// left right
			if (balanceFactor(x.left) < 0) {
				x.left = rotateLeft(x.left);
			}
			// left left
			x = rotateRight(x);
		}
		// right leaning
		else if (balanceFactor(x) < -1) {
			// right left
			if (balanceFactor(x.right) > 0) {
				x.right = rotateRight(x.right);
			}
			// right right
			x = rotateLeft(x);
		}
		return x;
	}

	/**
	 * The balance factor is defined as the difference in height of the left
	 * subtree and right subtree, in this order.左子树与右子树的差
	 * <p>
	 * Therefore, a subtree with a balance factor of -1, 0 or 1 has the AVL
	 * property since the heights of the two child subtrees differ by at most
	 * one.-1,0,1是合法的。-1即是左0右1，0即是左0右0，1即是左1右0
	 * 
	 * @param x
	 * @return
	 */
	private int balanceFactor(Node x) {
		return height(x.left) - height(x.right);
	}

	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.left);
		h.height = 1 + Math.max(height(h.left), height(h.right));
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return x;
	}

	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		h.height = 1 + Math.max(height(h.left), height(h.right));
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return x;
	}

	public K min() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null) {
			return x;
		}
		return min(x.left);
	}

	public K max() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return min(root).key;
	}

	private Node max(Node x) {
		if (x.right == null) {
			return x;
		}
		return min(x.right);
	}

	public void delete(K key) {
		checkKey(key);
		if (!contains(key)) {
			return;
		}
		root = delete(root, key);
	}

	private Node delete(Node x, K key) {
		if (key.compareTo(x.key) < 0) {
			x.left = delete(x.left, key);
		} else if (key.compareTo(x.key) > 0) {
			x.right = delete(x.right, key);
		} else {
			if (x.left == null) {
				return x.right;
			} else if (x.right == null) {
				return x.left;
			} else {
				Node cache = x;
				x = min(x.right);
				x.right = deleteMin(cache.right);
				x.left = cache.left;
			}
		}
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return balance(x);
	}

	public void deleteMin() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return balance(x);
	}
}
