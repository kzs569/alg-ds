package Algorithm.Sort;

public class InsertSort extends Sort {
    @Override
    public void sort(Comparable[] a) {
        if (a.length == 0) {
            return;
        }

        Comparable temp;

        for (int i = 0; i < a.length; i++) {
            temp = a[i];
            int j = i - 1;
            while (j >= 0 && less(temp, a[j])) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        Integer arr[] = new Integer[]{ 61, 17, 29, 22, 34, 60, 72, 21, 50, 1, 62 };
        BubbleSort sort = new BubbleSort();
        sort.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
