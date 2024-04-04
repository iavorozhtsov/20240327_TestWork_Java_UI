package CRT_TestWork;

import CRT_TestWork.Utils.SignUpException;
import CRT_TestWork.Utils.Utils;
import org.junit.jupiter.api.*;

import static CRT_TestWork.Utils.Utils.*;

public class LoginTests extends AbstractTest{

    @BeforeEach
    protected void loginPageOpen(){
        getWebDriver().get(getLoginUrl());
    }

    @AfterEach
    protected void logoutAndOpenLoginPage() {
        ProfilePage pp = new ProfilePage(getWebDriver());
        pp.menuLogoutBtnClick();
        getWebDriver().get(getLoginUrl());
    }

    @Test
    @DisplayName("Т-Login-00 There is all required elements on Login page")
    protected void checkRequiredElements(){
        LoginPage lp = new LoginPage(getWebDriver());

        Assertions.assertAll(
                () -> Assertions.assertTrue(lp.emailIsAvailable()),
                () -> Assertions.assertTrue(lp.passwordIsAvailable()),
                () -> Assertions.assertTrue(lp.loginIsAvailable())
        );
    }

    @Test
    @DisplayName("Т-Login-01 Registered user can log in with valid credentials")
    protected void loginWithValidCredentials() throws SignUpException {
        getRegisteredUserCredentials(getWebDriver());

        LoginPage lp = new LoginPage(getWebDriver());
        lp.logMeIn(getUserEmail(), getUserPassword());

        ProfilePage pp = new ProfilePage(getWebDriver());

        Assertions.assertAll(
                () -> Assertions.assertTrue(pp.menuProfileBtnIsAvailable()),
                () -> Assertions.assertTrue(pp.menuLogoutBtnIsAvailable()),
                () -> Assertions.assertEquals(getProfileUrl(), getWebDriver().getCurrentUrl()),
                () -> Assertions.assertEquals(getWelcomeMessage() + getUserName() + "!", pp.titleGetMessage()),
                () -> Assertions.assertEquals(getProfileButtonText(), pp.menuProfileBtnGetText()),
                () -> Assertions.assertEquals(getLogoutButtonText(), pp.menuLogoutBtnGetText())
        );
    }

    @Test
    @DisplayName("Т-Login-02 Registered user can not log in with invalid password")
    protected void loginWithInvalidPassword() throws SignUpException {
        SignUpPage sp = new SignUpPage(getWebDriver());
        getRegisteredUserCredentials(getWebDriver());

        LoginPage lp = new LoginPage(getWebDriver());
        lp.logMeIn(getUserEmail(), Utils.getPassword());

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
        Assertions.assertEquals(getWrongCredentialsMessage(), lp.notificationGetText());
    }

    @Test
    @DisplayName("Т-Login-03 Registered user can not log in with other user's valid password ")
    protected void loginWithOtherUserPassword() throws SignUpException {
        getRegisteredUserCredentials(getWebDriver());
        String otherUserPassword = getUserPassword();
        clearUserCredentials();
        getRegisteredUserCredentials(getWebDriver());


        LoginPage lp = new LoginPage(getWebDriver());
        lp.logMeIn(getUserEmail(), otherUserPassword);

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
        Assertions.assertEquals(getWrongCredentialsMessage(), lp.notificationGetText());
    }

    @Test
    @DisplayName("Т-Login-04 Unregistered user can not log in")
    protected void loginWithUnregisteredUser(){
        LoginPage lp = new LoginPage(getWebDriver());
        lp.logMeIn(Utils.getValidEmail(), getPassword());

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
        Assertions.assertEquals(getWrongCredentialsMessage(), lp.notificationGetText());
    }

    @Test
    @DisplayName("Т-Login-05 Unregistered user can not log in with registered user password")
    protected void loginWithUnregisteredUserAndOtherUserPassword() throws SignUpException {
        getRegisteredUserCredentials(getWebDriver());

        LoginPage lp = new LoginPage(getWebDriver());
        lp.logMeIn(Utils.getValidEmail(), getUserPassword());

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
        Assertions.assertEquals(getWrongCredentialsMessage(), lp.notificationGetText());
    }
}
