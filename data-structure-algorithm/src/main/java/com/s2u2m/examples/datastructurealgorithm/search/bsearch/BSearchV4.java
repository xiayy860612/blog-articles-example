package com.s2u2m.examples.datastructurealgorithm.search.bsearch;

/**
 * 二分查找: 查找第一个大于等于值等于给定值的元素
 */
public class BSearchV4 {

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
        if (target <= mid) {
            if(midIndex == 0) {
                return midIndex;
            }

            if (target > nums[midIndex-1]) {
                return midIndex;
            }

            return search(nums, start, midIndex-1, target);
        }

        return search(nums, midIndex + 1, end, target);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 3, 4, 6, 9, 12};
        int index = new BSearchV4().search(nums, 10);
        System.out.println(index);
    }
}
