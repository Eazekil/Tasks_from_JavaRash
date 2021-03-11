package task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name","Ivanov");
        map.put("country","Ukraine");
        map.put("city","Kiev");
        map.put("age",null);

        System.out.println(map);
        System.out.println(getQuery(map));

    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder resultString = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if(entry.getValue() != null){
                if (first) {
                    first = false;
                    resultString.append(String.format("%s = '%s'", entry.getKey(), entry.getValue()));
                } else {
                    resultString.append(String.format(" and %s = '%s'", entry.getKey(), entry.getValue()));
                }
            }
        }

        return resultString.toString();
    }
}
