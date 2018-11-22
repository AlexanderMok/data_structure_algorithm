package algorithm.analysis;

import java.util.Arrays;

import algorithm.exercise.Stopwatch;

/**
 * solves the 3-sum problem in time proportional to N^2 log N time
 * @author Alex
 *
 */
public class ThreeSumFast {

	public static void main(String[] args) {
		int[] array = { 1, 3, 5, 2, -3, -1 };
		Stopwatch time = new Stopwatch();
		int count = count(array);
		System.out.printf("Count %d. Elapsed %.5f seconds", count, time.elapsedTime());
	}

	private static int count(int[] array) {
		int n = array.length;
		Arrays.sort(array);
		if (containDuplicate(array)) {
			throw new IllegalArgumentException("Arrays contains duplicates");
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
                int k = Arrays.binarySearch(array, -(array[i] + array[j]));
                if (k > j) {
                	count++;
                }
			}
		}
		return count;
	}

	private static boolean containDuplicate(int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i] == array[i - 1]) {
				return true;
			}
		}
		return false;
	}

}
