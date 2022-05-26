package _3_Tree;

import ent.Tree;
import util.AlgoUtil;

import java.util.Deque;
import java.util.LinkedList;

public class NodePathFromChildPrinter {

    public static void main(String[] args) {
        Tree root = AlgoUtil.getInitialTree();
        LinkedList<Integer> pathList = new LinkedList<>();
        printPathToNode(root, 8, pathList);
        System.out.println(pathList);

        printPathFromRootToNode(root, 8);
    }

    private static boolean printPathToNode(Tree node, int nodeVal, LinkedList<Integer> path) {
        if(node == null) return false;
        if(node.val == nodeVal || printPathToNode(node.left, nodeVal, path) || printPathToNode(node.right, nodeVal, path)) {
            path.addFirst(node.val);
            return true;
        }
        return false;
    }

    public static void printPathFromRootToNode(Tree root, int nodeVal) {

        Deque<Integer> stack = new LinkedList<>();
        printPathFromRootToNode(root, nodeVal, stack);
        while(!stack.isEmpty()) System.out.print(stack.pop() + " ");
    }

// Root Left Right

    // stop recursion when found, push to stack. Found -> Not Empty Stack
    public static Deque<Integer> printPathFromRootToNode(
            Tree root,
            int nodeVal,
            Deque<Integer> stack) {

        if(root == null) return stack;
        if(root.val == nodeVal ||
                !printPathFromRootToNode(root.left, nodeVal, stack).isEmpty() ||
                !printPathFromRootToNode(root.right, nodeVal, stack).isEmpty() ) {

                stack.push(root.val);
            }
        return stack;
    }

}
