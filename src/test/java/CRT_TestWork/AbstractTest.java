package CRT_TestWork;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;


public abstract class AbstractTest
{
    private static WebDriver driver;

    final String BaseUrl = "http://localhost:5000/";
    final String LoginUrl = BaseUrl + "login";
    final String SignUpUrl = BaseUrl + "signup";
    final String ProfileUrl = BaseUrl + "profile";

    static final String validEmail = new SimpleDateFormat("yyyyMMdd@HHmm").format(Calendar.getInstance().getTime());
    final String constantUserEmail = "test@test.com";
    final String unregisteredUserEmail = "unregistered@test.com";

    final String mainPageTitle = "Flask Auth Example";
    final String validName = "John Doe";
    final String validPassword = "$tr0ng_@nd_L0ng";
    final String emailExistMessage = "Email address already exists. Go to login page.";
    final String wrongCredentialsMessage = "Please check your login details and try again.";
    final String welcomeMessage = "Welcome, " + validName + "!";
    final String logoutButtonText = "Logout";
    final String profileButtonText = "Profile";


    @BeforeAll
    static void init() {

        driver = getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public static WebDriver getWebDriver(){
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");

            driver = new ChromeDriver(options);
        }

        return driver;
    }

    @AfterAll
    static void tearDown(){
        if (driver == null) driver.quit();
    }
}
