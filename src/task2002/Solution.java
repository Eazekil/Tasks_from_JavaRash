package task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution {
    public static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("tempFile", null,new File("d:/example_Java/"));
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user=new User();
            user.setFirstName("Marcus");
            user.setLastName("Aurelius Antoninus");
            user.setBirthDate(format.parse("07.03.161"));
            user.setMale(true);
            user.setCountry(User.Country.OTHER);
            javaRush.users.add(user);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method"+e);
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<User>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter writer=new PrintWriter(outputStream);
            for(User user:users){
                writer.write(user.getFirstName()+'\n');
                writer.write(user.getLastName()+'\n');
                //writer.write(user.getBirthDate().toString()+'\n');
                writer.write(Long.toString(user.getBirthDate().getTime())+'\n');
                writer.write(String.valueOf(user.isMale())+'\n');
                writer.write(user.getCountry().getDisplayName()+'\n');
                writer.flush();
            }
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.ready()){
                User user=new User();
                user.setFirstName(reader.readLine());
                user.setLastName(reader.readLine());
                //user.setBirthDate(new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH).parse(reader.readLine()));
                user.setBirthDate(new Date(Long.parseLong(reader.readLine())));
                user.setMale("true".equals(reader.readLine()));
                String loadCountry=reader.readLine();
                for(User.Country country: User.Country.values()){
                    if(loadCountry.equals(country.getDisplayName()))user.setCountry(country);
                }
                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
