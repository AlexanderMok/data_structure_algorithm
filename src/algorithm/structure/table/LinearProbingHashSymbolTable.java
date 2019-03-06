package algorithm.structure.table;

public class LinearProbingHashSymbolTable<K, V> {
	private static final int INIT_CAPCITY = 16;
	// number of key-value pairs in the hash table
	private int n;
	// size of linear probing table
	private int m;
	private K[] keys;
	private V[] values;

	public LinearProbingHashSymbolTable(int capacity) {
		m = capacity;
		n = 0;
		keys = (K[]) new Object[m];
		values = (V[]) new Object[m];
	}

	public LinearProbingHashSymbolTable() {
		this(INIT_CAPCITY);
	}

	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	/**
	 * Resize the hash table to given capacity by re-hashing all of the keys.
	 * 
	 * @param capacity
	 */
	private void resize(int capacity) {
		LinearProbingHashSymbolTable<K, V> tmp = new LinearProbingHashSymbolTable<K, V>(capacity);
		for (int i = 0; i < m; i++) {
			if (keys != null) {
				tmp.put(keys[i], values[i]);
			}
		}
		keys = tmp.keys;
		values = tmp.values;
		m = tmp.m;
	}

	private void checkKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(K key) {
		return get(key) != null;
	}

	/**
	 * Traverse the table to look for the given key
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		checkKey(key);
		int i = hash(key);
		while (keys[i] != null) {
			if (keys[i].equals(key)) {
				return values[i];
			}
			i = (i + 1) % m;
		}
		return null;
	}

	public void put(K key, V value) {
		checkKey(key);
		if (value == null) {
			delete(key);
			return;
		}
		if (n >= m / 2) {
			resize(2 * m);
		}
		int i = hash(key);
		while (keys[i] != null) {
			if (keys[i].equals(key)) {
				values[i] = value;
				return;
			}
			i = (i + 1) % m;
		}
		keys[i] = key;
		values[i] = value;
		n++;
	}

	public void delete(K key) {
		checkKey(key);
		if (!contains(key)) {
			return;
		}
		int i = hash(key);
		while (keys[i] != null) {
			if (keys[i].equals(key)) {
				keys[i] = null;
				values[i] = null;
				n--;
				return;
			}
			i = (i + 1) % m;
		}
	}

	public static void main(String[] args) {

	}

}
