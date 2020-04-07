package com.s2u2m.examples.datastructurealgorithm.sort;

/**
 * 插入排序:
 * 已排序|未排序
 * 将未排序中的第一值插入到已排序中
 */
public class InsertionSort {

    public int[] sortArray(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }

        for (int i = 1; i < nums.length; ++i) {
            int j = i;
            while (j > 0) {
                if (nums[j-1] <= nums[j]) {
                    break;
                }
                int num = nums[j];
                nums[j] = nums[j-1];
                nums[j-1] = num;
                --j;
            }
        }
        return nums;
    }
}
