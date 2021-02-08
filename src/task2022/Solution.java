package task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/

public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    public Solution(){

    }
    private String fName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fName=fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {

        out.defaultWriteObject();
        //out.writeObject(fName);

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        //fName=(String) in.readObject();
        stream=new FileOutputStream(fName,true);

    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws Exception {
        Solution sol = new Solution("d:/example_Java/ff1.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Solution.dat"));
        sol.writeObject("all you need is love");
        outputStream.writeObject(sol);

        ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("Solution.dat"));
        Solution newSol=(Solution) inputStream.readObject();
        newSol.writeObject("Love is all you need");
    }
}
