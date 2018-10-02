package com.gergoh.encrypters.classical;

/* Rail Fence Cipher
   Also known as zigzag cipher
   A type of transposition cipher
   Invented by ancient Greeks through a tool called Scytale
   It was used back in the American Civil War
 */

import java.util.ArrayList;

public class RailFenceCipher {
    // TODO Delete temporary test variables
    // User inputted plaintext
    private String plainText = "defend the east wall";
    // Key value tells us the depth (rows) of our cipher
    private int key = 3;

    private char[] formattedInput;
    private char[][] cipherText;

    public RailFenceCipher(){
        formattedInput = formatInput();
        cipherText = encrypt();
    }

    public RailFenceCipher(String input, int key){
        plainText = input;
        this.key = key;
        formattedInput = formatInput();
        cipherText = encrypt();
    }

    // Formats plaintext into consecutive uppercase letters
    private char[] formatInput(){
        ArrayList<Character> tempArray = new ArrayList<Character>();
        for(int i = 0; i < plainText.length(); i++){
            while(!Character.isLetter(plainText.charAt(i))) i++;
            tempArray.add(plainText.toUpperCase().charAt(i));
        }
        formattedInput = new char[tempArray.size()];
        for (int i = 0; i < tempArray.size(); i++) formattedInput[i] = tempArray.get(i);

        return formattedInput;
    }

    // Encryption process
    private char[][] encrypt(){
        cipherText = new char[key][formattedInput.length];

        for (int columnCounter = 0, rowCounter = 0; columnCounter < formattedInput.length; columnCounter++) {
            for(rowCounter = 0; rowCounter < key; rowCounter++, columnCounter++) cipherText[rowCounter][columnCounter] = formattedInput[columnCounter];
            for(rowCounter -= 2; rowCounter > 0; rowCounter--, columnCounter++) cipherText[rowCounter][columnCounter] = formattedInput[columnCounter];
        }
        return cipherText;
    }


    // Outputs finished ciphertext in a single line
    public void outputCipherText() {
        for (int row = 0; row < key; row++) {
            for (int column = 0; column < formattedInput.length; column++)
                if (Character.isLetter(cipherText[row][column])) System.out.println(cipherText[row][column]);
            System.out.print(" ");
        }
    }
}
