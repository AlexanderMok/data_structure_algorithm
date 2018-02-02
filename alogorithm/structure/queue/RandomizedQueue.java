package algorithm.structure.queue;

public class RandomizedQueue<T> implements Iterable<T> {
	public RandomizedQueue(){}                 // construct an empty randomized queue

	public boolean isEmpty(){}                 // is the randomized queue empty?

	public int size(){}                        // return the number of items on the randomized queue

	public void enqueue(T item){}        // add the item

	public T dequeue(){}                   // remove and return a random item

	public T sample(){}                     // return a random item (but do not remove it)

	public Iterator<T> iterator(){}         // return an independent iterator over items in random order

	public static void main(String[] args) {}   // unit testing (required)
}
