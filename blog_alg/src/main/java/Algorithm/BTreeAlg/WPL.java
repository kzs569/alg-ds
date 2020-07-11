package Algorithm.BTreeAlg;
import DataStructure.Tree.BinaryTree;
import DataStructure.Tree.BinaryTreeNode;
import DataStructure.Queue;

public class WPL {

    /***
     * 计算二叉树的带权路径长度（WPL）
     * 二叉树的带权路径长度是二叉树中所有叶结点的带权路径长度之和
     * @param btree
     * 主要有深度优先遍历和广度优先遍历
     * 深度优先遍历将遍历深度作为路径长度
     * 广度优先遍历需要变量记录深度
     */
    public static int wpl_dfs = 0; //wpl_dfs为加权和

    public static void wpl_dfs(BinaryTreeNode<Integer> node,int deepth){
        if(node == null){
            return ;
        }
        if(node.isLeaf()){
            wpl_dfs += node.getData() * deepth;
        }else{
            wpl_dfs(node.getLeftChild(),deepth + 1);
            wpl_dfs(node.getRightChild(), deepth + 1);
        }
    }

    public static int get_WPL_dfs(BinaryTree<Integer> btree){
        wpl_dfs(btree.getRoot(),0);
        return wpl_dfs;
    }


    public static int get_WPL_bfs(BinaryTree<Integer> btree){
        if (btree.isEmpty()){
            return 0;
        }

        int wpl_bfs = 0;
        int deepth = 0;
        BinaryTreeNode<Integer> last = btree.getRoot();
        Queue<BinaryTreeNode<Integer>> queue = new Queue <BinaryTreeNode<Integer>>();
        queue.enqueue(btree.getRoot());
        while(!queue.isEmpty()){
            BinaryTreeNode<Integer> node = queue.dequeue();
            if(node.isLeaf()){
                wpl_bfs += node.getData() * deepth;
            }else{
                if(node.getLeftChild() != null){
                    queue.enqueue(node.getLeftChild());
                }else if(node.getRightChild() != null){
                    queue.enqueue(node.getRightChild());
                }
            }

            if(node == last && !queue.isEmpty()){
                last = queue.peek();
                ++deepth;
            }
        }
        return wpl_bfs;
    }


    public static void main(String[] args) {
        BinaryTree<Integer> btree = new BinaryTree<Integer>();
        for(int i = 1;i < 10; i++) {
            btree.InsertTreeNode(new Integer(i));
        }


    }

}
