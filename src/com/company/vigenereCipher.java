package com.company;

import java.util.Scanner;

public class vigenereCipher {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String choice;
        String key;

        // User is offered two options, to Encrypt text or Decrypt
        // Keeps looping until answer starts with either letter E or D
        System.out.println("Encrypt or Decrypt? E/D");
        do {
            choice = input.nextLine().toUpperCase();
        } while (!choice.startsWith("E") && !choice.startsWith("D"));

        // Switch statement check first letter of String choice (E or D)
        switch (choice.charAt(0)) {
            case 'E': // Encryption dialog
                System.out.println("Enter plaintext:\t");
                String plainText = input.nextLine();
                System.out.println("Enter encryption key:\t");
                key = input.nextLine();
                System.out.println(vigenereEncrypt(plainText, key));
                break;
            case 'D': // Decryption dialog
                System.out.println("Enter ciphertext:\t");
                String cipherText = input.nextLine();
                System.out.println("Enter decryption key:\t");
                key = input.nextLine();
                System.out.println(vigenereDecrypt(cipherText, key));
                break;
        }

    }

    // Encryption
    private static String vigenereEncrypt(String text, String key) {
        // Copying input text and key into char arrays so later we can access them by index
        char[] charText = text.toCharArray();
        char[] keyArray = key.toCharArray();

        int ASCII; // A temporarily holder for the converted ASCII values
        int shift; // A holder for the amount the text needs to be shifted
        int shiftIter = 0; // Key needs separate iterator so it can loop over

        // If it isn't an alphabetic letter, it keeps it unchanged
        for (int i = 0; i < charText.length; i++) {
            // Looping the key characters if text is longer
            if (i >= keyArray.length && shiftIter >= keyArray.length) shiftIter = 0;
            if (Character.isUpperCase(charText[i])) {
                // ASCII value of key (in int)
                shift = (int) keyArray[shiftIter];
                // -65 so it becomes its number in the alphabet
                shift -= 65;
                // ASCII value of character (in int)
                ASCII = (((int) charText[i]) + shift);
                // Checking if ASCII value after adding key would is still an alphabetic character
                // Adding/substracting 26 loops the value back to the right ASCII range
                if (ASCII > 90) ASCII -= 26;
                if (ASCII < 65) ASCII += 26;
                // ASCII value converted into its char representation and charText with index = i is updated
                charText[i] = (char) ASCII;
            }
            if (Character.isLowerCase(charText[i])) {
                // ASCII value of key (in int)
                shift = (int) keyArray[shiftIter];
                // -97 so it becomes its number in the alphabet
                shift -= 97;
                // ASCII value of character (in int)
                ASCII = (((int) charText[i]) + shift);
                // Checking if ASCII value after adding key would is still an alphabetic character
                // Adding/substracting 26 loops the value back to the right ASCII range
                if (ASCII > 122) ASCII -= 26;
                if (ASCII < 97) ASCII += 26;
                // ASCII value converted into its char representation and charText with index = i is updated
                charText[i] = (char) ASCII;
            }

            // Key's iterator is incremented
            shiftIter++;
        }

        // The finished charText is converted into a String and returned
        return new String(charText);
    }

    // Decryption
    private static String vigenereDecrypt(String text, String key) {
        // Copying input text and key into char arrays so later we can access them by index
        char[] charText = text.toCharArray();
        char[] keyArray = key.toCharArray();

        int ASCII; // A temporarily holder for the converted ASCII values
        int shift; // A holder for the amount the text needs to be shifted
        int shiftIter = 0; // Key needs separate iterator so it can loop over

        // If it isn't an alphabetic letter, it keeps it unchanged
        for (int i = 0; i < charText.length; i++) {
            // Looping the key characters if text is longer
            if (i >= keyArray.length && shiftIter >= keyArray.length) shiftIter = 0;
            if (Character.isUpperCase(charText[i])) {
                // ASCII value of key (in int)
                shift = (int) keyArray[shiftIter];
                // -65 so it becomes its number in the alphabet
                shift -= 65;
                // ASCII value of character (in int)
                ASCII = (((int) charText[i]) - shift);
                // Checking if ASCII value after adding key would is still an alphabetic character
                // Adding/substracting 26 loops the value back to the right ASCII range
                if (ASCII > 90) ASCII -= 26;
                if (ASCII < 65) ASCII += 26;
                // ASCII value converted into its char representation and charText with index = i is updated
                charText[i] = (char) ASCII;
            }
            if (Character.isLowerCase(charText[i])) {
                // ASCII value of key (in int)
                shift = (int) keyArray[shiftIter];
                // -97 so it becomes its number in the alphabet
                shift -= 97;
                // ASCII value of character (in int)
                ASCII = (((int) charText[i]) - shift);
                // Checking if ASCII value after adding key would is still an alphabetic character
                // Adding/substracting 26 loops the value back to the right ASCII range
                if (ASCII > 122) ASCII -= 26;
                if (ASCII < 97) ASCII += 26;
                // ASCII value converted into its char representative and charText with index = i is updated
                charText[i] = (char) ASCII;
            }

            // Key's iterator is incremented
            shiftIter++;
        }

        // The finished charText is converted into a String and returned
        return new String(charText);
    }

}