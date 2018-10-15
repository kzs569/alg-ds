package DataStructure.Tree;

import java.util.*;
import java.util.Queue;
import java.util.Stack;


public class BinaryTree<T extends Comparable <? super T>> {

    protected BinaryTreeNode <T> root;              //二叉树的根节点
    private HashMap <BinaryTreeNode <T>, Integer> levels = new HashMap <BinaryTreeNode <T>, Integer>();           //二叉树结点的层级

    public BinaryTree() {
        BinaryTreeNode <T> node = new BinaryTreeNode <T>();
        this.root = node;
    }

    /**
     * 通过中序序列和后序序列构建二叉树（通过中序序列和前序序列构建二叉树的思想一摸一样）
     *
     * @param order1 中序序列
     * @param order2 后序序列
     */
    public BinaryTree(ArrayList <T> order1, ArrayList <T> order2) {
        if (order1.size() > 0 && order2.size() > 0 && order1.size() == order2.size()) {
            this.root = buildTree(order1, order2, 0, order1.size() - 1, 0, order2.size() - 1);
        }
    }

    private BinaryTreeNode <T> buildTree(ArrayList <T> midOrder, ArrayList <T> postOrder,
                                         int mstart, int mend, int pstart, int pend) {
        if (pend > pstart) return null;
        BinaryTreeNode <T> root = new BinaryTreeNode <T>(postOrder.get(pend));
        int imid = midOrder.indexOf(root.getData());
        root.setLeftChild(buildTree(midOrder, postOrder, mstart, imid - 1, pstart, pstart + imid - mstart - 1));
        root.setRightChild(buildTree(midOrder, postOrder, imid + 1, mend, pstart + imid - mstart, pend - 1));
        return root;
    }

    /**
     * @param levelOrder 层次遍历
     * @param midOrder   中序遍历
     * @param lstart     层次遍历开头指针
     * @param lend       层次遍历结尾指针
     * @return
     */

    private BinaryTreeNode <T> buildTree(LinkedList <T> levelOrder, ArrayList <T> midOrder, int lstart, int lend) {
        BinaryTreeNode <T> temp = new BinaryTreeNode <T>(levelOrder.get(0));
        int index = midOrder.indexOf(temp.getData());

        LinkedList <T> leftside = new LinkedList <T>();
        for (int i = lstart; i < index - 1; i++) {
            leftside.add(midOrder.get(i));
        }

        LinkedList <T> levelLeftOrder = new LinkedList <T>();
        LinkedList <T> levelRightOrder = new LinkedList <T>();
        for (int i = 0; i < levelOrder.size() - 1; i++) {
            if (leftside.indexOf(levelOrder.get(i)) == -1) {
                levelRightOrder.add(levelOrder.get(i));
            } else {
                levelLeftOrder.add(levelOrder.get(i));
            }
        }

        temp.setLeftChild(buildTree(levelLeftOrder, midOrder, lstart, index - 1));
        temp.setRightChild(buildTree(levelRightOrder, midOrder, index + 1, lend));
        return temp;
    }

    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    public BinaryTreeNode <T> getRoot() {
        return this.root;
    }

    public void InsertTreeNode(T data) {
        this.InsertTreeNode(root, new BinaryTreeNode <T>(data));
    }

    public void InsertTreeNode(BinaryTreeNode <T> root, BinaryTreeNode <T> newpoint) {

        System.out.println("the insert node" + newpoint.getData().toString());
        /**
         * 如果是空树，则用指针newpoint作为指针
         **/
        if (this.root == null || this.root.getData() == null) {
            this.root = newpoint;
        } else {
            if (Math.random() > 0.5) {                   //采用随机方式创建二叉树
                if (root.getLeftChild() == null) {
                    root.setLeftChild(newpoint);
                } else {
                    InsertTreeNode(root.getLeftChild(), newpoint);
                }
            } else {
                if (root.getRightChild() == null) {
                    root.setRightChild(newpoint);
                } else {
                    InsertTreeNode(root.getRightChild(), newpoint);
                }
            }
        }

    }

    public void visit(BinaryTreeNode <T> current) {
        if (current != null && current.getData() != null) {
            System.out.print(current.getData());
        } else {
            System.out.println("null");
        }
    }

    public void breadthFirstSearch(BinaryTreeNode <T> root) {
        Queue <BinaryTreeNode <T>> queue = new LinkedList <BinaryTreeNode <T>>();
        BinaryTreeNode <T> pointer = root;
        if (pointer != null) {
            queue.add(pointer);
        }
        while (!queue.isEmpty()) {
            pointer = queue.peek();
            visit(pointer);
            queue.remove();
            if (pointer.getLeftChild() != null) {
                queue.add(pointer.getLeftChild());
            }
            if (pointer.getRightChild() != null) {
                queue.add(pointer.getRightChild());
            }
        }
    }

