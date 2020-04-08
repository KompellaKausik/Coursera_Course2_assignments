package Course_2_week_1;

import edu.duke.*;

public class WordLengths
{
    public void countWordLengths(FileResource resource, int countOfLenghts[]){
        for(String word : resource.words()){
            int lengthOfWord = word.length();
            if (!Character.isLetter(word.charAt(0))){
                lengthOfWord--;
            }
            if (!Character.isLetter(word.charAt(word.length()-1)) && word.length()!=1){
                lengthOfWord--;
            }
            countOfLenghts[lengthOfWord]++;
        }
        int index = indexOfMax(countOfLenghts);

        System.out.println("The most common word length is " + index);
    }

    public int indexOfMax(int[] values){
        int index = 0;
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i]>max){
                max = values[i];
                index = i;
            }

        }
        return index;
    }

    public void testCountWordLengths(){
        int[] counts = new int[31];
        FileResource resource = new FileResource();
        countWordLengths(resource,counts);
    }

    public static void main(String[] args){
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    }
}