package CRT_TestWork.Utils;

import CRT_TestWork.SignUpPage;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class Utils {

    private static Integer MinLen = 3;
    private static Integer MaxLen = 15;
    private static String AvailableSymbols =
                    "QWERTYUIOPASDFGHJKLZXCVBNM" +
                    "qwertyuiopasdfghjklzxcvbnm" +
                    "1234567890";
    private static String PasswordChars = "!@#$%^&*()_=,./|\\';:";
    private static Random random = new Random();

    private static String baseUrl = "http://localhost:5000/";

    private static String loginUrl = baseUrl + "login";
    private static String signUpUrl = baseUrl + "signup";
    private static String profileUrl = baseUrl + "profile";

    private static String mainPageTitle = "Flask Auth Example";
    private static String emailExistMessage = "Email address already exists. Go to login page.";
    private static String wrongCredentialsMessage = "Please check your login details and try again.";
    private static String nonAuthorized = "Please log in to access this page.";
    private static String welcomeMessage = "Welcome, ";
    private static String logoutButtonText = "Logout";
    private static String profileButtonText = "Profile";

    private static String userEmail = "";
    private static String userPassword = "";
    private static String userName = "";

    public static String getValidEmail(){

        return generateString(AvailableSymbols, MaxLen) + "@" + generateString(AvailableSymbols, MaxLen) + "." + generateString(AvailableSymbols,MinLen + 2);
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

    public static String generateUserName(){
        return generateString(AvailableSymbols.substring(0, 52), MaxLen);
    }

    public static String getPassword(){
        return generateString(AvailableSymbols + PasswordChars, MaxLen);
    }

    private static String generateString(String symbols,Integer length){
        StringBuilder newString = new StringBuilder("");

        for (int i = 0; i <= MinLen + random.nextInt(length - MinLen); i++) {
            newString.append(symbols.charAt(random.nextInt(symbols.length())));
        }

        return newString.toString();
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getLoginUrl() {
        return loginUrl;
    }

    public static String getSignUpUrl() {
        return signUpUrl;
    }

    public static String getProfileUrl() {
        return profileUrl;
    }

    public static String getMainPageTitle() {
        return mainPageTitle;
    }

    public static String getEmailExistMessage() {
        return emailExistMessage;
    }

    public static String getWrongCredentialsMessage() {
        return wrongCredentialsMessage;
    }

    public static String getNonAuthorized() {
        return nonAuthorized;
    }

    public static String getWelcomeMessage() {
        return welcomeMessage;
    }

    public static String getLogoutButtonText() {
        return logoutButtonText;
    }

    public static String getProfileButtonText() {
        return profileButtonText;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static String getUserName() {
        return userName;
    }

    public static void clearUserCredentials(){
        userEmail = "";
        userName = "";
        userPassword = "";
    }

    public static void getRegisteredUserCredentials(WebDriver driver) throws SignUpException {
        if (userEmail.isEmpty()){
            userEmail = getValidEmail();
            userName = generateUserName();
            userPassword = getPassword();

            driver.get(getSignUpUrl());
            SignUpPage sp = new SignUpPage(driver);

            SignUpPage.registerMe(userEmail, userName, userPassword);
            if (!getLoginUrl().equals(driver.getCurrentUrl())) throw new SignUpException("User was not registered with email: '" +
                    userEmail + "', and name: '" + userName + "', and password: '" + userPassword +"'");
        }
    }
}
