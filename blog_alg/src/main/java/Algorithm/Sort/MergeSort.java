package Algorithm.Sort;

public class MergeSort extends Sort {
    public void sort(Comparable[] a) {
        Comparable[] ret = new Comparable[a.length];
        merge(a,ret,0,a.length-1);
    }

    public void merge(Comparable[] in,Comparable[] out,int start,int end){
        if(start >= end){
            return;
        }
        int len = end - start;
        int mid = (len >> 1) + start;
        merge(in,out,start,mid);
        merge(in,out,mid + 1, end);

        int i = start;
        int k = start;
        int j = mid + 1;

        while (i <= mid && j <= end)
            out[k++] = in[i].compareTo(in[j]) < 0 ? in[i++] : in[j++];
        while (i <= mid)
            out[k++] = in[i++];
        while (j <= end)
            out[k++] = in[j++];
        for (k = start; k <= end; k++)
            in[k] = out[k];
    }

    public void merge_sort(Comparable[] a){
        Comparable[] ret = new Comparable[a.length];

        for (int block = 1; block < a.length; block *= 2) {
            for (int start = 0; start < a.length; start += 2*block) {
                int low = start;
                int mid = (start + block) < a.length ? (start + block) : a.length;
                int high = (start + block) < a.length ? (start + 2 * block) :a.length;

                int i = low;
                int j = mid;
                int k = low;

                while (i < mid && j < high)
                    ret[k++] = a[i].compareTo(a[j]) < 0 ? a[i++] : a[j++];
                while (i < mid)
                    ret[k++] = a[i++];
                while (j < high)
                    ret[k++] = a[j++];
                for (k = low; k < high; k++)
                    a[k] = ret[k];
            }
        }
    }
}
