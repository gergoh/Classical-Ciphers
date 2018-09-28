package com.gergoh.encrypters.classical;

public class vigenereCipher {
    public String input;
    public String key;

    // Overloaded constructor
    // Needs the input plaintext/ciphertext and the key value (both String)
    public vigenereCipher(String inputText, String inputKey){
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
