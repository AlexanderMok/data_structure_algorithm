package algorithm.structure.table;

import java.util.NoSuchElementException;

public class SequentialSearchSymbolTable<K, V> {
    //number of key-value pairs
	private int n;
	//the linked-list of key-value pairs
	private Node first;
	
	public SequentialSearchSymbolTable(){
		
	}
	
	public int size() {
		return n;
	}
	
	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		Node traverse = first;
		while(traverse != null) {
			if (key.equals(traverse.key)) {
				return traverse.value;
			} 
			traverse = traverse.next;
		}
		return null;
	}
	
	public void put(K key, V value) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		Node traverse = first;
		while (traverse != null) {
			if (key.equals(traverse.key)) {
				traverse.value = value;
				return;
			}
			traverse = traverse.next;
		}
		Node newFirst = new Node(key, value, first);
		first = newFirst;
		n++;
	}
	
	public V delete(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		if (isEmpty()) {
			throw new NoSuchElementException("Symbol table is empty");
		}
		Node traverse = first;
		while(traverse != null) {
			if(key.equals(traverse.key)) {
				Node remove = traverse.next;
				traverse.next = remove.next;
				n--;
				return remove.value;
			}
			traverse = traverse.next;
		}
		return null;
	}
	
	public boolean contains(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be empty");
		}
		return get(key) != null;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	
	
	
	private class Node {
    	private K key;
    	private V value;
    	private Node next;
    	
    	public Node(K key, V value, Node next) {
    		this.key = key;
    		this.value = value;
    		this.next = next;
    	}
    }
	
	public static void main(String[] args) {
		SequentialSearchSymbolTable<String, Integer> st = new SequentialSearchSymbolTable<String, Integer>();
		st.put("a", 1);
		st.put("b", 2);
		System.out.println(st.contains("a"));
		System.out.println(st.get("b"));
		System.out.println(st.delete("b"));
		System.out.println(st.get("b"));
	}
}
