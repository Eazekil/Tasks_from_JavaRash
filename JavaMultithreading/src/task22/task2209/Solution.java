package task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Составить цепочку слов

d:/example_Java/forString.txt
d:/example_Java/forString2.txt

*/

public class Solution {
    public static void main(String[] args) {
        //...
        StringBuilder words = new StringBuilder();

        try (BufferedReader readSys = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader = new BufferedReader(new FileReader(readSys.readLine()))) {

            while (reader.ready()) {
                words.append(reader.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder result = getLine(words.toString().split(" "));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();
        List<String> arr = new ArrayList<>(Arrays.asList(words));

        if (arr.size() > 0) {
            result.append(arr.get(arr.size() - 1));
            arr.remove(arr.size() - 1);

            while (arr.size() > 0) {

                for (int i = 0; i < arr.size(); i++) {
                    String word = arr.get(i);
                    //System.out.println(word);

                    String end = String.valueOf(result.charAt(result.length() - 1));
                    String start = String.valueOf(result.charAt(0));

                    if (word.startsWith(end.toLowerCase()) || word.startsWith(end.toUpperCase())) {
                        result.append(" " + word);
                        arr.remove(i);
                    } else if (word.endsWith(start.toLowerCase()) || word.endsWith(start.toUpperCase())) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(word + " ");
                        sb.append(result);
                        result = sb;
                        arr.remove(i);
                    } else {
                        boolean added = false;
                        String andWord = String.valueOf(word.charAt(word.length() - 1)).toLowerCase();
                        String startWord = String.valueOf(word.charAt(0)).toLowerCase();

                        StringBuilder addString = new StringBuilder();

                        String[] list = result.toString().split(" ");

                        for (int j = 0; j < list.length - 1; j++) {
                            addString.append(list[j]+" ");
                            if (list[j].endsWith(startWord) || list[j].endsWith(startWord.toUpperCase())) {
                                if (list[j+1].startsWith(andWord.toUpperCase()) ||
                                        list[j+1].startsWith(andWord)){
                                    addString.append(word+" ");
                                    added = true;
                                }
                            }
                        }
                        if(added){
                            addString.append(list[list.length - 1]);
                            result = addString;
                            arr.remove(i);
                        }

                    }
                }
            }
        }

        return result;
    }
}
