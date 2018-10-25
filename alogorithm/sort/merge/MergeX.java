package alogorithm.sort.merge;

import java.util.Arrays;

import alogorithm.sort.Sort;

/**
 * Improvement
 * <p>
 * 1. Use insertion sort for small subarrays. We can improve most recursive
 * algorithms by handling small cases differently. Switching to insertion sort
 * for small subarrays will improve the running time of a typical mergesort
 * implementation by 10 to 15 percent.
 * <p>
 * 2. Test whether array is already in order. We can reduce the running time to
 * be linear for arrays that are already in order by adding a test to skip call
 * to merge() if a[mid] is less than or equal to a[mid+1].
 * <P>
 * 
 * @author Alex
 *
 */
public class MergeX extends Sort {
	private static final int CUTOFF = 7; // cutoff to insertion sort

	private MergeX() {

	}

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 3, 5, 7, 23, 9, 5, 67 };
		MergeX.sort(a);
		Arrays.stream(a).forEach(e -> System.out.print(e + " "));
		System.out.println(MergeX.isSorted(a));
		Arrays.sort(a);
	}

	private static void sort(Comparable[] a) {
		Comparable[] aux = a.clone();
		sort(aux, a, 0, a.length - 1);

	}

	private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
		// use insertion sort for small subarrays
		if (hi <= lo + CUTOFF - 1) {
			insertionSort(dst, lo, hi);
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(dst, src, lo, mid);
		sort(dst, src, mid + 1, hi);
		// stop if already sorted
		if (less(src[mid], src[mid + 1])) {
			return;
		}

		merge(src, dst, lo, mid, hi);
	}

	private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
		// eliminate copy to the auxiliary array
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				dst[k] = src[j++];
			} else if (j > hi) {
				dst[k] = src[i++];
			} else if (less(src[j], src[i])) {
				dst[k] = src[j++];
			} else {
				dst[k] = src[i++];
			}
		}
	}

	private static void insertionSort(Comparable[] a, int lo, int hi) {
		for (int i = lo; i <= hi; i++) {
			for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
				exchange(a, j, j - 1);
			}
		}
	}
}
