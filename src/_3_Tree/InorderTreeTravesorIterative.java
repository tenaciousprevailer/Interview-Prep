package _3_Tree;

import ent.Tree;

import java.util.HashSet;
import java.util.Stack;

public class InorderTreeTravesorIterative implements ITreeTravesor {

//    @Override
    public void traverse0(Tree root) {
        System.out.println();
        Stack<Tree> stack = new Stack<Tree>();
        stack.push(root);
        HashSet<Tree> hashset = new HashSet<>();
        while(stack.size() > 0) {
            Tree current = stack.pop();
            if(current!=null) {
                // leaf node
                if(current.left == null && current.right == null) {
                    System.out.print(current.val + " ");
                } else if(hashset.contains(current)) {
                    // if current has been processed already
                    System.out.print(current.val + " ");
                } else {
                    // else new node
                    hashset.add(current);
                    stack.push(current.right);
                    stack.push(current);
                    stack.push(current.left);
                }
            }
        }
    }

    @Override
    public void traverse(Tree root) {
        Stack<Tree> treeStack = new Stack<>();
        treeStack.push(root);
        while(treeStack.size() > 0) {

            Tree current = treeStack.pop();
            if(current == null)
                continue;

            boolean print = false;

            // if current is leaf, print
            if(current.left == null && current.right == null){
                System.out.print(current.val + " ");
                continue;
            } else {
                // not leaf
                if(treeStack.size() > 0 && treeStack.peek() == current.right) {
                    System.out.print(current.val + " ");
                    continue;
                } else {
                    treeStack.push(current.right);
                    treeStack.push(current);
                    treeStack.push(current.left);
                }

            }
        }
    }
}
