package DataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {
    private T[] stack;
    private int number;

    public Stack(int cap){
        stack = (T[]) new Object[cap];
    }

    public boolean isEmpty(){
        return number == 0;
    }

    public int size(){
        return number;
    }

    public void push(T item){
        if(number == stack.length)
            resize(2*stack.length);
        stack[number++] = item;
    }

    public T pop(){
        T item = stack[--number];
        stack[number] = null;
        if (number > 0 && stack.length / 4 == number) {
            resize(stack.length/2);
        }
        return item;
    }

    public T peek(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        T item = stack[number];
        return item;
    }

    private void resize(int max){
        T[] temp = (T[]) new Object[max];
        for (int i = 0; i < number; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T>{
        private int i = number;

        public boolean hasNext() {
            return i > 0;
        }

        public T next() {
            return stack[--i];
        }

        public void remove() {

        }
    }

    private static class QueueFromStack {
        private Stack<Integer> stack1 = new Stack<Integer>(10);
        private Stack<Integer> stack2 = new Stack<Integer>(10);

        public void push(int node) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.peek());
                stack2.pop();
            }
            stack1.push(node);
        }

        public int pop() {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.peek());
                stack1.pop();
            }
            int result = stack2.pop();
            stack2.pop();
            return result;
        }
    }

    public static void main(String[] args) {
        QueueFromStack collection = new QueueFromStack();
        int[] numbers = new int[]{1,2,3,4,5,6,7,8};
        for (int i = 0; i < numbers.length; i++) {
            collection.push(numbers[i]);
        }

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(collection.pop());
        }
    }
}
