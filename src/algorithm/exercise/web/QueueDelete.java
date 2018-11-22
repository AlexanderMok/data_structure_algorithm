package algorithm.exercise.web;

import java.time.Period;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueDelete<T> implements Iterable<T>{
    private int size;
	private int first;
    private int last;
    private T[] array;
    
    public QueueDelete(int capacity) {
    	size = 0;
    	first = 0;
    	last = 0;
    	array = (T[])new Object[capacity];
    }
    
    public int size() {
    	return size;
    }
    
    public boolean isEmpty() {
    	return size == 0;
    }
    
    public void resize(int capacity) {
    	assert capacity >= size;
    	array = Arrays.copyOf(array, capacity);
    	first = 0;
    	last = size;
    }
    
    public void enqueue(T item) {
        if(array.length == size) {
        	resize(2 * size);
        }
        array[last++] = item;
        size++;
    }
    
    public T deqeue() {
    	T item = array[first];
    	array[first] = null;
    	first++;
    	size--;
    	if(size > 0 && array.length / 4 == size) {
    		resize(size / 2);
    	}
    	return item;
    }
    
    public T deqeue(int index) {
    	index--;
    	if(index < first || index >= last) {
    		throw new IndexOutOfBoundsException();
    	}
    	T item = array[index];
    	// index first last
    	// first index last
    	// first last index
    	for (int i = index + 1; i < last; i++) {
    		array[index++] = array[i];
    	}
    	array[index] = null;// avoid loitering
    	last = index - 1;
    	size--;
    	return item;
    }
    
    public T peek() {
    	return array[first];
    }
    
    public T last() {
    	return array[last];
    }
    
    @Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (T item : this) {
			s.append(item + "").append(" ");
		}
		return s.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<T>{
        private int n = 0;
		@Override
		public boolean hasNext() {
			return n < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T item = array[(n + first) % array.length];
			n++;
			return item;
		}
	}
	
	public static void main(String[] args) {
		QueueDelete<Integer> queue = new QueueDelete<>(10);
		for(int i = 0; i < 10; i++) {
			queue.enqueue(i);
		}
		System.out.println(queue.last);
		System.out.println(queue);
		queue.deqeue(11);
		System.out.println(queue);
		System.out.println(queue.peek() + " " + queue.last());
	}
}
