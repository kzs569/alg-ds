package Algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.sqrt;

public class Eratosthenes {

    private Boolean[] flags;

    public Eratosthenes(int n) {

        n = n + 1;

        if (n <= 1) {
            System.out.println("There is no prime less than 2!");
        }

        flags = new Boolean[n];   //default all false
        Arrays.fill(flags, Boolean.TRUE);

        flags[0] = false;
        flags[1] = false;

        for (int i = 2; i < sqrt(n); i++) {
            if (flags[i]) {
                for (int j = i * i; j < n; j += i) {
                    flags[j] = false;
                }
            }
        }
    }

    public List <Integer> getPrimeList() {
        List <Integer> ret = new LinkedList <Integer>();

        for (int i = 0; i < flags.length; i++) {
            if (flags[i]) {
                ret.add(i);
            }
        }
        return ret;
    }

    public int getPrimeCount() {
        int count = 0;
        for (int i = 0; i < flags.length; i++) {
            if (flags[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Eratosthenes e = new Eratosthenes(19);
        List <Integer> t = e.getPrimeList();
        for (int i = 0; i < t.size(); i++) {
            System.out.println(t.get(i));
        }
    }
}
