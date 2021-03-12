package task22.task2212;

/* 
Проверка номера телефона

*/


public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if(telNumber != null && !telNumber.isEmpty()){
            int count = telNumber.replaceAll("[^\\d]", "").length();
            String start = String.valueOf(telNumber.charAt(0));

            if (start.equals("+") && count == 12) {
                return checkDash(telNumber) && checkBracket(telNumber) && lastCheck(telNumber);
            } else if ((start.matches("\\d") || start.equals("(")) && count == 10) {
                return checkDash(telNumber) && checkBracket(telNumber) && lastCheck(telNumber);
            } else {
                return false;
            }
        }else{
            return false;
        }



    }

    public static boolean checkDash(String telNumber) {
        int count = telNumber.replaceAll("[^\\-]", "").length();

        if (telNumber.contains("--")) {
            return false;
        }

        return count < 3;
    }

    //               +38(050)1234567
    public static boolean checkBracket(String telNumber) {
        int start = telNumber.indexOf("(");
        int end = telNumber.indexOf(")");
        int dash = telNumber.indexOf("-");

        if (start < 0 && end < 0) {
            return true;
        }

        if (dash < 0) {
            return end - start == 4;
        } else {
            return end - start == 4 && end < dash;
        }

    }

    public static boolean lastCheck(String telNumber) {
        int rep = telNumber.replaceAll("[a-zA-z]", "").length();
        int original = telNumber.length();
        String end = String.valueOf(telNumber.charAt(original - 1));

        if (rep == original) {
            return end.matches("\\d");
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));



    }
}
