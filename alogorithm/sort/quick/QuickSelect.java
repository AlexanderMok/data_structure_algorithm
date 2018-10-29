package alogorithm.sort.quick;

import java.util.Arrays;

import algorithm.sort.Sort;
import algorithm.sort.elmentary.Insertion;

/**
 * Quick sort partition application
 * 
 * Given an array of n items, find the kth smallest item.
 * 
 * min(k=0), max(k=n-1), median(k=n/2)
 * 
 * applications: Order statistics Find the "top k"
 * 
 * @author Alex
 *
 */
public class QuickSelect extends Sort {

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 3, 5, 7, 23, 9, 5, 67 };
		Comparable r = select(a, 3);
		System.out.println(r);
		Insertion.sort(a);
		Arrays.stream(a).forEach(e -> System.out.print(e + " "));
	}

	public static Comparable select(Comparable[] a, int k) {
        int lo = 0, hi = a.length - 1;
        while(hi > lo) {
        	int j = partition(a, lo, hi);
        	if (j < k) {
        		lo = j + 1;
        	} else if (j > k) {
        		hi = j - 1;
        	} else {
        		return a[k];
        	}
        }
        return a[k];
	}

	public static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi + 1;
		while (true) {
			while (less(a[++i], a[lo])) {
				if (i == hi) {
					break;
				}
			}

			while (less(a[lo], a[--j])) {
				if (j == lo) {
					break;
				}
			}

			if (i >= j) {
				break;
			}
			exchange(a, i, j);
		}
		exchange(a, lo, j);
		return j;
	}

}
