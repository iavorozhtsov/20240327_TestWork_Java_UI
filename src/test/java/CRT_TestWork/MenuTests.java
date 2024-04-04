package CRT_TestWork;

import CRT_TestWork.Utils.SignUpException;
import CRT_TestWork.Utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static CRT_TestWork.Utils.Utils.*;

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

    @Test
    @DisplayName("T-Main-04 Profile menu item appears after login")
    protected void menuItemProfileAppearsAfterLogIn() throws SignUpException {
        getRegisteredUserCredentials(getWebDriver());

        getWebDriver().get(getLoginUrl());
        LoginPage lp = new LoginPage(getWebDriver());

        LoginPage.logMeIn(getUserEmail(), getUserPassword());

        ProfilePage pp = new ProfilePage(getWebDriver());

        Assertions.assertTrue(pp.menuProfileBtnIsAvailable());
        Assertions.assertEquals(pp.menuProfileBtnGetText(), getProfileButtonText());

        pp.menuLogoutBtnClick();
    }

    @Test
    @DisplayName("T-Main-05 Unauthorized user has no access to Profile page")
    protected void unauthorizedUserHasNoAccessToProfile(){
        getWebDriver().get(getProfileUrl());

        LoginPage lp = new LoginPage(getWebDriver());
        Assertions.assertEquals(getNonAuthorized(), lp.getNotification());
    }

    @Test
    @DisplayName("T-Main-06 Logout menu item appears after login")
    protected void menuItemLogoutAppearsAfterLogIn() throws SignUpException {
        getRegisteredUserCredentials(getWebDriver());

        getWebDriver().get(getLoginUrl());
        LoginPage lp = new LoginPage(getWebDriver());
        LoginPage.logMeIn(getUserEmail(), getUserPassword());

        ProfilePage pp = new ProfilePage(getWebDriver());

        Assertions.assertTrue(pp.menuLogoutBtnIsAvailable());
        Assertions.assertEquals(pp.menuLogoutBtnGetText(), getLogoutButtonText());

        pp.menuLogoutBtnClick();
    }

    @Test
    @DisplayName("T-Main-07 After Logout clicked user will be logged out")
    protected void logoutClickWillLogout() throws SignUpException {
        getRegisteredUserCredentials(getWebDriver());

        getWebDriver().get(getLoginUrl());
        LoginPage lp = new LoginPage(getWebDriver());
        LoginPage.logMeIn(getUserEmail(), getUserPassword());

        ProfilePage pp = new ProfilePage(getWebDriver());
        pp.menuLogoutBtnClick();

        Assertions.assertEquals(getBaseUrl(), getWebDriver().getCurrentUrl());
    }
}
