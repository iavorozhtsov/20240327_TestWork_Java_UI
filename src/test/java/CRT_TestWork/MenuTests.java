package CRT_TestWork;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTests extends AbstractTest{

    @BeforeEach
    public void openMainPage(){
        getWebDriver().get(getBaseUrl());
    }

    @Test
    @DisplayName("Т-Main-00 Site is available")
    protected void mainPageOpen(){
        Assertions.assertAll(
                () -> Assertions.assertEquals(getBaseUrl(), getWebDriver().getCurrentUrl()),
                () -> Assertions.assertEquals(getMainPageTitle(),getWebDriver().getTitle())
        );
    }

    @Test
    @DisplayName("Т-Main-01 Menu items are available on main page")
    protected void mainPageMenuItems(){
        MainPage mp = new MainPage(getWebDriver());

        Assertions.assertAll(
                () -> Assertions.assertTrue(mp.menuHomeAvailable()),
                () -> Assertions.assertTrue(mp.menuLoginAvailable()),
                () -> Assertions.assertTrue(mp.menuSignUpAvailable())
        );
    }

    @Test
    @DisplayName("Т-Main-02 Login button will open Login page")
    protected void menuItemLoginWillOpenLogin(){
        MainPage mp = new MainPage(getWebDriver());
        mp.menuLoginClick();

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Т-Main-03 SignUp button will open Sign Up page")
    protected void menuItemSignUpWillOpenSignUp(){
        MainPage mp = new MainPage(getWebDriver());
        mp.menuSignUpClick();

        Assertions.assertEquals(getSignUpUrl(), getWebDriver().getCurrentUrl());
    }

    //ToDo: Home button returns user to main page regardless of current section

    //ToDo: Authorized user will see menu with Profile and Logout buttons

    //ToDo: Logout button will log out user and open main page

    //ToDo: Logged out user is not able to open Profile page
}
