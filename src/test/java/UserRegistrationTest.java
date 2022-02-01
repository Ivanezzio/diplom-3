import com.codeborne.selenide.Configuration;
import com.testbase.LoginPage;
import com.testbase.MainPage;
import com.testbase.RegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;


public class UserRegistrationTest {


    MainPage mainPage = open(MainPage.BURGERS_MAIN_PAGE_URL, MainPage.class);
    LoginPage loginPage = page(LoginPage.class);
    RegistrationPage registrationPage = page(RegistrationPage.class);

    @Before
    public void setUp() {
        Configuration.startMaximized = true;
    }

    @Test
    @DisplayName("Регистрация с валидными данными пользователя")
    public void userRegistrationIsPossible() {

        mainPage.clickEnterAccountButton();
        loginPage.clickRegistrationButton();
        //генерация учетных данных пользователя
        String userName = RandomStringUtils.randomAlphabetic(7);
        String userEmail =
                RandomStringUtils.randomAlphabetic(4) + "@" + RandomStringUtils.randomAlphabetic(4) + ".ru";
        String userPassword = RandomStringUtils.randomAlphabetic(7);

        registrationPage.setUserDataAndRegister(userName, userEmail, userPassword);
        loginPage.isOpen();
        loginPage.enterCredentialsAndClickEnter(userEmail, userPassword);
        assertTrue(mainPage.isMainPageLoggedAuthorised());
    }

    @Test
    @DisplayName("Регистрация пользователя с паролем короче 6 символов")
    public void userRegistrationWithIncorrectPassword() {

        mainPage.clickEnterAccountButton();
        loginPage.clickRegistrationButton();
        //генерация учетных данных пользователя с некорректным паролем
        String userName = RandomStringUtils.randomAlphabetic(7);
        String userEmail =
                RandomStringUtils.randomAlphabetic(4) + "@" + RandomStringUtils.randomAlphabetic(4) + ".ru";
        String userIncorrectPassword = RandomStringUtils.randomAlphabetic(5);

        registrationPage.setUserDataAndRegister(userName, userEmail, userIncorrectPassword);
        registrationPage.checkErrorTextPopUp();
    }

    @After
    public void terminate() {
        getWebDriver().quit();
    }
}