    public void preOrder() {
        preOrder(this.root);
    }

    private void preOrder(BinaryTreeNode <T> root) {
        if (root != null) {
            visit(root);
            preOrder(root.getLeftChild());
            preOrder(root.getRightChild());
        }
    }

    public void inOrder() {
        inOrder(this.root);
    }

    private void inOrder(BinaryTreeNode <T> root) {

        if (root != null) {
            inOrder(root.getLeftChild());
            visit(root);
            inOrder(root.getRightChild());
        }
    }

    public void postOrder() {
        postOrder(this.root);
    }

    private void postOrder(BinaryTreeNode <T> root) {
        if (root != null) {
            postOrder(root.getLeftChild());
            postOrder(root.getRightChild());
            visit(root);
        }
    }

    /*
     * 非递归方式实现的前序遍历
     */
    public void NPreOrder(BinaryTreeNode <T> root) {


        Stack <BinaryTreeNode <T>> stack = new Stack <BinaryTreeNode <T>>();
        BinaryTreeNode <T> pointer = root;
        /*
         * 当前节点不为空，就一直放入队尾；当前节点为空时，访问队首元素，然后访问做孩子节点；然后弹出，再对新的队首元素进行判断
         */
        while (!stack.isEmpty() || pointer != null) {
            if (pointer != null) {
                visit(pointer);
                if (pointer.getRightChild() != null) {
                    stack.push(pointer.getRightChild());
                } else {
                    pointer = pointer.getLeftChild();
                }
            } else {
                pointer = stack.pop();
            }
        }
    }

    /*
     * 非递归方式实现的中序遍历
     */
    public void NInOrder(BinaryTreeNode <T> root) {


        Stack <BinaryTreeNode <T>> stack = new Stack <BinaryTreeNode <T>>();
        BinaryTreeNode <T> pointer = root;
        /*
         * 当前节点不为空，就一直进栈；当前节点为空时，访问栈顶元素，然后再访问右孩子节点
         */
        while (!stack.isEmpty() || pointer != null) {
            if (pointer != null) {
                stack.push(pointer);
                pointer = pointer.getLeftChild();
            } else {
                pointer = stack.peek();
                visit(pointer);
                pointer = pointer.getRightChild();
                stack.pop();
            }
        }
    }

    /*
     * 非递归方式实现的后序遍历二叉树
     */
    public void NPostOrder(BinaryTreeNode <T> root) {
        if (root == null) {
            return;
        }
        Stack <BinaryTreeNode <T>> stack = new Stack <BinaryTreeNode <T>>();//初始化栈，用于保存带访问的节点
        BinaryTreeNode <T> pointer = root;                               //保存根节点
        BinaryTreeNode <T> preNode = root;                               //保存前一个被访问的节点
        while (!stack.isEmpty() || pointer != null) {
            //若当前节点不空，就一直进栈，然后继续向左走
            while (pointer.getLeftChild() != null) {
                stack.push(pointer);
                pointer = pointer.getLeftChild();
            }
            /*
             * 当前节点为空时，分两种情况：
             * 1、当前节点移动到栈顶处，然后访问栈顶元素的右节点
             * 2、当前节点移动到栈顶，但是栈顶元素没有右节点，这就需要弹出栈顶元素，再对此元素访问；
             * 然后再对新的栈顶元素进行判断即可
             */
            while (pointer != null && (pointer.getRightChild() == null) || (pointer.getRightChild() == preNode)) {
                visit(pointer);
                preNode = pointer;
                if (stack.isEmpty()) {
                    return;
                }
                pointer = stack.peek();
                stack.pop();
            }
            stack.push(pointer);
            pointer = pointer.getRightChild();

        }
    }

