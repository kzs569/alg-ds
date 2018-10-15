package Algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PrimeFactorization {

    Queue<Integer> primeQueue;
    int number;

    public PrimeFactorization(int n) {
        number = n;
        Eratosthenes era = new Eratosthenes(n);
        primeQueue = (LinkedList)era.getPrimeList();
    }

    public List<Integer> getPrimeFactorization(){

        List<Integer> ret = new LinkedList <Integer>();

        while(number!=1){
            int prime = primeQueue.element();
            if(number%prime == 0){
                number = number/prime;
                ret.add(prime);
            }else{
                primeQueue.poll();
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        PrimeFactorization pf = new PrimeFactorization(256);
        List<Integer> list = pf.getPrimeFactorization();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
