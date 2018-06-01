package Algorithm.Sort;

public class BucketSort extends Sort.Sort {
    @Override
    public void sort(Comparable[] arr) {
//        Comparable max=arr[0],min=arr[0];
//        for (Comparable a:arr) {
//            if (max.compareTo(a) < 0)
//                max = a;
//            if (min.compareTo(a) > 0)
//                min = a;
//        }
//        //该值也可根据实际情况选择
//        int bucketNum=max/10-min/10+1;
//        List buckList=new ArrayList<List<Integer>>();
//        //create bucket
//        for (int i=1;i<=bucketNum;i++){
//            buckList.add(new ArrayList<Integer>());
//        }
//        //push into the bucket
//        for (int i=0;i<arr.length;i++){
//            int index=indexFor(arr[i],10);
//            ((ArrayList<Integer>)buckList.get(index)).add(arr[i]);
//        }
//        ArrayList<Integer> bucket=null;
//        int index=0;
//        for (int i=0;i<bucketNum;i++){
//            bucket=(ArrayList<Integer>)buckList.get(i);
//            InsertSort sort = new InsertSort();
//            sort.sort(bucket);
//            for (int k : bucket) {
//                arr[index++]=k;
//            }
//        }
    }

    private int indexFor(Comparable a,int step){
//        return a/step;
        return 0;
    }
}
