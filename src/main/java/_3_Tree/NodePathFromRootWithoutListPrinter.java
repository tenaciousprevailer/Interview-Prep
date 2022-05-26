package _3_Tree;

import ent.Tree;
import util.AlgoUtil;

import java.util.LinkedList;

public class NodePathFromRootWithoutListPrinter {

    public static void main(String[] args) {
        Tree root = AlgoUtil.getInitialTree();
        String path = printPath(root, 8);
        System.out.println(path);
    }

    private static String printPath(Tree root, int val) {
        if(root == null) return null;
        if(root.val == val) {
            return root.val + "";
        }
        String left = printPath(root.left, val);
        if(left != null) {
            return root.val + " " + left;
        }
        String right = printPath(root.right, val);
        if(right != null) {
            return root.val + " " + right;
        }

        return null;
    }

}
