package alogorithm.sort.merge;

/**
 * the sequence of subarray sizes after each merge 
 * @author Alex
 *
 */
public class MergeSizes {

	public static void main(String[] args) {
		int n = 20;
        String[] a = new String[n];
        MergeSizes.topDownMergeSort(a);
        System.out.println();
        MergeSizes.bottomUpMergeSort(a);
        System.out.println();
	}
	
	public static void bottomUpMergeSort(Comparable[] a) {
		int n = a.length;
		for (int len = 1; len < n; len *= 2) {
			for (int lo = 0; lo < n - len; lo += len + len) {
				int mid = lo + len -1;
				int hi = Math.min(lo + len -1 + len, n -1);
				merge(a, lo, mid, hi);
			}
		}
	}
	
	public static void topDownMergeSort(Comparable[] a) {
		topDownMergesort(a, 0, a.length - 1);
	}

	private static void topDownMergesort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
        	return;
        }
        int mid = lo + (hi - lo) / 2;
        topDownMergesort(a, lo, mid);
        topDownMergesort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
	}

	private static void merge(Comparable[] a, int lo, int mid, int hi) {
        System.out.print(hi - lo + 1);
        System.out.print(" ");		
	}

}
