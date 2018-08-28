package alogorithm.sort;

import java.util.Comparator;

public abstract class Sort {
	protected static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
		return v.compareTo(w) < 0;
	}

	protected static <Key extends Comparable<Key>> boolean less(Comparator<Key> comparator, Key v, Key w) {
		return comparator.compare(v, w) < 0;
	}

	protected static void exchange(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	protected static <Key extends Comparable<Key>> boolean isSorted(Key[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	protected static <Key extends Comparable<Key>> boolean isSorted(Key[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++) {
			if (less(a[i], a[i - 1])) {
				return false;
			}
		}
		return true;
	}
}
