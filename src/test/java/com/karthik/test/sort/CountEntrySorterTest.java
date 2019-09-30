package com.karthik.test.sort;

import com.karthik.test.map.CountEntry;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class CountEntrySorterTest {

    private static final CountEntry ACE_6 = new CountEntry(new char[]{'A', 'c', 'e'}, 6);
    private static final CountEntry JACK_3 = new CountEntry(new char[]{'J', 'a', 'c', 'k'}, 3);
    private static final CountEntry KING_1 = new CountEntry(new char[]{'K', 'i', 'n', 'g'}, 1);
    private static final CountEntry QUEEN_2 = new CountEntry(new char[]{'J', 'a', 'c', 'k'}, 2);
    private static final CountEntry JOKER_10 = new CountEntry(new char[]{'A', 'c', 'e'}, 10);
    private static final CountEntry HEART_5 = new CountEntry(new char[]{'J', 'a', 'c', 'k'}, 5);
    private static final CountEntry CLUB_12 = new CountEntry(new char[]{'A', 'c', 'e'}, 12);
    private static final CountEntry DIAMOND_3 = new CountEntry(new char[]{'J', 'a', 'c', 'k'}, 3);


    @Test
    public void testEntrySort() {

        CountEntry[] entries = new CountEntry[2];
        entries[0] = JACK_3;
        entries[1] = ACE_6;

        CountEntrySorter sorter = new CountEntrySorter();
        sorter.sort(entries);

        // sorts descending by default
        assertEquals(ACE_6, entries[0]);
        assertEquals(JACK_3, entries[1]);

        // sorts ascending if flag is set
        sorter.sort(entries, true);
        assertEquals(JACK_3, entries[0]);
        assertEquals(ACE_6, entries[1]);
    }

    @Test
    public void testEntrySortLarge() {
        CountEntry[] entries = Stream.of(ACE_6, JACK_3, KING_1, QUEEN_2, JOKER_10, HEART_5, CLUB_12, DIAMOND_3).toArray(CountEntry[]::new);

        CountEntrySorter sorter = new CountEntrySorter();
        sorter.sort(entries);

        assertEquals(CLUB_12, entries[0]);
        assertEquals(JOKER_10, entries[1]);
        assertEquals(ACE_6, entries[2]);
        assertEquals(HEART_5, entries[3]);
        assertEquals(JACK_3, entries[4]);
        assertEquals(DIAMOND_3, entries[5]);
        assertEquals(QUEEN_2, entries[6]);
        assertEquals(KING_1, entries[7]);
    }
}
