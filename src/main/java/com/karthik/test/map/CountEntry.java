package com.karthik.test.map;

import java.util.Arrays;

public class CountEntry {
    private int hash;

    private char[] word;

    private int count;

    private CountEntry next;

    private CountEntry prev;

    public CountEntry(char[] word) {
        this.word = word;
        this.hash = hashCode();
    }

    public CountEntry(char[] word, int count) {
        this.word = word;
        this.count = count;
        this.hash = hashCode();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CountEntry getNext() {
        return next;
    }

    public void setNext(CountEntry next) {
        this.next = next;
    }

    public CountEntry getPrev() {
        return prev;
    }

    public void setPrev(CountEntry prev) {
        this.prev = prev;
    }

    public int getHash() {
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountEntry that = (CountEntry) o;
        return Arrays.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(word);
    }

    @Override
    public String toString() {
        return "{" +
                "word=" + Arrays.toString(word) +
                ", count=" + count +
                '}';
    }
}
