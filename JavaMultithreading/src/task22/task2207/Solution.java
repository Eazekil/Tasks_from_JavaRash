package task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
//d:/example_Java/forString.txt

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {

        ArrayList<String> allWords = new ArrayList<>();
        ArrayList<String> addedWord = new ArrayList<>();

        try (BufferedReader readerS = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader = new BufferedReader(new FileReader(readerS.readLine()))) {

            while (reader.ready()) {
                for (String word : reader.readLine().split(" ")) {
                    allWords.add(word);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String word : allWords) {
            String reverseWord = new StringBuilder(word).reverse().toString();

            if (allWords.contains(reverseWord)) {
                Pair res =new Pair();
                res.first = word;
                res.second = reverseWord;


                if(!addedWord.contains(word)){
                    /*System.out.printf("word : %s  contains %b",word,addedWord.contains(word));
                    System.out.println();*/
                    result.add(res);
                    addedWord.add(word);
                    addedWord.add(reverseWord);
                }

            }
        }
        //System.out.println(allWords);

        result.forEach(System.out::println);
        //System.out.println(result);

    }

    public static class Pair {
        String first;
        String second;



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
