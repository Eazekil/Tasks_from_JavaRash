package task2013;



import java.io.*;
import java.util.List;

/* 
Externalizable Person
*/

public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;
        public Person(){}

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(mother);
            out.writeObject(father);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = (String) in.readObject();
            lastName = (String)in.readObject();
            age = in.readInt();
            mother = (Person) in.readObject();
            father = (Person) in.readObject();
            children = (List) in.readObject();
        }
    }

    public static void main(String[] args) {
        Person person = new Person("Pontius","Pilatus",2021+12);

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("person.dat"))){
            outputStream.writeObject(person);
        }catch (Exception e){
            System.out.println(e);}
        Person newPerson=new Person();
        try(ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("person.dat"))){
            newPerson=(Person) inputStream.readObject();
        }catch (Exception e){
            System.out.println(e);}
        System.out.println(newPerson.firstName);
        System.out.println(newPerson.lastName);
        System.out.println(newPerson.age);


    }
}
