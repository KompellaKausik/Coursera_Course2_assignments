package Course_2_week_1;

import edu.duke.*;

import java.util.*;

class CodonCount {

    private HashMap<String, Integer> codonsMap;

    CodonCount() {
        this.codonsMap = new HashMap<>();
    }

    void buildCodonMap(int start, String dna) {
        this.codonsMap.clear();
        for (int i = start; i < dna.length()-3; i+=3) {
            String codon = dna.substring(i,i+3);
            if (!this.codonsMap.containsKey(codon)){
                this.codonsMap.put(codon,1);
            }
            else {
                this.codonsMap.put(codon,this.codonsMap.get(codon)+1);
            }
        }
    }

    String getMostCommonCodon() {
        int count = 0;
        String index = "";
        for (String i : this.codonsMap.keySet()) {
            int c = this.codonsMap.get(i);
            if (c > count) {
                count = c;
                index = i;
            }
        }
        return index;
    }

     void printCodonCounts ( int start, int end){
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        for (String key : codonsMap.keySet()) {
            int count = codonsMap.get(key);
            if (count >= start && count <= end) {
                System.out.println(key + " " + this.codonsMap.get(key));
            }
        }
        System.out.print("\n\n\n");
    }

     void test (){
        FileResource fr = new FileResource();
        String dna = fr.asString();

        for (int i = 0; i < 3; i++) {
            this.buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " results in "+ this.codonsMap.size()+" unique codons" );

            String mostCommonCodon = this.getMostCommonCodon();
            System.out.println("and most common codon is " + mostCommonCodon + " with count " + this.codonsMap.get(mostCommonCodon));
            this.printCodonCounts(1,5);
        }
    }

    public static void main(String[] args){
        CodonCount cc =new CodonCount();
        cc.test();
    }

}