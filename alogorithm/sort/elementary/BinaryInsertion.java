package alogorithm.sort.elementary;

import java.util.Arrays;

import algorithm.sort.Sort;

/**
 * apply binary search to find the smallest position to insert with regard the
 * to-be-inserted element
 * 
 * @author Alex
 *
 */
public class BinaryInsertion extends Sort {
	private BinaryInsertion() {
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 3, 5, 7, 23, 9, 5, 67 };
		BinaryInsertion.sort(a);
		Arrays.stream(a).forEach(e -> System.out.print(e + " "));
		System.out.println(BinaryInsertion.isSorted(a));
	}

	public static void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) {
			Comparable v = a[i];
			int lo = 0;
			int hi = i;
			// binary search to find index j at which to insert a[i]
			// find to lower bound position to insert
			while (lo < hi) {
				int mid = lo + (hi - lo) / 2;
				if (less(v, a[mid])) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
            
			// shift a[j],...a[i-1] to right
			for (int j = i; j > lo; --j) {
				a[j] = a[j - 1];
			}
			// insert a[i] at index j, the lower bound
			a[lo] = v;
		}
	}
}
