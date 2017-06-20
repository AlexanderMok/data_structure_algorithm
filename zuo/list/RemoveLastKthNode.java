package zuo.list;

public class RemoveLastKthNode {

	public static void main(String[] args) {
		Node node1 = new Node(2);
        Node node2 = new Node(4);
        node1.setNext(node2);
        Node node3 = new Node(6);
        node2.setNext(node3);
        Node node4 = new Node(8);
        node3.setNext(node4);
        
        System.out.println(node1);
        
        Node node = removeLastKthNodeSingleList(node1, 3);
        System.out.println(node);
        
        DoubleNode dn1 = new DoubleNode(2);
        dn1.setLast(null);
        DoubleNode dn2 = new DoubleNode(4);
        dn1.setNext(dn2);
        dn2.setLast(dn1);
        DoubleNode dn3 = new DoubleNode(6);
        dn2.setNext(dn3);
        dn3.setLast(dn2);
        DoubleNode dn4 = new DoubleNode(8);
        dn3.setNext(dn4);
        dn4.setLast(dn3);
        dn4.setNext(null);
        DoubleNode doubleNode = removeLastkthNodeDoubleList(dn1, 3);
	}
	
	/**
	 * 让链表从头开始早到尾，每移动一步，让lastkth值减1
	 * 这样可以起到类似双指针的效果
	 * @param singleList
	 * @param lastKth
	 * @return
	 */
	public static Node removeLastKthNodeSingleList(Node singleList, int lastKth){
		if(singleList == null || lastKth < 1){
			return singleList;
		}
		Node cur = singleList;
		while(cur != null) {
			//每移动一次链表指针，lastKth--
			lastKth--;
			cur = cur.next;
		}
		if (lastKth == 0) {
			//lastKth is head of the list,so return the second node of the list 
			//which equals to delete the head node
			singleList = singleList.next;
		}
		if (lastKth < 0) {
			//find the node where lastkth = 0.
			//This node is the last node of the to-be-removed node  
			cur = singleList;
			while (++lastKth != 0) {
				cur = cur.next;
			}
			//cur.next points to the to-be-removed node
			//now point to the next node of the to-be-removed node
			cur.next = cur.next.next;
		}
		//lastkth > 0 means lastKth does not exist
		return singleList;
	}
	
	public static DoubleNode removeLastkthNodeDoubleList(DoubleNode doubleList, int lastKth) {
		if(doubleList == null || lastKth < 1){
			return doubleList;
		}
		DoubleNode cur = doubleList;
		while(cur!=null) {
			lastKth--;
			cur = cur.next;
		}
		if(lastKth == 0){
			doubleList = doubleList.next;
			doubleList.last = null;
		}
		if (lastKth < 0 ) {
			cur = doubleList;
			while(++lastKth != 0){
				cur = cur.next;
			}
			//Deletion of a double list
			DoubleNode newNext = cur.next.next;
			cur.next = newNext;
			
			if(newNext != null){
				newNext.last = cur;
			}
		}
		return doubleList;
	}

}
