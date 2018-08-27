package alogorithm.sort.elementary;

import algorithm.sort.Sort;

public class ShellSort extends Sort {
	private ShellSort() {
	}

	public static void main(String[] args) {

	}

	public static void sort(Comparable[] a) {
		int n = a.length;
		int h = 1;
		// 3x+1 increment sequence: 1,4,13,40
		while (h < n / 3) {
			h = 3 * h + 1;
		}

		while (h >= 1) {
			for (int i = 0; i < n; i++) {
				for (int j = i; j >= h && less(j, j - h); j -= h) {
					exchange(a, j, j - h);
				}
			}
			h /= 3;
		}
	}
}
