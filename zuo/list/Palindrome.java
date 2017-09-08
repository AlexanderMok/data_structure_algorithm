package zuo.list;

import java.util.Stack;

public class Palindrome {
    
	/**
	 * Time O(n) 
	 * Space O(n)
	 * @param head
	 * @return
	 */
	public static boolean isPalindrome1(Node head) {
		Stack<Node> stack = new Stack<Node>();
		//We do not want to modify head and cause side effect
		//So we copy head
		Node cur = head;
		while(cur != null){
			stack.push(cur);
			cur = cur.next;
		}
		System.out.println(head);
		//We do not want to modify head and cause side effect
		cur = head;
		while(cur != null) {
			if (cur.value != stack.pop().value) {
				return false;
			}
			cur = cur.next;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Node head = new Node(2);
        Node node2 = new Node(4);
        head.setNext(node2);
        Node node3 = new Node(6);
        node2.setNext(node3);
        Node node4 = new Node(4);
        node3.setNext(node4);
        Node node5 = new Node(2);
        node4.setNext(node5);
        boolean i = isPalindrome1(head);
        System.out.println(i);
        
	}

}
