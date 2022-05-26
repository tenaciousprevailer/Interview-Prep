package ent;

public class Node{
	public Node(int val){
		this.val = val;
	}
	public int val;
	public Node next;
	@Override
	public String toString() {
		return val + " -> " + next;
	}
}
