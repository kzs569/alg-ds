package util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by liuzongqian on 2017/9/4.
 */
public class StringUtil {
    public static final Pattern NUMRIC_ALPHA_PUNCT = Pattern.compile("[a-zA-Z\\d \t\n，。*？！：；·…~&@#$,.?!:;、～＆＠＃“”‘’〝〞 \"'＂＇´（）【】《》＜＞￥﹝﹞<>()\\[\\]«»‹›〔〕〈〉『』〖〗｛｝「」［］{}/|\\\\／｜＼`^­\\-—+●○¨ˊˋˇ①②③④⑤⑥⑦⑧⑨⑩⑪⑫⑬⑭⑮⑯⑰⑱⑲⑳ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫⅹⅸⅷⅶⅵⅴⅳⅲⅱⅰ﹣=﹤﹥≦≧≮≯≡＋－×÷＝≤≥≈≠﹢∶∅%‰％℅°℃℉§Β◇ェストＦＯＸ±?ΓΜφ–〃ド㎜５ＧＰＹΔΦ║★㎝ＨＱＺΕ―∫■コ．ＩＲ\037‖←△ＡＪＳ_ΠΩИС※↑〇ガサブＢＫＴΡ″∮キムラ㎏㎡１ＣＬＵ￠Ιシテリ２ＤＭＶΑФ◆ジバル﹟＊ＥＮＷ]+");

    public static final Map<Character, Character> quotation_equal = new HashMap<Character, Character>(){{
        put(Character.valueOf('"'), Character.valueOf('"'));
        put(Character.valueOf('\''), Character.valueOf('\''));
        put(Character.valueOf('＂'), Character.valueOf('＂'));
        put(Character.valueOf('＇'), Character.valueOf('＇'));
        put(Character.valueOf('´'), Character.valueOf('´'));
    }};

    public static final Map<Character, Character>  quotation_left_right = new HashMap<Character, Character>() {{
        put(Character.valueOf('“'), Character.valueOf('”'));
        put(Character.valueOf('‘'), Character.valueOf('’'));
        put(Character.valueOf('〝'), Character.valueOf('〞'));
    }};

    public static final Map<Character, Character>  quotation_right_left = new HashMap<Character, Character>() {{
        put(Character.valueOf('”'), Character.valueOf('“'));
        put(Character.valueOf('’'), Character.valueOf('‘'));
        put(Character.valueOf('〞'), Character.valueOf('〝'));
    }};


    public static final Map<Character, Character> bracket_left_right = new HashMap<Character, Character>(){{
        put('（', '）');
        put(Character.valueOf('('), Character.valueOf(')'));
        put(Character.valueOf('【'), Character.valueOf('】'));
        put(Character.valueOf('['), Character.valueOf(']'));
        put(Character.valueOf('［'), Character.valueOf('］'));
        put(Character.valueOf('「'), Character.valueOf('」'));
        put(Character.valueOf('〖'), Character.valueOf('〗'));
        put(Character.valueOf('『'), Character.valueOf('』'));
        put(Character.valueOf('﹝'), Character.valueOf('﹞'));
        put(Character.valueOf('〔'), Character.valueOf('〕'));
        put(Character.valueOf('{'), Character.valueOf('}'));
        put(Character.valueOf('｛'), Character.valueOf('｝'));
        put(Character.valueOf('《'), Character.valueOf('》'));
        put(Character.valueOf('＜'), Character.valueOf('＞'));
        put(Character.valueOf('<'), Character.valueOf('>'));
        put(Character.valueOf('«'), Character.valueOf('»'));
        put(Character.valueOf('‹'), Character.valueOf('›'));
        put(Character.valueOf('〈'), Character.valueOf('〉'));
    }};
    public static final Map<Character, Character> bracket_right_left = new HashMap<Character, Character>(){{
        put(Character.valueOf('）'), Character.valueOf('（'));
        put(Character.valueOf(')'), Character.valueOf('('));
        put(Character.valueOf('】'), Character.valueOf('【'));
        put(Character.valueOf(']'), Character.valueOf('['));
        put(Character.valueOf('］'), Character.valueOf('［'));
        put(Character.valueOf('」'), Character.valueOf('「'));
        put(Character.valueOf('〗'), Character.valueOf('〖'));
        put(Character.valueOf('』'), Character.valueOf('『'));
        put(Character.valueOf('﹞'), Character.valueOf('﹝'));
        put(Character.valueOf('〕'), Character.valueOf('〔'));
        put(Character.valueOf('}'), Character.valueOf('{'));
        put(Character.valueOf('｝'), Character.valueOf('｛'));
        put(Character.valueOf('》'), Character.valueOf('《'));
        put(Character.valueOf('＞'), Character.valueOf('＜'));
        put(Character.valueOf('>'), Character.valueOf('<'));
        put(Character.valueOf('»'), Character.valueOf('«'));
        put(Character.valueOf('›'), Character.valueOf('‹'));
        put(Character.valueOf('〉'), Character.valueOf('〈'));

    }};



