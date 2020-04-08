package Course_2_week_1;

class CaesarBreaker {

    public int[] countLetters(String message) {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        int[] countofFrequency = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int index = alphabets.indexOf(ch);
            if (index != -1) {
                countofFrequency[index] += 1;
            }
        }
        return countofFrequency;
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

    public String halfOfString(String message, int start) {
        String halfString = "";
        for (int i = start; i < message.length(); i += 2) {
            halfString = halfString + message.charAt(i);
        }
        return halfString;
    }

    public int getKey(String s) {
        int[] frequencies = countLetters(s);
        int maxInd = maxIndex(frequencies);
        int dkey = maxInd - 4;
        if (maxInd < 4) {
            dkey = 26 - (4 - maxInd);
        }
        return dkey;
    }

    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String messageF = halfOfString(encrypted, 0);
        String messageS = halfOfString(encrypted, 1);
        int key1 = getKey(messageF);
        int key2 = getKey(messageS);
        System.out.println(key1 + " " + key2);
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }

    public void testDecrypt() {
        System.out.println(decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
    }
    public static void main(String[] args){
        CaesarBreaker cb =new CaesarBreaker();
        cb.testDecrypt();
    }
}
