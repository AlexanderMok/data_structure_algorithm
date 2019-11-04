package algorithm.structure.table;

/**
 * The {@code TrieSearchTreeArray} class represents an symbol table of key-value
 * pairs, with string keys and generic values.
 * <p>
 * It also provides character-based methods for finding the string in the symbol
 * table that is the <em>longest prefix</em> of a given prefix, finding all
 * strings in the symbol table that <em>start with</em> a given prefix, and
 * finding all strings in the symbol table that <em>match</em> a given pattern.
 * <p>
 * This implementation uses a 256-way trie. Different from that in
 * {@code TrieSearchTree}.
 * <p>
 * For additional documentation, see
 * <a href="http://algs4.cs.princeton.edu/52trie">Section 5.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Alex
 *
 * @param <T>
 */
public class TrieSearchTreeArray<T> {
	/* extended ASCII */
	private static final int R = 256;

	/* root of trie */
	private TrieNode root;
	/* number of keys in trie */
	private int n;

	/**
	 * R-way tire node
	 * 
	 * @author Alex
	 *
	 * @param <T>
	 */
	private static class TrieNode {
		private Object value;
		private TrieNode[] next = new TrieNode[R];
	}

	public TrieSearchTreeArray() {
	}

	/**
	 * Returns the value associated with the given key.
	 * 
	 * @param key
	 * @return
	 */
	public T get(String key) {
		TrieNode x = get(root, key, 0);
		if (x == null) {
			return null;
		}
		return (T) x.value;
	}

	// recursive version of searching down the trie prefix tree
	private TrieNode get(TrieNode x, String key, int depth) {
		if (x == null) {
			return null;
		}
		if (depth == key.length()) {
			return x;
		}
		char c = key.charAt(depth);
		return get(x.next[c], key, depth + 1);
	}

	// non-recursive version of searching down the trie prefix tree
	private TrieNode get2(TrieNode x, String key, int depth) {
		char[] characters = key.toCharArray();
		TrieNode node = x;
		for (int i = depth; i < characters.length; i++) {
			node = node.next[characters[i]];
			if (node == null) {
				return null;
			}
		}
		return node;
	}

	/**
	 * Returns the value associated with the given key.
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(String key) {
		return get(key) != null;
	}

	public void put(String key, T value) {
		if (value == null) {
			delete(key);
		} else {
			root = put(root, key, value, 0);
		}
	}

	private TrieNode put(TrieNode x, String key, T value, int depth) {
		if (x == null) {
			return new TrieNode();
		}
		// key sequence has put into the Trie
		if (depth == key.length()) {
			if (x.value == null) {
				n++;
			}
			x.value = value;
			return x;
		}
		// go down to next level
		char character = key.charAt(depth);
		x.next[character] = put(x.next[character], key, value, depth + 1);
		return x;
	}

	public T put2(String key, T value) {
		if (value == null) {
			delete(key);
		} else {
			char[] characters = key.toCharArray();
			TrieNode node = this.root;
			for (int i = 0; i < characters.length; i++) {
				char c = characters[i];
				TrieNode childNode = node.next[c];
				if (childNode == null) {
					childNode = new TrieNode();
				}
				node = childNode;
			}
			if (node.value == null) {
				n++;
			}
			node.value = value;
		}
		return value;
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * 
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void delete(String key) {
		root = delete(root, key, 0);
	}

	private TrieNode delete(TrieNode x, String key, int depth) {
		if (x == null) {
			return null;
		}
		if (depth == key.length()) {
			if (x.value != null) {
				n--;
			}
			x.value = null;
		} else {
			char c = key.charAt(depth);
			x.next[c] = delete(x.next[c], key, depth + 1);
		}
		
		// remove subtrie rooted at x if it is completely empty
		if (x.value != null) {
			return x;
		}
		for (int c = 0; c < R; c++) {
			if (x.next[c] != null) {
				return x;
			}
		}
		return null;
	}

}
