package com.s2u2m.examples.datastructurealgorithm.list.linkedlist.palindrome;

import java.util.LinkedList;

/**
 * 将值复制到数组中后用双指针法
 */
public class PalindromeSolutionA {

    public boolean isPalindrome(LinkedList<String> content) {
        String[] arrays = content.toArray(new String[] {});
        int head = 0;
        int tail = arrays.length - 1;

        int cnt = arrays.length / 2;
        while (cnt-- > 0) {
            String headChar = arrays[head];
            String tailChar = arrays[tail];
            if (headChar.equals(tailChar)) {
                return false;
            }
            ++head;
            --tail;
        }
        return true;
    }
}
