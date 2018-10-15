package DataStructure.Tree;

public class HuffmanTreeNode<T extends Comparable<? super T>> extends BinaryTreeNode {
    //权值或频率大小
    private double weight;
    private HuffmanTreeNode<T> parent;

    public HuffmanTreeNode(double weight,T data, HuffmanTreeNode<T> left,HuffmanTreeNode<T> right, HuffmanTreeNode<T> parent ){
        super(data, left, right);
        this.weight = weight;
        this.parent = parent;
    }

    public int compareTo(HuffmanTreeNode node){
        return Double.compare(this.weight, node.weight);
    }
}
