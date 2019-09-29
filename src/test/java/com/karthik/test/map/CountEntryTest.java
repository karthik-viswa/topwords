package com.karthik.test.map;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CountEntryTest {

    private final char[] WORD1 = new char[]{'A', 'p', 'p', 'l', 'e'};

    private final char[] WORD2 = new char[]{'O', 'r', 'a', 'n', 'g', 'e'};

    @Test
    public void testCreateCountEntry() {
        CountEntry entry1 = new CountEntry(WORD1);
        assertEquals(Arrays.hashCode(WORD1), entry1.getHash());

        CountEntry entry2 = new CountEntry(WORD2);
        assertEquals(Arrays.hashCode(WORD2), entry2.getHash());

        CountEntry entry3 = new CountEntry(null);
        assertEquals(0, entry3.getHash());
    }

    @Test
    public void linkEntries() {
        CountEntry entry1 = new CountEntry(WORD1);

        CountEntry entry2 = new CountEntry(WORD2);

        entry1.setNext(entry2);

        assertEquals(entry2, entry1.getNext());
        assertEquals(null, entry2.getNext());
    }
}
