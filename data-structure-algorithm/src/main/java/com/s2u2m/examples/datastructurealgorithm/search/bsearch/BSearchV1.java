package com.s2u2m.examples.datastructurealgorithm.search.bsearch;

/**
 * 二分查找: 不重复的有序数组的二分查找
 */
public class BSearchV1 {

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        return search(nums, 0, nums.length - 1, target);
    }

    public int search(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int midIndex = start + ((end - start) >>> 1);
        int mid = nums[midIndex];
        if (target < mid) {
            return search(nums, start, midIndex-1, target);
        }
        if (target > mid) {
            return search(nums, midIndex + 1, end, target);
        }
        return midIndex;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        int index = new BSearchV1().search(nums, 9);
        System.out.println(index);
    }
}
