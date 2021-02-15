package task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;


/* 
Что внутри папки?
d:/example_Java
d:/example_Java/ff1.txt
*/

public class Solution extends SimpleFileVisitor<Path> {
    private static int folderCount = 0;
    private static int fileCount = 0;
    private static long size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String folder = reader.readLine();
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        final Solution solution = new Solution();
        Files.walkFileTree(Paths.get(folder), options, 20, solution);
        if(folderCount==-1){
            System.out.println(folder+" - не папка");
        }else{
            System.out.println("Всего папок - "+folderCount);
            System.out.println("Всего файлов - "+(fileCount+folderCount));
            System.out.println("Общий размер - "+size);
        }
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if(attrs.isDirectory())folderCount++;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(attrs.isRegularFile()){
            fileCount++;
            size+=attrs.size();
        }
        return FileVisitResult.CONTINUE;
    }
}
