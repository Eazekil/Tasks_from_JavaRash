package task22.task2210;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 
StringTokenizer
*/

public class Solution {
    public static void main(String[] args) {
        //System.out.println(Arrays.toString(getTokens("level22.lesson13.task01", ".")));
        //System.out.println(Arrays.toString("level22.lesson13.task01".split("\\.")));
    }

    public static String[] getTokens(String query, String delimiter) {
        StringTokenizer tok = new StringTokenizer(query, delimiter);
        List<String> arr = new ArrayList<>();
        while (tok.hasMoreElements()){
            arr.add(tok.nextToken());
        }

        return arr.toArray(new String[0]);
    }
}
