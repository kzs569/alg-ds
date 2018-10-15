package DataStructure.Tree;

import java.util.*;

public class HuffmanTree {

    private BinaryNode root;
    private int nodes;
    /**
    第一步：根据各个节点出现的频率来构造节点。初始时，根据每个频率构造一棵单节点的树
     */
    public List<BinaryNode> make_set(Integer[] frequency){
        List<BinaryNode> nodeList = new ArrayList<BinaryNode>(frequency.length);
        for(Integer i : frequency){
            nodeList.add(new BinaryNode(i,null,null,null));
        }
        nodes = (frequency.length << 1) - 1;
        return nodeList;
    }

    /**
     *第二步：将结点放到优先级队列(最小堆)中保存起来，这样易于每次选取当前两个最小频率的结点

     算法正式开始：

     第三步：从优先级队列中弹出两个结点，再创建一个新的结点作为这两个结点的父亲，新结点的频率为这两个结点的频率之和，并把新结点插入到优先级队列中

     第四步：重复步骤 3) 直到优先级队列中只剩下一个结点为止
     */
    public BinaryNode buildHuffmanTree(List<BinaryNode> roots){
        if(roots.size() == 1){
            return roots.remove(0);
        }
        PriorityQueue<BinaryNode> pq = new PriorityQueue<BinaryNode>(roots);
        while(pq.size() != 1){
            BinaryNode left = pq.remove();
            BinaryNode right = pq.remove();
            BinaryNode parent = new BinaryNode(left.frequency + right.frequency,left,right,null);
            left.parent = parent;
            right.parent = parent;
            pq.add(parent);
        }
        return (root = pq.remove());
    }

    public int huffman_cost(List<BinaryNode> nodeList){
        int cost = 0;
        int level;
        BinaryNode currentNode;
        for(BinaryNode binaryNode : nodeList){
            level = 0;
            currentNode = binaryNode;
            while(currentNode != root){
                currentNode = currentNode.parent;
                level++;
            }
            cost += level * binaryNode.frequency;
        }
        return cost;
    }

    public String huffmanEncoding(List<BinaryNode> nodeList){
        StringBuilder sb = new StringBuilder();
        BinaryNode currentNode;
        for(BinaryNode binaryNode : nodeList){
            currentNode = binaryNode;
            while(currentNode != root){
                if(currentNode.isLeftChild()) {
                    sb.append("0");
                } else if(currentNode.isRightChild()) {
                    sb.append("1");
                }
                currentNode = currentNode.parent;
            }
        }
        return sb.toString();
    }

    public Map<BinaryNode,String> huffmanDecoding(String encodeString){
        BinaryNode currentNode = root;
        Map<BinaryNode,String> node_Code = new HashMap<BinaryNode, String>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encodeString.length(); i++) {
            char codeChar = encodeString.charAt(i);
            sb.append(codeChar);
            if (codeChar == '0'){
                currentNode = currentNode.left;
            }else{
                currentNode = currentNode.right;
            }
            if(currentNode.left == null && currentNode.right == null){
                node_Code.put(currentNode,sb.toString());
                sb.delete(0,sb.length());
                currentNode = root;
            }
        }
        return node_Code;
    }

    private class BinaryNode implements Comparable<BinaryNode>{
        int frequency;
        BinaryNode left;
        BinaryNode right;
        BinaryNode parent;

        public BinaryNode(int frequency, BinaryNode left, BinaryNode right, BinaryNode parent) {
            this.frequency = frequency;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public int compareTo(BinaryNode o) {
            return frequency - o.frequency;
        }

        public boolean isLeftChild(){
            return parent != null && parent.left == this;
        }

        public boolean isRightChild(){
            return parent != null && parent.right == this;
        }
    }

    public static void main(String[] args) {
         Integer[] frequency = { 10, 15, 12, 3, 4, 13, 1 };
         //各个结点的初始频率
         HuffmanTree hc = new HuffmanTree();
         List<BinaryNode> nodeList = hc.make_set(frequency);
         //构造各个单节点树
         hc.buildHuffmanTree(nodeList);
         //构建huffman tree
         int totalCost = hc.huffman_cost(nodeList);
         //计算huffman tree的代价
         System.out.println(totalCost);
         String encodeStr = hc.huffmanEncoding(nodeList);
         //将各个叶子结点进行huffman 编码
         System.out.println("编码后的字符串" + encodeStr);

         //根据编码字符串解码
         Map<BinaryNode, String> decodeMap = hc.huffmanDecoding(encodeStr);
         Set<Map.Entry<BinaryNode, String>> entrys = decodeMap.entrySet();
        for (Map.Entry<BinaryNode, String> entry : entrys) {
                 BinaryNode node = entry.getKey();
                 String code = entry.getValue();
                 System.out.println("Node's frequency=" + node.frequency + " : " + code);
            }
    }
}


