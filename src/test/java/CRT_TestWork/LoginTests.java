package CRT_TestWork;

import org.junit.jupiter.api.*;

public class LoginTests extends AbstractTest{

    static String userEmail = "";
    static String userPassword = "";
    static String userName = "";
    static String otherUserPassword = "";


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
    protected void loginWithValidCredentials(){
        getRegisteredUserCredentials();

        LoginPage lp = new LoginPage(getWebDriver());
        lp.logMeIn(userEmail, userPassword);

        ProfilePage pp = new ProfilePage(getWebDriver());

        Assertions.assertAll(
                () -> Assertions.assertTrue(pp.menuProfileBtnIsAvailable()),
                () -> Assertions.assertTrue(pp.menuLogoutBtnIsAvailable()),
                () -> Assertions.assertEquals(getProfileUrl(), getWebDriver().getCurrentUrl()),
                () -> Assertions.assertEquals(getWelcomeMessage() + userName + "!", pp.titleGetMessage()),
                () -> Assertions.assertEquals(getProfileButtonText(), pp.menuProfileBtnGetText()),
                () -> Assertions.assertEquals(getLogoutButtonText(), pp.menuLogoutBtnGetText())
        );
    }

    @Test
    @DisplayName("Т-Login-02 Registered user can not log in with invalid password")
    protected void loginWithInvalidPassword(){
        SignUpPage sp = new SignUpPage(getWebDriver());
        getRegisteredUserCredentials();

        LoginPage lp = new LoginPage(getWebDriver());
        lp.logMeIn(userEmail, Utils.getPassword());

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
        Assertions.assertEquals(getWrongCredentialsMessage(), lp.notificationGetText());
    }

    @Test
    @DisplayName("Т-Login-03 Registered user can not log in with other user's valid password ")
    protected void loginWithOtherUserPassword(){
        getRegisteredUserCredentials();

        LoginPage lp = new LoginPage(getWebDriver());
        lp.logMeIn(userEmail, otherUserPassword);

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
        Assertions.assertEquals(getWrongCredentialsMessage(), lp.notificationGetText());
    }

    @Test
    @DisplayName("Т-Login-04 Unregistered user can not log in")
    protected void loginWithUnregisteredUser(){
        LoginPage lp = new LoginPage(getWebDriver());
        lp.logMeIn(Utils.getValidEmail(), Utils.getPassword());

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
        Assertions.assertEquals(getWrongCredentialsMessage(), lp.notificationGetText());
    }

    @Test
    @DisplayName("Т-Login-05 Unregistered user can not log in with registered user password")
    protected void loginWithUnregisteredUserAndOtherUserPassword(){
        getRegisteredUserCredentials();

        LoginPage lp = new LoginPage(getWebDriver());
        lp.logMeIn(Utils.getValidEmail(), userPassword);

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
        Assertions.assertEquals(getWrongCredentialsMessage(), lp.notificationGetText());
    }

    private void getRegisteredUserCredentials(){
        if (userEmail.isEmpty()){
            userEmail = Utils.getValidEmail();
            userName = Utils.getUserName();
            userPassword = Utils.getPassword();
            otherUserPassword = Utils.getPassword();

            getWebDriver().get(getSignUpUrl());
            SignUpPage.registerMe(userEmail, userName, userPassword);
            Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());

            getWebDriver().get(getSignUpUrl());
            SignUpPage.registerMe(Utils.getValidEmail(), Utils.getUserName(), otherUserPassword);
            Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
        }
    }
}
