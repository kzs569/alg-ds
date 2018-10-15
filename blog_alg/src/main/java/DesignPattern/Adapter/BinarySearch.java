package DesignPattern.Adapter;

import java.util.Scanner;

public class BinarySearch implements DataOperation {

    public void sort(int[] array) {

    }


    public void search(int[] array) {
        QuickSort sort = new QuickSort();
        sort.sort(array);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number you want to search:\n");
        int x = scanner.nextInt();
        int index = binarySearch(array,0,array.length - 1,x);
        if (index == -1){
            System.out.println("the element is not found!");
        }else{
            System.out.println("the element has been found!");
        }

    }

    private static int binarySearch(int[] a, int l,int r,int x) {
        if(l<=r){//注意：这里有“=”号
            int middle=l+(r-l)/2;
            if(a[middle]==x){
                return  middle;

            }
            else if(a[middle]<x){
                return binarySearch(a,middle+1,r,x);

            }
            else{
                return binarySearch(a, l, middle-1, x);
            }

        }
        return -1;
    }

}
