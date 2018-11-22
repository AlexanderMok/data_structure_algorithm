package algorithm.sort.elementary;

import java.util.Arrays;

import algorithm.sort.Sort;

/**
 * The {@code InsertionX} class provides static methods for sorting an array
 * using an optimized version of insertion sort (with half exchanges and a
 * sentinel).
 * 
 * @author Alex
 *
 */
public class InsertionX extends Sort {

	private InsertionX() {
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 3, 5, 7, 23, 9, 5, 67 };
		InsertionX.sort(a);
		Arrays.stream(a).forEach(e -> System.out.print(e + " "));
		System.out.println(InsertionX.isSorted(a));
	}

	public static void sort(Comparable[] a) {
		int n = a.length;

		int exchanges = 0;
		//put smallest element in position(left most) to server as sentinel
		for (int i = n - 1; i > 0; i--) {
			if (less(a[i], a[i - 1])) {
				exchange(a, i, i - 1);
				exchanges++;
			}
		}
		if (exchanges == 0) {
			return;
		}

		for (int i = 2; i < n; i++) {
			Comparable v = a[i];
			int j = i;
			//shift
			while (less(v, a[j - 1])) {
				a[j] = a[j - 1];
				j--;
			}
			//insert
			a[j] = v;
		}
	}

}
