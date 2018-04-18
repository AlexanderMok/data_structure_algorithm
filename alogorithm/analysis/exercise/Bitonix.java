package analysis.exercise;

import java.util.Arrays;
import java.util.Random;

/**
 * An array is bitonic if it is comprised of an increasing sequence of integers
 * followed immediately by a decreasing sequence of integers. Write a program
 * that, given a bitonic array of N distinct int values, determines whether a
 * given integer is in the array. Your program should use ~ 3 lg N compares in
 * the worst case see
 * <a href="https://algs4.cs.princeton.edu/14analysis/">Creative problem</a>
 * 
 * @author Alex
 *
 */
public class Bitonix {

	/**
	 * create a bitonic array of size n
	 * 
	 * @param n
	 * @return
	 */
	public static int[] bitonic(int n) {
		int mid = new Random().nextInt(n);
		int[] a = new int[n];
		for (int i = 1; i < mid; i++) {
			a[i] = a[i - 1] + 1 + new Random().nextInt(9);
		}

		if (mid > 0) {
			a[mid] = a[mid - 1] + new Random().nextInt(10) - 5;
		}

		for (int i = mid + 1; i < n; i++) {
			a[i] = a[i - 1] - 1 - new Random().nextInt(9);
		}

		Arrays.stream(a).forEach(e -> System.out.print(e + " "));
		return a;
	}

	public static int max(int[] a, int lo, int hi) {
		if (hi == lo) {
			return hi;
		}
		int mid = lo + (hi - lo) / 2;
		if (a[mid] > a[mid + 1]) {
			return max(a, lo, mid);
		} else if (a[mid] < a[mid + 1]) {
			return max(a, mid + 1, hi);
		} else {
			return mid;
		}
	}

	public static boolean isInBitonixArray(int key, int[] bitonix) {
		int n = bitonix.length - 1;
		int max = max(bitonix, 0, n);
		if ((Arrays.binarySearch(bitonix, 0, max, key) >= 0)) {
			return true;
		} else if (Arrays.binarySearch(bitonix, max + 1, n, key) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		int n = 20;
		int[] a = bitonic(n);
		System.out.println("max = " + a[max(a, 0, n - 1)]);
		int key = 13;
		boolean isIn = isInBitonixArray(key, a);
		System.out.println(isIn);

	}

}
