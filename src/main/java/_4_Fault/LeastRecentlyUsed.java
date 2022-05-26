package _4_Fault;

import util.AlgoUtil;

import java.util.LinkedHashSet;

public class LeastRecentlyUsed {

	public static void main(String[] args) {
		int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
		int pageSlot = 4;
		calculate(pages, pageSlot);

		int pageFault0 = calculate0(pages, pageSlot);
		System.out.println("Total page fault:" + pageFault0);
	}

	private static int calculate0(int[] pages, int pageSlot) {
		int faults = pageSlot;
		LinkedHashSet<Integer> linkedPages = new LinkedHashSet<>();
		for (int i = 0; i < pageSlot; i++) {
			linkedPages.add(pages[i]);
		}

		int pageHits = 0;
		for (int i = pageSlot; i < pages.length; i++) {
			if(!linkedPages.remove(pages[i])) {
				faults++;
				linkedPages.remove(linkedPages.stream().findFirst().get());
			} else pageHits++;
			linkedPages.add(pages[i]);
		}
		System.out.println("PageHits:" + pageHits);
		return faults;
	}

	private static void calculate(int[] pages, int pageSlot) {
		Integer[] pageFrames = new Integer[pageSlot];
		int pagesFilled = 0;
		int newPageIndex = 0;
		int pageHit = 0;
		for(int i=0;i<pages.length;i++) {
			int newPage = pages[i];
			if(contains(pageFrames, newPage)) {
				pageHit++;
				continue;
			}

			if(pagesFilled < pageSlot) {
				newPageIndex = pagesFilled;
				pagesFilled++;
			}else {
				newPageIndex = getNewPageIndex(pageFrames, pages, i);
			}
			pageFrames[newPageIndex] = newPage;
			AlgoUtil.printArr(pageFrames);
		}
		System.out.println("PageFault:" + (pages.length - pageHit) + ", pageHit:" + pageHit);
	}
	
	private static int getNewPageIndex(Integer[] pageFrames, int[] newPages, int currentIndex) {
		
		int newIndex = 0, jthFarthestIndex = currentIndex-1;
		for(int i = 0; i<pageFrames.length;i++) {
			
			int indexOfExistingPageInFuturePages = getIndexOf(newPages, pageFrames[i], currentIndex-1);
			
			if(indexOfExistingPageInFuturePages < jthFarthestIndex) {
				jthFarthestIndex = indexOfExistingPageInFuturePages;
				newIndex = i;
			}
		}
		return newIndex;
	}

	private static int getIndexOf(int[] pageFrames, int n, int currentIndex) {
		int index = 0;
		for(int i=currentIndex;i>=0;i--) {
			if(pageFrames[i] == n) {
				index = i;
				break;
			}
		}
		return index;
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
