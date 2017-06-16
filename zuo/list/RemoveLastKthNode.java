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
	}
	
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

}
