package algorithm.structure.table;

import java.util.NoSuchElementException;

/**
 * an ordered symbol table
 * <p>
 * This implementation uses a sorted array.The underlying data structure is two
 * parallel array, with the keys kept in order.
 * 
 * 
 * 
 * @author Alex
 *
 * @param <K>
 * @param <V>
 */
public class BinarySearchSymbolTableOrdered<K extends Comparable<K>, V> {
	private static final int INIT_CAPACITY = 5;
	private K[] keys;
	private V[] vals;
	private int n;

	public BinarySearchSymbolTableOrdered(int capacity) {
		keys = (K[]) new Comparable[capacity];
		vals = (V[]) new Object[capacity];
	}

	public BinarySearchSymbolTableOrdered() {
		this(INIT_CAPACITY);
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	private void resize(int capacity) {
		K[] tmpk = (K[]) new Comparable[capacity];
		V[] tmpv = (V[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			tmpk[i] = keys[i];
			tmpv[i] = vals[i];
		}
		keys = tmpk;
		vals = tmpv;
	}

	/**
	 * O(logN)
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		return get(key) != null;
	}

	/**
	 * O(logN)
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		if (isEmpty()) {
			return null;
		}
		int i = rank(key);
		if (i < n && keys[i].compareTo(key) == 0) {
			return vals[i];
		}
		return null;
	}

	/**
	 * Returns the index of keys in this symbol table strictly less than
	 * {@code key} O(logN)
	 * 
	 * @param key
	 * @return
	 */
	public int rank(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp > 0) {
				lo = mid + 1;
			} else if (cmp < 0) {
				hi = mid - 1;
			} else {
				return mid;
			}
		}
		return lo;
	}

	public void put(K key, V val) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		if (val == null) {
			delete(key);
			return;
		}
		int i = rank(key);
		// scenario 1, key is already in table
		if (i < n && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}

		if (n == keys.length) {
			resize(2 * keys.length);
		}
		// scenario 2, insert new one and shift ordered elements
		for (int j = n; j > i; j--) {
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		keys[i] = key;
		vals[i] = val;
		n++;
	}

	/**
	 * worst case O(N)
	 * 
	 * @param key
	 */
	public void delete(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		if (isEmpty()) {
			return;
		}

		int i = rank(key);

		if (i == n || keys[i].compareTo(key) != 0) {
			return;
		}

		for (int j = i; j < n - 1; j++) {
			keys[j] = keys[j + 1];
			vals[j] = vals[j + 1];
		}
		n--;
		keys[n] = null;
		vals[n] = null;
		if (n > 0 && n == keys.length / 4) {
			resize(keys.length / 2);
		}
	}

	public void deleteMin() {
		if (isEmpty()) {
			throw new NoSuchElementException("Symbol table underflow error");
		}
		delete(min());
	}

	public void deleteMax() {
		if (isEmpty()) {
			throw new NoSuchElementException("Symbol table underflow error");
		}
		delete(max());
	}

	public K min() {
		if (isEmpty()) {
			return null;
		}
		return keys[0];
	}

	public K max() {
		if (isEmpty()) {
			return null;
		}
		return keys[n - 1];
	}

	/**
	 * As ordered array is applied, this operation can be implemented.
	 * <p>
	 * Return the kth smallest key in this symbol table
	 * 
	 * @param k
	 * @return the kth smallest key in this symbol table
	 */
	public K select(int k) {
		if (k < 0 || k > n - 1) {
			return null;
		}
		return keys[k];
	}

	public K floor(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		int i = rank(key);
		if (i < n && keys[i].compareTo(key) == 0) {
			return keys[i];
		}
		if (i == 0) {
			return null;
		} else {
			return keys[i - 1];
		}
	}
	
	public K ceiling(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		int i = rank(key);
		if (i == n) {
			return null;
		} else {
			return keys[i];
		}
	}
}
