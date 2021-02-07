package task2007;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* 
Как сериализовать JavaRush?
*/

public class Solution {
    public static class JavaRush implements Serializable {
        public List<User> users = new ArrayList<>();
    }

    public static void main(String[] args) {
        JavaRush jr = new JavaRush();
        jr.users.add(new User());
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("jr.dat"))){
            outputStream.writeObject(jr);
        }catch (Exception e){
            System.out.println(e);}
    }
}
