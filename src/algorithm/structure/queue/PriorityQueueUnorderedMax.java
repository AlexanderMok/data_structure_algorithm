package algorithm.structure.queue;

/**
 * Perhaps the simplest priority queue implementation is based on our code for
 * pushdown stacks.
 * <p>
 * apply inner loop of selection sort to exchange the maximum item with the item
 * at the end and then delete that one
 * 
 * not practical for huge input
 * 
 * O(n) for delMax, O(1) for insert
 * 
 * @author Alex
 *
 * @param <E>
 */
public class PriorityQueueUnorderedMax<E extends Comparable<E>> {
	private E[] pq;
	private int n;

	public PriorityQueueUnorderedMax(int capacity) {
		pq = (E[]) new Comparable[capacity];
		n = 0;
	}

	public void insert(E key) {
		pq[++n] = key;
	}

	public E delMax() {
		int max = 0;
		for (int i = 1; i < n; i++) {
			if (less(pq[max], pq[i])) {
				max = i;
			}
		}
		exchange(max, n - 1);
		E key = pq[n - 1];
		pq[n - 1] = null;
		n--;
		return key;
	}

	private boolean less(E v, E w) {
		return v.compareTo(w) < 0;
	}

	private void exchange(int i, int j) {
		E key = pq[i];
		pq[i] = pq[j];
		pq[j] = key;
	}
}
