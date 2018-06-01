package util;

/**
 * 字符通用工具类
 *
 * Created by liuzongqian on 2017/9/4.
 */
public class CharUtil {

    /**
     * @param args
     */
    public static void main(String[] args) {
//        String[] strArr = new String[] { "www.micmiu.com",
//                "!@#$%^*()_+{}[]|\"'?/:;>,.", "！￥……（）——：；“”‘’《》，。？、", "不要啊", "测试文件222aaa,》、?",
//                "やめて", "韩佳人", "한가인" };
//        for (String str : strArr) {
//            System.out.println("===========测试字符串：" + str);
//
//            System.out.println("Unicode判断结果 ：" + hasChinese(str));
//            System.out.println("详细判断列表：");
//            char[] ch = str.toCharArray();
//            for (int i = 0; i < ch.length; i++) {
//                char c = ch[i];
//                System.out.println(c + " -- >> " + (isChinese(c) ? "是" : "否"));
//            }
//        }

        char c = '1';
        System.out.println(isChinese(c));
    }

    // 根据Unicode编码完美的判断中文汉字和符号
    public static boolean isChineseAndSymbols(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                ) {
            return true;
        }
        return false;
    }

    // 根据Unicode编码完美的判断中文汉字
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                ) {
            return true;
        }
        return false;
    }

    public static boolean isAlpha(char ch) {
        return ((ch <= 'z') && (ch >= 'a')) || ((ch <= 'Z') && (ch >= 'A'));
    }

    public static boolean isNumber(char ch) {
        return (ch <= '9') && (ch >= '0');
    }


    // 完整的判断中文汉字和符号
    public static boolean hasChinese(String strName) {
        if(strName == null) {
            return false;
        }
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 保留字符串中的中文
     * @param str
     * @return
     */
    public static String remainChinsesChar(String str) {
        if(str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(char c: str.toCharArray()) {
            if(isChinese(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}