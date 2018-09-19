package com.company;

import java.util.Scanner;

public class caesarCipher {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String choice;

        // User is offered two options, to Encrypt text or Decrypt
        // Keeps looping until answer starts with either letter E or D
        System.out.println("Encrypt or Decrypt? E/D");
        do {
            choice = input.nextLine().toUpperCase();
        } while (!choice.startsWith("E") && !choice.startsWith("D"));

        int key;
        // Switch statement check first letter of String choice (E or D)
        switch (choice.charAt(0)) {
            case 'E': // Encryption dialog
                System.out.println("Enter plaintext:\t");
                String plainText = input.nextLine();
                System.out.println("Enter encryption key:\t");
                key = Integer.parseInt(input.nextLine());
                System.out.println(caesarCipher(plainText, key));
                break;
            case 'D': // Decryption dialog
                System.out.println("Enter ciphertext:\t");
                String cipherText = input.nextLine();
                System.out.println("Enter decryption key:\t");
                key = Integer.parseInt(input.nextLine());
                key *= -1; // VITAL part for decryption!
                System.out.println(caesarCipher(cipherText, key));
                break;
        }

    }

    // The key value needs to be multiplied by -1 if we are decrypting
    private static String caesarCipher(String text, int key) {
        // Copying the String text into an array of chars so we can access it and modify it, index by index
        char[] charText = text.toCharArray();
        // A temporarily holder for the converted ASCII values
        int ASCII;

        // If it isn't an alphabetic letter, it keeps it unchanged
        for (int i = 0; i < charText.length; i++) {
            if (Character.isUpperCase(charText[i])) {
                // ASCII value of character (in int)
                ASCII = (((int) charText[i]) + key);
                // Checking if ASCII value after adding key would is still an alphabetic character
                // Adding/substracting 26 loops the value back to the right ASCII range
                if (ASCII > 90) ASCII -= 26;
                if (ASCII < 65) ASCII += 26;
                // ASCII value converted into its char representation and charText with index = i is updated
                charText[i] = (char) ASCII;
            }
            if (Character.isLowerCase(charText[i])) {
                // ASCII value of character (in int)
                ASCII = (((int) charText[i]) + key);
                // Checking if ASCII value after adding key would is still an alphabetic character
                // Adding/substracting 26 loops the value back to the right ASCII range
                if (ASCII > 122) ASCII -= 26;
                if (ASCII < 97) ASCII += 26;
                // ASCII value converted into its char representation and charText with index = i is updated
                charText[i] = (char) ASCII;
            }
        }

        // The finished charText is converted into a String and returned
        return new String(charText);
    }

}