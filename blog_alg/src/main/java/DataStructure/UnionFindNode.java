package DataStructure;

public class UnionFindNode<T extends Comparable <? super T>> {
    T value;
    UnionFindNode<T> parent;
    int count;

    public UnionFindNode(T value) {
        this.value = value;
        this.parent = null;
        this.count = 1;
    }
}
