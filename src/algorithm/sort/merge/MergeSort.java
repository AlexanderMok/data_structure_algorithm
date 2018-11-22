package algorithm.sort.merge;

import java.util.Arrays;

import algorithm.sort.Sort;

/**
 * Top-down mergesort Abstract in-place merge.
 * 
 * @author Alex
 *
 */
public class MergeSort extends Sort {
	private MergeSort() {
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 3, 5, 7, 23, 9, 5, 67 };
		MergeSort.sort(a);
		Arrays.stream(a).forEach(e -> System.out.print(e + " "));
		System.out.println(MergeSort.isSorted(a));
		Arrays.sort(a);
	}

	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		// precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);

		// copy to aux
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		// merge back to a[]
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) { // [lo, mid] side
				a[k] = aux[i++];
			} else if (less(aux[j], aux[i])) { // [mid + 1, hi] side
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
		}
	}
}
