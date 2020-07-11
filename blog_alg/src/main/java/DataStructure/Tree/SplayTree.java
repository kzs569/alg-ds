package DataStructure.Tree;

public class SplayTree<T extends Comparable<? super T>> {

    SplayTreeNode<T> root;

    public void splay(T key) {
        root = splay(this.root, key);
    }

    private SplayTreeNode<T> splay(SplayTreeNode<T> root, T key) {
        if (root == null) {
            return null;
        }



    }

    public void insert(T data) {
        if (root == null) {
            root = new SplayTreeNode<>(data);
            return;
        }

        root = splay(root, data);
    }

    /**
     * (递归实现)查找"伸展树x"中键值为key的节点
     */
    private SplayTreeNode<T> search(SplayTreeNode<T> x, T key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.getData());
        if (cmp < 0)
            return search(x.getLchild(), key);
        else if (cmp > 0)
            return search(x.getRchild(), key);
        else
            return x;
    }

    public SplayTreeNode<T> search(T key) {
        return search(this.root, key);
    }

    private SplayTreeNode<T> singleRotation(SplayTreeNode<T> root)
}
