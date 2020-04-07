package com.s2u2m.examples.datastructurealgorithm.sort;

/**
 * 选择排序:
 * 已排序|未排序
 * 将未排序中的最小值, 放到已排序的最后面
 */
public class SelectionSort {

    public int[] sortArray(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }

        for (int i=0; i< nums.length - 1; ++i) {
            int minIndex = i;
            for (int j=i+1; j < nums.length; ++j) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex == i) {
                continue;
            }
            int min = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = min;
        }

        return nums;
    }
}
