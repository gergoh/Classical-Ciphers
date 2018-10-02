package com.gergoh.encrypters.classical;

/* Playfair Cipher
   Manual symmetric encryption technique
   First literal digram substitution cipher
   There are 600 possibilities
   Treats diagrams in the plaintext as single units and translates these units into ciphertext diagrams
   Based on the use of a 5x5 matrix of letters constructed using a keyword
   Requires no equipment, just a pencil and paper
   Invented by British scientist Sir Charles Wheatstone in 1854 but bears the name of Lord Playfair for promoting its use
   It was initially rejected by the British Foreign Office for its perceived complexity
   Later it was used as the standard field system by the British Army in WWI and the U.S. Army and other Allied forces during WWII
   First solution was described in a 19 page pamphlet by Lieutenant Joseph O. Mauborgne in 1914
   The German Army, Air Force and Police used the Double Playfair system as a medium-grade cipher in WWII
   It lends itself well to crossword puzzles
 */

import java.util.ArrayList;

public class PlayfairCipher {
    // Boolean values for which rule we need to use
    private boolean columnRule = false,
                    rowRule = false,
                    thirdRule = false;

    // User inputted text and key taken from the constructor
    private String plainText;
    private String key;

    // Two-letter chunk version of plaintext input
    private char [][] formattedInput;
    // 5x5 key table to use for encryption
    private char [][] keyTable;

    // Constructor that takes a plaintext input and key from the user
    public PlayfairCipher(String inputText, String inputKey){
        this.plainText = inputText;
        this.key = inputKey;
        formattedInput = formatInput();
        keyTable = fillKeyTable();
    }

    // Format input into two letter chunks
    // If uneven letters, 'Z' is added to last chunk
    private char[][] formatInput(){
        ArrayList<char[]> tempArray = new ArrayList<>();
        for (int i = 0; i < plainText.length(); i += 2) {
            char[] tempChar = new char[2];
            for (int j = 0; j < tempChar.length; j++) {
                while (!Character.isLetter(plainText.charAt(i + j))) i++;
                tempChar[j] = plainText.toUpperCase().charAt(i + j);
                if(i == plainText.length() - 1) tempChar[++j] = 'Z';
            }
            tempArray.add(tempChar);
        }
        char [][] formattedInput = new char[tempArray.size()][2];
        for (int i = 0; i < tempArray.size(); i++) formattedInput[i] = tempArray.get(i);

        return formattedInput;
    }

    // TODO duplicateCheck methods -> change parameters because we're using class variables
    // Takes the key and fills the 5x5 key table
    public char[][] fillKeyTable() {
        // 5x5 key table
        keyTable = new char[5][5];

        // Placing key text into beginning of key table
        tableloop:
        for (int i = 0; i < key.length();) {
            for (int row = 0; row < 5; row++) {
                for (int column = 0; column < 5; column++) {
                    while (Character.isWhitespace(key.charAt(i))) i++;
                    while (duplicateCheck(keyTable, i)) i++;
                    keyTable[row][column] = key.toUpperCase().charAt(i++);
                    if (i >= key.length()) break tableloop;
                }
            }
        }

        // Putting missing letters of ABC into key table following the text letters
        // Q is omitted (table is only 25 letters)
        // r(ow) and c(olumn) variable set to the next empty index
        int r = 0;
        int c = 0;
        findemptyloop:
        for (; r < 5; r++) {
            for (; c < 5; c++) if (keyTable[r][c] == 0) break findemptyloop;
            c = 0;
        }

        // If text that was put into key table ends in the middle of a row, fill it separately
        char ch = 'A';
        if (c != 0) {
            for (; c < 5; c++) {
                while (duplicateCheck(keyTable, ch)) ch++;
                if (ch != 'Q') keyTable[r][c] = ch++;
            }
            r++;
        }

        // Rest of the key table is filled with ABC
        for(; r < 5; r++) {
            for (int column = 0; column < 5; column++) {
                if (ch == 'Q') ch++;
                while (duplicateCheck(keyTable, ch)) ch++;
                keyTable[r][column] = ch++;
            }
        }

        return keyTable;
    }

    // Checks if any letters that are about to be added to the key table are not already there to avoid duplicates
    private boolean duplicateCheck(char[][] keyTable, int itr) {
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
    private boolean duplicateCheck(char[][] keyTable, char ch) {
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
    public String encryptionProcess(char[][] keyTable) {
        // Finding correct rule and finding index in key table
        int[] firstIndex = new int[2];
        int[] secondIndex = new int[2];
        char [] encrypted = new char[formattedInput.length * 2];
        for(int i = 0, j = 0; i < formattedInput.length; i++, j++){
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (formattedInput[i][0] == keyTable[r][c]) {
                        firstIndex[0] = r;
                        firstIndex[1] = c;
                    }
                    if (formattedInput[i][1] == keyTable[r][c]) {
                        secondIndex[0] = r;
                        secondIndex[1] = c;
                    }
                }
            }

            // Getting correct encrypted letter from key table
            findRule(keyTable, formattedInput[i]);
            if(columnRule){
                if(firstIndex[0] == 4) firstIndex[0] = -1;
                encrypted[j++] = keyTable[firstIndex[0] + 1][firstIndex[1]];
                if(secondIndex[0] == 4) secondIndex[0] = -1;
                encrypted[j] = keyTable[secondIndex[0] + 1][secondIndex[1]];
            }
            if(rowRule){
                if(firstIndex[1] == 4) firstIndex[1] = -1;
                encrypted[j++] = keyTable[firstIndex[0]][firstIndex[1] + 1];
                if(secondIndex[1] == 4) secondIndex[1] = -1;
                encrypted[j] = keyTable[secondIndex[0]][secondIndex[1] + 1];
            }
            if(thirdRule){
                encrypted[j++] = keyTable[firstIndex[0]][secondIndex[1]];
                encrypted[j] = keyTable[secondIndex[0]][firstIndex[1]];
            }
        }

        return new String(encrypted);
    }

    // Method iterates through input to check which rule we need to use on each chunk
    private void findRule(char [][] keyTable, char [] chunk) {
        // Resetting all boolean values to false
        columnRule = false;
        rowRule = false;
        thirdRule = false;

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

        if (firstIndex[1] == secondIndex[1]) columnRule = true;
        else if(firstIndex[0] == secondIndex[0]) rowRule = true;
        else thirdRule = true;
    }
}
