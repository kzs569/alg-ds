package DesignPattern.Adapter;

public class DataOperationAdapter implements Operation {

    DataOperation dataOp;

    public DataOperationAdapter(String opType){
        if(opType.equalsIgnoreCase("sort")){
            dataOp = new QuickSort();
        }
        if(opType.equalsIgnoreCase("search")){
            dataOp = new BinarySearch();
        }
    }

    @Override
    public void operation(String opType, int[] x) {
        if(opType.equalsIgnoreCase("sort")){
            dataOp.sort(x);
        }
        if(opType.equalsIgnoreCase("search")){
            dataOp.search(x);
        }
    }
}
