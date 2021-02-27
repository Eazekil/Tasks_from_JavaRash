package TaskYandex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class SolutionForTest {
    public static TreeMap<Integer, String> treeMap = new TreeMap();
    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while (true){
                String s = reader.readLine();
                if(s != null && !s.isEmpty()){
                    int number = Integer.parseInt(s.replaceAll("\\D+",""));
                    String string = s.replaceAll("\\d+","");
                    treeMap.put(number,string);
                }else{
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        treeMap.forEach((k, v) ->
                System.out.println(v));
    }
}
