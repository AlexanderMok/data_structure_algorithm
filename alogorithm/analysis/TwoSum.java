package analysis;

import algorithm.exercise.Stopwatch;

public class TwoSum {
	public static void main(String[] args) {
		// read all int
		int[] array = { 1, 3, 5, 2 };
		Stopwatch time = new Stopwatch();
		int count = count(array);
		System.out.printf("Count %d. Elapsed %.5f seconds", count, time.elapsedTime());

	}

	/**
	 * return number of distinct pairs (i, j) such that a[i] + a[j] = 0
	 * 
	 * @param a
	 * @return
	 */
	public static int count(int[] a) {
		int n = a.length;
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (a[i] + a[j] == 0) {
					count++;
				}
			}
		}
		return count;
	}
}
