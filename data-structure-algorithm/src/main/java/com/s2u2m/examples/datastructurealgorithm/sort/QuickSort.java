package com.s2u2m.examples.datastructurealgorithm.sort;

/**
 * 快速排序:
 * left|pivot|right
 * 将数组不断根据pivot分成前后两部分数组, 小于pivot的在前, 大于pivot的在后;
 * 然后对前后两部分的数组重复pivot分区操作, 直到左右只剩一个为止;
 * 此时所有数据为有序状态
 */
public class QuickSort {

    public int[] sortArray(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }
        return quickSort(nums, 0, nums.length - 1);
    }

    private int[] quickSort(int[] nums, int start, int end) {
        if (start == end) {
            return nums;
        }

        int pivotIndex = partition(nums, start, end);
        if (pivotIndex > start) {
            quickSort(nums, start, pivotIndex-1);
        }
        if (pivotIndex < end) {
            quickSort(nums, pivotIndex+1, end);
        }
        return nums;
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int pivotIndex = start;
        int tmp = 0;
        for(int i = start; i < end; ++i) {
            if (nums[i] >= pivot) {
                continue;
            }
            tmp = nums[i];
            nums[i] = nums[pivotIndex];
            nums[pivotIndex] = tmp;
            ++pivotIndex;
        }
        tmp = nums[pivotIndex];
        nums[pivotIndex] = pivot;
        nums[end] = tmp;
        return pivotIndex;
    }

}
