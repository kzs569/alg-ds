package Algorithm.BTreeAlg;

import DataStructure.Tree.BinaryTree;
import DataStructure.Stack;

import java.util.ArrayList;

public class ArithmeticExpression {

    public static String[] str = new String[]{"25","4","+","3","3","4","+","*","4","+","*"};

    public static int arithexpr(BinaryTree<String> btree){
        btree.postOrder();
        ArrayList<String> exprlist = btree.getNodesequence();

        String[] param = new String[exprlist.size()];
        param = exprlist.toArray(param);

        int res = arithexpr(param);
        return res;
    }

    public static int arithexpr(String[] str){
        Stack<Integer> stack = new Stack <Integer>(str.length + 1);
        for(String s : str){
            try {
                Integer number = Integer.parseInt(s);
                stack.push(number);
            }catch(NumberFormatException e){
                if(s == "+"){
                    int addend = stack.pop();
                    int augend = stack.pop();
                    stack.push(addend + augend);
                }
                else if(s == "-"){
                    int minuend = stack.pop();
                    int subtrahend = stack.pop();
                    stack.push(subtrahend - minuend);
                }
                else if(s == "*"){
                    int multiplier = stack.pop();
                    int multiplicand = stack.pop();
                    stack.push(multiplicand * multiplier);
                }
                else if(s == "/"){
                    int divisor = stack.pop();
                    int dividend = stack.pop();
                    stack.push(dividend / divisor);
                }else{
                    throw new IllegalArgumentException();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        if(!stack.isEmpty()) {
            int result = stack.pop();
            return result;
        }else{
            System.out.println("expression is wrong!!");
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(arithexpr(str));
    }
}
