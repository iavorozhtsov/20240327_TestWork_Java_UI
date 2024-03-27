package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    static WebDriver driver = new ChromeDriver();
    static String BaseUrl = "http://localhost:5000";

    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver.get(BaseUrl);
    }
}
