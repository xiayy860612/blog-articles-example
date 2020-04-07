package com.s2u2m.examples.datastructurealgorithm.sort;

import java.util.Arrays;

/**
 * 归并排序:
 * 将数组不断分成2部分, 对这2部分数组各自进行排序(这里相当与还要用到其他的排序算法),
 * 然后再对排序好的两个部分进行不断的合并.
 */
public class MergeSort {

    public int[] sortArray(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int[] mergeSort(int[] nums, int start, int end) {
        if (start == end) {
            return nums;
        }

        int mid = (start + end) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        return merge(nums, start, mid, end);
    }

    private int[] merge(int[] nums, int start, int mid, int end) {
        for (int i = mid + 1; i <= end; ++i) {
            int index = i;
            while(index > start) {
                if (nums[index-1] <= nums[index]) {
                    break;
                }
                int tmp = nums[index];
                nums[index] = nums[index-1];
                nums[index-1] = tmp;
                --index;
            }
        }
        return nums;
    }

}
