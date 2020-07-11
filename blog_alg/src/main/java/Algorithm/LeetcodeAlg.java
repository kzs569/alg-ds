package Algorithm;

import java.util.*;

public class LeetcodeAlg {

    public static int atoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        char firstChar = str.charAt(0);

        int sign = 1, start = 0, len = str.length();
        long sum = 0;
        if (firstChar == '+') {
            sign = 1;
            start++;
        }
        if (firstChar == '-') {
            sign = -1;
            start++;
        }
        for (int i = start; i < len; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return (int) sum * sign;
            }
            sum = sum * 10 + str.charAt(i) - '0';
            if (sign == 1 && sum > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (sign == -1 && (-1) * sum < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
        }
        return (int) sum * sign;
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int i = 0;
        int j = height.length - 1;
        int maxArea = 0;

        while (i < j) {

            int h = Math.min(height[i], height[j]);

            maxArea = Math.max(maxArea, (j - i) * h);

            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return maxArea;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "Empty";

        String shortStr = "shortEst";

//        for (int i = 0; i < strs.length; i++) {
//            if(shortStr.length() == 0){
//                shortStr = strs[i];
//            }else{
//                shortStr = shortStr.length() < strs[i].length() ? shortStr : strs[i];
//            }
//        }
//
//        int len = shortStr.length();
//
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < strs.length; j++) {
//                System.out.println(strs[j]);
//                if(strs[j].charAt(i) != shortStr.charAt(i)){
//                    return i == 0 ? null : shortStr.substring(0,i);
//                }
//            }
//        }
        return shortStr;
    }

    private static class QueueFromStack {
        private Stack<Integer> stack1 = new Stack<Integer>();
        private Stack<Integer> stack2 = new Stack<Integer>();


        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (stack1.empty() && stack2.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            if (stack2.empty()) {
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }

//    public static List<List<Integer>> twoSum(int[] nums, int target) {
//        List<List<Integer>> ret = new LinkedList<List<Integer>>();
//        int[] transnums = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            transnums[i] = target - nums[i];
//        }
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < transnums.length; j++) {
//                if(nums[i] == transnums[j]){
//                    List<Integer> t = new LinkedList<Integer>();
//                    t.add(nums[i]);
//                    t.add(nums[j]);
//                    ret.add(t);
//                }
//            }
//        }
//        return ret;
//    }
    
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            int target = -nums[i];
            while( j < k ){
                if(nums[j] + nums[k] == target){
                    res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                    while(j<k && nums[j] == nums[j-1]) j++;
                    while(j<k && nums[k] == nums[k+1]) k--;
                }else if(nums[j] + nums[k] > target){
                    k--;
                }else{
                    j++;
                }
            }
        }
        return res;
    }

    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public static List<String> letterCombinations(String digits) {
        List<String> ret = new LinkedList<String>();
        combination("", digits, 0, ret);
        return ret;
    }

    private static void combination(String prefix, String digits, int offset, List<String> ret) {
        if (offset >= digits.length()) {
            ret.add(prefix);
            return;
        }
        String letters = KEYS[(digits.charAt(offset) - '0')];
        for (int i = 0; i < letters.length(); i++) {
            combination(prefix + letters.charAt(i), digits, offset + 1, ret);
        }
    }

    public static void strpre(){
        String str = "abcdefgabcdefg";
        int[][] f = new int[str.length()][26];
        for (int i = 0; i < 26; i++) {
            f[str.length() - 1][i] = -1;
        }
        for (int i = str.length() - 2; i >= 0 ; i--) {
            for (int j = 0; j < 26; j++) {
                if(j != (str.charAt(i) - 'a')){
                    f[i][j] = f[i+1][j];
                }
            }
            f[i][str.charAt(i + 1)-'a'] = i+1;
        }
        System.out.println("");
    }

    public static int[] twoSum(int[] numbers, int target) {
        int[] ret = new int[2];
        HashMap<Integer, Integer> map = new HashMap <Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if(!map.containsKey(numbers[i])){
                map.put(target - numbers[i], i);
            }else{
                ret[0] = map.get(numbers[i]) + 1;
                ret[1] = i + 1;
                return ret;
            }
        }
        return null;
    }

    public static void runMedium(int[] a, int[] b){
        System.out.println(medium(a,b,0,a.length - 1,0, b.length - 1));
    }

    public static int medium(int[] a, int[] b, int as, int ae, int bs, int be){
        int m1 = (ae + as)/2;
        int m2 = (be + bs)/2;
        System.out.println(String.valueOf(as) + String.valueOf(ae) + String.valueOf(bs) + String.valueOf(be) );

        if(as >= ae || bs >= be){
            return a[as] < b[bs] ? a[as]: b[bs];
        }

        if (a[m1] == b[m2]) {
            return a[m1];
        } else if (a[m1] > b[m2]) {
            if ((as + ae)%2 == 0) {
                medium(a, b, as, m1, m2, be);
            }else{
                medium(a, b, as, m1 + 1, m2, be);
            }
        } else {
            if ((as + ae)%2 == 0) {
                medium(a, b, m1, ae, bs, m2);
            }else{
                medium(a, b, m1 + 1, ae, bs, m2);
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] a = new int[]{2,7,11,15,17};
        int[] b = new int[]{1,3,4,5,9};

        runMedium(a, b);
//        int target = 9;
//
//        int[] ret = twoSum(numbers, target);
//
//        for (int i = 0; i < ret.length; i++) {
//            System.out.println(ret[i]);
//        }
        //atoi() test
//        String[] strs = new String[]{"","123456789","abc","-123","010","+010023","2147483647","-2147483648",
//                "   -2147483649","23ef8","+4488","\\n\\r\\t\\v 123\\n\\r\\t","0+123"};
//        for (String str : strs){
//            System.out.println(atoi(str));
//        }
        //maxArea test
//        int[] height = new int[]{2,1};
//        System.out.println(maxArea(height));

        //
//        String[] temp = new String[]{};
//        System.out.println(longestCommonPrefix(temp));
//
//        int[] test = new int[]{3, 2, 1};
//        Arrays.sort(test);
//        for (Integer i : test) {
//            System.out.println(i);
//        }
//
//        QueueFromStack collection = new QueueFromStack();
//        int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
//        for (int i = 0; i < numbers.length; i++) {
//            collection.push(numbers[i]);
//        }
//
//        for (int i = 0; i < numbers.length; i++) {
//            System.out.println(collection.pop());
//        }
//
//        Deque<Integer> deque = new ArrayDeque<Integer>();

//        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
//        List<List<Integer>> res = threeSum(nums);
//        for (int i = 0; i < res.size(); i++) {
//            List<Integer> r = res.get(i);
//            for (int j = 0; j < r.size(); j++) {
//                System.out.println(r.get(j));
//            }
//        }

//        String s = "23";
//        List<String> r = letterCombinations(s);
//        for (int i = 0; i < s.length(); i++) {
//            System.out.println(r.get(i));
//        }
        strpre();
    }

}
