package _3_Tree;

import ent.Tree;

import java.util.Stack;

public class PreOrderTreeTravesorIterative implements ITreeTravesor {

    @Override
    public void traverse(Tree tree) {
        Stack<Tree> treeStack = new Stack<>();
        treeStack.push(tree);
        while(treeStack.size() > 0) {

            Tree current = treeStack.pop();
            if(current == null)
                continue;

            System.out.print(current.val + " ");

            if(current.right != null) {
                treeStack.push(current.right);
            }
            if(current.left != null) {
                treeStack.push(current.left);
            }
        }
    }

}
