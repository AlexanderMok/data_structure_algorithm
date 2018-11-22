package algorithm.analysis;

import java.util.Arrays;

import algorithm.exercise.Stopwatch;

/**
 * Sort and binarysearch. 
 * O(NlogN)
 * @author Alex
 *
 */
public class TwoSumFast {
	public static void main(String[] args) {
		// read all int
		int[] array = { 1, 3, 5, 2 ,-3, -1};
		Stopwatch time = new Stopwatch();
		int count = count(array);
		System.out.printf("Count %d. Elapsed %.2f seconds", count, time.elapsedTime());
	}

	private static int count(int[] array) {
		int n = array.length;
		Arrays.sort(array);
		if(containsDuplicates(array)) {
			throw new IllegalArgumentException("Arrays contains duplicates");
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			int j = Arrays.binarySearch(array, -array[i]);
			if (j > i) {
				count++;
			}
		}
		return count;
	}

	private static boolean containsDuplicates(int[] array) {
		// array is sorted
		for (int i=1; i<array.length; i++) {
			if (array[i] == array[i-1]) {
				return true;
			}
		}
		return false;
	}
}
