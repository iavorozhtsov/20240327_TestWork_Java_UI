package CRT_TestWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"navbarMenuHeroA\"]/div/a[1]")
    private WebElement menuHomeBtn;

    @FindBy(xpath = "//*[@id=\"navbarMenuHeroA\"]/div/a[2]")
    private WebElement menuProfileBtn;

    @FindBy(xpath = "//*[@id=\"navbarMenuHeroA\"]/div/a[3]")
    private WebElement menuLogoutBtn;

    @FindBy(className = "title")
    private WebElement title;


    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean menuProfileBtnIsAvailable(){
        return menuProfileBtn.isEnabled();
    }

    public String menuProfileBtnGetText(){
        return menuProfileBtn.getText();
    }

    public boolean menuLogoutBtnIsAvailable(){
        return menuLogoutBtn.isEnabled();
    }

    public String menuLogoutBtnGetText(){
        return menuLogoutBtn.getText();
    }

    public void menuLogoutBtnClick(){
        menuLogoutBtn.click();
    }

    public boolean titleIsAvailable(){
        return title.isDisplayed();
    }

    public String titleGetMessage(){
        return title.getText();
    }


}
