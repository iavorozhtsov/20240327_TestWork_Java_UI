package CRT_TestWork;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;


public class AbstractTest
{
    private static WebDriver driver;

    private String BaseUrl = "http://localhost:5000/";

    private String LoginUrl = BaseUrl + "login";
    private String SignUpUrl = BaseUrl + "signup";
    private String ProfileUrl = BaseUrl + "profile";

    private  String mainPageTitle = "Flask Auth Example";
    private  String emailExistMessage = "Email address already exists. Go to login page.";
    private  String wrongCredentialsMessage = "Please check your login details and try again.";
    private  String welcomeMessage = "Welcome, ";
    private  String logoutButtonText = "Logout";
    private  String profileButtonText = "Profile";


    @BeforeAll
    public static void init() {

        driver = getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterAll
    public static void tearDown(){
        if (driver == null) driver.quit();
    }

    public static WebDriver getWebDriver(){
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");

            driver = new ChromeDriver(options);
        }

        return driver;
    }

    public String getBaseUrl() {
        return BaseUrl;
    }

    public String getLoginUrl() {
        return LoginUrl;
    }

    public String getSignUpUrl() {
        return SignUpUrl;
    }

    public String getProfileUrl() {
        return ProfileUrl;
    }

    public String getMainPageTitle() {
        return mainPageTitle;
    }

    public String getEmailExistMessage() {
        return emailExistMessage;
    }

    public String getWrongCredentialsMessage() {
        return wrongCredentialsMessage;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public String getLogoutButtonText() {
        return logoutButtonText;
    }

    public String getProfileButtonText() {
        return profileButtonText;
    }
}
