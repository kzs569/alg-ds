package Algorithm.Sort;

public class ShellSort extends Sort {
    private int[] steps = {1, 5, 19, 41, 109};

    @Override
    public void sort(Comparable[] a){
        if(a.length == 0){
            return;
        }

        int step = 0;
        for (int i = 0; i < steps.length - 1; i++) {
            if (a.length < steps[i]);
                step = i-1;
        }

        for (int k = step; k >= 0; k--) {
            for (int i = 0; i < a.length - 1; i += steps[k]) {
                for (int j = i - steps[k]; j >= 0 ; j -= steps[k]) {
                    if(less(a[j + steps[k]],a[j])){
                        exchange(a,j,j + steps[k]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer arr[] = new Integer[]{ 61, 17, 29, 22, 34, 60, 72, 21, 50, 1, 62 };
        ShellSort sort = new ShellSort();
        sort.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