    public static void sortWithStringLen(String[] arr, boolean reverse) {
        if(reverse) {
            Arrays.sort(arr, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    return o2.length() -  o1.length();
                }
            });
        }
        else {
            Arrays.sort(arr, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    return o1.length() -  o2.length();
                }
            });
        }
    }

    public static Character QtoB(Character ch) {
        Map<Character, Character> ChineseEnglishInterpunctionMap = new HashMap<Character, Character>(){{
            put(Character.valueOf('“'), Character.valueOf('"'));
            put(Character.valueOf('”'), Character.valueOf('"'));
            put(Character.valueOf('‘'), Character.valueOf('\''));
            put(Character.valueOf('’'), Character.valueOf('\''));
            put(Character.valueOf('。'), Character.valueOf('.'));
            put(Character.valueOf((char)65292), Character.valueOf(','));
            put(Character.valueOf((char)65307), Character.valueOf(';'));
            put(Character.valueOf((char)65306), Character.valueOf(':'));
            put(Character.valueOf((char)65311), Character.valueOf('?'));
            put(Character.valueOf((char)65281), Character.valueOf('!'));
            put(Character.valueOf('—'), Character.valueOf('-'));
            put(Character.valueOf((char)65374), Character.valueOf('~'));
            put(Character.valueOf((char)65288), Character.valueOf('('));
            put(Character.valueOf((char)65289), Character.valueOf(')'));
            put(Character.valueOf('《'), Character.valueOf('<'));
            put(Character.valueOf('》'), Character.valueOf('>'));
            put(Character.valueOf((char)65371), Character.valueOf('{'));
            put(Character.valueOf((char)65373), Character.valueOf('}'));
            put(Character.valueOf('、'), Character.valueOf(','));
            put(Character.valueOf((char)65339), Character.valueOf('['));
            put(Character.valueOf((char)65341), Character.valueOf(']'));
            put(Character.valueOf('【'), Character.valueOf('['));
            put(Character.valueOf('】'), Character.valueOf(']'));
            put(Character.valueOf('〖'), Character.valueOf('['));
            put(Character.valueOf('〗'), Character.valueOf(']'));
            put(Character.valueOf('〔'), Character.valueOf('['));
            put(Character.valueOf('〕'), Character.valueOf(']'));
        }};

        if (ch == null) {
            return null;
        }
        Character newCh = ch;
        if (ChineseEnglishInterpunctionMap.containsKey(ch)) {
            newCh = ChineseEnglishInterpunctionMap.get(ch);
        } else if (ch.charValue() == '　') {
            newCh = Character.valueOf(' ');
        } else if ((ch.charValue() > 65280) && (ch.charValue() < 65375)) {
            newCh = Character.valueOf((char) (ch.charValue() - 65248));
        }
        return newCh;
    }
}


