package task22.task2211;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/* 
Смена кодировки
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        Charset win1251 = Charset.forName("Windows-1251");
        Charset utf = Charset.forName("UTF-8");

        try(FileOutputStream fos = new FileOutputStream(args[1])){

            byte[] array = Files.readAllBytes(Paths.get(args[0]));
            String result = new String(array, win1251);
            array = result.getBytes(utf);
            fos.write(array);


        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
