package DataStructure.Tree;

public class BinarySearchTree<T extends Comparable <? super T>> extends BinaryTree<T>
{
    public void InsertTreeNode(BinaryTreeNode <T> root, BinaryTreeNode <T> newpoint) {
        BinaryTreeNode <T> point = null;
        System.out.println("the insert node" + newpoint.getData().toString());
        /**
         * 如果是空树，则用指针newpoint作为指针
         **/
        if (this.root == null || this.root.getData() == null) {
            this.root = newpoint;
            return;
        } else {
            point = root;
        }

        while (point != null) {
            if (newpoint.getData() == point.getData()) {
                return;
            } else if (newpoint.getData().compareTo(point.getData()) < 0) {
                if (point.getLeftChild() == null) {
                    point.setLeftChild(newpoint);
                    return;
                } else {
                    point = point.getRightChild();
                }
            } else if (newpoint.getData().compareTo(point.getData()) > 0) {
                if (point.getRightChild() == null) {
                    point.setRightChild(newpoint);
                    return;
                } else {
                    point = point.getRightChild();
                }
            }
        }
    }
}
