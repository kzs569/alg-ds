package TMoP;

import java.util.Arrays;

public class Ch01_String {

    /**
     * 1.1 字符串的旋转
     * <p>
     * 解法1 ： 蛮力移位
     *
     * @param s 字符串s
     * @param m 字符串开头的m个字符移到字符串尾部
     */

    public static String LeftRotateString(String s, int m) {
        while (m > 0) {
            s = LeftShiftOne(s);
            m--;
        }
        return s;
    }

    public static String LeftShiftOne(String s) {
        char[] chars = s.toCharArray();
        char t = chars[0];
        for (int i = 1; i < chars.length; i++) {
            chars[i - 1] = chars[i];
        }
        chars[chars.length - 1] = t;
        return new String(chars);
    }

    /**
     * 解法二 三步反转
     */

    public static String LeftRotateString_v2(String s, int m) {
        m %= s.length();

        s = ReverseString(s, 0, m - 1);
        s = ReverseString(s, m, s.length() - 1);
        s = ReverseString(s, 0, s.length() - 1);

        return s;
    }

    public static String ReverseString(String s, int from, int to) {
        char[] chars = s.toCharArray();

        while (from < to) {
            char t = chars[from];
            chars[from] = chars[to];
            chars[to] = t;
            from++;
            to--;
        }

        return new String(chars);
    }

    /**
     * 举一反三
     */

    public static String ReverseWords(String s) {
        String[] words = s.split(" ");

        int from = 0;
        int to = words.length - 1;

        while (from < to) {
            String t = words[from];
            words[from] = words[to];
            words[to] = t;
            from++;
            to--;
        }

        StringBuilder t = new StringBuilder();
        for (String str : words) {
            t.append(str + " ");
        }
        return t.toString();
    }

    /**
     * 1.2 字符串的包含
     */

    public static boolean isSubStringContain(String a, String b){
        for (int i = 0; i < b.length() - 1; i++) {
            int j;
            for (j = 0; (j < a.length())&&(a.charAt(j) != b.charAt(i)); j++) {}
            if (j >= a.length()){
                return false;
            }
        }
        return true;
    }

    public static boolean isSubStringContain_v2(String a, String b){

        String a_sorted = sort(a);
        String b_sorted = sort(b);

        for(int pa = 0,pb = 0;pb < b.length();pb++){
            while((pa < a_sorted.length())&&(pb < b_sorted.length())){
                System.out.println(" ");
            }
        }

        return true;
    }

    public static String sort(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }


}
