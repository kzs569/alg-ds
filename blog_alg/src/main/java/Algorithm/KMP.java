package Algorithm;

public class KMP {
//    private static void getNext(String pattern, int next[]) {
//        int j = 0;
//        int k = -1;
//        int len = pattern.length();
//        next[0] = -1;
//
//        while (j < len - 1) {
//            if (k == -1 || pattern.charAt(k) == pattern.charAt(j)) {
//
//                j++;
//                k++;
//                next[j] = k;
//            } else {
//
//                // 比较到第K个字符，说明p[0——k-1]字符串和p[j-k——j-1]字符串相等，而next[k]表示
//                // p[0——k-1]的前缀和后缀的最长共有长度，所接下来可以直接比较p[next[k]]和p[j]
//                k = next[k];
//            }
//        }
//    }

    private static int[] getNext(String pattern) {

        if(pattern == null){
            return null;
        }

        char[] p = pattern.toCharArray();
        int[] next = new int[p.length];

        int k = -1;
        int j = 0;
        next[0] = -1; // next数组中next[0]为-1

        while(j < p.length - 1){
            while(k >= 0 || p[j] != p[k]){
                k = next[k];
            }
            j++;
            k++;
            if(j == p.length)
                break;
            if(p[j]==p[k]){
                next[j] = next[k];
            }else{
                next[j] = k;
            }
        }

//        while (j < p.length - 1) {
//            if (k == -1 || p[j] == p[k]) {
//                k++;
//                j++;
//                // 修改next数组求法
//                if (p[j] != p[k]) {
//                    next[j] = k;// KMPStringMatcher中只有这一行
//                } else {
//                    // 不能出现p[j] = p[next[j]],所以如果出现这种情况则继续递归,如 k = next[k],
//                    // k = next[[next[k]]
//                    next[j] = next[k];
//                }
//            } else {
//                k = next[k];
//            }
//        }
        return next;
    }

    private static String nextToString(int[] next) {
        StringBuilder s = new StringBuilder();
        s.append("next[] is : ");
        for (int k = 0; k < next.length; k++) {
            s.append(next[k]);
            s.append(" ");
        }
        return s.toString();
    }

    private static int kmp(String s, String p) {
        int i = 0, j = 0;
        int[] next = getNext(p);
        System.out.println(nextToString(next));
        while (i < s.length() && j < p.length()) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j >= p.length()) {
            return i - j + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        String str = "abababdafdasabcfdfeaba";
        String pattern = "abc";

        System.out.println(str);
        System.out.println(pattern);

        System.out.println(kmp(str, pattern));
    }
}
