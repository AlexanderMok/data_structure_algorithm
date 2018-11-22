package zuo.stackandqueue;

import java.util.Stack;

/**
 * A stack that has getMin() method. push(),pop(),getMin() take O(1) time
 * 
 * @author Alex
 *
 */
public class MinStack {
	private Stack<Integer> stackData;
	private Stack<Integer> stackMin;

	public MinStack() {
		this.stackData = new Stack<Integer>();
		this.stackMin = new Stack<Integer>();
	}

	public void push(int item) {
		if (stackMin.isEmpty()) {
			stackMin.push(item);
		} else if (item <= this.getMin()) {
			stackMin.push(item);
		}
		stackData.push(item);
	}
	
	public void  push2(int item) {
		if (stackMin.isEmpty()) {
			stackMin.push(item);
		} else if (item < this.getMin()) {
			stackMin.push(item);
		} else {
			int minItem = stackMin.peek();
			stackMin.push(minItem);
		}
		stackData.push(item);
	}

	public int pop() {
		if (stackData.isEmpty()) {
			throw new IndexOutOfBoundsException("Stack is empty.");
		}
		/**
		 * 要令到栈在pop完后，再运行getMin，返回的是当前栈的正确最小值，stackMin存储的，正是每一次压栈的最小值
		 * 且value必定>=getMin()
		 * 
		 * 大于时，stackMin没有压栈，最小值没有更换，所以没有值，只需 pop stackData
		 * 等于时，stackMin压过新的最小值，故需要pop stackMin
		 */
		int value = stackData.pop();
		if (value == this.getMin()) {
			stackMin.pop();
		}
		return value;
	}
	
	public int pop2(){
		if (stackData.isEmpty()) {
			throw new IndexOutOfBoundsException("Stack is empty.");
		}
		stackMin.pop();
		return stackData.pop();
	}
	
	public int getMin() {
		if (stackMin.isEmpty()) {
			throw new IndexOutOfBoundsException("Stack is empty.");
		}
		return stackMin.peek();
	}
	
	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(3);
		minStack.push(1);
		minStack.push(8);
		minStack.push(6);
		System.out.println(minStack.getMin());
	}

}
