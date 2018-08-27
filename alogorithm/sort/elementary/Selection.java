package alogorithm.sort.elementary;

import java.util.Arrays;
import java.util.Comparator;

import algorithm.sort.Sort;

public class Selection extends Sort {
	private Selection() {
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 3, 5, 7, 23, 9, 5, 67 };
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
	
	
}
