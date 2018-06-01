package DesignPattern.Adapter;

public class QuickSort implements DataOperation {
    @Override
    public void sort(int[] array) {
        quickSort(array,0,array.length - 1);
    }

    private void quickSort(int[] arr, int l, int r) {
        if(l<r){
            int q=partition(arr,l,r);
            quickSort(arr,l,q-1);
            quickSort(arr,q+1,r);
        }

    }

    private int partition(int[] arr, int l, int r) {
        /*
         * 选取第一个数组元素为主元
         * */
        int x=arr[l];
        int i=l;
        for(int j=l+1;j<=r;j++){
            if(arr[j]<=x){
                i++;
                swap(arr,i,j);

            }

        }
        swap(arr,i,l);

        return i;
    }

    private void swap(int[] arr, int i, int j) {
        if(i!=j){
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;

        }
    }

    @Override
    public void search(int[] array) {

    }
}
