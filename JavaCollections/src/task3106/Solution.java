package task3106;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
d:/example_Java/ResultFile.pdf d:/example_Java/11.z04 d:/example_Java/11.z02 d:/example_Java/11.z01 d:/example_Java/11.z03
*/

public class Solution {
    public static void main(String[] args) throws Exception{
        TreeMap tm = new TreeMap();
        for(int i=1;i<args.length;i++)tm.put(args[i],0);
        Set set = tm.entrySet();
        Iterator i = set.iterator();
        //File tempFile=new File("d:/example_Java/tempFile.zip");
        List<InputStream> inputList=new ArrayList<>();
        //File tempFile= File.createTempFile("myTemp", ".zip");
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            InputStream ff = new FileInputStream((String)me.getKey());
            inputList.add(ff);

            /*FileOutputStream fout = new FileOutputStream(tempFile);
            for (int c = ff.read(); c != -1; c = ff.read()) {
                fout.write(c);
            }
            fout.close();*/

            //ff.close();
            //System.out.println("11111111");
        }
        ZipInputStream zip = new ZipInputStream(
                new SequenceInputStream(Collections.enumeration(inputList)));
        FileOutputStream fout = new FileOutputStream(args[0]);
        for (int c = zip.read(); c != -1; c = zip.read()) {
            fout.write(c);
        }
        zip.close();
        fout.close();
        //for(InputStream f:inputList)f.close();

        /*try(ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile)))
        {

            ZipEntry entry;
            System.out.println("22222222222");
            String name;
            long size;
            while((entry=zin.getNextEntry())!=null){

                name = entry.getName(); // получим название файла
                size=entry.getSize();  // получим его размер в байтах
                System.out.printf("File name: %s \t File size: %d \n", name, size);

                // распаковка
                FileOutputStream fout = new FileOutputStream(args[0]);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }*/
    }
}
