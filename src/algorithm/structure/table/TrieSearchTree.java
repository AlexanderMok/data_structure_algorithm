package algorithm.structure.table;

import java.util.HashMap;
import java.util.Map;

/**
 * Trie Tree
 * <a href="https://github.com/emory-courses/cs253/blob/master/doc/tries.md">
 * emory cs253 tries</a>
 * 
 * @author Alex
 *
 * @param <T>
 */
public class TrieSearchTree<T> {

	private TrieNode<T> root;

	/**
	 * Helper class to represent Node in Trie tree.
	 * 
	 * @author Alex
	 *
	 * @param <T>
	 */
	private class TrieNode<T> {
		private TrieNode<T> parent;
		/* a shared key associated with its children */
		private Map<Character, TrieNode<T>> children;
		/* one character of a {@code String} */
		private char key;
		private T value;
		private boolean endState;

		public TrieNode(TrieNode<T> parent, char key) {
			this.parent = parent;
			this.children = new HashMap<Character, TrieNode<T>>();
			this.key = key;
			this.value = null;
			this.endState = false;
		}

		public Map<Character, TrieNode<T>> getChildrenMap() {
			return children;
		}

		public TrieNode<T> getChild(char key) {
			return children.get(key);
		}

		public TrieNode<T> addChild(char key) {
			TrieNode<T> childNode = getChild(key);
			if (childNode == null) {
				childNode = new TrieNode<>(this, key);
				children.put(key, childNode);
			}
			return childNode;
		}

		public TrieNode<T> removeChild(char key) {
			return children.remove(key);
		}

		public boolean isEndState() {
			return endState;
		}

		public boolean hasValue() {
			return value == null;
		}

		public boolean hasChildren() {
			return !children.isEmpty();
		}
	}

	/**
	 * Initialize an empty {@code TrieNode}.
	 */
	public TrieSearchTree() {
		root = new TrieNode<>(null, (char) 0);
	}

	/**
	 * Search the trie node with a specific key. If the key in the Trie Tree,
	 * and the node is in endState, return its value.
	 * 
	 * @param key
	 * @return
	 */
	public T get(String key) {
		TrieNode<T> node = search(key);
		if (node != null && node.isEndState()) {
			return node.value;
		}
		return null;
	}

	/**
	 * Search the trie node with a specific key.
	 * 
	 * @param key
	 * @return {@code TrieNode} if key in the Trie Tree. Otherwise {@code null}.
	 */
	public TrieNode<T> search(String key) {
		char[] chars = key.toCharArray();
		TrieNode<T> node = root;
		for (int i = 0; i < key.length(); i++) {
			node = node.getChild(chars[i]);
			if (node == null) {
				return null;
			}
		}
		return node;
	}

	/**
	 * Put a String {@code key} in the Trie Tree.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public T put(String key, T value) {
		char[] chars = key.toCharArray();
		TrieNode<T> node = root;
		// put characters of key in Trie Tree
		for (int i = 0; i < chars.length; i++) {
			node = node.addChild(chars[i]);
		}
		node.endState = true;
		node.value = value;
		return value;
	}

	public boolean remove(String key) {
		TrieNode<T> node = search(key);
		// key not in Trie Tree
		if (node == null || !node.isEndState()) {
			return false;
		}
		// characters in key are shared by others
		if (node.hasChildren()) {
			node.endState = false;
			return true;
		}
		// remove from bottom up until a shared node exists or endStatue
		TrieNode<T> parent = node.parent;
		while (parent != null) {
			parent.removeChild(node.key);

			if (parent.hasChildren() || parent.isEndState()) {
				break;
			} else {
				// go up
				node = parent;
				parent = parent.parent;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		TrieSearchTree<String> trieTree = new TrieSearchTree<>();
		trieTree.put("how", "some");
		trieTree.put("her", "some");
		trieTree.put("horn", "some");
		trieTree.put("ho", "some");
		trieTree.put("here", "some");
		String value = trieTree.get("horn");
		System.out.println(value);
		value = trieTree.get("no");
		System.out.println(value);
		trieTree.remove("ho");
		value = trieTree.get("how");
		System.out.println(value);
	}

}
