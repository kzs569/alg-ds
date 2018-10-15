package DataStructure.Tree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * MinHeap: parent node is the minimal
 */
public class MinHeap<T extends Comparable<? super T>>
{
    private ArrayList<T> heapArray;
    private int currentSize;
    private int maxSize;

    /**
     * initial a heap with its maxsize
     * @param maxSize
     */
    public MinHeap(int maxSize) {

        if(maxSize <= 0){
            return;
        }
        this.currentSize = 0;
        this.maxSize = maxSize;
        heapArray = new ArrayList <T>(maxSize);
        buildHeap();
    }


    public MinHeap(ArrayList<T> array, int n){
        heapArray = new ArrayList <T>(array);
        heapArray.ensureCapacity(n + 1);
        this.currentSize = heapArray.size();
        buildHeap();
    }

    private void buildHeap(){
        for (int i = this.currentSize/2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int left){
        int i = left;
        int j = leftChild(i);
        T temp = this.heapArray.get(i);
        while(j < this.currentSize){
            if((j < this.currentSize - 1) && (this.heapArray.get(j).compareTo(this.heapArray.get(j + 1)) > 0)){
                j++;
            }
            if(temp.compareTo(this.heapArray.get(j)) > 0){
                this.heapArray.set(i, heapArray.get(j));
                i = j;
                j = leftChild(j);
            }else {
                break;
            }
        }
        heapArray.set(i,temp);
    }

    public boolean insert(T node){
        if(this.currentSize == this.maxSize){
            return false;
        }
        this.heapArray.set(this.currentSize, node);
        siftUp(this.currentSize);
        this.currentSize++;
        return true;
    }

    private void siftUp(int pos){
        int tempPos = pos;
        T temp = heapArray.get(tempPos);
        while((tempPos > 0) && (heapArray.get(parent(tempPos))).compareTo(temp) > 0){
            this.heapArray.set(tempPos,this.heapArray.get(parent(tempPos)));
            tempPos = parent(tempPos);
        }
        heapArray.set(tempPos, temp);
    }

    public boolean isLeaf(int pos){
        return (pos >= this.currentSize/2) && (pos < this.currentSize);
    }

    public T removeMin(){
        if (this.currentSize == 0){
            System.out.println("Heap has no element!");
            return null;
        }
        else{
           swap(0,--this.currentSize);
           if(this.currentSize > 1){
               siftDown(0);
           }
           return heapArray.get(currentSize);
        }
    }

    public boolean remove(int pos, T node){
        if((pos < 0)||(pos >= this.currentSize)){
            return false;
        }
        node = heapArray.get(pos);
        heapArray.set(pos,heapArray.get(--this.currentSize));
        if (heapArray.get(parent(pos)).compareTo(heapArray.get(pos)) > 0){
            siftUp(pos);
        }else{
            siftDown(pos);
        }
        return true;
    }

    private void swap(int a,int b){
        T temp = this.heapArray.get(a);
        this.heapArray.set(a, this.heapArray.get(b));
        this.heapArray.set(b, temp);
    }

    public boolean isEmpty(){
        return (this.currentSize == 0);
    }

    public int size(){
        return this.currentSize;
    }

    private int leftChild(int pos) {
        return pos * 2 + 1;
    }

    private int rightChild(int pos) {
        return (pos + 1) * 2;
    }

    private int parent(int pos){
        return (pos - 1)/2;
    }


    public void printHeap(){
        while(!isEmpty()){
            System.out.println(removeMin().toString());
        }
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{28,13,14,12,15,17,18};
        ArrayList<Integer> list = new ArrayList <Integer>(Arrays.asList(data));
        MinHeap<Integer> mheap = new MinHeap <Integer>(list, 20);
        mheap.printHeap();
    }
}