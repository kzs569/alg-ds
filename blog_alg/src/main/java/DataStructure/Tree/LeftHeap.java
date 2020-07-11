package DataStructure.Tree;

public class LeftHeap<T extends Comparable <? super T>> {
    private LeftHeapNode <T> root;

    public LeftHeap() {
        this.root = null;
    }

    public LeftHeap(LeftHeapNode <T> root) {
        this.root = root;
    }

    /**
     * 合并堆
     *
     * @param rhs 另一个左式堆
     */

    public void merge(LeftHeap rhs) {
        if (this == rhs) {
            return;
        }
        root = merge(this.root, rhs.root);
        rhs.root = null;
    }

    /**
     * 合并两个左式堆(判断过程，真正合并过程由merge1操作)
     *
     * @param h1
     * @param h2
     * @return
     */
    private LeftHeapNode <T> merge(LeftHeapNode <T> h1, LeftHeapNode <T> h2) {
        if (h1 == null)
            return h2;
        if (h2 == null)
            return h1;
        if (h1.getData().compareTo(h2.getData()) > 0)
            return merge1(h2, h1);
        else
            return merge1(h1, h2);

    }

    /**
     * 合并两个左式堆的真正操作  h1的元素小于h2(即h2与h1的右子堆合并)
     *
     * @param h1
     * @param h2
     * @return
     */
    private LeftHeapNode <T> merge1(LeftHeapNode <T> h1, LeftHeapNode <T> h2) {
        if (h1.getLeftchild() == null) {//h1为单节点
            h1.setLeftchild(h2);
        } else {//h1不是单节点
            h1.setRightchild(merge(h1.getRightchild(), h2));
            if (h1.getLeftchild().getNpl() > h1.getRightchild().getNpl()) {//比较零路径长，确保左式堆性质不被破坏
                LeftHeapNode <T> temp = h1.getLeftchild();
                h1.setLeftchild(h1.getRightchild());
                h1.setRightchild(temp);
            }
            h1.setNpl(h1.getRightchild().getNpl() + 1);//零路径长为右儿子的零路径长+1
        }
        return h1;
    }

    /**
     * 找出最小元素
     *
     * @return
     */
    public T getMin() {
        if (isEmpty()) {
            System.out.println("该左式堆为空");
        }
        return root.getData();
    }


    /**
     * 删除最小元素
     *
     * @return
     */
    public T removeMin() {
        if (isEmpty()) {
            System.out.println("该左式堆为空");
            System.out.println("error!");
        }
        T min = root.getData();
        root = merge(root.getLeftchild(), root.getRightchild());
        return min;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 置空
     */
    public void makeEmpty() {
        this.root = null;

    }
    private void print(LeftHeapNode t) {
        if (t == null)
            return;
        print(t.getLeftchild());
        System.out.println(t.getData().toString() + ":" + String.valueOf(t.getData()));
        print(t.getRightchild());
    }

    public void insert(T x) {
        root = merge(root, new LeftHeapNode(x));
    }

    public static void main(String[] args) {
        int numItems = 100;
        LeftHeap <Integer> h = new LeftHeap<Integer>();
        LeftHeap <Integer> h1 = new LeftHeap <Integer>();
        int i = 37;
        for (i = 37; i != 0; i = (i + 37) % numItems)
            if (i % 2 == 0)
                h1.insert(i);
            else
                h.insert(i);

        h.merge(h1);
        for (i = 1; i < numItems; i++)
            if (h.removeMin() != i)
                System.out.println("Oops! " + i);
    }


}
