package task2001;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
*/

public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File tempFile = File.createTempFile("tempFile", null,new File("d:/example_Java/"));
            OutputStream outputStream = new FileOutputStream(tempFile);
            InputStream inputStream = new FileInputStream(tempFile);

            Human ivanov = new Human("Ivanov", new Asset("home", 999999.99), new Asset("car", 2999.99));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            System.out.println(somePerson.equals(ivanov));
            System.out.println(somePerson.hashCode()==ivanov.hashCode());


        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<Asset>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            String isNamePresent = name != null ? "yes" : "no";
            outputStream.write(isNamePresent.getBytes());
            outputStream.write(32);
            //outputStream.flush();
            if (name != null)outputStream.write(name.getBytes());
            outputStream.write(32);
            String isAssetPresent = assets != null ? "yes" : "no";
            outputStream.write(isAssetPresent.getBytes());
            outputStream.write(32);
            for(Asset ass:assets){
                if(ass !=null){
                    outputStream.write(ass.getName().getBytes());
                    outputStream.write(32);
                    outputStream.write(String.valueOf(ass.getPrice()).getBytes());
                    outputStream.write(32);
                }
            }
            outputStream.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer, 0, inputStream.available());
            String[] sList=new String(buffer).trim().split(" ");
            if(sList[0]!=null)name=sList[1];
            if(sList[2]!=null){
                for(int i=3;i<sList.length;i+=2){
                    if(sList[i]!=null)assets.add(new Asset(sList[i],Double.parseDouble(sList[i+1])));
                }
            }

        }
    }
}
