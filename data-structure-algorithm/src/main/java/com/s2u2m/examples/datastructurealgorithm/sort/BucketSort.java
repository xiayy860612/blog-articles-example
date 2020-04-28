package com.s2u2m.examples.datastructurealgorithm.sort;

/**
 * 桶排序:
 * 遍历数据找到最大值, 根据桶的个数划分step, 来创建有序的桶, 并将数据分配到相应的桶中;
 * 对每个桶进行排序(插入排序), 最后将桶拼接
 */
public class BucketSort {

    public int[] sortArray(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }

        int bucketCount = 10;
        int min = min(nums);
        int max = max(nums);
        int step = (int) Math.ceil((max - min + 1) * 1.0 / bucketCount);

        int[] tmp = new int[nums.length];
        int index = 0;
        int bm = 0;
        int bx = 0;
        for(int i=0; i<bucketCount; ++i) {
            bm = min + i * step;
            bx = Math.min(bm+step-1, max);

            int endIndex = getBucket(nums, bm, bx, tmp, index);
            insertSort(tmp, index, endIndex);
            index = endIndex;
        }

        return tmp;
    }

    private int getBucket(int[] nums, int min, int max, int[] tmp, int startIndex) {
        int endIndex = startIndex;
        for (int i=0; i<nums.length; ++i) {
            int num = nums[i];
            if (num < min || num > max) {
                continue;
            }
            tmp[endIndex++] = num;
        }
        return endIndex;
    }

    private void insertSort(int[] tmp, int start, int end) {
        if (start >= end) {
            return;
        }

        for(int i=start+1; i<end; ++i) {
            int j = i;
            while(j > start) {
                if (tmp[j-1] <= tmp[j]) {
                    break;
                }

                int num = tmp[j];
                tmp[j] = tmp[j-1];
                tmp[j-1] = num;
                --j;
            }
        }
    }

    private int min(int[] nums) {
        int min = nums[0];
        for(int i=1; i<nums.length;++i) {
            if (nums[i] >= min) {
                continue;
            }
            min = nums[i];
        }
        return min;
    }
    private int max(int[] nums) {
        int max = nums[0];
        for(int i=1; i<nums.length;++i) {
            if (nums[i] <= max) {
                continue;
            }

            max = nums[i];
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {3, -20000, -20000, -1, 20000, 20000, 1};
        int[] rst = new BucketSort().sortArray(arr);
        for(int i=0; i<rst.length; ++i) {
            System.out.printf("%s ", rst[i]);
        }
    }

}
