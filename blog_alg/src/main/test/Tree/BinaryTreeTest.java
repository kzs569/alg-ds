package Tree;

import DataStructure.Tree.BinaryTree;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/** 
* BinaryTree Tester. 
* 
* @author <Authors name> 
* @version 1.0
*/ 
public class BinaryTreeTest { 
    BinaryTree btree = new BinaryTree();

@Before
public void before() throws Exception {
    System.out.println("@Before");
    Integer[] array = new Integer[]{1,2,3,4,5,6,7,8,9,10};
    ArrayList<Integer> nums = (ArrayList)Arrays.asList(array);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getNodesequence() 
* 
*/ 
@Test
public void testGetNodesequence() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setNodesequence(ArrayList <T> nodesequence) 
* 
*/ 
@Test
public void testSetNodesequence() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: isEmpty() 
* 
*/ 
@Test
public void testIsEmpty() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getRoot() 
* 
*/ 
@Test
public void testGetRoot() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: InsertTreeNode(BinaryTreeNode <T> root, T data) 
* 
*/ 
@Test
public void testInsertTreeNodeForRootData() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: InsertTreeNode(BinaryTreeNode <T> root, BinaryTreeNode <T> newpoint) 
* 
*/ 
@Test
public void testInsertTreeNodeForRootNewpoint() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: visit(BinaryTreeNode <T> current, ArrayList <T> nodesequence) 
* 
*/ 
@Test
public void testVisit() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: breadthFirstSearch(BinaryTreeNode <T> root) 
* 
*/ 
@Test
public void testBreadthFirstSearch() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: preOrder(BinaryTreeNode <T> root) 
* 
*/ 
@Test
public void testPreOrder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: inOrder(BinaryTreeNode <T> root) 
* 
*/ 
@Test
public void testInOrder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: postOrder(BinaryTreeNode <T> root) 
* 
*/ 
@Test
public void testPostOrder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: NPreOrder(BinaryTreeNode <T> root) 
* 
*/ 
@Test
public void testNPreOrder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: NInOrder(BinaryTreeNode <T> root) 
* 
*/ 
@Test
public void testNInOrder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: NPostOrder(BinaryTreeNode <T> root) 
* 
*/ 
@Test
public void testNPostOrder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: is_complete() 
* 
*/ 
@Test
public void testIs_complete() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: exchangeNodePre(BinaryTreeNode<T> root) 
* 
*/ 
@Test
public void testExchangeNodePre() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: exchangeNodePost(BinaryTreeNode<T> root) 
* 
*/ 
@Test
public void testExchangeNodePost() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: height() 
* 
*/ 
@Test
public void testHeight() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: width() 
* 
*/ 
@Test
public void testWidth() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: buildTree(ArrayList<T> midOrder, ArrayList<T> postOrder, int mstart, int mend, int pstart, int pend) 
* 
*/ 
@Test
public void testBuildTreeForMidOrderPostOrderMstartMendPstartPend() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = BinaryTree.getClass().getMethod("buildTree", ArrayList<T>.class, ArrayList<T>.class, int.class, int.class, int.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: buildTree(LinkedList<T> levelOrder, ArrayList<T> midOrder, int lstart, int lend) 
* 
*/ 
@Test
public void testBuildTreeForLevelOrderMidOrderLstartLend() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = BinaryTree.getClass().getMethod("buildTree", LinkedList<T>.class, ArrayList<T>.class, int.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: binaryTreeHeight(BinaryTreeNode node) 
* 
*/ 
@Test
public void testBinaryTreeHeight() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = BinaryTree.getClass().getMethod("binaryTreeHeight", BinaryTreeNode.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
