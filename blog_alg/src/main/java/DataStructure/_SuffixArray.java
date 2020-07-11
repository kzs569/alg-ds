package DataStructure;

public class _SuffixArray {

    /**
     * 长文本解决方案：
     * 1.不再保存实际后缀数组对象，而是用一个索引数组index[i],节省空间
     * 2.将构造时的排序方法替换为“三向字符串快速排序”，
     * 将文本中出现大量重复字符的性能提升至线性
     */
    private static int CUTOFF = 5; // cutoff to insertion sort
    private char[] text;   // 保存文本字符串
    private int[] index;   // index[i] = j表示排序后第i个后缀字符串为text.substring(j)
    private int n;         // number of characters in text


    public _SuffixArray(String text) {
        this.n = text.length();
        text += '\0';
        this.text = text.toCharArray();
        this.index = new int[n];
        for (int i = 0; i < n; i++) {
            this.index[i] = i;
        }
        sort(0, n - 1, 0);
    }

    /**
     * 三向字符串快速排序
     *
     * @param low  起始后缀字符串text[index[lo]...N-1]
     * @param high 结束后缀字符串text[index[hi]...N-1]
     * @param d    待排序字符位置
     */
    private void sort(int low, int high, int d) {
        // 对于小数组，切换为插入排序
        if (high <= low + CUTOFF) {
            insertion(low, high, d);
            return;
        }

        int lt = low, gt = high;
        char v = text[index[low] + d];
        int i = low + 1;
        while (i <= gt) {
            char t = text[index[i] + d];
            if (t < v)
                exchange(lt++, i++);
            else if (t > v)
                exchange(i, gt--);
            else
                i++;
        }
        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(low, lt - 1, d);
        if (v > 0)
            sort(lt, gt, d + 1);
        sort(gt + 1, high, d);
    }

    // sort from a[lo] to a[hi], starting at the dth character
    private void insertion(int low, int high, int d) {
        for (int i = low; i <= high; i++)
            for (int j = i; j > low && less(index[j], index[j - 1], d); j--)
                exchange(j, j - 1);
    }

    private void exchange(int i, int j) {
        int swap = index[i];
        index[i] = index[j];
        index[j] = swap;
    }

    // is text[i+d..n) < text[j+d..n) ?
    private boolean less(int i, int j, int d) {
        if (i == j)
            return false;
        i = i + d;
        j = j + d;
        while (i < n && j < n) {
            if (text[i] < text[j])
                return true;
            if (text[i] > text[j])
                return false;
            i++;
            j++;
        }
        return i > j;
    }
    public int length() {
        return n;
    }

    /**
     * Returns the index into the original string of the <em>i</em>th smallest
     * suffix. That is, {@code text.substring(sa.index(i))} is the <em>i</em>
     * smallest suffix.
     *
     * @param i
     *            an integer between 0 and <em>n</em>-1
     * @return the index into the original string of the <em>i</em>th smallest
     *         suffix
     * @throws java.lang.IllegalArgumentException
     *             unless {@code 0 <=i < n}
     */
    public int index(int i) {
        if (i < 0 || i >= n)
            throw new IllegalArgumentException();
        return index[i];
    }

    /**
     * Returns the length of the longest common prefix of the <em>i</em>th
     * smallest suffix and the <em>i</em>-1st smallest suffix.
     *
     * @param i
     *            an integer between 1 and <em>n</em>-1
     * @return the length of the longest common prefix of the <em>i</em>th
     *         smallest suffix and the <em>i</em>-1st smallest suffix.
     * @throws java.lang.IllegalArgumentException
     *             unless {@code 1 <= i < n}
     */
    public int lcp(int i) {
        if (i < 1 || i >= n)
            throw new IllegalArgumentException();
        return lcp(index[i], index[i - 1]);
    }

    // longest common prefix of text[i..n) and text[j..n)
    private int lcp(int i, int j) {
        int length = 0;
        while (i < n && j < n) {
            if (text[i] != text[j])
                return length;
            i++;
            j++;
            length++;
        }
        return length;
    }

    /**
     * Returns the <em>i</em>th smallest suffix as a string.
     *
     * @param i
     *            the index
     * @return the <em>i</em> smallest suffix as a string
     * @throws java.lang.IllegalArgumentException
     *             unless {@code 0 <= i < n}
     */
    public String select(int i) {
        if (i < 0 || i >= n)
            throw new IllegalArgumentException();
        return new String(text, index[i], n - index[i]);
    }

    /**
     * Returns the number of suffixes strictly less than the {@code query}
     * string. We note that {@code rank(select(i))} equals {@code i} for each
     * {@code i} between 0 and <em>n</em>-1.
     *
     * @param query
     *            the query string
     * @return the number of suffixes strictly less than {@code query}
     */
    public int rank(String query) {
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = compare(query, index[mid]);
            if (cmp < 0)
                hi = mid - 1;
            else if (cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    // is query < text[i..n) ?
    private int compare(String query, int i) {
        int m = query.length();
        int j = 0;
        while (i < n && j < m) {
            if (query.charAt(j) != text[i])
                return query.charAt(j) - text[i];
            i++;
            j++;

        }
        if (i < n)
            return -1;
        if (j < m)
            return +1;
        return 0;
    }

    /**
     * Unit tests the {@code SuffixArrayx} data type.
     *
     * @param args
     *            the command-line arguments
     */

    public static void main(String[] args) {
        //String s = StdIn.readAll().replaceAll("\n", " ").trim();
        String s = "ladygaga";
        _SuffixArray suffix = new _SuffixArray(s);


        System.out.println("  i ind lcp rnk  select");
        System.out.println("---------------------------");

        for (int i = 0; i < s.length(); i++) {
            int index = suffix.index(i);
            String ith = "\"" + s.substring(index, Math.min(index + 50, s.length())) + "\"";
            int rank = suffix.rank(s.substring(index));
            assert s.substring(index).equals(suffix.select(i));
            if (i == 0) {
                System.out.printf("%3d %3d %3s %3d  %s\n", i, index, "-", rank, ith);
            } else {
                // int lcp = suffix.lcp(suffix2.index(i), suffix2.index(i-1));
                int lcp = suffix.lcp(i);
                System.out.printf("%3d %3d %3d %3d  %s\n", i, index, lcp, rank, ith);
            }
        }
    }


}
