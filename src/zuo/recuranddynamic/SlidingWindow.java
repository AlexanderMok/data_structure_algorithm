package zuo.recuranddynamic;

import java.util.HashSet;
import java.util.Set;

public class SlidingWindow {
	public static void main(String[] args) {
		String string = "";
		LengthOfLongestSubstring instance = new LengthOfLongestSubstring();
		int l = instance.lengthOfLongestSubstring(string);
	}
}

class LengthOfLongestSubstring {

	/**
	 * Brutal solution O(n^3)
	 * 
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring(String s) {
		int n = s.length();
		// store length of substring of each iteration
		int length = 0;

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				if (unique(s, i, j)) {
					length = Math.max(length, j - i);
				}
			}
		}
		return length;
	}

	private boolean unique(String s, int begin, int end) {
		Set<Character> set = new HashSet<>(end - begin + 1);
		for (int i = begin; i < end; i++) {
			char character = s.charAt(i);
			if (set.contains(character)) {
				return false;
			}
			set.add(character);
		}
		return true;
	}

	/**
	 * 使用HashSet作为一个滑动窗口，检查一个字符是否已经存在于现有的子字符中。 滑动窗口经常作为一个抽象的概念来处理数组/字符串问题。
	 * 窗口代表着一组数据/字符串元素，通过开头和结尾的索引来定义窗口。
	 * 
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstringSlidingWindow(String s) {
		int n = s.length();
		Set<Character> window = new HashSet<>();
		int length = 0, i = 0, j = 0;
		// keep window [i, j] within range 
		while (i < n && j < n) {
			
		} 
		return length;
	}
}
