package com.gergoh;

import com.gergoh.encrypters.classical.RailFenceCipher;

// Rail Fence Cipher testing
public class Main {
    public static void main(String[] args) {
        RailFenceCipher railCipher = new RailFenceCipher();

        railCipher.encrypt();

        railCipher.outputCipherText();


    }
}