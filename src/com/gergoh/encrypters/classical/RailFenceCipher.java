package com.gergoh.encrypters.classical;

/* Rail Fence Cipher
   Also known as zigzag cipher
   A type of transposition cipher
   Invented by ancient Greeks through a tool called Scytale
   It was used back in the American Civil War
 */

import java.util.ArrayList;
import java.util.Scanner;

public class RailFenceCipher {
    Scanner input = new Scanner(System.in);
    private String plainText; // User inputted plaintext
    private int key; // Key value tells us the depth (rows) of our cipher

    private char[] formattedInput; // plaintext reformatted for later use
    private char[][] cipherTable;

    // Constructor if no input passed in
    // Goes through entire encryption process and outputs finished ciphertext
    public RailFenceCipher(){
        System.out.println("\nEnter plaintext: ");
        plainText = input.nextLine();
        System.out.println("\nEnter a key number: ");
        key = input.nextInt();
        encrypt();
        System.out.print(outputCipherText());
    }

    // Constructor takes in the plaintext and key value
    // Goes through entire encryption process and outputs finished ciphertext
    public RailFenceCipher(String input, int key){
        plainText = input;
        this.key = key;
        encrypt();
        System.out.print(outputCipherText());
    }

    // Formats plaintext into consecutive uppercase letters
    private void formatInput(){
        ArrayList<Character> tempArray = new ArrayList<Character>();
        for(int i = 0; i < plainText.length(); i++){
            while(!Character.isLetter(plainText.charAt(i))) i++;
            tempArray.add(plainText.toUpperCase().charAt(i));
        }
        formattedInput = new char[tempArray.size()];
        for (int i = 0; i < tempArray.size(); i++) formattedInput[i] = tempArray.get(i);
    }

    // TODO Add 'X's as placeholders so that there are the same number of letters on the top row, as on the bottom row
    // Encryption process
    private String encrypt(){
        formatInput();
        cipherTable = new char[key][formattedInput.length];
        for (int columnCounter = 0, rowCounter = 0; columnCounter < formattedInput.length;) {
            for(rowCounter = 0; rowCounter < key; rowCounter++, columnCounter++) {
                if(columnCounter >= formattedInput.length) break;
                cipherTable[rowCounter][columnCounter] = formattedInput[columnCounter];
            }
            for(rowCounter -= 2; rowCounter > 0; rowCounter--, columnCounter++) {
                if(columnCounter >= formattedInput.length) break;
                cipherTable[rowCounter][columnCounter] = formattedInput[columnCounter];
            }
        }
        return outputCipherText();
    }


    // Converts finished encrypted multi-row table into single line String
    private String outputCipherText() {
        char[] cipherText = new char[cipherTable[0].length];
        for (int row = 0; row < key; row++) {
            for (int column = 0; column < cipherTable[0].length; column++)
                if (Character.isLetter(cipherTable[row][column])) cipherText[row] = cipherTable[row][column];
        }
        return new String(cipherText);
    }

    /*
    // Outputs encryption table in 'key' amount of rows
    // FOR TESTING!
    public void outputCipherTable() {
        for (int row = 0; row < key; row++) {
            for (int column = 0; column < formattedInput.length; column++)
                if (Character.isLetter(cipherTable[row][column])) System.out.print(cipherTable[row][column]);
                else System.out.print(" ");
            System.out.println();
        }
    }
    */
}
