package algorithm.structure.table;

import java.util.NoSuchElementException;

import algorithm.structure.queue.Queue;

/**
 * A binary search tree (BST) is a binary tree where each node has a Comparable
 * key (and an associated value) and satisfies the restriction that the key in
 * any node is larger than the keys in all nodes in that node's left subtree and
 * smaller than the keys in all nodes in that node's right subtree.
 * <p>
 * key-value對，且節點的key比左子節點大，比右子節點小
 * <p>
 * This implementation uses an (unbalanced) binary search tree.
 * <p>
 * 
 * @author Alex
 *
 * @param <K>
 * @param <V>
 */
public class BinarySearchTreeSymbolTable<K extends Comparable<K>, V> {
	// reference to the Root node
	private Node root;

	private class Node {
		private K key;
		private V val;
		private Node left, right;
		// number of nodes in subtree
		private int size;

		public Node(K key, V val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null) {
			return 0;
		}
		return x.size;
	}

	public boolean contains(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		return get(key) != null;
	}

	public void put(K key, V val) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		if (val == null) {
			delete(key);
		}

		Node x = root;
		// traverse the tree to check if key already exists
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp > 0) {
				x = x.right;
			} else if (cmp < 0) {
				x = x.left;
			} else {
				x.val = val;
			}
		}
		// not in tree. traverse tree and find proper node to insert and adjust
		// the tree
		x = new Node(key, val, 1);
	}

	public void putRecursive(K key, V val) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		if (val == null) {
			delete(key);
		}
		root = put(root, key, val);
	}

	private Node put(Node x, K key, V val) {
		if (x == null) {
			return new Node(key, val, 1);
		}
		int cmp = key.compareTo(x.key);
		if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else if (cmp < 0) {
			x.left = put(x.left, key, val);
		} else {
			x.val = val;
		}
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		Node x = root;
		// traverse the tree
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp > 0) {
				// to right subtree
				x = x.right;
			} else if (cmp < 0) {
				// to left subtree
				x = x.left;
			} else {
				return x.val;
			}
		}
		// not found
		return null;
	}

	public V getRecursive(K key) {
		return get(root, key);
	}

	private V get(Node x, K key) {
		if (x == null) {
			return null;
		}
		int cmp = x.key.compareTo(key);
		if (cmp == 0) {
			return x.val;
		} else if (cmp > 0) {
			return get(x.right, key);
		} else {
			return get(x.left, key);
		}
	}

	public void delete(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		root = delete(root, key);
	}

	private Node delete(Node x, K key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp > 0) {
			x.right = delete(x.right, key);
		} else if (cmp < 0) {
			x.left = delete(x.left, key);
		} else {
			// check if this node has left or right subtree
			if (x.right == null)
				return x.left;
			if (x.left == null)
				return x.right;
			Node t = x;
			x = min(t.right);

		}
		return x;
	}

	public K min() {
		if (isEmpty()) {
			throw new NoSuchElementException("called min() with empty symbol table");
		}
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null) {
			return x;
		} else {
			return min(x.left);
		}
	}

	public K max() {
		if (isEmpty()) {
			throw new NoSuchElementException("called max() with empty symbol table");
		}
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null) {
			return x;
		} else {
			return max(x.right);
		}
	}

	public K maxLoop() {
		Node x = root;
		while (x.right != null) {
			x = x.right;
		}
		return x.key;
	}

	public K floor(K key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to floor() is null");
		}
		if (isEmpty()) {
			throw new NoSuchElementException("called floor() with empty symbol table");
		}
		Node x = floor(root, key);
		if (x == null) {
			return null;
		} else {
			return x.key;
		}
	}

	/**
	 * the largest Node less than or equal to given key
	 * 
	 * @param x
	 * @param key
	 * @return
	 */
	private Node floor(Node x, K key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			return x;
		}
		if (cmp < 0) {
			return floor(x.left, key);
		}
		// cmp > 0, could be on the right, so check right subtree
		Node t = floor(x.right, key);
		if (t != null) {
			return t;
		} else {
			return x;
		}
	}

	public K ceiling(K key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to ceiling() is null");
		}
		if (isEmpty()) {
			throw new NoSuchElementException("called ceiling() with empty symbol table");
		}
		Node x = ceiling(root, key);
		if (x == null) {
			return null;
		} else {
			return x.key;
		}
	}

	/**
	 * the smallest Node greater than or equal to given key
	 * 
	 * @param x
	 * @param key
	 * @return
	 */
	private Node ceiling(Node x, K key) {
		if (x == null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			return x;
		}
		if (cmp > 0) {
			return ceiling(x.right, key);
		}
		// check left subtree
		Node t = ceiling(x.left, key);
		if (t != null) {
			return t;
		} else {
			return x;
		}
	}

	public K select(int k) {
		return select(root, k).key;
	}

	private Node select(Node x, int k) {
		if (x == null) {
			return null;
		}
		int t = size(x.left);
		if (t > k) {
			return select(x.left, k);
		} else if (t < k) {
			// t是剛才經過的left的rank，1是root的rank
			return select(x.right, k - t - 1);
		} else {
			return x;
		}

	}

	/**
	 * How many keys less than a given key
	 * <p>
	 * If the given key is equal to the key at root, return the number of keys
	 * in left subtree.
	 * <p>
	 * If the given key is less than the key at root, return the rank of the key
	 * in the left subtree.
	 * <p>
	 * If the given key is larger than the key at root, return left and right
	 * subtree plus one(to count the key at root)
	 * 
	 * @param key
	 * @return the rank of a given key
	 */
	public int rank(K key) {
		return rank(root, key);
	}

	private int rank(Node x, K key) {
		if (x == null) {
			return 0;
		}
		int cmp = key.compareTo(x.key);
		if (cmp > 0) {
			return size(x.left) + size(x.right) + 1;
		} else if (cmp < 0) {
			return rank(x.left, key);
		} else {
			return size(x.left);
		}

	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void deleteMax() {
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node x) {
		if (x.right == null) {
			return x.left;
		}
		x.right = deleteMax(x.right);
		x.size = size(x.left) + size(x.right) + 1;
		return x; 
	}

	public Iterable<K> iterator() {
		Queue<K> queue = new Queue<K>();
		inorder(root, queue);
		return queue;
	}

	private void inorder(Node x, Queue<K> queue) {
		if (x == null) {
			return;
		}
		inorder(x.left, queue);
		queue.enqueue(x.key);
		inorder(x.right, queue);
	}
}
