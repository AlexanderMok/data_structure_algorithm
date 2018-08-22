package alogorithm.sort;

import java.util.Arrays;
import java.util.Comparator;

public class Selection {
	private Selection() {
	}
	
	public static void main(String[] args) {
		Integer[] a = new Integer[] {3,5,7,23,9,5,67};
		Selection.sort(a);
		Arrays.stream(a).forEach(e -> System.out.print(e + " "));
		System.out.println(Selection.isSorted(a));
	}

	public static void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (less(a[j], a[min])) {
					min = j;
				}
			}
			exchange(a, i, min);
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
