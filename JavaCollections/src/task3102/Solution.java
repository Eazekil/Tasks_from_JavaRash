package task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/* 
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> result=new ArrayList<>();
        ArrayDeque<String> files = new ArrayDeque<String>();
        String[] folder = new File(root).list();
        for(String s:folder){
            files.addLast(s);
        }
        while (files.peek()!=null){
            if(new File(root+"/"+files.peek()).isDirectory()){
                String pp=files.poll();
                String[] ff = new File(root+"/"+pp).list();
                for(String ss:ff){
                    files.addLast(pp+"/"+ss);
                }
            }else{
                result.add(root+"/"+files.poll());
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        for(String s:getFileTree("d:/example_Java")){
            System.out.println(s);
        }

    }
}
