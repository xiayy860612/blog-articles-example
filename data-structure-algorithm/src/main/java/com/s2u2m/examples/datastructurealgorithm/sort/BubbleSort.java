package com.s2u2m.examples.datastructurealgorithm.sort;

/**
 * 冒泡排序:
 * 未排序|已排序
 * 将未排序中的最大值, 放到已排序部分的最前面
 */
public class BubbleSort {

    public int[] sortArray(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }

        int count = nums.length;
        while (count > 1) {
            boolean swapped = false;
            // swap amount
            for (int i = 0; i < count; ++i) {
                if (nums[i] <= nums[i+1]) {
                    continue;
                }
                int num = nums[i+1];
                nums[i+1] = nums[i];
                nums[i] = num;
                swapped = true;
            }
            if (!swapped) {
                // all are in order
                break;
            }
            --count;
        }
        return nums;
    }

}
