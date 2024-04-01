package CRT_TestWork;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SignUpTests extends AbstractTest{

    @BeforeEach
    private void signUpPageOpen(){
        getWebDriver().get(SignUpUrl);
    }

    @Test
    @DisplayName("Т-SignUp-00 There is all required elements on page Sign In")
    void checkRequiredElements(){
        SignUpPage sp = new SignUpPage(getWebDriver());

        Assertions.assertTrue(sp.emailIsAvailable());
        Assertions.assertTrue(sp.nameIsAvailable());
        Assertions.assertTrue(sp.passwordIsAvailable());
        Assertions.assertTrue(sp.signUpIsAvailable());
    }

    @Test
    @DisplayName("Т-SignUp-01 Registration with valid credentials")

    void firstRegistrationWithValidCredentials(){
        registerMe(validEmail, validName, validPassword);

        Assertions.assertEquals(LoginUrl, getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Т-SignUp-02 Email field is required")
    /* Тест упадёт только в первый запуск, так как зарегистрирует пользователя с пустым полем почты */
    void emailIsRequired(){
        registerMe("", validName, validPassword);

        Assertions.assertEquals(SignUpUrl, getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Т-SignUp-03 Password field is required")
    void passwordIsRequired(){
        registerMe(validEmail + "1", validName, "");

        Assertions.assertEquals(SignUpUrl, getWebDriver().getCurrentUrl());
        Assertions.assertNotEquals(LoginUrl, getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Т-SignUp-04 Name field is not required")
    void nameIsNotRequired(){
        registerMe(validEmail+2, "", validPassword);

        Assertions.assertEquals(LoginUrl, getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Т-SignUp-05 Try to register with already existing valid credentials")
    /* В зависимости от порядка запуска тестов в первом прогоне может упасть,
    * так как выполнится до позитивного теста  Т-SignUp-01 */
    void secondRegistrationWithValidCredentials(){
        registerMe(validEmail, validName, validPassword);

        SignUpPage sp = new SignUpPage(getWebDriver());

        Assertions.assertEquals(SignUpUrl, getWebDriver().getCurrentUrl());
        Assertions.assertTrue(sp.notificationIsVisible());
        Assertions.assertEquals(emailExistMessage, sp.notificationGetText());
    }

    @ParameterizedTest
    @DisplayName("Т-SignUp-06-8 Try to register with invalid email")
    @ValueSource(strings = {"1", "1@", "@1"})
    void tryRegisterWithInvalidEmail(String invalidEmail){
        registerMe(invalidEmail, validName, validPassword);

        SignUpPage sp = new SignUpPage(getWebDriver());

        Assertions.assertEquals(SignUpUrl, getWebDriver().getCurrentUrl());
    }

    void registerMe(String email, String name, String password){
        SignUpPage sp = new SignUpPage(getWebDriver());

        sp.emailSendkeys(email);
        sp.nameSendkeys(name);
        sp.passwordSendkeys(password);
        sp.signUpBtnClick();
    }
}
