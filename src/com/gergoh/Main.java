// TODO Make key table accessible for the future as it is needed to decrypt
// TODO simplify findRule booleans
// TODO Turn it into a package com.gergoh.encrypters.classical.playfairCipher
package com.gergoh;

import java.util.ArrayList;


// Playfair cipher constructing, testing
public class Main {

    public static void main(String[] args) {
        String text = "hide the gold";
        String key = "Hello world";

        ArrayList<char[]> formattedInput = formatInput(text);
    }

    // Format input into two letter chunks
    // If uneven letters, 'Z' is added to last chunk
    private static ArrayList<char[]> formatInput(String input){
        ArrayList<char[]> formattedInput = new ArrayList<>();
        char[] temp = new char[2];
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < 2; j++) {
                if (i >= input.length()) {
                    temp[j] = 'Z';
                    break;
                }
                if (Character.isLetter(input.charAt(i))) temp[j] = input.toUpperCase().charAt(i);
                i++;
            }
            formattedInput.add(temp);
        }
        return formattedInput;
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
        // Boolean value for which rule we need to use
        boolean column = false,
                row = false,
                neither = false;

        // Finding correct rule and finding index in key table
        int[] firstIndex = new int[2];
        int[] secondIndex = new int[2];
        char [] encrypted = new char[input.size() * 2];
        for(int i = 0; i < input.size(); i++){
            char [] letterChunk = input.get(i);
            findRule(keyTable, letterChunk, column, row, neither);
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (letterChunk[0] == keyTable[r][c]) {
                        firstIndex[0] = r;
                        firstIndex[1] = c;
                    }
                    if (letterChunk[1] == keyTable[r][c]) {
                        secondIndex[0] = r;
                        secondIndex[1] = c;
                    }
                }
            }

            // Getting correct encrypted letter from key table
            if(column){
                if(firstIndex[0] == 4) firstIndex[0] = -1;
                encrypted[i] = keyTable[firstIndex[0] + 1][firstIndex[1]];
                i++;
                if(secondIndex[0] == 4) secondIndex[0] = -1;
                encrypted[i] = keyTable[secondIndex[0] + 1][secondIndex[1]];
            }
            if(row){
                if(firstIndex[1] == 4) firstIndex[1] = -1;
                encrypted[i] = keyTable[firstIndex[0]][firstIndex[1] + 1];
                i++;
                if(secondIndex[1] == 4) secondIndex[1] = -1;
                encrypted[i] = keyTable[secondIndex[0]][secondIndex[1] + 1];
            }
            if(neither){
                encrypted[i] = keyTable[firstIndex[0]][secondIndex[1]];
                i++;
                encrypted[i] = keyTable[secondIndex[0]][firstIndex[1]];
            }
        }

        return new String(encrypted);
    }

    // Method iterates through input to check which rule we need to use on each chunk
    private static void findRule(char [][] keyTable, char [] chunk, boolean column, boolean row, boolean neither) {
        // Resetting all boolean values to false
        column = row = neither = false;

        // Storing indexes of letters of chunks in key table to later compare positions and decide rule
        int[] firstIndex = new int[2];
        int[] secondIndex = new int[2];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (chunk[0] == keyTable[r][c]) {
                    firstIndex[0] = r;
                    firstIndex[1] = c;
                }
                if (chunk[1] == keyTable[r][c]) {
                    secondIndex[0] = r;
                    secondIndex[1] = c;
                }
            }
        }

        if (firstIndex[1] == secondIndex[1]) column = true;
        else if(firstIndex[0] == secondIndex[0]) row = true;
        else neither = true;
    }
}
