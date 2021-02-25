package task2107;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* 
Глубокое клонирование карты
*/

public class Solution implements Cloneable{

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone =(Solution) solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);
            /*System.out.println(solution.equals(clone));
            System.out.println(solution.hashCode());
            System.out.println(clone.hashCode());*/

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;
        Solution solution = (Solution) o;
        return users.equals(solution.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Solution clone=(Solution) super.clone();
        clone.users = new LinkedHashMap();
        Iterator i = users.entrySet().iterator();
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            clone.users.put((String) me.getKey(),(User)((User) me.getValue()).clone());
        }
        return clone;

    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable{
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            User clone = (User) super.clone();
            clone.age = age;
            clone.name = name;
            return clone;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            return age == user.age &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }
    }
}
