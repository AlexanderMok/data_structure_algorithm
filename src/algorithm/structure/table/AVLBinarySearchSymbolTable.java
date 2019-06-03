package algorithm.structure.table;

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
		// TODO Auto-generated method stub
		return null;
	}
	
	public void delete(K key) {
	}
}
