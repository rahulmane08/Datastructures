package leetcode.hard;

public class MedianOf2SortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int [] merged = merge(nums1, nums2);
        int n = merged.length;
        if (n % 2 == 0) {
            return (double)(merged[n/2] + merged[n/2-1]) / 2;
        }
        return merged[n/2];
    }

    private int[] merge(int[] nums1, int[] nums2) {
        if (isEmpty(nums1) && isEmpty(nums2)) {
            return null;
        }
        if (isEmpty(nums1)) {
            return nums2;
        }
        if (isEmpty(nums2)) {
            return nums1;
        }
        int k = nums1.length + nums2.length;
        int [] merged = new int[k];
        int i = 0, j = 0;
        k = 0;
        while(i < nums1.length && j < nums2.length) {
            merged[k++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }

        while(i < nums1.length) {
            merged[k++] = nums1[i++];
        }

        while(j < nums2.length) {
            merged[k++] = nums2[j++];
        }
        return merged;
    }

    private boolean isEmpty(int[] nums) {
        return nums == null || nums.length == 0;
    }

    public static void main(String[] args) {
        MedianOf2SortedArrays m = new MedianOf2SortedArrays();
        System.out.println(m.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
    }
}
