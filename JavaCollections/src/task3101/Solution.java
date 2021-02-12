package task3101;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* 
Проход по дереву файлов
d:/example_Java d:/example_Java/resultFileAbsolutePath.txt
d:/example_Java/ff1.txt
*/

public class Solution {
    static Map map=new TreeMap();
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.size();
        a.get(0);
        FileUtils.renameFile(new File(args[1]),new File(args[0]+"/"+"allFilesContent.txt"));
        directory(new File(args[0]));

        try(FileWriter writer = new FileWriter(new File(args[0]+"/"+"allFilesContent.txt"))){
            for (Object key : map.keySet()) {
                FileReader reader=new FileReader(new File(String.valueOf(map.get(key))));
                //System.out.println(key+"  "+map.get(key));
                while (reader.ready()){
                    writer.write(reader.read());
                }
                writer.write("\n");
                reader.close();
            }
        }catch (Exception e){System.out.println(e);}

    }

    public static void directory(File file){
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++){
            if(files[i].isDirectory())directory(files[i]);
            else{
                if(files[i].length()<=50)map.put(files[i].getName(),files[i]);
            }
        }
    }
}
