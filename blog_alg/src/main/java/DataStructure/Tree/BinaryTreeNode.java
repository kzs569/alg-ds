package DataStructure.Tree;

import java.util.LinkedList;
import java.util.List;

public class BinaryTreeNode<T extends Comparable<? super T>>{
    private T data;
    private BinaryTreeNode<T> leftchild;
    private BinaryTreeNode<T> rightchild;
    private int height;
    private Tag tag;
    //左子结点和右子结点
    public enum Tag {Left, Right};

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode() {
        this.data = null;
        this.leftchild = null;
        this.rightchild = null;
        this.height = -1;
    }

    public BinaryTreeNode(T data) {
        this.data = data;
        this.leftchild = null;
        this.rightchild = null;
        this.height = -1;
    }

    public BinaryTreeNode(T data, BinaryTreeNode <T> leftchild, BinaryTreeNode <T> rightchild) {
        this.data = data;
        this.leftchild = leftchild;
        this.rightchild = rightchild;
        this.height = -1;
    }

    public BinaryTreeNode(T data, BinaryTreeNode <T> leftchild, BinaryTreeNode <T> rightchild, int height) {
        this.data = data;
        this.leftchild = leftchild;
        this.rightchild = rightchild;
        this.height = height;
    }

    public BinaryTreeNode<T> getLeftChild() {
        return leftchild;
    }

    public void setLeftChild(BinaryTreeNode<T> leftchild) {
        this.leftchild = leftchild;
    }

    public BinaryTreeNode<T> getRightChild() {
        return rightchild;
    }

    public void setRightChild(BinaryTreeNode<T> rightchild) {
        this.rightchild = rightchild;
    }

    public Tag getTag() {return tag;}

    public void setTag(Tag tag) {this.tag = tag;}


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public boolean isLeaf(){
        if (this.leftchild == null && this.rightchild == null){
            return true;
        }
        return false;
    }

    public List<BinaryTreeNode<T>> getChilds(){
        List<BinaryTreeNode<T>> nodelist = new LinkedList <BinaryTreeNode <T>>();
        if(this.leftchild != null){
            nodelist.add(this.leftchild);
        }
        if(this.rightchild != null){
            nodelist.add(this.rightchild);
        }
        return nodelist;
    }
}