package alogorithm.sort.merge;

import java.util.Arrays;

import algorithm.sort.Sort;

public class MergeBottomUp extends Sort {

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 3, 5, 7, 23, 9, 5, 67 };
		MergeBottomUp.sort(a);
		Arrays.stream(a).forEach(e -> System.out.print(e + " "));
		System.out.println(MergeBottomUp.isSorted(a));
	}

	private static void sort(Comparable[] a) {
		int n = a.length;
		Comparable[] aux = new Comparable[n];
		// start to merge from 1, 2, 4, 6...
		for (int len = 1; len < n; len *= 2) {
			for (int lo = 0; lo < n - len; lo += len + len) {
				int mid = lo + len - 1;
				int hi = Math.min(lo + len + len - 1, n - 1);
				merge(a, aux, lo, mid, hi);
			}
		}
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		// copy to aux
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (less(aux[j], aux[i])) {
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
		}
	}

}
