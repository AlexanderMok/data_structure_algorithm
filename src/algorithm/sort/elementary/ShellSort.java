package algorithm.sort.elementary;

import java.util.Arrays;

import algorithm.sort.Sort;

public class ShellSort extends Sort {
	private ShellSort() {
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 3, 5, 7, 23, 9, 5, 67 };
		ShellSort.sort(a);
		Arrays.stream(a).forEach(e -> System.out.print(e + " "));
		System.out.println(ShellSort.isSorted(a));
	}

	public static void sort(Comparable[] a) {
		int n = a.length;
		int h = 1;
		// 3x+1 increment sequence: 1,4,13,40
		while (h < n / 3) {
			h = 3 * h + 1;
		}

		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					exchange(a, j, j - h);
				}
			}
			h /= 3;
		}
	}
}
