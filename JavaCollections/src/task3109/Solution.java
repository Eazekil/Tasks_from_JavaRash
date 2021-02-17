package task3109;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

/* 
Читаем конфиги
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties properties=new Properties();
        try {
            String type=fileName.split("\\.")[fileName.split("\\.").length-1];
            if(type.equals("xml")){
                FileInputStream fis = new FileInputStream(fileName);
                properties.loadFromXML(fis);
            }else{
                properties.load(new FileReader(fileName));
            }

        }catch (Exception e){
            //System.out.println(e);
        }
        return properties;
    }
}
