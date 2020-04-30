package _3_Tree;

import ent.Tree;

import java.util.Stack;

public class PostOrderTreeTravesorIterative implements ITreeTravesor {

    @Override
    public void traverse(Tree root) {
        Stack<Tree> treeStack = new Stack<>();
        treeStack.push(root);

        Tree lastChildPrinted = null;

        while(treeStack.size() > 0) {
            Tree current = treeStack.pop();

            if(current.left == null && current.right == null){
                System.out.print(current.val + " ");
                lastChildPrinted = current;
                continue;
            }

            if(current.right != null) {
                if(current.right == lastChildPrinted) {
                    System.out.print(current.val + " ");
                    lastChildPrinted = current;
                    continue;
                }
            } else{
                // current.right is null, current.left won't be , due to leaf check at top
                if(current.left == lastChildPrinted) {
                    System.out.print(current.val + " ");
                    lastChildPrinted = current;
                    continue;
                }
            }

            treeStack.push(current);

            if(current.right != null) {
                treeStack.push(current.right);
            }
            if(current.left != null) {
                treeStack.push(current.left);
            }


        }
    }

}
