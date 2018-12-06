package algorithm.structure.table;

/**
 * A binary search tree (BST) is a binary tree where each node has a Comparable
 * key (and an associated value) and satisfies the restriction that the key in
 * any node is larger than the keys in all nodes in that node's left subtree and
 * smaller than the keys in all nodes in that node's right subtree.
 * <p>
 * key-value對，且節點的key比左子節點大，比右子節點小
 * <p>
 * 
 * @author Alex
 *
 * @param <K>
 * @param <V>
 */
public class BinarySearchTree<K extends Comparable<K>, V> {
	// reference to the Root node
	private Node root;

	private class Node {
		private K key;
		private V val;
		private Node left, right;

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
		}
	}

	public void put(K key, V val) {
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
		root = new Node(key, val);
	}

	public void putRecursive(K key, V val) {
		root = put(root, key, val);
	}

	private Node put(Node x, K key, V val) {
		if (x == null) {
			return new Node(key, val);
		}
		int cmp = key.compareTo(x.key);
		if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else if (cmp < 0) {
			x.left = put(x.left, key, val);
		} else {
			x.val = val;
		}
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
		if (x == null)
			return null;
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
	}

	public Iterable<K> iterator() {
		return null;
	}
}
