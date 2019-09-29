package com.karthik.test.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountMap {

    private static final int INITIAL_CAPACITY = 4;

    private List<CountEntry> buckets;

    private int capacity;

    public CountMap() {
        buckets = new ArrayList<>(INITIAL_CAPACITY);

        for(int i=0; i < INITIAL_CAPACITY; i++) {
            buckets.add(null);
        }

        capacity = INITIAL_CAPACITY;
    }

    public void put(char[] word, int count) {
        //CountEntry existingEntry = getEntry(word);

        CountEntry newEntry = new CountEntry(word, count);

//        if(existingEntry != null) {
//            CountEntry prevEntry = existingEntry.getPrev();
//            if(prevEntry != null) {
//                prevEntry.setNext(newEntry);
//            }
//            else {
//
//            }
//
//            newEntry.setPrev(prevEntry);
//            newEntry.setNext(existingEntry.getNext());
//
//            existingEntry.setPrev(null);
//            existingEntry.setNext(null);
//
//            return;
//        }

        int targetHash = Arrays.hashCode(word) & 0x7fffffff;
        int bucketNumber = targetHash % capacity;

        if(buckets.get(bucketNumber) == null) {
            buckets.set(bucketNumber, newEntry);
        }
        else {
            CountEntry entry = buckets.get(bucketNumber);

            do {
                if(entry.equals(newEntry)) {
                    CountEntry prevEntry = entry.getPrev();
                    if(prevEntry != null) {
                        prevEntry.setNext(newEntry);
                    }
                    newEntry.setPrev(prevEntry);
                    newEntry.setNext(entry.getPrev());

                    entry.setPrev(null);
                    entry.setNext(null);

                    if(newEntry.getPrev() == null) {
                        buckets.set(bucketNumber, newEntry);
                    }
                    return;
                }

                if(entry.getNext() != null)
                    entry = entry.getNext();
            }while(entry.getNext() != null);

            entry.setNext(newEntry);
            newEntry.setPrev(entry);
        }
    }

    public int get(char[] word) {
        CountEntry entry = getEntry(word);

        if(entry != null) {
            return entry.getCount();
        }

        return 0;
    }

    private CountEntry getEntry(char[] word) {
        int targetHash = Arrays.hashCode(word) & 0x7fffffff;
        int bucketNumber = targetHash % capacity;

        if(buckets.get(bucketNumber) == null) {
            return null;
        }

        CountEntry bucketEntry = buckets.get(bucketNumber);

        do {
            if((bucketEntry.getHash() & 0x7fffffff)== targetHash) {
                return bucketEntry;
            }
            bucketEntry = bucketEntry.getNext();
        } while(bucketEntry != null);

        return null;
    }

    public List<CountEntry> getEntries() {
        List<CountEntry> entries = new ArrayList<>();

        for(CountEntry entry : buckets) {
            while(entry != null) {
                entries.add(entry);
                entry = entry.getNext();
            }
        }

        return entries;
    }

    @Override
    public String toString() {
        return getEntries().toString();
    }
}
