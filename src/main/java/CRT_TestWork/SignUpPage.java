package CRT_TestWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends AbstractPage {

    @FindBy(name = "email")
    private static WebElement email;

    @FindBy(name = "name")
    private static WebElement name;

    @FindBy(name = "password")
    private static WebElement password;

    @FindBy(className = "button")
    private static WebElement signUpBtn;

    @FindBy(className = "notification")
    private WebElement notification;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public boolean emailIsAvailable(){
        return email.isEnabled();
    }

    public boolean nameIsAvailable(){
        return name.isEnabled();
    }

    public boolean passwordIsAvailable(){
        return password.isEnabled();
    }

    public boolean signUpIsAvailable(){
        return signUpBtn.isEnabled();
    }

    public boolean notificationIsVisible(){
        return notification.isDisplayed();
    }

    public String notificationGetText(){
        return notification.getText();
    }

    public static void registerMe(String _email, String _name, String _password){
        email.click();
        email.sendKeys(_email);
        name.click();
        name.sendKeys(_name);
        password.click();
        password.sendKeys(_password);
        signUpBtn.click();
    }
}
