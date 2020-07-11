package DataStructure.Tree;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

public class BinaryTreePanel<T extends Comparable <? super T>> extends JPanel {
    private BinaryTree <T> btree;
    HashMap<BinaryTreeNode<T>, Integer> levels;

    private int gridWidth = 80;     //每个结点的宽度
    private int gridHeight = 20;    //每个结点的高度
    private int vGap = 50;          //每2个结点的垂直距离
    private int hGap = 30;          //每2个结点的水平距离

    private int startY = 10;        //根结点的Y，默认距离顶部10像素
    private int startX = 0;         //根结点的X，默认水平居中对齐

    private int childAlign;                     //孩子对齐方式
    public static int CHILD_ALIGN_ABSOLUTE = 0; //相对Panel居中
    public static int CHILD_ALIGN_RELATIVE = 1; //相对父结点居中

    private Font font = new Font("微软雅黑", Font.BOLD, 14);  //描述结点的字体

    private Color gridColor = Color.BLACK;      //结点背景颜色
    private Color linkLineColor = Color.BLACK;  //结点连线颜色
    private Color stringColor = Color.WHITE;    //结点描述文字的颜色

    /**
     * 默认构造
     */
    public BinaryTreePanel() {
        this(null, CHILD_ALIGN_ABSOLUTE);
    }

    /**
     * 根据传入的Node绘制树，以绝对居中的方式绘制
     *
     * @param btree 要绘制的树
     */
    public BinaryTreePanel(BinaryTree <T> btree) {
        this(btree, CHILD_ALIGN_ABSOLUTE);
    }

    /**
     * 设置要绘制时候的对齐策略
     *
     * @param childAlign 对齐策略
     */
    public BinaryTreePanel(int childAlign) {
        this(null, childAlign);
    }

    /**
     * 根据孩子对齐策略childAlign绘制的树的根结点n
     *
     * @param btree      要绘制的树的根结点
     * @param childAlign 对齐策略
     */
    public BinaryTreePanel(BinaryTree <T> btree, int childAlign) {
        super();
        this.btree = btree;
        this.childAlign = childAlign;
    }

    //重写而已，调用自己的绘制方法
    public void paintComponent(Graphics g){

        this.levels = btree.getNodesLevel();

        startX = (getWidth()-gridWidth)/2;
        super.paintComponent(g);
        g.setFont(font);
        drawNode(btree.root, startX, g);
    }

    public void drawNode(BinaryTreeNode <T> node, int x, Graphics g) {
        int y = levels.get(node) * (vGap + gridHeight) + startY;
        int fontY = y + gridHeight - 5;     //5为测试得出的值，你可以通过FM计算更精确的，但会影响速度

        g.setColor(gridColor);
        g.fillRect(x, y, gridWidth, gridHeight);    //画结点的格子

        g.setColor(stringColor);
        g.drawString(node.getData().toString(), x, fontY);       //画结点的名字

        if (!node.isLeaf()) {
            List <BinaryTreeNode<T>> childs = node.getChilds();
            int size = childs.size();
            int tempPosx = childAlign == CHILD_ALIGN_RELATIVE
                    ? x + gridWidth / 2 - (size * (gridWidth + hGap) - hGap) / 2
                    : (getWidth() - size * (gridWidth + hGap) + hGap) / 2;

            int i = 0;
            for (BinaryTreeNode<T> child : childs) {
                int newX = tempPosx + (gridWidth + hGap) * i; //孩子结点起始X
                g.setColor(linkLineColor);
                g.drawLine(x + gridWidth / 2, y + gridHeight, newX + gridWidth / 2, y + gridHeight + vGap);   //画连接结点的线
                drawNode(child, newX, g);
                i++;
            }
        }
    }

    public Color getGridColor() {
        return gridColor;
    }

    /**
     * 设置结点背景颜色
     *
     * @param gridColor 结点背景颜色
     */
    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    public Color getLinkLineColor() {
        return linkLineColor;
    }

    /**
     * 设置结点连接线的颜色
     *
     * @param gridLinkLine 结点连接线的颜色
     */
    public void setLinkLineColor(Color gridLinkLine) {
        this.linkLineColor = gridLinkLine;
    }

    public Color getStringColor() {
        return stringColor;
    }

    /**
     * 设置结点描述的颜色
     *
     * @param stringColor 结点描述的颜色
     */
    public void setStringColor(Color stringColor) {
        this.stringColor = stringColor;
    }

    public int getStartY() {
        return startY;
    }

    /**
     * 设置根结点的Y位置
     *
     * @param startY 根结点的Y位置
     */
    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getStartX() {
        return startX;
    }

    /**
     * 设置根结点的X位置
     *
     * @param startX 根结点的X位置
     */
    public void setStartX(int startX) {
        this.startX = startX;
    }

}
