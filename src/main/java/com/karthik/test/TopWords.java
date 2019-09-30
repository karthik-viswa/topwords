package com.karthik.test;

import com.karthik.test.map.CountEntry;
import com.karthik.test.map.CountMap;
import com.karthik.test.sort.CountEntrySorter;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

public class TopWords
{
    public static void main(String[] args)
    {
        if(args.length == 0)
        {
            System.out.println("You must supply the path to the file");
            return;
        }

        CountMap wordCounts = new CountMap();

        try
        {
            String filePath = args[0];
            BufferedInputStream in = new BufferedInputStream(Files.newInputStream(Paths.get(filePath)));
            byte[] chunk = new byte[1000];

            int bytesRead;

            while((bytesRead = in.read(chunk)) != -1)
            {
                char[] characters = getCharactersFromChunk(chunk, bytesRead);

                char[] wordBuffer = new char[characters.length];
                int bufferIndex = 0;

                for(char ch : characters) {
                    if(ch != ' ' && (!Character.isLetterOrDigit(ch) || ch == '\u0000')) {
                        continue;
                    }
                    if(ch == ' ') {
                        char[] wordArray = new char[bufferIndex];
                        System.arraycopy(wordBuffer, 0, wordArray, 0, wordArray.length);

                        wordBuffer = new char[characters.length];
                        bufferIndex = 0;

                        countWord(wordCounts, wordArray);
                    }
                    else {
                        wordBuffer[bufferIndex++] = ch;
                    }
                }

                if(bufferIndex > 0) {
                    char[] wordArray = new char[bufferIndex];
                    System.arraycopy(wordBuffer, 0, wordArray, 0, wordArray.length);

                    countWord(wordCounts, wordArray);
                }
                chunk = new byte[1000];
            }

            //System.out.println("Word counts:\n" + wordCounts);

            List<CountEntry> entries = wordCounts.getEntries();

            final CountEntry[] orderedWords = entries.toArray(new CountEntry[entries.size()]);

            new CountEntrySorter().sort(orderedWords);

            int displaySize = orderedWords.length >= 20? 20 : orderedWords.length;

            IntStream.range(0, displaySize).forEach(i -> display(orderedWords[i]));
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

    private static void countWord(final CountMap wordCounts, char[] word)
    {
        int existingCount = wordCounts.get(word);

        wordCounts.put(word, ++existingCount);
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
                characters[chIndex++] = Character.toLowerCase(ch);
                whitespaceMode = true;
            }
        }

        return characters;
    }

    private static void display(CountEntry entry) {
        System.out.print(entry.getCount() + " ");

        char[] word = entry.getWord();
        IntStream.range(0, word.length).forEach(i -> System.out.print(word[i]));
        System.out.println();
    }
}
