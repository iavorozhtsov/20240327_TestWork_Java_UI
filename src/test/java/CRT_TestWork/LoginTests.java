package CRT_TestWork;

import org.junit.jupiter.api.*;

public class LoginTests extends AbstractTest{


    @BeforeEach
    private void loginPageOpen(){
        getWebDriver().get(LoginUrl);
    }

    @AfterEach
    private void logoutAndOpenLoginPage() {
        ProfilePage pp = new ProfilePage(getWebDriver());
        pp.menuLogoutBtnClick();
        getWebDriver().get(LoginUrl);
    }

    @Test
    @DisplayName("Т-Login-00 There is all required elements on Login page")
    void checkRequiredElements(){
        LoginPage lp = new LoginPage(getWebDriver());

        Assertions.assertTrue(lp.emailIsAvailable());
        Assertions.assertTrue(lp.passwordIsAvailable());
        Assertions.assertTrue(lp.loginIsAvailable());
    }

    @Test
    @DisplayName("Т-Login-01 Registered user can log in with valid credentials")
    void loginWithValidCredentials(){
        logMeIn(constantUserEmail, validPassword + "1");

        ProfilePage pp = new ProfilePage(getWebDriver());

        Assertions.assertEquals(ProfileUrl, getWebDriver().getCurrentUrl());

        Assertions.assertEquals(welcomeMessage, pp.titleGetMessage());

        Assertions.assertTrue(pp.menuProfileBtnIsAvailable());
        Assertions.assertTrue(pp.menuLogoutBtnIsAvailable());
        Assertions.assertEquals(profileButtonText, pp.menuProfileBtnGetText());
        Assertions.assertEquals(logoutButtonText, pp.menuLogoutBtnGetText());
    }

    @Test
    @DisplayName("Т-Login-02 Registered user can not log in with invalid password")
    void loginWithInvalidPassword(){
        logMeIn(constantUserEmail, validPassword + "2");

        LoginPage lp = new LoginPage(getWebDriver());

        Assertions.assertEquals(LoginUrl, getWebDriver().getCurrentUrl());
        Assertions.assertEquals(wrongCredentialsMessage, lp.notificationGetText());
    }

    @Test
    @DisplayName("Т-Login-03 Registered user can not log in with other user's valid password ")
    void loginWithOtherUserPassword(){
        logMeIn(constantUserEmail, validPassword);

        LoginPage lp = new LoginPage(getWebDriver());

        Assertions.assertEquals(LoginUrl, getWebDriver().getCurrentUrl());
        Assertions.assertEquals(wrongCredentialsMessage, lp.notificationGetText());
    }

    @Test
    @DisplayName("Т-Login-04 Unregistered user can not log in")
    void loginWithRegisteredUserPassword(){
        logMeIn(unregisteredUserEmail, validPassword);

        LoginPage lp = new LoginPage(getWebDriver());

        Assertions.assertEquals(LoginUrl, getWebDriver().getCurrentUrl());
        Assertions.assertEquals(wrongCredentialsMessage, lp.notificationGetText());
    }

    private void logMeIn(String email, String password) {
        LoginPage lp = new LoginPage(getWebDriver());

        lp.emailSendkeys(email);
        lp.passwordSendkeys(password);
        lp.loginClick();
    }
}
