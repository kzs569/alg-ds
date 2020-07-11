package DataStructure.Tree;

import java.util.ArrayList;
import java.util.Arrays;

public class LoserTree {
    private int[] tree = null;
    private int size = 0;
    private ArrayList<Integer> leaves = null;

    public LoserTree(ArrayList<Integer> leaves) {
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

    private void adjust(int s) {
        // s指向当前的值最小的叶子结点（胜者）
        // t是s的双亲
        for(int t = (s + size)/2; t > 0; t /= 2){
            if (s >= 0
                    && (tree[t] == -1 || leaves.get(s).compareTo(
                    leaves.get(tree[t])) > 0))
            {
                // 将树中的当前结点指向其子树中值最小的叶子
                swap(s, tree[t]);
            }
        }
        tree[0] = s;// 树根指向胜者
    }

    private void swap(int i, int j){
        int tmp = i;
        i = j;
        j = tmp;
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

    public void add(Integer leaf, int s)
    {
        leaves.set(s, leaf);// 调整叶子结点
        adjust(s);// 调整非叶子结点
    }

    public Integer getLeaf(int i)
    {
        return leaves.get(i);
    }

    public int getWinner()
    {
        return tree[0];
    }

    public static void main(String[] args) {

        ArrayList<Integer> nums = new ArrayList <Integer>(Arrays.asList(1,2,4,5,6,4,15,27,365,1000));
        LoserTree lt = new LoserTree(nums);
        System.out.println(lt.getWinner());

    }
}
