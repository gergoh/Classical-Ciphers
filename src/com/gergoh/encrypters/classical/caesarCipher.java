package com.gergoh.encrypters.classical;

public class caesarCipher {
    public String input;
    public int key;

    // Overloaded constructors
    public caesarCipher(){
    }

    // Needs the input plaintext/ciphertext and the key value
    public caesarCipher(String inputText, int inputKey){
        input = inputText;
        key = inputKey;
    }

    // Encryption (plaintext -> ciphertext)
    public String encrypt(String text, int key) {
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

    // Decryption (ciphertext -> plaintext)
    public String decrypt(String text, int key) {
        // Key turned into negative number to decrypt
        int decryptKey = key * -1;

        // Only difference between encrypt/decrypt is the direction of shift
        return encrypt(text, decryptKey);
    }
}
