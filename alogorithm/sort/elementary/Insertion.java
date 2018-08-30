package alogorithm.sort.elementary;

import java.util.Arrays;

import algorithm.sort.Sort;

public class Insertion extends Sort {

	private Insertion() {
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 3, 5, 7, 23, 9, 5, 67 };
		Insertion.sort2(a);
		Arrays.stream(a).forEach(e -> System.out.print(e + " "));
		System.out.println(Insertion.isSorted(a));
	}

	public static void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
				exchange(a, j, j - 1);
			}
		}
	}

	public static void sort2(Comparable[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) {
			Comparable v = a[i];
			int j = i;
			while (less(v, a[j - 1])) {
				a[j] = a[j - 1];
				j--;
			}
			a[j] = v;
		}
	}
}
