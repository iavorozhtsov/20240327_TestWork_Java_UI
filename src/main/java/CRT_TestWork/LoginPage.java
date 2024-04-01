package CRT_TestWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends SignUpPage{

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(className = "button")
    private WebElement loginBtn;

    @FindBy(className = "notification")
    private WebElement notification;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean loginIsAvailable(){
        return loginBtn.isEnabled();
    }

    public void loginClick(){
        loginBtn.click();
    }
}
