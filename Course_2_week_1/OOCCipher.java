package Course_2_week_1;

import edu.duke.*;

 class TestCaesarCipherTwo {

    public String halfOfString(String message, int start) {
        String answer = "";
        for (int i = start; i < message.length(); i += 2) {
            answer = answer + message.charAt(i);
        }
        return answer;

    }

    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] values) {
        int index = 0;
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                index = i;
            }
        }
        return index;
    }


    public String breakCaesarCipher(String input) {
        String messageF = halfOfString(input, 0);
        String messageS = halfOfString(input, 1);

        int[] freqs1 = countLetters(messageF);
        int maxDex1 = maxIndex(freqs1);
        int dkey1 = maxDex1 - 4;
        if (maxDex1 < 4) {
            dkey1 = 26 - (4 - maxDex1);
        }

        int[] freqs2 = countLetters(messageS);
        int maxDex2 = maxIndex(freqs2);
        int dkey2 = maxDex2 - 4;
        if (maxDex2 < 4) {
            dkey1 = 26 - (4 - maxDex2);
        }

        CaesarCipherTwo cc = new CaesarCipherTwo(dkey1, dkey2);
        return cc.decrypt(input);
    }

    public void simpleTests() {
        FileResource resource = new FileResource();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        String message = resource.asString();
        String encryptMessage = cc.encrypt(message);
        System.out.println(encryptMessage + "\n" + cc.decrypt(encryptMessage));
        String decryptMessage = this.breakCaesarCipher(encryptMessage);
        System.out.println(decryptMessage);
    }
}
 class CaesarCipherTwo {

    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;


    public CaesarCipherTwo(int key1, int key2) {
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.shiftedAlphabet1 = this.alphabet.substring(key1) +
                this.alphabet.substring(0, key1);
        this.shiftedAlphabet2 = this.alphabet.substring(key2) +
                this.alphabet.substring(0, key2);
        this.mainKey1 = key1;
        this.mainKey2 = key2;

    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (i % 2 == 0) {
                if (this.alphabet.indexOf(currChar) != -1) {
                    int idx = this.alphabet.indexOf(currChar);
                    char newChar = this.shiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            } else {
                if (this.alphabet.indexOf(currChar) != -1) {
                    int idx = this.alphabet.indexOf(currChar);
                    char newChar = this.shiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(input);
    }
}
public class OOCCipher {
    public static void main(String[] args) {
        TestCaesarCipherTwo tc = new TestCaesarCipherTwo();
        tc.simpleTests();
    }

}
