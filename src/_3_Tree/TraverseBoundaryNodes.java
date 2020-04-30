package _3_Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import ent.Tree;

public class TraverseBoundaryNodes {

	public static Tree getInitialTree() {
		Tree root = new Tree(7);
		root.left = new Tree(5);
		root.left.right = new Tree(6);
		root.left.right.right = new Tree(0); // tmp node.
		root.left.left = new Tree(3);
		root.left.left.left = new Tree(1);
		root.left.left.right = new Tree(4);
		
		root.right = new Tree(12);
		root.right.left = new Tree(9);
		root.right.left.left = new Tree(8);
		root.right.left.right = new Tree(10);
		root.right.right = new Tree(15);
		root.right.right.left = new Tree(13);
		root.right.right.right = new Tree(17);
		return root;
	}
	
	public static void traverse(Tree leaf) {
		
		if(leaf == null)
			return;

		printLeftBoundaryTopDown(leaf);
		printLeaves(leaf);
		printRightBoundaryBottomUp(leaf);
	}

	private static void printLeaves(Tree leaf) {
		Stack<Tree> stack = new Stack<>();
		stack.push(leaf);
		while(!stack.isEmpty()) {

			Tree tmp = stack.pop();
			if(tmp.left == null && tmp.right == null)
				System.out.print(tmp.val + " ");
			
			if(tmp.right != null)
				stack.push(tmp.right);
			
			if(tmp.left != null)
				stack.push(tmp.left);
		}
	}

	private static void printLeftBoundaryTopDown(Tree leaf) {
		Queue<Tree> queue = new LinkedList<Tree>();
		queue.add(leaf);
		while(!queue.isEmpty()) {
			Tree tmp = queue.poll();
			if(tmp.left != null)
				System.out.print(tmp.val + " ");
			
			if(tmp.left != null)
				queue.add(tmp.left);
		}
	}

	private static void printRightBoundaryBottomUp(Tree leaf) {
		Stack<Tree> stack = new Stack<Tree>();
		Tree tmp = leaf.right;
		while(tmp.right != null) {
			stack.push(tmp);
			tmp=tmp.right;
		}
		
		while(!stack.isEmpty())
			System.out.print(stack.pop().val + " ");
	}

	public static void main(String[] args) {
		Tree root = getInitialTree();
		traverse(root);
	}

}
