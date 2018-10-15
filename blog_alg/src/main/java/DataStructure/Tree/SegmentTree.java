package DataStructure.Tree;

public class SegmentTree {
    Node root;
    /*
     * 外部接口
     * 建立一棵线段树
     * */
    public void build(int left,int right){
        root = new Node(left, right);
        build(root);
    }
    /*
     * 内部接口
     * 建立一棵线段树
     * */
    private void build(Node root) {
        int left = root.left;
        int right = root.right;
        //root节点为叶子节点
        if (right - left == 1) {
            return;
        } else if (right - left > 1) {
            int mid = (left + right) >> 1;////将左右区间平分
            Node leftNode = new Node(left, mid);
            Node rightNode = new Node(mid , right);
            root.leftChild = leftNode;
            root.rightChild = rightNode;
//        递归的创建左右子树
            build(leftNode);
            build(rightNode);
        }
    }

    /*
     * 插入一条线段[c,d]的外部接口
     * c为左端点
     * d为右端点
     * root 为此线段树的根节点
     * */
    public void insert(int c, int d) {
        insert(c,d,root);
    }
    /*
     * 插入一条线段[c,d]的内部接口
     * c为左端点
     * d为右端点
     * root 为此线段树的根节点
     * */
    private void insert(int c, int d, Node node) {
        if(node==null||c<node.left||d>node.right){
            System.out.println("输入的参数不合法!"+"c:"+c+" "+"d:"+d);
            System.out.println("root:"+node.left+" "+node.right);

            return ;
        }
        if(node.left==c&&node.right==d)
        {
            node.count++;
            node.cover=true;
            return;
        }
        int mid=(node.left+node.right)>>1;
        if(d<=mid){
            insert(c,d,node.leftChild);
        }

        else if(c>=mid)
            insert(c,d,node.rightChild);
        else {
            insert(c,mid,node.leftChild);
            insert(mid,d,node.rightChild);
        }
    }

    /*
     * 删除一条线段[c,d]的外部接口
     * c:删除线段的左端点
     *d:删除线段的右端点
     * root:删除线段树的根结点
     * */
    public  void delete(int c, int d){
        delete(c,d,root);
    }
    /*
     * 删除一条线段[c,d]
     * c:删除线段的左端点
     *d:删除线段的右端点
     * root:删除线段树的根结点
     * */
    private void delete(int c, int d, Node node) {
        if (node == null || c < node.left || d > node.right)
        {
            System.out.println("输入的参数不合法!");
            return;
        }
        if(c==node.left&&d==node.right)
        {
            node.count--;
            if(node.count==0)
                node.cover=false;
            return;
        }
        int mid=(node.left+node.right)>>1;
        if(d<=mid)
            delete(c,d,node.leftChild);
        else if(c>=mid)
            delete(c,d,node.rightChild);
        else {
            delete(c,mid,node.leftChild);
            delete(mid,d,node.rightChild);//注意不是mid+1，比如区间【1,10】的左右两部分分别是【1,5】，【5,10】
        }
    }
    /*
     * 前序遍历
     * 外部接口
     * */
    public void preOrder(){
        preOrder(root);
    }

    /*
     * 前序遍历
     * 内部接口
     * */
    private void preOrder(Node root){
//        叶子节点
        if(root.right-root.left==1) {
            System.out.println("["+root.left+","+root.right+"]:"+root.count);
            return ;
        }
        else if(root.right-root.left>1){
            System.out.println("["+root.left+","+root.right+"]:"+root.count);
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }
    }

    /*
     * 外部接口
     * 统计线段树中cover为true的线段的总长度
     * */
    public int Count(){
        return Count(root);
    }

    /*
     * 内部接口
     * 统计线段树中cover为true的线段的总长度
     * */
    private int Count(Node node){
        if(node.cover==true)//不继续往下查找，否则会重复
            return node.right-node.left;
        else {
            if(node.right-node.left==1)
                return 0;
            else
                return Count(node.leftChild)+Count(node.rightChild);
        }
    }


    //    线段树的树节点
    class Node {
        int left, right;//左右区间的值
        boolean cover;//表示是否被覆盖
        int count;//表示此节点表示的线段区间出现的次数（被覆盖的次数），默认为0
        Node leftChild;
        Node rightChild;

        Node(int left, int right) {
            this.left = left;
            this.right = right;
            count = 0;
            cover=false;
        }
    }

    public static void main(String[] args) {
        SegmentTree segmentTree=new SegmentTree();
        segmentTree.build(1,10);
        segmentTree.insert(3,5);
        segmentTree.insert(3,5);
        segmentTree.insert(2,5);
        segmentTree.insert(3,9);
        segmentTree.insert(1,10);
        //[2,5]被分为[2,3],[3,5]
        //[3,9]被分为[3,5],[5,9]
        //[5,9]被分为[5,7],[7,8]+[8,9]
        System.out.println("前序遍历线段树:");
        segmentTree.preOrder();

        System.out.println("删除线段:");
        segmentTree.delete(1,10);
        segmentTree.delete(3,5);

        System.out.println("前序遍历线段树:");
        segmentTree.preOrder();

        System.out.println("被覆盖的长度:"+segmentTree.Count());
        //        输出为[2,3]+[3,5]+[5,7]+[7,8]+[8,9]=7
    }
}
