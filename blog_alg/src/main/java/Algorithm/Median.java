package Algorithm;

public class Median {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 < n2)
            return findMedianSortedArrays(nums2, nums1); // 确保nums2为短数组
        int lo = 0, hi = n2 * 2; //
        while (lo <= hi) {
            int c2 = (lo + hi) >> 1;
            int c1 = n1 + n2 - c2;
            double L1 = (c1 == 0) ? Integer.MIN_VALUE : nums1[(c1 - 1) / 2];
            double L2 = (c2 == 0) ? Integer.MIN_VALUE : nums2[(c2 - 1) / 2];
            double R1 = (c1 == n1 * 2) ? Integer.MAX_VALUE : nums1[c1 / 2];
            double R2 = (c2 == n2 * 2) ? Integer.MAX_VALUE : nums2[c2 / 2];

            if (L1 > R2)
                lo = c2 + 1; // 增大c2，减小c1，向右移动c2
            else if (L2 > R1)
                hi = c2 - 1; // 减小c2，增大c1，向左移动c2
            else
                return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
        }

        return -1;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5,17,19};
        int[] b = new int[]{5,11,12,13,45,56};

        double res = findMedianSortedArrays(a, b);

        System.out.println(res);
    }
}
