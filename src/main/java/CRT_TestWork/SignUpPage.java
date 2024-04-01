package CRT_TestWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends AbstractPage {

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "name")
    private WebElement name;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(className = "button")
    private WebElement signUpBtn;

    @FindBy(className = "notification")
    private WebElement notification;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public boolean emailIsAvailable(){
        return email.isEnabled();
    }

    public void emailSendkeys (String s){
        email.click();
        email.sendKeys(s);
    }

    public boolean nameIsAvailable(){
        return name.isEnabled();
    }

    public void nameSendkeys(String s){
        name.click();
        name.sendKeys(s);
    }

    public boolean passwordIsAvailable(){
        return password.isEnabled();
    }

    public void passwordSendkeys(String s){
        password.click();
        password.sendKeys(s);
    }

    public boolean signUpIsAvailable(){
        return signUpBtn.isEnabled();
    }
    public void signUpBtnClick(){
        signUpBtn.click();
    }

    public boolean notificationIsVisible(){
        return notification.isDisplayed();
    }

    public String notificationGetText(){
        return notification.getText();
    }
}
