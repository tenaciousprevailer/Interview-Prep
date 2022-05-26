package _3_Tree;

import ent.Tree;
import util.AlgoUtil;

public class LowestCommonAncestor {

    public static void main(String[] args) {
        Tree root = AlgoUtil.getInitialTree();
        Tree lowestCommonAncestor0 = findLCA(root, 5, 12);
        System.out.println(lowestCommonAncestor0);
    }

    static boolean foundT1 = false;
    static boolean foundT2 = false;
    private static Tree findLCA(Tree root, int t1, int t2) {
        if(root == null) return null;

        // left
        Tree leftResult = findLCA(root.left, t1, t2);
        if(leftResult != null) return leftResult;

        // right
        Tree rightResult = findLCA(root.right, t1, t2);
        if(rightResult != null) return rightResult;

        // root
        if(foundT1 && foundT2) return root;
        else if(root.val == t1) foundT1 = true;
        else if(root.val == t2) foundT2 = true;

        // base
        return null;
    }

}
