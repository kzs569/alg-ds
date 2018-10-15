package Tree;


import DataStructure.Tree.BinaryTree;
import DataStructure.Tree.BinaryTreePanel;

import javax.swing.*;
import java.awt.*;

public class TestDrawPanel extends JFrame{

    public TestDrawPanel(String title){
        super(title);
        initComponents();
    }

    public void initComponents(){
        BinaryTree<Integer> btree = new BinaryTree <Integer>();
        Integer[] nums = new Integer[]{1,2,3,4,5,6,7,8,9};
        for(Integer num : nums){
            btree.InsertTreeNode(num);
        }

        btree.inOrder();
        System.out.println();

        BinaryTreePanel panel = new BinaryTreePanel(btree,BinaryTreePanel.CHILD_ALIGN_RELATIVE);
        panel.setBackground(Color.WHITE);
        panel.setGridColor(Color.white);
        panel.setLinkLineColor(Color.BLACK);
        panel.setStringColor(Color.BLACK);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(2,1));
        contentPane.add(panel);

        add(contentPane,BorderLayout.CENTER);
    }

    public static void main(String[] args){
        TestDrawPanel frame = new TestDrawPanel("Test Tree Panel!");

        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
