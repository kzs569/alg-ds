package DataStructure.Tree;

import static java.lang.Math.max;

public class AVLTree<T extends Comparable <? super T>> extends BinaryTree<T>{

    @Override
    public void InsertTreeNode(T data) {
        this.InsertTreeNode(root, new BinaryTreeNode <T>(data));
    }

    @Override
    public void InsertTreeNode(BinaryTreeNode <T> root, BinaryTreeNode <T> newpoint) {
        System.out.println("the insert node" + newpoint.getData().toString());
        /**
         * 如果是空树，则用指针newpoint作为指针
         **/
        if (this.root == null || this.root.getData() == null) {
            this.root = newpoint;
        } else {
            int cmp = root.getData().compareTo(newpoint.getData());
            if(cmp > 0){
                if (root.getLeftChild() == null) {
                    root.setLeftChild(newpoint);
                } else {
                    InsertTreeNode(root.getLeftChild(), newpoint);
                }
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (root.getLeftChild().getHeight() - root.getRightChild().getHeight() >= 2) {
                    if (root.getData().compareTo(root.getLeftChild().getData()) < 0)
                        root = leftLeftRotation(root);
                    else
                        root = leftRightRotation(root);
                }
            }else if(cmp < 0){
                if (root.getRightChild() == null) {
                    root.setRightChild(newpoint);
                } else {
                    InsertTreeNode(root.getRightChild(), newpoint);
                }
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (root.getRightChild().getHeight() - root.getLeftChild().getHeight() == 2) {
                    if (root.getData().compareTo(root.getRightChild().getData()) > 0)
                        root = rightRightRotation(root);
                    else
                        root = rightLeftRotation(root);
                }
            }else{
                System.out.println("Error! the node is already in the tree!");
            }
        }

    }

    public boolean isAVLTree(){
        boolean balance = true;
        return true;
    }

    private void judgeAVLTree(BinaryTreeNode root, boolean balance){
        //空树时，必定为平衡树
        if(this.root==null){
            balance = true;
        }
        //仅有根节点时，也为平衡树
        if(this.root.getLeftChild() == null && this.root.getRightChild() == null){
            balance = true;
        }

    }

    /*
     * LL：左左对应的情况(左单旋转)。
     *
     * 返回值：旋转后的根节点
     */
    private BinaryTreeNode <T> leftLeftRotation(BinaryTreeNode <T> k2) {

        if (k2.getLeftChild() == null) {
            return null;
        }

        BinaryTreeNode <T> k1 = k2.getLeftChild();
        k2.setLeftChild(k1.getRightChild());
        k1.setRightChild(k2);

        k2.setHeight(max(k2.getLeftChild().getHeight(), k2.getRightChild().getHeight()) + 1);
        k1.setHeight(max(k1.getLeftChild().getHeight(), k2.getHeight()) + 1);

        return k1;
    }

    /*
     * RR：右右对应的情况(右单旋转)。
     *
     * 返回值：旋转后的根节点
     */
    private BinaryTreeNode <T> rightRightRotation(BinaryTreeNode<T> k2){
        if (k2.getLeftChild() == null) {
            return null;
        }

        BinaryTreeNode <T> k1 = k2.getRightChild();
        k2.setRightChild(k1.getLeftChild());
        k1.setLeftChild(k2);

        k1.setHeight(max(k1.getLeftChild().getHeight(), k1.getRightChild().getHeight()) + 1);
        k2.setHeight(max(k1.getHeight(), k2.getRightChild().getHeight()) + 1);

        return k1;
    }

    /*
     * LR：左右对应的情况(左双旋转)。
     *
     * 返回值：旋转后的根节点
     */
    private BinaryTreeNode<T> leftRightRotation(BinaryTreeNode<T> k3){
        k3.setLeftChild(rightRightRotation(k3.getLeftChild()));
        return leftLeftRotation(k3);
    }

    /*
     * RL：右左对应的情况(右双旋转)。
     *
     * 返回值：旋转后的根节点
     */
    private BinaryTreeNode<T> rightLeftRotation(BinaryTreeNode<T> k3){
        k3.setRightChild(leftLeftRotation(k3.getRightChild()));
        return rightRightRotation(k3);
    }


}
