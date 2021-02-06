package task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
d:/example_Java/ff1.txt
*/

public class Solution {

    public static Map<String, String> runtimeStorage = new HashMap<>();
    static {
        /*runtimeStorage.put("1","one");
        runtimeStorage.put("2","two");
        runtimeStorage.put("3","three");*/
    }

    public static void save(OutputStream outputStream) throws Exception {
        //напишите тут ваш код
        Properties properties = new Properties();
        for (Map.Entry<String, String> entry: runtimeStorage.entrySet()){
            properties.put(entry.getKey(),entry.getValue());
        }
        properties.store(outputStream,"Some comments");
    }

    public static void load(InputStream inputStream) throws IOException {
        //напишите тут ваш код
        Properties properties = new Properties();
        properties.load(inputStream);
        Iterator itr =  properties.keySet().iterator();
        while(itr.hasNext()){
            String key=(String)itr.next();
            runtimeStorage.put(key,properties.getProperty(key));
        }
    }

    public static void main(String[] args) throws Exception{
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream fos = new FileInputStream(reader.readLine())) {
            load(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream writer=new FileOutputStream("d:/example_Java/ff1.txt");
        //save(writer);

        System.out.println(runtimeStorage);
    }
}
