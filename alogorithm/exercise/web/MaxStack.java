package algorithm.exercise.web;


import java.util.NoSuchElementException;

import algorithm.structure.stack.Stack;

/**
 * Stack + max.<p> 
 * Create a data structure that efficiently supports the stack
 * operations (pop and push) and also return the maximum element. Assume the
 * elements are integers or reals so that you can compare them.<p> 
 * 
 * Hint: use two stacks, one to store all of the elements and a second stack to
 * store the maximums.
 * 
 * @author Alex
 *
 */
public class MaxStack {
    public Stack<Integer> stackData;
    public Stack<Integer> stackMax;
    
    public MaxStack() {
    	stackData = new Stack<>();
    	stackMax = new Stack<>();
	}
    
    public void push(Integer item) {
    	if(stackMax.isEmpty()) {
    		stackMax.push(item);
    	} else if (item >= getMax()) {
    		stackMax.push(item);
    	}
    	stackData.push(item);
    }
    
    public Integer pop() {
    	if(stackData.isEmpty()) {
    		throw new NoSuchElementException("Stack underflow");
    	}
    	Integer item = stackData.pop();
    	if(item == getMax()) {
    		stackMax.pop();
    	}
    	return item;
    }
    
    public Integer getMax() {
    	if(stackMax.isEmpty()) {
    		throw new NoSuchElementException("Stack underflow");
    	}
    	return stackMax.peek();
    }
    
    public static void main(String[] args) {
    	MaxStack maxStack = new MaxStack();
    	maxStack.push(3);
    	maxStack.push(1);
    	maxStack.push(8);
    	maxStack.push(6);
		System.out.println(maxStack.getMax());
		System.out.println(maxStack.stackMax);
		System.out.println(maxStack.stackData);
	}
}
