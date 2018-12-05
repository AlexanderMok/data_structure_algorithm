package algorithm.structure.table;

/**
 * Develop a symbol-table implementation ArrayST.java that uses an (unordered)
 * array as the underlying data structure to implement our basic symbol table
 * API
 * 
 * @author Alex
 *
 */
public class ArraySymbolTable<K extends Comparable<K>, V> {
	private static final int INIT_CAPACITY = 5;
	private K[] keys;
	private V[] vals;
	private int n;

	public ArraySymbolTable(int capacity) {
		keys = (K[]) new Comparable[capacity];
		vals = (V[]) new Object[capacity];
	}

	public ArraySymbolTable() {
		this(INIT_CAPACITY);
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int rank(K key) {
		for (int i = 0; i < n; i++) {
			if (key.equals(keys[i])) {
				return i;
			}
		}
		return n;
	}

	public void resize(int capacity) {
		K[] tmpK = (K[]) new Comparable[capacity];
		V[] tmpV = (V[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			tmpK[i] = keys[i];
			tmpV[i] = vals[i];
		}
		keys = tmpK;
		vals = tmpV;
	}

	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		int i = rank(key);
		if (i < n && key.equals(keys[i])) {
			return vals[i];
		}
		return null;
	}

	public void put(K key, V val) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		if (val == null) {
			delete(key);
			return;
		}
		if (n >= keys.length) {
			resize(n * 2);
		}
		int i = rank(key);
		// key in table
		if (i < n && key.equals(keys[i])) {
			vals[i] = val;
			return;
		}
		// key not in table
		keys[n] = key;
		vals[n] = val;
		n++;
	}

	public void delete(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		if (isEmpty()) {
            return;
		}
		int i = rank(key);
		if (i < n && key.equals(keys[i])) {
            for (int j = i; j < n - 1; j++) {
            	keys[j] = keys[j+1];
            	vals[j] = vals[j+1];
            }
            n--;
            keys[n] = null;
            vals[n] = null;
		}
		if (n >0 && n == keys.length / 4) {
			resize(keys.length / 2);
		} 
	}

}
