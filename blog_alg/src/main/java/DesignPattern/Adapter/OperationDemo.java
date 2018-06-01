package DesignPattern.Adapter;

public class OperationDemo implements Operation{
    DataOperationAdapter adapter;

    public void operation(String opType, int[] x) {
        if (opType.equalsIgnoreCase("sort")
                ||opType.equalsIgnoreCase("search")){
            adapter = new DataOperationAdapter(opType);
            adapter.operation(opType,x);
        }else{
            System.out.println("Invalid operation!" + opType + "  is not supported!");
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3,4,5,62,13,4,5,7,8};
        OperationDemo demo = new OperationDemo();
        demo.operation("sort",array);
        demo.operation("search",array);
    }
}
