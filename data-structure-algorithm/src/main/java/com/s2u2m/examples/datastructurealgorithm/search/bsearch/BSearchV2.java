package com.s2u2m.examples.datastructurealgorithm.search.bsearch;

/**
 * 二分查找: 查找第一个等于值等于给定值的元素, 即数组中有重复数据
 */
public class BSearchV2 {

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

        int targetIndex = midIndex;
        if (targetIndex == 0) {
            return targetIndex;
        }

        // 往前找
        while(nums[targetIndex-1] == target) {
            --targetIndex;
        }
        return targetIndex;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 4, 6, 7, 7, 12};
        int index = new BSearchV2().search(nums, 7);
        System.out.println(index);
    }
}
