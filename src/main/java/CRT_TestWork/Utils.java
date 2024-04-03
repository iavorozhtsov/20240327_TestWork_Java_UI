package CRT_TestWork;

import java.util.Random;

public class Utils {

    private static Integer MinLen = 3;
    private static Integer MaxLen = 15;
    private static String AvailableSymbols =
                    "QWERTYUIOPASDFGHJKLZXCVBNM" +
                    "qwertyuiopasdfghjklzxcvbnm" +
                    "1234567890" +
                    "_.";
    private static String PasswordChars = "!@#$%^&*()_=,./|\\';:";
    private static Random random = new Random();

    public static String getValidEmail(){

        return generateString(AvailableSymbols, MaxLen) + "@" + generateString(AvailableSymbols, MaxLen).replace('_', 'a');
    }

    public static String getInvalidEmail(String type){
        String s = generateString(AvailableSymbols, MaxLen);
        switch (type){
            case "@*":
                return "@" + s;

            case "*@":
                return s + "@";

            case "@@":
                return s + "@@" + s;

            case "*":
                return s;

            default:
                break;
        }
        return null;
    }

    public static String getUserName(){
        return generateString(AvailableSymbols.substring(0, 52), MaxLen);
    }

    public static String getPassword(){
        return generateString(AvailableSymbols + PasswordChars, MaxLen);
    }

    private static String generateString(String symbols,Integer lenght){
        StringBuilder newString = new StringBuilder("");

        for (int i = 0; i <= MinLen + random.nextInt(MaxLen - MinLen); i++) {
            newString.append(symbols.charAt(random.nextInt(symbols.length())));
        }
        if ((newString.charAt(0) == '.') || (newString.charAt(newString.length()-1) == '.'))
            newString.toString().replace('.', 'c');

        return newString.toString();
    }
}
