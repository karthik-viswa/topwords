package com.karthik.test;

import java.util.Arrays;

public class Word
{
    private char[] chars;

    private int count;

//    public Word(final char[] chars)
//    {
//        this.chars = chars;
//    }

    public char[] getChars()
    {
        return chars;
    }

    public void setChars(final char[] chars)
    {
        this.chars = chars;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(final int count)
    {
        this.count = count;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final Word word = (Word) o;

        return Arrays.equals(chars, word.chars);
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(chars);
    }

    @Override
    public String toString()
    {
        return "Word{" + "chars=" + Arrays.toString(chars) + "}"; //, count=" + count + '}';
    }
}
