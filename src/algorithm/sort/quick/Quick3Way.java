package algorithm.sort.quick;

import java.util.Arrays;

import algorithm.sort.Sort;

/**
 * deal with duplicate values
 * quicksort with 3-way partitioning is entropy-optimal
 * @author Alex
 *
 */
public class Quick3Way extends Sort {

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 3, 5, 7, 5, 9, 5, 67 };
		Quick3Way.sort(a);
		Arrays.stream(a).forEach(e -> System.out.print(e + " "));
	}

	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) {
            return;
		}
		
		int lt = lo, i = lo + 1, gt = hi;
		Comparable v = a[lo];
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0) {
				exchange(a, lt++, i++);
			} else if (cmp > 0 ) {
				exchange(a, i, gt--);
			} else {//equal
				i++;
			}
		}
		// Now a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]
		sort(a, lo, lt -1);
		sort(a, gt + 1, hi);
	}

}
