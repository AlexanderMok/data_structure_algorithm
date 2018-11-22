package algorithm.structure.table;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class SymbolTable<Key extends Comparable<Key>, Value> implements Iterable<Key> {

	private TreeMap<Key, Value> st;

	public SymbolTable() {
		st = new TreeMap<Key, Value>();
	}

	public void put(Key key, Value value) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		if (value == null) {
			st.remove(key);
		} else {
			st.put(key, value);
		}
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException();
		return st.get(key);
	}

	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		st.remove(key);
	}

	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		return st.containsKey(key);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return st.size();
	}

	public Iterable<Key> keys() {
		return st.keySet();
	}

	@Override
	public Iterator<Key> iterator() {
		return st.keySet().iterator();
	}

	/**
	 * Returns the first (lowest) key currently in this symbol table
	 * 
	 * @return
	 */
	public Key min() {
		if (isEmpty()) {
			throw new NoSuchElementException("empty symbol table");
		}
		return st.firstKey();
	}

	/**
	 * Returns the last (greatest) key currently in this symbol table
	 * 
	 * @return
	 */
	public Key max() {
		if (isEmpty()) {
			throw new NoSuchElementException("empty symbol table");
		}
		return st.lastKey();
	}

	/**
	 * Return smallest key greater than or equal to key
	 * 
	 * Param key does not necessarily exist in the symbol table
	 * @param key
	 * @return
	 */
	public Key ceiling(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		Key k = st.ceilingKey(key);
		if (k == null) {
           throw new NoSuchElementException("all keys are less than " + key);
		}
		return k;
	}
	
	/**
	 * Return largest key less than or equal to key
	 * 
	 * @param key
	 * @return
	 */
	public Key floor(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		Key k = st.floorKey(key);
		if( k == null) {
			throw new NoSuchElementException("all keys are greater than " + key);
		}
		return k;
	}
}
