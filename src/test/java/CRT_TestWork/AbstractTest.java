package CRT_TestWork;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;


public class AbstractTest
{
    private static WebDriver driver;

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
}
