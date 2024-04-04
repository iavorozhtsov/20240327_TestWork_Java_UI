package CRT_TestWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends SignUpPage{

    @FindBy(name = "email")
    private static WebElement email;

    @FindBy(name = "password")
    private static WebElement password;

    @FindBy(className = "button")
    private static WebElement loginBtn;

    @FindBy(className = "notification")
    private static WebElement notification;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean loginIsAvailable(){
        return loginBtn.isEnabled();
    }

    public void loginClick(){
        loginBtn.click();
    }

    public static String getNotification() {
        return notification.getText();
    }

    public static void logMeIn(String _email, String _password) {
        email.sendKeys(_email);
        password.sendKeys(_password);
        loginBtn.click();
    }
}