    //判断二叉树是否是完全二叉树
    public boolean is_complete() {
        Queue <BinaryTreeNode <T>> queue = new PriorityQueue <BinaryTreeNode <T>>();

        if (this.root == null) {
            return false;
        }
        //按需加入结点及其左右子节点，若队列非空时遇到空结点
        //说明在顺序队列中间出现了空结点，则为非完全二叉树
        queue.add(this.root);
        while (!queue.isEmpty()) {
            try {
                BinaryTreeNode <T> node = queue.remove();
                queue.add(node.getLeftChild());
                queue.add(node.getRightChild());
            } catch (NoSuchElementException e) {
                if (!queue.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    //交换子结点顺序

    public void exchangeNodePre(BinaryTreeNode <T> root) {
        BinaryTreeNode <T> temp;

        if (root == null) {
            return;
        }

        temp = root.getLeftChild();
        root.setLeftChild(root.getLeftChild());
        root.setRightChild(temp);

        exchangeNodePost(root.getLeftChild());
        exchangeNodePost(root.getRightChild());

    }

    public void exchangeNodePost(BinaryTreeNode <T> root) {
        BinaryTreeNode <T> temp;

        if (root == null) {
            return;
        }

        exchangeNodePost(root.getLeftChild());
        exchangeNodePost(root.getRightChild());

        temp = root.getLeftChild();
        root.setLeftChild(root.getLeftChild());
        root.setRightChild(temp);
    }

    public int height() {
        return binaryTreeHeight(root);
    }

    private int binaryTreeHeight(BinaryTreeNode node) {
        int height = 0;
        if (node != null) {
            int leftHeight = binaryTreeHeight(node.getLeftChild());
            int rightHeight = binaryTreeHeight(node.getRightChild());
            height = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
        }
        return height;
    }

    public int width() {
        int width = 0;

        if (root == null) {
            return width;
        }

        int nLastLevelWidth = 0;//记录上一层的宽度
        int nCurLevelWidth = 0;//记录当前层的宽度  

        Queue <BinaryTreeNode <T>> queue = new LinkedList <BinaryTreeNode <T>>();
        queue.offer(root);
        width = 1;
        nLastLevelWidth = 1;

        while (!queue.isEmpty())//队列不空
        {
            while (nLastLevelWidth != 0) {
                BinaryTreeNode <T> temp = queue.poll();//取出队列头元素

                if (temp.getLeftChild() != null)
                    queue.offer(temp.getLeftChild());

                if (temp.getRightChild() != null)
                    queue.offer(temp.getRightChild());
                nLastLevelWidth--;
            }

            nCurLevelWidth = queue.size();
            width = nCurLevelWidth > width ? nCurLevelWidth : width;
            nLastLevelWidth = nCurLevelWidth;
        }

        return width;
    }

    public HashMap <BinaryTreeNode <T>, Integer> getNodesLevel() {
        Queue <BinaryTreeNode <T>> queue = new LinkedList <BinaryTreeNode <T>>();

        if (this.root == null) {
            return null;
        }

        BinaryTreeNode <T> root = this.root;

        levels.put(root, 0);
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode <T> node = queue.poll();
            if (node.getLeftChild() != null) {
                queue.offer(node.getLeftChild());
                levels.put(node.getLeftChild(), levels.get(node) + 1);
            }
            if (node.getRightChild() != null) {
                queue.offer(node.getRightChild());
                levels.put(node.getRightChild(), levels.get(node) + 1);
            }
        }
        return levels;
    }

    //算术表达式求值，待修改
    public static ArrayList <String> getNodesequence() {
        return null;
    }

    /**
     * 给定一个树结点，求从根节点到此节点路径上的所有结点
     * 基本思想，类比非递归后序遍历的方法，当便利到这个节点时，
     * stack中剩余的结点即为路径的倒序
     */

    public List <BinaryTreeNode <T>> getPath(T data) {
        return getPath(new BinaryTreeNode <T>(data));
    }

    public List <BinaryTreeNode <T>> getPath(BinaryTreeNode <T> node) {
        List <BinaryTreeNode <T>> nodes = new LinkedList <BinaryTreeNode <T>>();
        if (this.root == null) {
            return null;
        }
        Stack <BinaryTreeNode <T>> stack = new Stack <BinaryTreeNode <T>>();//初始化栈，用于保存带访问的节点
        BinaryTreeNode <T> pointer = this.root;                               //保存根节点
        BinaryTreeNode <T> preNode = this.root;                               //保存前一个被访问的节点

        while (!stack.isEmpty() || pointer != null) {
            //如果元素相等，退出循环
            if(pointer == node){
                break;
            }

            while (pointer.getLeftChild() != null) {
                pointer.setTag(BinaryTreeNode.Tag.Left);
                stack.push(pointer);
                pointer = pointer.getLeftChild();
            }
            pointer = stack.pop();
            if (pointer.getTag() == BinaryTreeNode.Tag.Left){
                pointer.setTag(BinaryTreeNode.Tag.Right);
                stack.push(pointer);
                pointer = pointer.getRightChild();
            }else{
                visit(pointer);
            }
        }

        if (!stack.isEmpty()){
            while(!stack.isEmpty()){
                nodes.add(stack.pop());
            }
        }
        return nodes;
    }

    


    public static void main(String[] args) {
        BinaryTree <Integer> btree = new BinaryTree <Integer>();
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (Integer num : nums) {
            btree.InsertTreeNode(num);
        }
        btree.NPostOrder(btree.root);

    }
}
