package Course_2_week_1;

import edu.duke.*;

class CaesarCipher {

    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);		// Convert String input to StringBuilder
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";			// The alphabet

        for(int i = 0, n = input.length(); i < n; i++) {
            char currChar = input.charAt(i);					// Current char from input
            int currCharIndex = alphabet.indexOf(Character.toUpperCase(currChar));		// Index of current char from alphabet

            if(currCharIndex != -1) { 	// If char isn't a space or a punctuation
                if(Character.isUpperCase(currChar)) {	// If current char is uppercase
                    String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";				// The alphabet Uppercase
                    String alphabet_shiftedUpper = alphabetUpper.substring(key);		// The shifted portion of the alphabet by key
                    alphabet_shiftedUpper += alphabetUpper.substring(0, key);			// The shifted alphabet by key

                    char charShifted = alphabet_shiftedUpper.charAt(currCharIndex); 	// Encrypted char
                    encrypted.setCharAt(i, charShifted);	// Insert encrypted char at the i'th location of encrypted string
                }

                if(Character.isLowerCase(currChar)) {	// If current char is lowercase
                    String alphabetLower = "abcdefghijklmnopqrstuvwxyz";				// The alphabet Lowercase
                    String alphabet_shiftedLower = alphabetLower.substring(key);		// The shifted portion of the alphabet by key
                    alphabet_shiftedLower += alphabetLower.substring(0, key);			// The shifted alphabet by key

                    char charShifted = alphabet_shiftedLower.charAt(currCharIndex); 	// Encrypted char
                    encrypted.setCharAt(i, charShifted);	// Insert encrypted char at the i'th location of encrypted string
                }
            }
        }

        return encrypted.toString();
    }

    /*
     * This method returns a character that is encrypted by a key
     */
    public char encryptChar(char input, int key) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        if(Character.isUpperCase(input)) {
            String shifted = alphabet.toUpperCase().substring(key);
            shifted += alphabet.toUpperCase().substring(0, key);

            int currIndex = alphabet.toUpperCase().indexOf(input);
            char encrypted = shifted.charAt(currIndex);
            return encrypted;
        }

        if(Character.isLowerCase(input)) {
            String shifted = alphabet.toLowerCase().substring(key);
            shifted += alphabet.toLowerCase().substring(0, key);

            int currIndex = alphabet.toLowerCase().indexOf(input);
            char encrypted = shifted.charAt(currIndex);
            return encrypted;
        }

        return '\0';
    }

    /*
     * This method returns a String that has been encrypted using the following algorithm.
     * Parameter key1 is used to encrypt every other character with the Caesar Cipher
     * algorithm, starting with the first character, and key2 is used to encrypt every
     * other character, starting with the second character
     */
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int n = input.length();
        for(int i = 0; i < n; i++) {
            char currChar = input.charAt(i);
            int currCharIndex = alphabet.indexOf(Character.toUpperCase(currChar));
            if((i + 1) % 2 == 0 && currCharIndex!= -1) {	// If the character is even
                char encrChar = encryptChar(currChar, key2);
                encrypted.setCharAt(i, encrChar);
            }

            if((i + 1) % 2 != 0 && currCharIndex!= -1) {	// If the character is odd
                char encrChar = encryptChar(currChar, key1);
                encrypted.setCharAt(i, encrChar);
            }
        }
        return encrypted.toString();
    }

    public void testEncrypted() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, 15);
        System.out.println(encrypted);
    }

    public void testEncryptChar() {
        char x = encryptChar('D', 1);
        System.out.println(x);
    }

    public void testTwoKey() {
        String encrypted = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println(encrypted);
    }
    public static void main(String[] args){
        CaesarCipher c =new CaesarCipher();
        c.testEncryptChar();;
        c.testEncrypted();
        c.testTwoKey();
    }

}
