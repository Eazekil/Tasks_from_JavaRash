package task22.task2202;

/* 
Найти подстроку
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        try{

            String[] ss = string.split(" ");
            return String.format("%s %s %s %s", ss[1], ss[2], ss[3], ss[4]);

        }catch (Exception e){
            throw new TooShortStringException(e);
        }

    }

    public static class TooShortStringException extends RuntimeException{
        public TooShortStringException(Throwable cause) {
            super(cause);
        }
    }
}
