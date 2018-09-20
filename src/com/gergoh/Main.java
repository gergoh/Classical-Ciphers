package com.gergoh;

import java.util.ArrayList;


// Playfair cipher constructing, testing
public class Main {

    public static void main(String[] args) {
        String text = "hide the gold";
        String key = "Hello world";

        // Procedure to take input into two letter chunks
        // If uneven letters, 'Z' added to last chunk
        ArrayList<char[]> formattedInput = new ArrayList<char[]>();
        char[] temp = new char[2];
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < 2; j++) {
                if (i >= text.length()) {
                    temp[j] = 'Z';
                    break;
                }
                if (Character.isLetter(text.charAt(i))) temp[j] = text.toUpperCase().charAt(i);
                i++;
            }
            formattedInput.add(temp);
        }
    }

    // Takes the key and fills the 5x5 key table
    private static char[][] fillKeyTable(String key) {
        // 5x5 key table declaration
        char[][] keyTable = new char[5][5];

        // Placing key text into beginning of key table
        int itr = 0;
        tableloop:
        for (int i = 0; i < key.length(); i++) {
            for (int row = 0; row < 5; row++) {
                for (int column = 0; column < 5; column++) {
                    if (itr >= key.length()) break tableloop;
                    while (Character.isWhitespace(key.charAt(itr))) itr++;
                    while (duplicateCheck(keyTable, key, itr)) itr++;
                    keyTable[row][column] = key.toUpperCase().charAt(itr);
                    itr++;
                }
            }
        }

        // Putting missing letters of ABC into key table following the text letters
        // Q is omitted (table is only 25 letters)
        // r(ow) and c(olumn) variable set to the next empty index
        int r = 0;
        int c = 0;
        findemptyloop:
        while (r < 5) {
            while (c < 5) {
                if (keyTable[r][c] == 0) break findemptyloop;
                c++;
            }
            c = 0;
            r++;
        }

        // If text that was put into key table ends in the middle of a row, fill it separately
        char ch = 'A';
        if (c != 0) {
            while (c < 5) {
                while (duplicateCheck(keyTable, ch)) ch++;
                if (ch != 'Q') keyTable[r][c] = ch;
                ch++;
                c++;
            }
        }

        // Rest of the key table is filled with ABC
        r++;
        while (r < 5) {
            for (int column = 0; column < 5; column++) {
                if (ch == 'Q') ch++;
                while (duplicateCheck(keyTable, ch)) ch++;
                keyTable[r][column] = ch;
                ch++;
            }
            r++;
        }

        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                System.out.print(keyTable[row][column]);
            }
            System.out.println();
        }

        return keyTable;
    }

    // Checks if any letters that are about to be added to the key table are not already there to avoid duplicates
    private static boolean duplicateCheck(char[][] keyTable, String key, int itr) {
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                if (keyTable[row][column] == key.toUpperCase().charAt(itr)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Function overload for when char is used as iterator
    private static boolean duplicateCheck(char[][] keyTable, char ch) {
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                if (keyTable[row][column] == ch) {
                    return true;
                }
            }
        }
        return false;
    }

    // Actual encryption process
    // If both letters are in the same column, take the letter below each one
    // If both letters are in the same row, take the letter to the right of each one
    // If neither of the preceding two rules are true, form a rectangle of two letters and take the letters on the horizontal opposite corner of the rectangle
    private static String encryptionProcess(char[][] keyTable, ArrayList<char[]> input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < 2; j++) {

            }
        }
    }
}
