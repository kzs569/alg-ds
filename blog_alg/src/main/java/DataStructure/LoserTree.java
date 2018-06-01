package DataStructure;

import java.util.ArrayList;

public class LoserTree {
    private int[] tree = null;
    private int size = 0;
    private ArrayList<ArrayList<Integer>> leaves = null;

    public LoserTree(ArrayList<ArrayList<Integer>> leaves) {
        this.leaves = leaves;
        this.size = leaves.size();
        this.tree = new int[size];
        for (int i = 0; i < size; i++) {
            tree[i] = -1;
        }
        for (int i = size - 1; i >= 0; i--) {
            adjust(i);
        }
    }

    private void adjust(int i) {
        // s指向当前的值最小的叶子结点（胜者）
//        int t = (s + size) / 2;// t是s的双亲
//
//        while (t > 0)
//        {
//            if (s >= 0
//                    && (tree[t] == -1 || leaves.get(s).compareTo(
//                    leaves.get(tree[t])) > 0))
//            {
//                // 将树中的当前结点指向其子树中值最小的叶子
//                int tmp = s;
//                s = tree[t];
//                tree[t] = tmp;
//            }
//            t /= 2;
//        }
//        tree[0] = s;// 树根指向胜者
    }

    public void del(int s)
    {
        leaves.remove(s);
        size--;
        tree = new int[size];
        for (int i = 0; i < size; ++i)
        {
            tree[i] = -1;
        }
        for (int i = size - 1; i >= 0; --i)
        {
            adjust(i);
        }
    }

    public void add(ArrayList<Integer> leaf, int s)
    {
        leaves.set(s, leaf);// 调整叶子结点
        adjust(s);// 调整非叶子结点
    }

    public ArrayList<Integer> getLeaf(int i)
    {
        return leaves.get(i);
    }

    public int getWinner()
    {
        return tree[0];
    }

    public static void main(String[] args) {

    }
}
