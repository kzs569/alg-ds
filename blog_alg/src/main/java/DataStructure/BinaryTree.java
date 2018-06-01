package DataStructure;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class BinaryTree<T extends Comparable<? super T>> {

    private class BinaryTreeNode<T extends Comparable<? super T>>{
        private T data;
        private BinaryTreeNode<T> leftchild;
        private BinaryTreeNode<T> rightchild;

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
        }

        public BinaryTreeNode(T data) {
            this.data = data;
            this.leftchild = null;
            this.rightchild = null;
        }

        public BinaryTreeNode(T data, BinaryTreeNode<T> leftchild, BinaryTreeNode<T> rightchild) {
            this.data = data;
            this.leftchild = leftchild;
            this.rightchild = rightchild;
        }

        public BinaryTreeNode<T> getLeftchild() {
            return leftchild;
        }

        public void setLeftchild(BinaryTreeNode<T> leftchild) {
            this.leftchild = leftchild;
        }

        public BinaryTreeNode<T> getRightchild() {
            return rightchild;
        }

        public void setRightchild(BinaryTreeNode<T> rightchild) {
            this.rightchild = rightchild;
        }

        public boolean isLeaf(){
            if (this.leftchild == null && this.rightchild == null){
                return true;
            }
            return false;
        }
    }

    private BinaryTreeNode<T> root;

    public BinaryTree() {
        BinaryTreeNode<T> node = new BinaryTreeNode<T>();
        this.root = node;
    }

    public boolean isEmpty(){
        if(root == null){
            return true;
        }
        return false;
    }

    public BinaryTreeNode<T> getRoot() {
        return this.root;
    }

    public void InsertTreeNode(BinaryTreeNode<T> root,T data){
        this.InsertTreeNode(root,new BinaryTreeNode<T>(data));
    }

    public void InsertTreeNode(BinaryTreeNode<T> root,BinaryTreeNode<T> newpoint){
        BinaryTreeNode<T> point = null;
        System.out.println("the insert node"+newpoint.getData().toString());
        /**
         * 如果是空树，则用指针newpoint作为指针
         **/
        if(this.root == null || this.root.getData() == null){
            this.root = newpoint;
            return;
        }else{
            point = root;
        }

        while(point != null){
            if(newpoint.getData() == point.getData()){
                return;
            }else if(newpoint.getData().compareTo(point.getData()) < 0){
                if(point.getLeftchild() == null){
                    point.setLeftchild(newpoint);
                    return;
                }else{
                    point = point.getRightchild();
                }
            }else if(newpoint.getData().compareTo(point.getData()) > 0){
                if(point.getRightchild() == null){
                    point.setRightchild(newpoint);
                    return;
                }else{
                    point = point.getRightchild();
                }
            }
        }
    }

    public void visit(BinaryTreeNode<T> current){
        if(current != null && current.getData() != null){
            System.out.println(current.getData());
        }else{
            System.out.println("null");
        }
    }

    public void breadthFirstSearch(BinaryTreeNode<T> root){
        Queue<BinaryTreeNode<T>> queue = new LinkedBlockingQueue<BinaryTreeNode<T>>();
        BinaryTreeNode<T> pointer = root;
        if(pointer != null){
            queue.add(pointer);
        }
        while(!queue.isEmpty()){
            pointer = queue.peek();
            visit(pointer);
            queue.remove();
            if(pointer.leftchild != null){
                queue.add(pointer.leftchild);
            }
            if(pointer.rightchild != null){
                queue.add(pointer.rightchild);
            }
        }
    }

    public void preOrder(BinaryTreeNode<T> root){
        visit(root);
        preOrder(root.getRightchild());
        preOrder(root.getRightchild());
    }

    public void inOrder(BinaryTreeNode<T> root){
        inOrder(root.getLeftchild());
        visit(root);
        inOrder(root.getRightchild());
    }

    public void postOrder(BinaryTreeNode<T> root){
        postOrder(root.getLeftchild());
        postOrder(root.getRightchild());
        visit(root);
    }

    /*
     * 非递归方式实现的前序遍历
     */
    public void NPreOrder(BinaryTreeNode<T> root){
        Queue<BinaryTreeNode<T>> queue=new LinkedBlockingQueue<BinaryTreeNode<T>>();
        BinaryTreeNode<T> pointer=root;
        /*
         * 当前节点不为空，就一直放入队尾；当前节点为空时，访问队首元素，然后访问做孩子节点；然后弹出，再对新的队首元素进行判断
         */
        while(!queue.isEmpty()||pointer!=null){
            if(pointer!=null){
                visit(pointer);
                if(pointer.getRightchild()!=null){
                    queue.add(pointer.getRightchild());
                }
                pointer=pointer.getLeftchild();
            }else{
                pointer=queue.poll();
            }
        }
    }

    /*
     * 非递归方式实现的中序遍历
     */
    public void NInOrder(BinaryTreeNode<T> root){
        Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
        BinaryTreeNode<T> pointer=root;
        /*
         * 当前节点不为空，就一直进栈；当前节点为空时，访问栈顶元素，然后再访问右孩子节点
         */
        while(!stack.isEmpty()||pointer!=null){
            if(pointer!=null){
                stack.push(pointer);
                pointer=pointer.getLeftchild();
            }else{
                pointer=stack.peek();
                visit(pointer);
                pointer=pointer.getRightchild();
                stack.pop();
            }
        }
    }
    /*
     * 非递归方式实现的后序遍历二叉树
     */
    public void NPostOrder(BinaryTreeNode<T> root){
        if(root == null){
            return;
        }
        Stack<BinaryTreeNode<T>> stack=new Stack<BinaryTreeNode<T>>();//初始化栈，用于保存带访问的节点
        BinaryTreeNode<T> pointer=root;                               //保存根节点
        BinaryTreeNode<T> preNode=root;                               //保存前一个被访问的节点
        while(!stack.isEmpty()||pointer!=null){
            //若当前节点不空，就一直进栈，然后继续向左走
            while(pointer.getRightchild()!=null){
                stack.push(pointer);
                pointer=pointer.getLeftchild();
            }
            /*
             * 当前节点为空时，分两种情况：
             * 1、当前节点移动到栈顶处，然后访问栈顶元素的右节点
             * 2、当前节点移动到栈顶，但是栈顶元素没有右节点，这就需要弹出栈顶元素，再对此元素访问；
             * 然后再对新的栈顶元素进行判断即可
             */
            while(pointer!=null&&(pointer.getRightchild()==null)||(pointer.getRightchild()==preNode)){
                visit(pointer);
                preNode=pointer;
                if(stack.isEmpty()){
                    return;
                }
                pointer=stack.peek();
                stack.pop();
            }
            stack.push(pointer);
            pointer=pointer.getRightchild();

        }
    }


    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        for (int i = 1; i < 10; i++) {
            tree.InsertTreeNode(tree.root, new Integer(i));
        }
        System.out.println("-----------下面是广度优先遍历二叉树--------------");
        tree.breadthFirstSearch(tree.root);
        System.out.println("-----------下面是非递归的前序遍历方式-------------");
        tree.NPreOrder(tree.root);
        System.out.println("-----------下面是非递归的中序遍历方式-------------");
        tree.NInOrder(tree.root);
        System.out.println("-----------下面是非递归的后序遍历方式-------------");
        tree.NPostOrder(tree.root);
    }
}
