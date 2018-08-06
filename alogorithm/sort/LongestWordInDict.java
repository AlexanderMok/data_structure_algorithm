package algorithm.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed 
 * by deleting some characters of the given string.<p> 
 * If there are more than one possible results, return the longest word with the smallest lexicographical order.<p> 
 * If there is no possible result, return the empty string.<p>
 * <p>
 * <pre>
 * Example 1:
 * Input:s = "abpcplea", d = ["ale","apple","monkey","plea"]  Output: "apple"
 * Example 2:
 * Input:s = "abpcplea", d = ["a","b","c"]  Output: "a"
 * Note:
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 * <pre>
 * 
 * 做复杂题目的基本思路是将复杂的问题分解成几个独立或者相关的简单一点的问题。
 * 本题可以分解成两个小问题：
 * 如何给给定的字符串数组排序：按照字符串的长度和字母的顺序
 * 如何判断一个字符串 w 是 字符串 s 的子序列
 * 
 * Time O(nlogn)
 * @author Alex
 *
 */
public class LongestWordInDict {
    public static String solution(String str, List<String> dict) {
    	//defensive code
	    if(str == null || dict == null || str.length() == 0 || dict.size() == 0) return "";	
	    //sort
	    Collections.sort(dict, (str1,str2) -> {
	    	if (str1.length() == str2.length()) {
				return str1.compareTo(str2);
			} else {
                return str2.length() - str1.length();
			}
	    });
	    //isSub 字典中的字符串是否是输入参数word的子串
	    for (String word : dict) {
			if (isSub(str, word)) {
				return word;
			}
		}
	    return "";
    }
    
    
    /**
     * 判断是否是子串，双指针
     * @param word
     * @param str 
     * @return True if word is a substring of param str otherwise False
     */
    private static boolean isSub(String str, String word) {
		int i = 0, j = 0;
		while (i < str.length() && j < word.length()) {
			if (str.charAt(i) == word.charAt(j)) {
				i++;
				j++;
			} else {
				i++;
			}
		}
		return j == word.length();
	}



	public static void main(String[] args) {
		//Input:s = "abpcplea", d = ["ale","apple","monkey","plea"]  Output: "apple"
		List<String> dict = Arrays.asList("ale","apple","monkey","plea");
		String str = "abpcplea";
		String output = LongestWordInDict.solution(str, dict);
		System.out.println(output);
	}
}
