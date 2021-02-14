package task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
https://i.pinimg.com/originals/f3/03/92/f30392846ca52de149142be2dab23026.jpg
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("d:/example_Java/new folder/and other folder"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        String ss=downloadDirectory+"/"+ urlString.substring(urlString.lastIndexOf('/'));
        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();
        Path tempFile = Files.createTempFile("temp-","");
        Files.copy(inputStream, tempFile,StandardCopyOption.REPLACE_EXISTING);
        Files.move(tempFile,Paths.get(ss));
        return Paths.get(ss);
    }
}
