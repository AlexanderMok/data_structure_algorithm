package zuo.list;

import java.util.Stack;

public class ReverseList {
    
	/**
	 * Time O(n) Space O(1)
	 * Method 1: In place reverse
	 * two pointers
	 * 两个指针
	 * 1->2->3->4->5
	 * @param head
	 * @return
	 */
	public static Node reverseSingleList0(Node head){
		Node pre = null;
		//指向需要反转的节点
		Node next = null;
		while(head != null){
			next = head.next;
			//将原来指向下一节点的指针，指向辅助指针，断开其与连接的节点 Node [value=2, next=null]
			//辅助指针记录着每次断开的节点
			head.next = pre;
			//更新结果
			pre = head;
			//更新头节点，头节点向后移动1位
			head = next;
		}
		return pre;
	}
	
	/**
	 * Time O(n) Space O(1)
	 * @param head
	 * @return
	 */
	public static DoubleNode reverseDoubleList(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			head.last = next;
			pre = head;
			head = next;
		}
		return pre;
	} 
	
	public static void main(String[] args) {
		Node head = new Node(2);
        Node node2 = new Node(4);
        head.setNext(node2);
        Node node3 = new Node(6);
        node2.setNext(node3);
        Node node4 = new Node(8);
        node3.setNext(node4);
        Node node5 = new Node(9);
        node4.setNext(node5);
        Node reversedList = reverseSingleList0(head);
        System.out.println(reversedList);
	}

}
