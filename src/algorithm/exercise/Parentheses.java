package algorithm.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import algorithm.structure.stack.Stack;

/**
 * Reads in sequence of left and right parentheses, braces, and brackets from standard input 
 * and uses a stack to determine whether the sequence is properly balanced.<p> 
 * For example, your program should print true for [()]{}{[()()]()} and false for [(])
 * 
 * 参考中缀表达式方式,有点像回文
 * @author Alex
 *
 */
public class Parentheses {
	private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';
    private static final char LEFT_BRACE     = '{';
    private static final char RIGHT_BRACE    = '}';
    private static final char LEFT_BRACKET   = '[';
    private static final char RIGHT_BRACKET  = ']';
    
    public static boolean isBalanced(String s) {
    	char[] chars = s.toCharArray();
    	Stack<Character> stack = new Stack<>();
    	for (int i = 0; i < chars.length; i++) {
			if(chars[i] == LEFT_PAREN) stack.push(LEFT_PAREN);
			if(chars[i] == LEFT_BRACE) stack.push(LEFT_BRACE);
			if(chars[i] == LEFT_BRACKET) stack.push(LEFT_BRACKET);
			
			switch (chars[i]) {
			case RIGHT_PAREN:
				if(stack.isEmpty()) return false;
				if(stack.pop() != LEFT_PAREN) return false;
				break;
			case RIGHT_BRACE:
				if(stack.isEmpty()) return false;
				if(stack.pop() != LEFT_BRACE) return false;
				break;
			case RIGHT_BRACKET:
				if(stack.isEmpty()) return false;
				if(stack.pop() != LEFT_BRACKET) return false;
				break;
			}
		}
    	return stack.isEmpty();
    }
    
    public static void main(String[] args) throws IOException {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean isBalanced = isBalanced(reader.readLine().trim());
		System.out.println(isBalanced);
	}
}
