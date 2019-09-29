package com.karthik.test.map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountMapTest {

    private final char[] WORD1 = new char[]{'A', 'p', 'p', 'l', 'e'};

    private final char[] WORD2 = new char[]{'O', 'r', 'a', 'n', 'g', 'e'};

    private final char[] WORD3 = new char[]{'A', 'p', 'p', 'l', 'e', 's'};

    private final char[] WORD4 = new char[]{'b', 'a', 'n', 'a', 'n', 'a'};

    private final char[] WORD5 = new char[]{'g', 'r', 'a', 'p', 'e'};

    @Test
    public void testPutEntry() {

        CountMap map = new CountMap();
        assertEquals(0, map.get(WORD1));
        assertEquals(0, map.get(WORD2));

        CountEntry entry1 = new CountEntry(WORD1);
        map.put(WORD1, 1);

        assertEquals(1, map.get(WORD1));
        assertEquals(0, map.get(WORD2));

        CountEntry entry2 = new CountEntry(WORD2);
        map.put(WORD2, 2);

        assertEquals(1, map.get(WORD1));
        assertEquals(2, map.get(WORD2));

        map.put(WORD1, 2);
        assertEquals(2, map.get(WORD1));
        assertEquals(2, map.get(WORD2));

        map.put(WORD3, 3);
        assertEquals(2, map.get(WORD1));
        assertEquals(2, map.get(WORD2));
        assertEquals(3, map.get(WORD3));

        map.put(WORD4, 4);
        map.put(WORD5, 5);
        assertEquals(4, map.get(WORD4));
        assertEquals(5, map.get(WORD5));
    }

    @Test
    public void testGetEntries() {
        CountMap map = new CountMap();

        assertEquals(0, map.getEntries().size());

        map.put(WORD1, 1);
        assertEquals(1, map.getEntries().size());

        map.put(WORD1, 2);
        assertEquals(1, map.getEntries().size());

        map.put(WORD2, 1);
        assertEquals(2, map.getEntries().size());

        map.put(WORD1, 3);
        assertEquals(2, map.getEntries().size());


        map.put(WORD3, 1);
        map.put(WORD4, 1);
        map.put(WORD5, 1);
        assertEquals(5, map.getEntries().size());

        for(int i=0; i < 100; i++) {
            map.put(("word"+i).toCharArray(), i);
        }

        assertEquals(105, map.getEntries().size());
    }
}
