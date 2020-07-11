package Algorithm.Sort;

public class RadixSort {
    private int decimal = 10;

    public void sort(int[] array){
        int maxb = maxbit(array);
        for (int i = 0; i < maxb; i++) {
            // 存储"被排序数据"的临时数组
            int[] output = new int[array.length];
            int[] buckets = new int[decimal];

            // 将数据出现的次数存储在buckets[]中
            for (int j = 0; j < array.length; j++)
                buckets[ (array[j]/(int)Math.pow(decimal,i))%10 ]++;

            // 更改buckets[i]。目的是让更改后的buckets[i]的值，是该数据在output[]中的位置。
            for (int j = 1; j < decimal; j++)
                buckets[j] += buckets[j - 1];

            // 将数据存储到临时数组output[]中
            for (int j = array.length - 1; j >= 0; j--) {
                output[buckets[ (array[j]/(int)Math.pow(decimal,i))%10 ] - 1] = array[j];
                buckets[ (array[j]/(int)Math.pow(decimal,i))%10 ]--;
            }

            // 将排序好的数据赋值给a[]
            for (int j = 0; j < array.length; j++)
                array[j] = output[j];

            output = null;
            buckets = null;
        }
    }
    /**
     * 求数组的最大位数
     */
    public int maxbit(int[] array){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if(max < array[i]){
                max = array[i];
            }
        }
//        int bit = 1;
//
//        while(max >= decimal){
//            max = max/decimal;
//            ++bit;
//        }
        return (int)Math.ceil(Math.log(max)/Math.log(10.0));
    }

    public static void main(String[] args) {
        int arr[] = new int[]{ 61, 17, 29, 22, 34, 60, 72, 21, 50, 1, 62, 123, 456687, 123478, 45678111 };
        RadixSort sort = new RadixSort();
        sort.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
