package Algorithm;



public class Palindromic {


    public static boolean isPalindrome(String str){
        int len = str.length();
        for (int i = 0, j = len - 1; i < j; i++, j--) {
                if(str.charAt(i) != str.charAt(j)){
                    return false;
                }
        }
        return true;
    }
    //暴力破解法
    //从最大长度的字串开始，而不是从最小开始。假如说给定的字符串为len，先遍历长度为len的字串是否为回文串，如果是停止，
    // 如果不是遍历长度为len-1的字串是否是回文串，依次类推。
    //递推写法
    public static void MaxSubPalindromeNumber_BF(String str){
        //判断回文子串缩短的长度,i = str.length() - 1时就只剩单个字符的判断了
        for (int i = 0; i < str.length() - 2; i++) {
            int len = str.length() - i;
            for (int j = 0; j <= i; j++) {
                if(isPalindrome(str.substring(j,j+len))){
                    System.out.println(str.substring(j,j+len));
                    return;
                }
            }
        }
    }
    //中心字段判别法
    //假设现在已经遍历到第 i 个字符，要找出以该字符为“中心”的最长对称字符串，我们需要用另两个指针分别向前和向后移动，
    // 直到指针到达字符串两端或者两个指针所指的字符不相等。因为对称子字符串有两种情况，所以需要写出两种情况下的代码：
    //（1） 第 i 个字符是该对称字符串的真正的中心，也就是说该对称字符串以第 i 个字符对称， 比如： “aba”
    //（2）第 i 个字符串是对称字符串的其中一个中心。比如“abba”
    //所以遍历到每个字符都要考虑两种情况，它可能是在奇数个回文串中或者是在偶数个回文串中
    public static void MaxSubPalindromeNumber_Sec(String str){
        String ret = "";
        for (int i = 0; i < str.length(); i++) {
            String oddParse = isPalindrome(i,Math.min(i,str.length() - i),str,true);
            String evenParse = isPalindrome(i,Math.min(i,str.length() - i),str,false);
            ret = ret.length() > oddParse.length() ? ret : oddParse;
            ret = ret.length() > evenParse.length() ? ret : evenParse;
        }
        System.out.println(ret);
    }

    //判断指定中心词周围是否有回文
    public static String isPalindrome(int pos, int width, String str,boolean odd){
        if(odd) {
            for (int i = width; i >= 0; i--) {
                if (isPalindrome(str.substring(pos - i, pos + i))) {
                    return str.substring(pos - i, pos + i);
                }
            }
        }else{
            for (int i = width - 1; i >= 0 ; i--) {
                if(isPalindrome(str.substring(pos - i - 1,pos + i))){
                    return str.substring(pos - i - 1,pos + i);
                }
            }
        }
        return "";
    }
    //Manacher算法
    //把原串每个字符中间用一个串中没出现过的字符分隔#开来(统一奇偶)，同时为了防止越界，
    // 在字符串的首部也加入一个特殊符$，但是与分隔符不同。同时字符串的末尾也加入'\0'。
    // 算法的核心：用辅助数组p记录以每个字符为核心的最长回文字符串半径。
    // 也就是p[i]记录了以str[i]为中心的最长回文字符串半径。p[i]最小为1，此时回文字符串就是字符串本身。

    public static void MaxSubPalindromeNumber_Mana(String str){

        int[] p = new int[str.length() + 1];

        str = MulitpleString(str);

        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) != '$'){
                int j = 1;
                while(str.charAt(i - j) != '$' && str.charAt(i + j) != '$'){
                    if(isPalindrome(str.substring(i-j,i+j))){
                        System.out.println(str.substring(i-j,i+j).replaceAll("#",""));
                    }
                    j++;
                }
            }
        }
    }

    public static String MulitpleString(String str){
        StringBuffer newstr = new StringBuffer();
        newstr.append('$');
        for (int i = 0; i < str.length(); i++) {
            newstr.append('#');
            newstr.append(str.charAt(i));
            if(i == str.length() - 1){
                newstr.append('#');
            }
        }
        newstr.append('$');
        return newstr.toString();
    }
    public static void main(String[] args) {
        //String str = "abccba";
        //String str = "djdslkAABCDEAfjdl1234321skjflkdsjfkldsababasdlkfjsdwieowowwpw";
        String str = "a";
        MaxSubPalindromeNumber_Sec(str);
    }

    public String convert(String s,int numRows){
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[numRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();

        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < numRows && i < len; idx++) // vertically down
                sb[idx].append(c[i++]);
            for (int idx = numRows-2; idx >= 1 && i < len; idx--) // obliquely up
                sb[idx].append(c[i++]);
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
    }




}
