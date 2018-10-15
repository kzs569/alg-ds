package DataStructure.Tree;

public class LeftHeapNode<T extends Comparable <? super T>> {
    private int npl; //零路径长度
    private T data;
    private LeftHeapNode<T> leftchild;
    private LeftHeapNode<T> rightchild;

    public LeftHeapNode(T element) {
        this.data = element;
        this.leftchild = null;
        this.rightchild = null;
        this.npl = 0;
    }

    public LeftHeapNode(T element, LeftHeapNode left, LeftHeapNode right) {
        this.data = element;
        this.leftchild = left;
        this.rightchild = right;
        this.npl = 0;
    }

    public int getNpl() {
        return npl;
    }

    public void setNpl(int npl) {
        this.npl = npl;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LeftHeapNode <T> getLeftchild() {
        return leftchild;
    }

    public void setLeftchild(LeftHeapNode <T> leftchild) {
        this.leftchild = leftchild;
    }

    public LeftHeapNode <T> getRightchild() {
        return rightchild;
    }

    public void setRightchild(LeftHeapNode <T> rightchild) {
        this.rightchild = rightchild;
    }
}
