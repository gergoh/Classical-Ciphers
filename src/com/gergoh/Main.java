package com.gergoh;

import com.gergoh.encrypters.classical.PlayfairCipher;

// Playfair cipher testing
public class Main {
    public static void main(String[] args) {
        String text = "hide the gold";
        String key = "Hello world";

        PlayfairCipher cipher = new PlayfairCipher(text, key);

        //char [][] formattedInput = cipher.formatInput();
        char[][] keyTable = cipher.fillKeyTable();

        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                System.out.print(keyTable[row][column]);
            }
            System.out.println();
        }


        String cipherText = cipher.encryptionProcess(keyTable);

        System.out.println("\n" + cipherText);

    }
}