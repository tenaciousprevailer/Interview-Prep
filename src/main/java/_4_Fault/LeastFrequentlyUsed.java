package _4_Fault;

import java.util.TreeMap;

public class LeastFrequentlyUsed {

    public static void main(String[] args) {
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
        int pageSlot = 4;
        calculate(pages, pageSlot);

        int pageFault0 = calculate0(pages, pageSlot);
        System.out.println("Total page fault:" + pageFault0);
    }


    private static int calculate0(int[] pages, int pageSlot) {
        int faults = pageSlot;
        new TreeMap<>();
        TreeMap<PageFrequency, PageFrequency> cacheMap = new TreeMap<>((a, b) -> {
            if(a.getKey() == b.getKey()) {
                return a.getValue() - b.getValue();
            }
            return a.getKey() - b.getKey();
        });
        for (int i = 0; i < pageSlot; i++) {
            final PageFrequency key = new PageFrequency(pages[i]);
            cacheMap.put(key, key);
        }

        int pageHits = 0;
        for (int i = pageSlot; i < pages.length; i++) {
            PageFrequency inCachePage = cacheMap.get(new PageFrequency(pages[i]));
            if (inCachePage == null) {
                faults++;
                cacheMap.pollFirstEntry();
                inCachePage = new PageFrequency(pages[i]);
                cacheMap.put(inCachePage, inCachePage);
            } else pageHits++;
            inCachePage.increment();
        }

        System.out.println("PageHits:" + pageHits);
        return faults;
    }

    private static void calculate(int[] pages, int pageSlot) {
        Integer[] pageFrames = new Integer[pageSlot];
        int pagesFilled = 0;
        int newPageIndex = 0;
        int pageHit = 0;
        for (int i = 0; i < pages.length; i++) {
            int newPage = pages[i];
            if (contains(pageFrames, newPage)) {
                pageHit++;
                continue;
            }

            if (pagesFilled < pageSlot) {
                newPageIndex = pagesFilled;
                pagesFilled++;
            } else {
                newPageIndex = getNewPageIndex(pageFrames, pages, i);
            }
            pageFrames[newPageIndex] = newPage;
        }
        System.out.println("PageFault:" + (pages.length - pageHit) + ", pageHit:" + pageHit);
    }

    private static int getNewPageIndex(Integer[] pageFrames, int[] newPages, int currentIndex) {

        int newIndex = 0, jthFarthestIndex = currentIndex - 1;
        for (int i = 0; i < pageFrames.length; i++) {

            int indexOfExistingPageInFuturePages = getIndexOf(newPages, pageFrames[i], currentIndex - 1);

            if (indexOfExistingPageInFuturePages < jthFarthestIndex) {
                jthFarthestIndex = indexOfExistingPageInFuturePages;
                newIndex = i;
            }
        }
        return newIndex;
    }

    private static int getIndexOf(int[] pageFrames, int n, int currentIndex) {
        int index = 0;
        for (int i = currentIndex; i >= 0; i--) {
            if (pageFrames[i] == n) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static boolean contains(Integer[] pageFrames, Integer n) {
        for (Integer i : pageFrames) {
            if (i == n) {
                return true;
            }
        }
        return false;
    }
}

class PageFrequency {
    private final int key;
    private int value;

    public PageFrequency(int key) {
        this.key = key;
        this.value = 1;
    }

    public PageFrequency(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
//        if (o instanceof Integer) return this.key == (Integer) o;
        if (getClass() != o.getClass()) return false;
        PageFrequency that = (PageFrequency) o;
        return key == that.key;
    }

    @Override
    public int hashCode() {
        return key;
    }

    public void increment() {
        this.value++;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}
