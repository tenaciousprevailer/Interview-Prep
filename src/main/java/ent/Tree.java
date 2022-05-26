package ent;

public class Tree {
	public Tree(int val){
		this.val = val;
		height=1;
	}
	public int height;
	public int val;
	public Tree left;
	public Tree right;
//	@Override
//	public String toString() {
//		//return "Tree [height=" + height + ", val=" + val + "]";
//	}
	
	
	@Override
	public String toString() {
		return "Tree{" +
				"val=" + val +
				", left=" + left +
				", right=" + right +
				'}';
	}
}
