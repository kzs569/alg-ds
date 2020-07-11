package DataStructure;
// https://www.jianshu.com/p/fab4f1a982ba
// author:_qazwsxedcrfv
import java.util.Arrays;

public class SuffixArray {

    private boolean isLongText = false;
    /**
     * 短文本解决方案，直接存储后缀数组
     */
    private Suffix[] suffixes;


    public SuffixArray(String text) {
        int n = text.length();
        this.suffixes = new Suffix[n];

        for (int i = 0; i < n; i++)
            suffixes[i] = new Suffix(text, i);
        Arrays.sort(suffixes);
    }

    /**
     * Returns the length of the input string.
     *
     * @return the length of the input string
     */
    public int length() {
        return suffixes.length;
    }

    /**
     * Returns the <em>i</em>th smallest suffix as a string.
     *
     * @param i the index
     * @return the <em>i</em> smallest suffix as a string
     * @throws java.lang.IllegalArgumentException unless {@code 0 <= i < n}
     */
    public String select(int i) {
            if (i < 0 || i >= suffixes.length)
                throw new IllegalArgumentException();
            return suffixes[i].toString();

    }

    /**
     * Returns the index into the original string of the <em>i</em>th smallest suffix.
     * That is, {@code text.substring(sa.index(i))} is the <em>i</em>th smallest suffix.
     *
     * @param i an integer between 0 and <em>n</em>-1
     * @return the index into the original string of the <em>i</em>th smallest suffix
     * @throws java.lang.IllegalArgumentException unless {@code 0 <= i < n}
     */
    public int index(int i) {
            if (i < 0 || i >= suffixes.length) throw new IllegalArgumentException();
            return suffixes[i].index;

    }

    /**
     * 返回后缀字符串suffixes[i]和suffixes[i-1]的最大公共前缀长度
     * <p>
     * Returns the length of the longest common prefix of the <em>i</em>th
     * smallest suffix and the <em>i</em>-1st smallest suffix.
     *
     * @param i an integer between 1 and <em>n</em>-1
     * @return the length of the longest common prefix of the <em>i</em>th
     * smallest suffix and the <em>i</em>-1st smallest suffix.
     * @throws java.lang.IllegalArgumentException unless {@code 1 <= i < n}
     */
    public int lcp(int i) {
            if (i < 1 || i >= suffixes.length)
                throw new IllegalArgumentException();
            return lcpSuffix(suffixes[i], suffixes[i - 1]);

    }

    // longest common prefix of s and t
    private static int lcpSuffix(Suffix s, Suffix t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) return i;
        }
        return n;
    }


    /**
     * Returns the number of suffixes strictly less than the {@code query} string.
     * We note that {@code rank(select(i))} equals {@code i} for each {@code i}
     * between 0 and <em>n</em>-1.
     *
     * @param query the query string
     * @return the number of suffixes strictly less than {@code query}
     */
    public int rank(String query) {
        int low = 0;
        int high = suffixes.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = compare(query, suffixes[mid]);

            if (cmp < 0) high = mid - 1;
            else if (cmp > 0) low = mid + 1;
            else return mid;
        }
        return low;
    }

    // compare query string to suffix
    private static int compare(String query, Suffix suffix) {
        int n = Math.min(query.length(), suffix.length());
        for (int i = 0; i < n; i++) {
            if (query.charAt(i) < suffix.charAt(i)) return -1;
            if (query.charAt(i) > suffix.charAt(i)) return +1;
        }
        return query.length() - suffix.length();
    }


    /**
     * Unit tests the {@code SuffixArray} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String input = "ladygaga";
        String s = input.replaceAll("\\s+", " ").trim();
        SuffixArray suffix = new SuffixArray(s);

        // StdOut.println("rank(" + args[0] + ") = " + suffix.rank(args[0]));

        System.out.println("  i ind lcp rnk select");
        System.out.println("---------------------------");

        for (int i = 0; i < s.length(); i++) {
            int index = suffix.index(i);
            String ith = "\"" + s.substring(index, Math.min(index + 50, s.length())) + "\"";
            assert s.substring(index).equals(suffix.select(i));
            int rank = suffix.rank(s.substring(index));
            if (i == 0) {
                System.out.format("%3d %3d %3s %3d %s\n", i, index, "-", rank, ith);
            } else {
                int lcp = suffix.lcp(i);
                System.out.format("%3d %3d %3d %3d %s\n", i, index, lcp, rank, ith);
            }
        }
    }

}
