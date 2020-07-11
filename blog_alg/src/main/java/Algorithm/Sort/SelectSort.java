package Algorithm.Sort;

public class SelectSort extends Sort {
    @Override
    public void sort(Comparable[] a){
        if(a.length == 0){
            return;
        }
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i+ 1; j < a.length - 1; j++) {
                if(!less(a[min], a[j])){
                    min = j;
                }
            }
            exchange(a,i,min);
        }
    }
}
