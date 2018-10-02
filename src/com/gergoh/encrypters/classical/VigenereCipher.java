package com.gergoh.encrypters.classical;

/* Vigenere Cipher
   A type of polyalphabetic substitution cipher
   It's using a series of interwoven Caesar ciphers
   It resisted all attempts to break it for three centuries
   In 1863, Friedrich Kasiski was the first to publish a general method of deciphering Vigenere ciphers
   It was originally described by Giovan Battista Bellaso in 1553 but the scheme was later misattributed to Blaise de Vigenere in the 19th century
   The Confederate States of America used a brass cipher disk to implement the Vigenere cipher
   The Confederacy's messages were regularly cracked by the Union during the American Civil War
   The Confederate leadership primarily relied upon three key phases: "Manchester Bluff", "Complete Victory" and "Come Retribution"
   Gilbert Vernan, trying to repair the broken cipher eventually led to the one-time pad, a theoretically unbreakable cipher
 */

public class VigenereCipher {
    private String input;
    private String key;

    // Overloaded constructor
    // Needs the input plaintext/ciphertext and the key value (both String)
    public VigenereCipher(String inputText, String inputKey){
        input = inputText;
        key = inputKey;
    }

    // Encryption (plaintext -> ciphertext)
    public String encrypt(String text, String key) {
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

    // Decryption (ciphertext -> plaintext)
    public  String decrypt(String text, String key) {
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
