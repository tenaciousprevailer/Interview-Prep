package util;

import ent.Node;
import ent.Tree;

import java.util.Collection;
import java.util.Comparator;
import java.util.Random;

public class AlgoUtil {

	public static void printArr(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i] + ((i== (arr.length-1)) ? "" : ", "));
		}
			
		System.out.println();
	}
	
	public static boolean isSorted(int[] arr) {
		int last = arr[0];
		for(int i : arr) {
			if(i < last)
				return false;
			last=i;
		}
		return true;
	}
	
	public static int[] getUnsortedArray(int size) {
		int[] arr = new int[size];
		int bound = size*size;
		Random random = new Random();
		for(int i=0;i<size;i++) {
			arr[i] = random.nextInt(bound);
		}
		return arr;
	}


	
	public static void printArr(int[] arr, int start, int end) {
		for(int i=start;i<=end;i++) {
			System.out.print(arr[i] + ((i== (arr.length-1)) ? "" : ", "));
		}
		System.out.println();
	}
	
	public static void swap(int[] arr, int pos1,int  pos2) {
		int temp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = temp;
	}
	
	/**
	 * 						1
	 * 				2				3
	 * 			4		5		6
	 * 		7	  8				  	9
	 * @return
	 */
	public static Tree getInitialTree() {
		// level 0
		Tree root = new Tree(1);
		
		// level 1
		root.left = new Tree(2);
		root.right = new Tree(3);
		
		// level 2
		root.left.left = new Tree(4);
		root.left.right = new Tree(5);
		
		root.right.left = new Tree(6);
		
		// level 3
		root.left.left.left = new Tree(7);
		root.left.left.right = new Tree(8);
		root.right.left.right = new Tree(9);
		
		return root;
	}
	
	public static Node getInitialLinkedList() {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		
		return head;
	}

	public static void printLinkedList(Node head) {
		while(head!=null){
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}

	
	public static boolean contains(Integer[] pageFrames, Integer n) {
		for(Integer i : pageFrames) {
			if(i == n) {
				return true;
			}
		}
		return false;
	}

	public static void printArr(Integer[] arr) {
		for(Integer i=0;i<arr.length;i++) {
			System.out.print(arr[i] + ((i== (arr.length-1)) ? "" : ", "));
		}
			
		System.out.println();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Integer[] getUnsortedIntegerArray(int size) {

		Random r = new Random();
		int low = 99;
		int high = 999;
		int result = r.nextInt(high-low) + low;

		Integer[] arr = new Integer[size];
//		int bound = 10000;//Math.min(100, Math.abs(size*size));//Math.min(10000, Math.abs(size* size));
//		bound = Math.abs(bound);
//		Random random = new Random();
		for(int i=0;i<size;i++) {
			arr[i] = r.nextInt(high-low) + low;
		}
		return arr;
	}

	public static Integer[] getSortedIntegerArray(int size) {

		Integer[] arr = new Integer[size];
		for(int i=0;i<size;i++) {
			arr[i] = i;
		}
		return arr;
	}
	
	public static void swapArray(Object[] arr, int pos1,int  pos2) {
		Object temp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = temp;
	}
	
	public static boolean isIntegerArraySorted(Integer[] arr, Comparator<Integer> comparator) {
		Integer last = arr[0];
		for(Integer i : arr) {
			int compareVal = comparator.compare(i, last);
			if(compareVal<0)
				return false;
			last=i;
		}
		return true;
	}

	public static boolean isIntegerArraySorted(Integer[] arr, int l, int r, Comparator<Integer> comparator) {
		Integer last = arr[l];
		for(int x = l; x <= r; x++) {
			int i = arr[x];
			int compareVal = comparator.compare(i, last);
			if(compareVal<0)
				return false;
			last=i;
		}
		return true;
	}
	
	public static boolean isIntegerCollectionSorted(Collection<Integer> collection) {
		Integer prev = Integer.MIN_VALUE;
		java.util.Iterator<Integer> itr = collection.iterator();
		while(itr.hasNext()) {
			Integer next = itr.next();
			System.out.println(next);
			if(next < prev) return false;
			prev = next;
		}
		return true;
	}

	public static void printMatrix(int[][] m) {
		int row = m.length;
		int col = m[0].length;
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}


