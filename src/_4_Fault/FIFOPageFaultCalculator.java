package _4_Fault;

public class FIFOPageFaultCalculator {

	public static void main(String[] args) {
		int[] pages = {3, 2, 1, 0, 3, 2, 4, 3, 2, 1, 0, 4};
		int pageSlot = 3;
		int pageFault = calculate(pages, pageSlot);
		System.out.println("Total page fault:" + pageFault);
	}

	private static int calculate(int[] pages, int pageSlot) {
		int pageFault = 0;
		Integer[] pageFrames = new Integer[pageSlot];
		int counter = 0;
		for(int newPage : pages) {
			if(!contains(pageFrames, newPage)) {
				if(counter > pageSlot - 1) counter = 0;
				pageFrames[counter] = newPage;
				counter++;
				pageFault++;
			}
		}
		return pageFault;
	}
	
	private static boolean contains(Integer[] pageFrames, Integer n) {
		for(Integer i : pageFrames) {
			if(i == n) {
				return true;
			}
		}
		return false;
	}
}
