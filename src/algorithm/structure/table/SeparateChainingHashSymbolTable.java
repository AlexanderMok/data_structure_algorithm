package algorithm.structure.table;

import java.util.stream.IntStream;

import algorithm.structure.queue.Queue;

/**
 * Separate chaining to hashing collision resolution with
 * SequentialSearchSymbolTable. Array chain with linked list implementation of  ST.
 * 
 * @author Alex
 *
 * @param <K>
 * @param <V>
 */
public class SeparateChainingHashSymbolTable<K, V> {
	private static final int INIT_CAPACITY = 4;
	// number of key-value pairs
	private int n;
	// hash table size
	private int m;
	// array of linked-list symbol tables
	private SequentialSearchSymbolTable<K, V>[] st;

	public SeparateChainingHashSymbolTable() {
		this(INIT_CAPACITY);
	}

	/**
	 * Initialize an empty ST with {@code m} chains.
	 * 
	 * @param m
	 */
	public SeparateChainingHashSymbolTable(int m) {
		this.m = m;
		st = (SequentialSearchSymbolTable<K, V>[]) new SequentialSearchSymbolTable[m];
		IntStream.range(0, m).forEach(i -> {
			st[i] = new SequentialSearchSymbolTable<K, V>();
		});
	}

	/**
	 * hash the key between 0 and m-1. change to 31-bit non-negative value and
	 * do modular hashing.
	 * 
	 * @param key
	 * @return
	 */
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	private void checkKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * resize the hash table but the size of chain remain unchanged.
	 * 
	 * @param chains
	 */
	private void resize(int chains) {
		SeparateChainingHashSymbolTable<K, V> tmp = new SeparateChainingHashSymbolTable<>(chains);
		for (int i = 0; i < m; i++) {
			for (K key : st[i].keys()) {
				tmp.put(key, st[i].get(key));
			}
		}
		this.m = tmp.m;
		this.n = tmp.n;
		this.st = tmp.st;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(K key) {
		checkKey(key);
		return get(key) != null;
	}

	public V get(K key) {
		return st[hash(key)].get(key);
	}

	public void put(K key, V value) {
		checkKey(key);
		if (value == null) {
			delete(key);
			return;
		}
		if (n >= 10 * m) {
			resize(2 * m);
		}
		int i = hash(key);
		if (!st[i].contains(key)) {
			n++;
		}
		st[i].put(key, value);
	}

	public void delete(K key) {
		checkKey(key);
		int i = hash(key);
		if (st[i].contains(key)) {
			n--;
		}
		st[i].delte(key);
		if (n <= 2 * m && m > INIT_CAPACITY) {
			resize(m / 2);
		}
	}

	public Iterable<K> keys() {
		Queue<K> queue = new Queue<K>();
		for (int i = 0; i < m; i++) {
			for (K key : st[i].keys()) {
				queue.enqueue(key);
			}
		}
		return queue;
	}

	public static void main(String[] args) {
		SeparateChainingHashSymbolTable<String, String> map = new SeparateChainingHashSymbolTable<>();
	    map.put("A", "1");
	    map.put("B", "2");
	    map.put("C", "3");
	    map.put("D", "4");
	    System.out.println(map.keys());
	}
}
