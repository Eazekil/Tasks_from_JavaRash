package task3107;

import java.nio.file.Files;
import java.nio.file.Paths;

/* 
Null Object Pattern
*/

public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {
        boolean hidden;
        boolean executable;
        boolean directory;
        boolean writable;
        try{
            if(Files.isExecutable(Paths.get(pathToFile))){
                executable=true;
            }else{
                executable=false;
            }
            hidden=Files.isHidden(Paths.get(pathToFile));
            directory=Files.isDirectory(Paths.get(pathToFile));
            writable=Files.isWritable(Paths.get(pathToFile));
            fileData=new ConcreteFileData(hidden,executable,directory,writable);
        }catch (Exception e){
            fileData=new NullFileData(e);
        }


        /*if(new File(pathToFile).exists()){
            if(Files.isExecutable(Paths.get(pathToFile))){
                executable=true;
            }else{
                executable=false;
            }
            hidden=new File(pathToFile).isHidden();
            directory=Files.isDirectory(Paths.get(pathToFile));
            writable=new File(pathToFile).canWrite();
            fileData=new ConcreteFileData(hidden,executable,directory,writable);
        }else{
            fileData=new NullFileData(new Exception());
        }  */
    }


    public FileData getFileData() {
        return fileData;
    }

    public static void main(String[] args) {
        Solution sol=new Solution("d:/example_Java/ff1.txtfdsd");
        System.out.println(sol.getFileData().isHidden());
        System.out.println(sol.getFileData().isExecutable());
        System.out.println(sol.getFileData().isDirectory());
        System.out.println(sol.getFileData().isWritable());
        //System.out.println(sol.getFileData().getException());
    }
}
