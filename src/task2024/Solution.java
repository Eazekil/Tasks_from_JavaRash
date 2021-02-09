package task2024;



import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/* 
Знакомство с графами
*/

public class Solution implements Serializable {
    int node;
    List<Solution> edges = new LinkedList<>();

    public static void main(String[] args) {
        Solution sol = new Solution();
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Solution.dat"))){
            outputStream.writeObject(sol);
        }catch (Exception e){
            System.out.println(e);}

    }
}
