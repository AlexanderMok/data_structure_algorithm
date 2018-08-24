package alogorithm.sort.elementary;

import java.util.Comparator;


public class Insertion {

	public static void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
				exchange(a, j, j - 1);
			}
		}
	}
	
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static boolean less(Comparator comparator, Object v, Object w) {
		return comparator.compare(v, w) < 0;
	}

	private static void exchange(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++) {
			if (less(a[i], a[i - 1])) {
				return false;
			}
		}
		return true;
	}
}
