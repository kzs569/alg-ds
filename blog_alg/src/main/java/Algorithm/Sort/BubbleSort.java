package Algorithm.Sort;

public class BubbleSort extends Sort.Sort {
    @Override
    public void sort(Comparable[] a) {
        if(a.length == 0){
            return;
        }
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j].compareTo(a[j+1]) > 0){
                    exchange(a,j,j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer arr[] = new Integer[]{ 61, 17, 29, 22, 34, 60, 72, 21, 50, 1, 62 };
        Sort.BubbleSort sort = new Sort.BubbleSort();
        sort.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
