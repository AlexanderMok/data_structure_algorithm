package alogorithm.sort;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.<p>
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.<p>
 * Note: The result may be very large, so you need to return a string instead of an integer.<p>
 * 
 * 比较数字，降序排列，组合成数字  --> 转换成字符串比较 --> 两个字符串组合成的数字，谁大<p>
 * 给定两个代表数字的字符串 A 和 B ， 如果 AB > BA ， 那么 我们可以认定 A > B<p>
 * sort()来说，compareTo()返回negative则ascending，返回positive则descending
 * <pre>
 * 解题思路
 * 1.将整数数组转换成字符串数组  convert int array to string array
 * 2.根据假设推论一降序排列字符串数组  sort the string array according to AB > BA -> A>B
 * 3.将排序后的数组拼接成新的字符串并返回  concat elements to construct a new string 
 * </pre>
 * @author Alex
 *
 */
public class GetLargestNum {
    
	public static String solution(int[] num){
		return new BigInteger(Arrays.stream(num)
		  .mapToObj(i -> String.valueOf(i))
		  .sorted((s1, s2) -> s2.concat(s1).compareTo(s1.concat(s2)))
		  .collect(Collectors.joining()).toString()).toString();
	}
	
	public static String solution2(int[] num){
		String[] numStr = new String[num.length];
		for (int i = 0; i < num.length; i++) {
			numStr[i] = String.valueOf(num[i]);
		}
		Arrays.sort(numStr, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s2.concat(s1).compareTo(s1.concat(s2));
			}
		});
		StringBuilder stringBuilder = new StringBuilder();
		for (String string : numStr) {
			stringBuilder.append(string);
		}
		return new BigInteger(stringBuilder.toString()).toString();
	}
	
	public static void main(String[] args) {
        int[] num = {3, 30, 34, 5, 9};
        String solution = solution(num);
        System.out.println(solution);
        String solution2 = solution2(num);
        System.out.println(solution2);
	}

}
