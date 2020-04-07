package com.s2u2m.examples.datastructurealgorithm.list.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

public class BiLinkedList {

    @Getter
    @AllArgsConstructor
    private static class BiLinkedListNode {
        private Integer value;

        private BiLinkedListNode prev;
        private BiLinkedListNode next;
    }

    private BiLinkedListNode first;
    private BiLinkedListNode last;
//    private int size;

    public void add(Integer value) {
        BiLinkedListNode l = this.last;
        BiLinkedListNode newBiLinkedListNode = new BiLinkedListNode(value, l, null);
        this.last = newBiLinkedListNode;
        if (Objects.isNull(l)) {
            this.first = newBiLinkedListNode;
        } else {
            l.next = newBiLinkedListNode;
        }
    }

    public void remove(Integer value) {
        for(BiLinkedListNode x = this.first; x != null; x = x.next) {
            if (!value.equals(x.value)) {
                continue;
            }

            BiLinkedListNode prev = x.prev;
            BiLinkedListNode next = x.next;
            if (Objects.isNull(prev)) {
                this.first = next;
            } else {
                prev.next = next;
                x.prev = null;
            }

            if (Objects.isNull(next)) {
                this.last = prev;
            } else {
                next.prev = prev;
                x.next = null;
            }
        }
    }

    public int indexOf(Integer value) {
        return 1;
    }

    public void add(int index, Integer value) {

    }

    public Integer removeIndex(int index) {
        return 1;
    }
}
