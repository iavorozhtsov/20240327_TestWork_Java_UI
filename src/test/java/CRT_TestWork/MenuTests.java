package CRT_TestWork;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTests extends AbstractTest{

    @BeforeEach
    public void openMainPage(){
        getWebDriver().get(BaseUrl);
    }

    @Test
    @DisplayName("Т-Main-00 Site is available")
    void mainPageOpen(){
        Assertions.assertEquals(BaseUrl, getWebDriver().getCurrentUrl());
        Assertions.assertEquals(mainPageTitle,getWebDriver().getTitle());
    }

    @Test
    @DisplayName("Т-Main-01 Menu items are available on main page")
    void mainPageMenuItems(){
        MainPage mp = new MainPage(getWebDriver());

        Assertions.assertTrue(mp.menuHomeAvailable());
        Assertions.assertTrue(mp.menuLoginAvailable());
        Assertions.assertTrue(mp.menuSignUpAvailable());
    }

    @Test
    @DisplayName("Т-Main-02 Login button will open Login page")
    void menuItemLoginWillOpenLogin(){
        MainPage mp = new MainPage(getWebDriver());

        mp.menuLoginClick();
        Assertions.assertEquals(LoginUrl, getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Т-Main-03 SignUp button will open Sign Up page")
    void menuItemSignUpWillOpenSignUp(){
        MainPage mp = new MainPage(getWebDriver());

        mp.menuSignUpClick();
        Assertions.assertEquals(SignUpUrl, getWebDriver().getCurrentUrl());
    }
}
