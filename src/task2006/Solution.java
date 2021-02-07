package task2006;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Как сериализовать?
*/

public class Solution {
    public static class Human implements Serializable {
        public String name;
        public List<String> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, String... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }
    }

    public static void main(String[] args){
        Human cat = new Human();
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cat.dat"))){
            outputStream.writeObject(cat);
        }catch (Exception e){
            System.out.println(e);}
    }
}
