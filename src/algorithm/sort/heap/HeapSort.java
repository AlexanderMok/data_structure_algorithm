package algorithm.sort.heap;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 3, 5, 7, 23, 9, 5, 67 };
		HeapSort.sort(a);
		Arrays.stream(a).forEach(e -> System.out.print(e + " "));
	}

	public static void sort(Comparable[] a) {
		// Construct a heap from right to left
		int n = a.length;
		for (int k = n / 2; k >= 1; k--) {
			sink(a, k, n);
		}
		// sortdown take the largest to the right
		while (n > 1) {
			exchange(a, 1, n--);
			sink(a, 1, n);
		}
	}

	private static void sink(Comparable[] a, int k, int n) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && less(a, j, j + 1)) {
				j++;
			}
			if (!less(a, k, j)) {
				break;
			}
			exchange(a, k, j);
			k = j;
		}
	}

	private static boolean less(Comparable[] a, int i, int j) {
		return a[i - 1].compareTo(a[j - 1]) < 0;
	}

	private static void exchange(Comparable[] a, int i, int j) {
		Comparable v = a[i - 1];
		a[i - 1] = a[j - 1];
		a[j - 1] = v;
	}
}
