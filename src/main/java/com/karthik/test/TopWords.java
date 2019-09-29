package com.karthik.test;

import com.karthik.test.count.WordCountEntry;
import com.karthik.test.map.CountMap;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TopWords
{
    public static void main(String[] args)
    {
//        if(args.length == 0)
//        {
//            System.out.println("You must supply the path to the file");
//            return;
//        }

        List<Word>  words = new ArrayList<>();

        CountMap wordCounts = new CountMap();

        List<WordCountEntry> allCounts = new ArrayList<>();

        // use this if using array
        WordCountEntry[] allCountsArr = new WordCountEntry[words.size()];

        try
        {
            String filePath = "/Users/Karthik/Work/topwords/src/main/resources/test2.txt"; //args[0];
            BufferedInputStream in = new BufferedInputStream(Files.newInputStream(Paths.get(filePath)));
            byte[] chunk = new byte[1000];

            int bytesRead;

            while((bytesRead = in.read(chunk)) != -1)
            {
                char[] characters = getCharactersFromChunk(chunk, bytesRead);

                Word word = new Word();

                char[] wordBuffer = new char[characters.length];
                int bufferIndex = 0;

                for(char ch : characters) {
                    if(ch == '\u0000' || ch == ',' || ch == '.') {
                        continue;
                    }
                    if(ch == ' ') {
                        char[] wordArray = new char[bufferIndex];
                        System.arraycopy(wordBuffer, 0, wordArray, 0, wordArray.length);

                        word.setChars(wordArray);
                        wordBuffer = new char[characters.length];
                        bufferIndex = 0;
                        words.add(word);

                        countWord(wordCounts, word);

                        word = new Word();
                    }
                    else {
                        wordBuffer[bufferIndex++] = ch;
                    }
                }

                if(bufferIndex > 0) {
                    char[] wordArray = new char[bufferIndex];
                    System.arraycopy(wordBuffer, 0, wordArray, 0, wordArray.length);

                    word.setChars(wordArray);
                    words.add(word);

                    countWord(wordCounts, word);
                }
                //chunk = new byte[1000];
            }
            System.out.println("Words:\n" + words);
            System.out.println("Word counts:\n" + wordCounts);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("The file was not found");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred when attempting to read from the specified file ");
        }
    }

//    private static void countWord(final Map<Word, Integer> wordCounts, final Word word)
//    {
//        Integer newValue = wordCounts.computeIfPresent(word, (key, value) -> value + 1);
//        if (newValue == null)
//        {
//            wordCounts.put(word, 1);
//        }
//    }

    private static void countWord(final CountMap wordCounts, final Word word)
    {
        int existingCount = wordCounts.get(word.getChars());

        wordCounts.put(word.getChars(), ++existingCount);
    }

    private static char[] getCharactersFromChunk(final byte[] chunk, final int bytesRead)
    {
        char[] characters = new char[bytesRead];
        int chIndex = 0;
        boolean whitespaceMode = true;

        for(int i=0; i < bytesRead; i++) {
            char ch = (char)chunk[i];
            if(ch == '\r' || ch == '\n')
            {
                if(whitespaceMode) {
                    characters[chIndex++] = ' ';
                    whitespaceMode = false;
                }
            }
            else {
                characters[chIndex++] = ch;
                whitespaceMode = true;
            }
        }

        return characters;
    }

    List<Word> makeWords(byte[] bytes)
    {
        return null;
    }
}
