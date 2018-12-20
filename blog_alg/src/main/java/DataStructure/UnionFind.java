package DataStructure;

import java.util.*;

public class UnionFind<T extends Comparable<? super T>> {

    ArrayList<UnionFindNode<T>> array;
    HashMap<T,UnionFindNode<T>> map;

    public UnionFind(List<T> elements) {
        array = new ArrayList<>(elements.size());
        map = new HashMap<>();
        for (T ele :
                elements) {
            UnionFindNode<T> tmp = new UnionFindNode<>(ele);
            array.add(tmp);
            map.put(ele,tmp);
        }
    }

    /*
     * 合并两个集合(加速)
     * 将表示小树的数根改为表示大树的数根的儿子结点
     */
    public void union(T p, T q) {
        UnionFindNode<T> rootP = find(p);
        UnionFindNode<T> rootQ = find(q);
        if (rootP == rootQ) return;

        // make root of smaller rank point to root of larger rank
        if (rootP.count < rootQ.count) {
            rootP.parent = rootQ;
            rootQ.count += rootP.count;
        } else {
            rootQ.parent = rootP;
            rootP.count += rootQ.count;
        }
    }

    /*
     * find运算(加速)
     * 从元素e相应的结点走到树根处，找出所在集合的名字
     */
    public UnionFindNode<T> find(T p) {
        UnionFindNode<T> pointer = getNodes(p);
        if (pointer.parent == null) {
            return pointer;
        }
        pointer.parent = find(pointer.parent.value);
        return pointer.parent;
    }


    private boolean validate(T p) {
        boolean flag = false;
        for (UnionFindNode<T> node :
                array) {
            if (p == node.value) {
                flag = true;
            }
        }
        return flag;
    }

    private UnionFindNode<T> getNodes(T t){
        return map.get(t);
    }

    public boolean connected(T p, T q) {
        return find(p) == find(q);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("the union find sequence includes : \n");
        for (UnionFindNode<T> ufnode :
                array) {
            sb.append("the vertex : " + ufnode.value);
            if (ufnode.parent == null){
                sb.append("'s parent is itself");
                sb.append(" its size is " + ufnode.count + "\n");
            }else{
                sb.append("'s parent is " + ufnode.parent.value);
                sb.append(" its size is " + ufnode.parent.count + "\n");
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1,2,34,5,6,7,8,9,10};
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(nums));

        UnionFind uf = new UnionFind(list);

        uf.union(1,2);
        uf.union(34,1);

        uf.union(5,6);
        uf.union(7,8);
        uf.union(8,9);
        uf.union(9,10);

        System.out.println(uf.toString());

        System.out.println(uf.connected(1,5));
    }

}
