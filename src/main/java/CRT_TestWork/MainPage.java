package CRT_TestWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"navbarMenuHeroA\"]/div/a[1]")
    private WebElement menuHomeBtn;

    @FindBy(xpath = "//*[@id=\"navbarMenuHeroA\"]/div/a[2]")
    private WebElement menuLoginBtn;

    @FindBy(xpath = "//*[@id=\"navbarMenuHeroA\"]/div/a[3]")
    private WebElement menuSignUpBtn;

    @FindBy(className = "title")
    private WebElement title;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void menuHomeClick(){
        menuHomeBtn.click();;
    }

    public boolean menuHomeAvailable(){
        return menuHomeBtn.isEnabled();
    }

    public void menuLoginClick(){
        menuLoginBtn.click();;
    }

    public boolean menuLoginAvailable(){
        return menuLoginBtn.isEnabled();
    }

    public void menuSignUpClick(){
        menuSignUpBtn.click();;
    }

    public boolean menuSignUpAvailable(){
        return menuSignUpBtn.isEnabled();
    }
}
