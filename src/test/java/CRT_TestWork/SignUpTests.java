package CRT_TestWork;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SignUpTests extends AbstractTest{

    @BeforeAll
    public static void init(){
        SignUpPage sp = new SignUpPage(getWebDriver());
    }

    @BeforeEach
    private void signUpPageOpen(){
        getWebDriver().get(getSignUpUrl());
    }

    @Test
    @DisplayName("Т-SignUp-00 There is all required elements on page Sign In")
    protected void checkRequiredElements(){
        SignUpPage sp = new SignUpPage(getWebDriver());

        Assertions.assertAll(
                () -> Assertions.assertTrue(sp.emailIsAvailable()),
                () -> Assertions.assertTrue(sp.nameIsAvailable()),
                () -> Assertions.assertTrue(sp.passwordIsAvailable()),
                () -> Assertions.assertTrue(sp.signUpIsAvailable())
        );
    }

    @Test
    @DisplayName("Т-SignUp-01 Registration with valid credentials")
    protected void registrationWithValidCredentials(){
        SignUpPage.registerMe(Utils.getValidEmail(), Utils.getUserName(), Utils.getPassword());

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Т-SignUp-02 Email field is required")
    /* Тест упадёт только в первый запуск, так как зарегистрирует пользователя с пустым полем почты */
    protected void emailIsRequired(){
        SignUpPage.registerMe("", Utils.getUserName(), Utils.getPassword());

        Assertions.assertEquals(getSignUpUrl(), getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Т-SignUp-03 Password field is required")
    protected void passwordIsRequired(){
        SignUpPage.registerMe(Utils.getValidEmail(), Utils.getUserName(), "");

        Assertions.assertEquals(getSignUpUrl(), getWebDriver().getCurrentUrl());
        Assertions.assertNotEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Т-SignUp-04 Name field is not required")
    protected void nameIsNotRequired(){
        SignUpPage.registerMe(Utils.getValidEmail(), "", Utils.getPassword());

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Т-SignUp-05 Try to register with already existing valid email")
    protected void registrationWithExistingValidEmail(){
        String validEmail = Utils.getValidEmail();

        SignUpPage sp = new SignUpPage(getWebDriver());
        sp.registerMe(validEmail, Utils.getUserName(), Utils.getPassword());

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
        getWebDriver().get(getSignUpUrl());

        sp.registerMe(validEmail, Utils.getUserName(), Utils.getPassword());

        Assertions.assertEquals(getSignUpUrl(), getWebDriver().getCurrentUrl());
        Assertions.assertTrue(sp.notificationIsVisible());
        Assertions.assertEquals(getEmailExistMessage(), sp.notificationGetText());
    }

    @Test
    @DisplayName("Т-SignUp-06 Try to register with already existing valid password")
    protected void registrationWithExistingValidPassword(){
        String validPassword = Utils.getPassword();

        SignUpPage.registerMe(Utils.getValidEmail(), Utils.getUserName(), validPassword);

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
        getWebDriver().get(getSignUpUrl());

        SignUpPage.registerMe(Utils.getValidEmail(), Utils.getUserName(), validPassword);

        Assertions.assertEquals(getLoginUrl(), getWebDriver().getCurrentUrl());
    }

    @ParameterizedTest
    @DisplayName("Т-SignUp-07-9 Try to register with invalid email")
    @ValueSource(strings = {"@*", "*@", "@@", "*"})
    protected void tryRegisterWithInvalidEmail(String invalidEmail){
        SignUpPage.registerMe(Utils.getInvalidEmail(invalidEmail), Utils.getUserName(), Utils.getPassword());

        Assertions.assertEquals(getSignUpUrl(), getWebDriver().getCurrentUrl());
    }
}
