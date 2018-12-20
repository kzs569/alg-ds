package DataStructure.Tree;

public class SplayTreeNode<T extends Comparable<? super T>> implements Comparable<SplayTreeNode<T>>{
    private SplayTreeNode<T> lchild;
    private SplayTreeNode<T> rchild;
    private SplayTreeNode<T> parent;
    private T data;

    public SplayTreeNode(T data){
        this.data = data;
        this.lchild = null;
        this.rchild = null;
        this.parent = null;
    }

    public SplayTreeNode(SplayTreeNode lchild,SplayTreeNode rchild ,SplayTreeNode parent, T data){
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
        this.parent = parent;
    }

    public SplayTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(SplayTreeNode<T> parent) {
        this.parent = parent;
    }

    public SplayTreeNode<T> getLchild() {
        return lchild;
    }

    public void setLchild(SplayTreeNode<T> lchild) {
        this.lchild = lchild;
    }

    public SplayTreeNode<T> getRchild() {
        return rchild;
    }

    public void setRchild(SplayTreeNode<T> rchild) {
        this.rchild = rchild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int compareTo(SplayTreeNode<T> o){
        if (this.data.compareTo(o.getData()) < 0)
            return -1;
        if (this.data.compareTo(o.getData()) > 0)
            return 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SplayTreeNode)) return false;

        SplayTreeNode<?> that = (SplayTreeNode<?>) o;

        if (getLchild() != null ? !getLchild().equals(that.getLchild()) : that.getLchild() != null) return false;
        if (getRchild() != null ? !getRchild().equals(that.getRchild()) : that.getRchild() != null) return false;
        if (getParent() != null ? !getParent().equals(that.getParent()) : that.getParent() != null) return false;
        return getData() != null ? getData().equals(that.getData()) : that.getData() == null;
    }

    @Override
    public int hashCode() {
        int result = getLchild() != null ? getLchild().hashCode() : 0;
        result = 31 * result + (getRchild() != null ? getRchild().hashCode() : 0);
        result = 31 * result + (getParent() != null ? getParent().hashCode() : 0);
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        return result;
    }
};
