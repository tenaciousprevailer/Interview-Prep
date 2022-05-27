package _5_Questions;

import ent.Tree;
import util.AlgoUtil;

import java.util.concurrent.atomic.AtomicInteger;

interface KthSmallestElementInBSTFinder {
    int findKthSmallestElement(Tree root, int k);
}

class KthSmallestElementInBSTFinderImpl implements KthSmallestElementInBSTFinder {

    public static void main(String[] args) {
        Tree Tree = AlgoUtil.getInitialBSTTree();
        KthSmallestElementInBSTFinder kthSmallestElementInBSTFinder = new KthSmallestElementInBSTFinderImpl();
        int kthSmallestElement = kthSmallestElementInBSTFinder.findKthSmallestElement(Tree, 9);
        System.out.println(kthSmallestElement);
    }

    public int findKthSmallestElement(Tree root, int k) {
        Tree tree = traverseBstInOrderTillKthTree(root, k, new AtomicInteger());
        if (tree == null) return -1;
        return tree.val;
    }


    // left root right
    private Tree traverseBstInOrderTillKthTree(Tree root, int k, AtomicInteger counter) {
        if(root != null) {
            Tree Tree = traverseBstInOrderTillKthTree(root.left, k, counter);
            if(Tree != null) return Tree;

            counter.incrementAndGet();
            if (counter.get() == k) {
                return root;
            }

            return traverseBstInOrderTillKthTree(root.right, k, counter);
        }
        return null;
    }

}
