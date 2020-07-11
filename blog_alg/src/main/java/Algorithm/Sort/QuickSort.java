package Algorithm.Sort;

public class QuickSort extends Sort {
    @Override
    public void sort(Comparable[] a) {
        quicksort(a,0,a.length - 1);
    }

    private void quicksort(Comparable[] a, int left, int right) {
        if (left >= right || a == null || a.length <= 1) {
            return;
        }

        int dp = partition(a,left,right);
        quicksort(a,left,dp-1);
        quicksort(a,dp+1,right);
    }

    public int partition(Comparable[] a,int left, int right) {
        Comparable pivot = a[left];
        while (left < right) {
            while (left < right && a[right].compareTo(pivot) >= 0)
                right--;
            if (left < right)
                a[left++] = a[right];
            while (left < right && a[left].compareTo(pivot) <= 0)
                left++;
            if (left < right)
                a[right--] = a[left];
        }
        a[left] = pivot;
        return left;
    }

    public static void main(String[] args) {
        Integer arr[] = new Integer[]{ 61, 17, 29, 22, 34, 60, 72, 21, 50, 1, 62 };
        QuickSort sort = new QuickSort();
        sort.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
