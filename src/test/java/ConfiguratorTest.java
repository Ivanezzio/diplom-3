import com.codeborne.selenide.Configuration;
import com.testbase.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;

public class ConfiguratorTest {
    MainPage mainPage = open(MainPage.BURGERS_MAIN_PAGE_URL, MainPage.class);

    @Test
    @DisplayName("Проверка открытия конструктора. Вкладка Булки открыта по умолчанию")
    public void checkIsBunsTabOpen() {
        Configuration.startMaximized = true;
        mainPage.isBunsTabOpen();
    }

    @Test
    @DisplayName("Проверка работы вкладки Соусы")
    public void isSaucesTabWorks() {
        Configuration.startMaximized = true;
        mainPage.goToSaucesTab();
        assertTrue("Вкладка Соусы не открыта", mainPage.isSaucesTabVisible());
    }

    @Test
    @DisplayName("Проверка работы вкладки Начинки")
    public void isFillingsTabWorks() {
        Configuration.startMaximized = true;
        mainPage.goToFillingsTab();
        assertTrue("Вкладка Начинки не открыта", mainPage.isFillingsTabVisible());
    }

    @Test
    @DisplayName("Проверка работы вкладки Булки")
    public void isBunsTabWorks() {
        Configuration.startMaximized = true;
        mainPage.goToFillingsTab();
        mainPage.goToBunsTab();
        assertTrue("Вкладка Начинки не открыта", mainPage.isBunsTabVisible());
    }

    @After
    public void terminate() {
        Configuration.startMaximized = true;
        getWebDriver().quit();
    }
}
