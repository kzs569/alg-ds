package DataStructure;

public class Tree <T>{

    private class TreeNode<T>{
        private T value;
        private TreeNode<T> leftMostChild;
        private TreeNode<T> rightSibling;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public boolean isLeaf(){
            if(this.leftMostChild == null){
                return true;
            }
            return false;
        }

        public void setLeftMostChild(TreeNode<T> leftMostChild) {
            this.leftMostChild = leftMostChild;
        }

        public void setRightSibling(TreeNode<T> rightSibling) {
            this.rightSibling = rightSibling;
        }

        public void insertFirst(TreeNode<T> node){
            this.leftMostChild = node;
        }

        public void insertNext(TreeNode<T> node){
            this.rightSibling = node;
        }
    }

    private TreeNode<T> root;

    public Tree(TreeNode<T> node) {
        this.root = node;
    }


}
