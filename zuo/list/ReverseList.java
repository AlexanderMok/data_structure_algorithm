package zuo.list;

import java.util.Optional;

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
			//旧的头节点保存到pre
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
	
	/**
	 * Time O(n) Space O(1)
	 * @param head
	 * @param from
	 * @param to
	 * @return
	 */
	public static Node reversePartialSingleList(Node head, int from ,int to) {
		int len = 0;
		Node node1 = head;
		//要反转部分的前一个节点
		Node fromPre = null;
		//要反转部分的后一个节点
		Node toPos = null;
		//先判断，1<=from<=to<=N
		while(node1 != null) {
			len++;
			//如果from-1刚好等于len，说明from超出长度，意味着可能反转整个链表，fromPre指向头节点
			fromPre = (len == from - 1) ? node1 : fromPre;
			//如果to+1等于len，意味着，反转部分的后一个节点，等于链表长度
			toPos = (len == to + 1) ? node1 : toPos;
			node1 = node1.next;
		}
		//去除不合法的输入from,to
		if(from > to || from < 1 || to > len) {
			return head;
		}
		//如果fromPre为null，说明反转部分包含头节点
		node1 = (fromPre == null) ? head : fromPre.next;
		//反转部分的第一个节点
		Node node2 = node1.next;
		//指向要反转部分的最后一个节点的后一个节点
		node1.next = toPos;
		Node next = null;
		while(node2 != toPos) {
			next = node2.next;
			node2.next = node1;
			node1 = node2;
		}
		if(fromPre != null) {
			fromPre.next = node1;
			return head;
		}
		return node1;
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
