package com.karthik.test.sort;

import com.karthik.test.map.CountEntry;

import java.util.function.BiPredicate;

public class CountEntrySorter {

    private static final BiPredicate<CountEntry, CountEntry> COUNT_GREATER = ((entry1, entry2) -> entry1.getCount() > entry2.getCount());

    private static final BiPredicate<CountEntry, CountEntry> COUNT_LESS_THAN_OR_EQUAL = ((entry1, entry2) -> entry1.getCount() <= entry2.getCount());

    public void sort(CountEntry[] entries) {

        sort(entries, false);

    }

    public void sort(CountEntry[] entries, boolean ascending) {
        if(entries == null || entries.length == 0) {
            return;
        }

//        CountEntry[] entriesArray = new CountEntry[entries.size()];
//        entriesArray = entries.toArray(entriesArray);

        quickSort(entries, 0, entries.length-1, ascending? COUNT_LESS_THAN_OR_EQUAL : COUNT_GREATER);

        //entries = Arrays.asList(entriesArray);
    }

    private void quickSort(CountEntry arr[], int begin, int end, BiPredicate<CountEntry, CountEntry> compare) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end, compare);

            quickSort(arr, begin, partitionIndex-1, compare);
            quickSort(arr, partitionIndex+1, end, compare);
        }
    }

    private int partition(CountEntry arr[], int begin, int end, BiPredicate<CountEntry, CountEntry> compare) {
        CountEntry pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (compare.test(arr[j], pivot)) {
                i++;

                CountEntry temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        CountEntry swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }
}
