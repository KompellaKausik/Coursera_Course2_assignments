package Course_2_week_1;

import edu.duke.*;

class Cipher {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;


    public Cipher(int key) {
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.shiftedAlphabet = this.alphabet.substring(key)+
                this.alphabet.substring(0,key);
        this.mainKey = key;
    }
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if(this.alphabet.indexOf(currChar) != -1){
                int idx = this.alphabet.indexOf(currChar);
                char newChar = this.shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input) {
        Cipher cc = new Cipher(26 - mainKey);
        return cc.encrypt(input);
    }
}
class TestCaesarCipher {

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
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        Cipher cc = new Cipher(dkey);
        return cc.decrypt(input);
    }

    public void simpleTests() {
        FileResource resource = new FileResource();
        Cipher cc = new Cipher(18);
        String message = resource.asString();
        String encryptMessage = cc.encrypt(message);
        System.out.println( "encrpted message "+ "\n" + encryptMessage);
        String decryptMessage = this.breakCaesarCipher(encryptMessage);
        System.out.println("decrpted message "+ "\n" +decryptMessage);
    }
}
public class OneKey {
    public static void main(String[] args) {
        TestCaesarCipher tc = new TestCaesarCipher();
        tc.simpleTests();
    }
}
