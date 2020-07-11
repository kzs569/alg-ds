package DataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int n;

    private class Node<T>{
        T item;
        Node<T> next;
    }

    public Queue(){
        this.first = this.last = null;
        n = 0;
    }

    public void enqueue(T item){
        Node<T> oldlast = last;
        last = new Node<T>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }

    public T dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        T item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return n;
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T>{
        private Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item);
            s.append(',');
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> collection = new Queue<Integer>();
        int[] numbers = new int[]{1,2,3,4,5,6,7,8};
        for (int i = 0; i < numbers.length; i++) {
            collection.enqueue(numbers[i]);
        }

        System.out.println(collection.peek());

        for (int i = 0; i < collection.size(); i++) {
            System.out.println(collection.dequeue());
        }
    }

}
