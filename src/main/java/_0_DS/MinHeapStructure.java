package _0_DS;

import java.util.Arrays;

import util.AlgoUtil;

public class MinHeapStructure <T extends Comparable<T>>{

	private int capacity;
	private double loadFactor;
	private int size = 0;
	private Object[] items;
	
	public MinHeapStructure() {
		this.capacity = 10;
		this.loadFactor = 0.75;
		this.items = new Object[capacity];
	}
	
	public MinHeapStructure(int capacity, int loadFactor) {
		this.capacity = capacity;
		this.loadFactor = loadFactor;
		this.items = new Object[capacity];
	}

	private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex + 1; }
	private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 2; }
	private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; }

	private boolean hasLeftChild(int parentIndex) { return (getLeftChildIndex(parentIndex) < size ? true : false); }
	private boolean hasRightChild(int parentIndex) { return (getRightChildIndex(parentIndex) < size ? true : false); }
	private boolean hasParent(int childIndex) { return (getParentIndex(childIndex) > -1 ? true : false); }
	
	private T leftChild(int parentIndex) { return (T)items[getLeftChildIndex(parentIndex)]; }
	private T rightChild(int parentIndex) { return (T)items[getRightChildIndex(parentIndex)]; }
	private T parent(int childIndex) { return (T)items[getParentIndex(childIndex)]; }
	
	private double currentLoadFactor() { return (double) capacity / (double) size; }
		
	private void ensureCapacity() {
		if(currentLoadFactor() < loadFactor) return;
		capacity = 2*capacity;
		items = Arrays.copyOf(items, capacity);
	}
	
	public void add(T elem) {
		ensureCapacity();
		this.items[size] = elem;
		size++;
		heapifyUp();
	}
	
	@SuppressWarnings("unchecked")
	private void heapifyUp() {
		int childIndex = size-1;
		if(!hasParent(childIndex)) return;
		int compareVal = ((T) (items[childIndex])).compareTo( (T) parent(childIndex) );
		int parentIndex;
		while(hasParent(childIndex) && compareVal < 0) {
			parentIndex = getParentIndex(childIndex);
			swap(childIndex, parentIndex);
			childIndex = parentIndex;
			compareVal = ((T) (items[childIndex])).compareTo( (T) parent(childIndex) );
		}
	}
	
	@SuppressWarnings("unchecked")
	private void swap(int idx1, int idx2) {
		T tmp = (T) this.items[idx1];
		this.items[idx1] = this.items[idx2];
		this.items[idx2] = tmp;
	}

	@SuppressWarnings("unchecked")
	public T peek() {
		if(size==0) throw new IllegalStateException();
		return (T) this.items[0];
	}
	
	@SuppressWarnings("unchecked")
	public T poll() {
		if(size==0) throw new IllegalStateException();
		T first = (T) this.items[0];
		size--;
		this.items[0] = this.items[size];
		heapifyDown();
		return first;
	}

	@SuppressWarnings("unchecked")
	private void heapifyDown() {
		
		int parentIndex = 0;
		int compareVal;
		while(hasLeftChild(parentIndex)) {
			int indexOfSmallerElem = getLeftChildIndex(parentIndex);
			if(hasRightChild(parentIndex)) {
				compareVal = ((T) (items[indexOfSmallerElem])).compareTo( (T) items[indexOfSmallerElem+1] );
				if(compareVal > 0) indexOfSmallerElem = getRightChildIndex(parentIndex);
			}
			
			compareVal = ((T) (items[indexOfSmallerElem])).compareTo( (T) items[parentIndex] );
			if(compareVal > 0) {
				break;
			}
			swap(indexOfSmallerElem, parentIndex);
			parentIndex = indexOfSmallerElem;
		}
		
	}
	
	public int size() {
		return this.size - 1;
	}
	
	public Object[] getArray() {
		return Arrays.copyOf(items, size);
    }

	public static void main(String[] args) {
		System.out.println("started");
		MinHeapStructure<Integer> heap = new MinHeapStructure<>();
		for(int x : AlgoUtil.getUnsortedArray(10)) {
			System.out.println("adding:" + x);
			heap.add(x);
			
			System.out.println("min:" + heap.peek());
		}
		
		System.out.println("\n\n\npolling");
		
		while(heap.size()>0) {
			System.out.println( heap.poll() );
		}
	}
}
