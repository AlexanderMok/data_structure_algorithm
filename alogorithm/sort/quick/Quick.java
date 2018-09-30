package alogorithm.sort.quick;

import algorithm.sort.Sort;

public class Quick extends Sort {
	private Quick() {
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 3, 5, 7, 23, 9, 5, 67 };
		Quick.sort(a);
		//Arrays.stream(a).forEach(e -> System.out.print(e + " "));
		System.out.println(Quick.isSorted(a));
	}

	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}
    /**
     * quick sort the subarray from a[lo] to a[hi]
     * @param a
     * @param lo
     * @param hi
     */
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int j = partition(a, lo, hi);
		System.out.print(j + " ");
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
    
	/**
	 * partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
	 * and return index j
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi + 1;
		while (true) {
			// scan from left to right
			while (less(a[++i], a[lo])) {
				if (i == hi) {
					break;
				}
			}
			// scan from right to left
			while (less(a[lo], a[--j])) {
				if (j == lo) {
					break;
				}
			}

			// check if pointers cross
			if (i >= j) {
				break;
			}
			// swap
			exchange(a, i, j);
		}
		// a[lo] act as sentinel after the loop, the subarray now [lo..<=lo...ji...>=lo..hi]
		// then put partitioning item v at a[j]
		exchange(a, lo, j);
		
		return j;
	}
}
