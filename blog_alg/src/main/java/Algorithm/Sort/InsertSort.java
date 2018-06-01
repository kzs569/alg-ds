package Algorithm.Sort;

public class InsertSort extends Sort.Sort {
    @Override
    public void sort(Comparable[] a){
        if(a.length == 0){
            return;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = i - 1; j >= 0 ; j--) {
                if(less(a[j+1],a[j])){
                    exchange(a,j,j+1);
                }
            }
        }
    }
}
