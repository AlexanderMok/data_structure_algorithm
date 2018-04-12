package analysis;

import algorithm.exercise.Stopwatch;

/**
 * counting and printing the number of triples in an array of integers that sum
 * to 0 (ignoring integer overflow).
 * <p>
 * This implementation uses a triply nested loop and takes proportional to n^3,
 * where n is the number of integers.
 * 
 * The brute-force 3-sum algorithm uses ~ N^3 / 2 array accesses to compute the
 * number of triples that sum to 0 among N numbers.
 * 
 * @author Alex
 *
 */
public class ThreeSum {
	private ThreeSum() {
	}

	/**
	 * Returns the number of triples (i, j, k) with {@code i < j < k} such that
	 * {@code a[i] + a[j] + a[k] == 0}.
	 * 
	 * 
	 * @param array
	 * @return
	 */
	public static int count(int[] array) {
		int n = array.length;
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				for (int k = j + 1; k < n; k++) {
					if (array[i] + array[j] + array[k] == 0) {
						count++;
					}
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 5, 2 ,-3, -1};
		Stopwatch timer = new Stopwatch();
		int count = count(a);
		System.out.printf("Count %d. Elapsed time = %.5f", count, timer.elapsedTime());
	}

}
