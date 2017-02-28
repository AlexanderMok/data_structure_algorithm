package zuo.stackandqueue;

import java.util.Stack;

public class DoubleStackQueue {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;
	
    public DoubleStackQueue(Stack<Integer> stackPush, Stack<Integer> stackPop) {
		this.stackPush = stackPush;
		this.stackPop = stackPop;
	}
    
    public void add(int item) {
    	stackPush.push(item);
    }
    
    public int poll() {
    	if (stackPop.isEmpty() && stackPush.isEmpty()) {
    		throw new RuntimeException("Queue is empty.");
    	} else if (stackPop.isEmpty()) {
    		while (!stackPush.isEmpty()) {
    			stackPop.push(stackPush.pop());
    		}
    	}
    	return stackPop.pop();
    }
    
    public int peek() {
    	if (stackPop.isEmpty() && stackPush.isEmpty()) {
    		throw new RuntimeException("Queue is empty.");
    	} else if (stackPop.isEmpty()) {
    		while (!stackPush.isEmpty()) {
    			stackPop.push(stackPush.pop());
    		}
    	}
    	return stackPop.peek();
    }
}
