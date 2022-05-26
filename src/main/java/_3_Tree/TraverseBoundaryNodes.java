package _3_Tree;

import ent.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TraverseBoundaryNodes {

	/**
	 *
	 * 						  7
	 * 				5						12
	 * 			 3		6			9				15
	 * 		   1  4	 	  0		  8   10  		 13    17
	 *
	 *
	 *
	 * @return
	 */
	public static Tree getInitialTree() {
		Tree root = new Tree(7);
		root.left = new Tree(5);
		root.left.right = new Tree(6);
		root.left.right.right = new Tree(0); // tmp node.
//		root.left.left = new Tree(3); /// THIS CODE IS WRONG.. try commenting this node.
//		root.left.left.left = new Tree(1);
//		root.left.left.right = new Tree(4);
		
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
		Queue<Tree> queue = new LinkedList<>();
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
		Stack<Tree> stack = new Stack<>();
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
		traverse0(root);
	}




	// boundary nodes
	public static void traverse0(Tree root) {

		LinkedList<LinkedList<Integer>> levelOrderTraversal = traverse00(root);

		// print left boundary
		for(LinkedList<Integer> level : levelOrderTraversal) {
			System.out.print(level.removeFirst() + " ");
		}

		levelOrderTraversal.removeFirst();


		// print leaves
		LinkedList<Integer> leafLevel = levelOrderTraversal.removeLast();
		for(Integer leaf : leafLevel) {
			System.out.print(leaf + " ");
		}

		// print right boundary
		while(levelOrderTraversal.size() > 0) {
			LinkedList<Integer> level = levelOrderTraversal.removeLast();
			System.out.print(level.removeLast() + " ");
		}

	}


	public static LinkedList<LinkedList<Integer>> traverse00(Tree root) {
		Queue<Tree> queue = new LinkedList<>();
		queue.add(root);
		queue.add(null);
		LinkedList<LinkedList<Integer>> levelOrderTraversal = new LinkedList<>();
		LinkedList<Integer> level = new LinkedList<>();
		while(!queue.isEmpty()) {
			Tree current = queue.poll();
			if(current == null) {
				if(!queue.isEmpty()) {
					queue.add(null);
				}
				levelOrderTraversal.add(level);
				level = new LinkedList<>();
				System.out.println();
			} else {
				level.add(current.val);
				if(current.left != null)
					queue.add(current.left);

				if(current.right != null)
					queue.add(current.right);
			}
		}
		return levelOrderTraversal;
	}

}
