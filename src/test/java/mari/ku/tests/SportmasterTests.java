package mari.ku.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import mari.ku.pages.SportmasterPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SportmasterTests extends TestData {

    SportmasterPage sportmasterPage = new SportmasterPage();

    @Owner("marina-ku4erenko")
    @Tag("positive")
    @Feature("Смена региона доставки")
    @CsvSource(value = {
            "Новосибирск| г Новосибирск",
            "Армавир | г Армавир",
            "Уфа | г Уфа"
    },
            delimiter = '|')
    @ParameterizedTest(name = "Выбор региона доставки {1} и проверка его отображения в шапке сайта Sportmaster")
    @DisplayName("Проверка выбора региона доставки")
    void selectAndCheckRegionTest(String searchQuery, String expectedResult) {
        sportmasterPage.
                openPage().
                openWindowRegion().
                inputSearchRegion(searchQuery, expectedResult).
                checkRegion(searchQuery);
    }

    @Owner("marina-ku4erenko")
    @Tag("positive")
    @Feature("Поиск товаров")
    @ValueSource(strings = {"Купальник", "Коньки"})
    @ParameterizedTest(name = "Поиск товара {0} и проверка отображения текста '{0}' в каждой карточке товара")
    @DisplayName("Проверка работы поиска товаров")
    void searchAndCheckProductTest(String searchItem) {
        sportmasterPage.
                openPage().
                inputSearchItem(searchItem).
                clickButtonSearch().
                checkNameItem(searchItem);

    }

    @Owner("marina-ku4erenko")
    @Tag("positive")
    @Feature("Сравнение")
    @Test
    @DisplayName("Проверка добавления и удаления товаров из сравнения")
    void compareCheckTest() {
        sportmasterPage.
                openPage().
                selectCategory(NAME_CATEGORY_SPORTS).
                selectSection(NAME_SECTION_DARTS).
                selectSubsection(NAME_SUBSECTION_DART).
                addToCompare(ITEM_QUANTITY).
                goToCompareAndCheckTitle(TITLE_COMPARE).
                deleteAllFromCompare(ITEM_QUANTITY, TEXT_COMPARE_EMPTY);
    }

    @Owner("marina-ku4erenko")
    @Tag("positive")
    @Feature("Корзина")
    @Test
    @DisplayName("Проверка добавления и удаления товаров из корзины")
    void basketCheckTest() {
        sportmasterPage.
                openPage().
                selectCategory(NAME_CATEGORY_SPORTS).
                selectSection(NAME_SECTION_YOGA).
                selectSubsection(NAME_SUBSECTION_ACCESSORIES).
                addToBasket(ITEM_QUANTITY).
                goToBasketAndCheckTitle(TITLE_BASKET).
                deleteAllFromBasket(TEXT_BASKET_EMPTY);
    }

    @Owner("marina-ku4erenko")
    @Tag("negative")
    @Feature("Авторизация")
    @Test
    @DisplayName("Проверка формирования ошибки 'Некорректный код подтверждения' при авторизации")
    void checkAuthErrorMessageTest() {
        sportmasterPage.
                openPage().
                openWindowAuth().
                inputMobileAndClickGetCode(MOBILE).
                inputAuthCode(AUTH_CODE).
                checkAuthCodeErrorMessage(AUTH_CODE_ERROR_MESSAGE);
    }

    @Owner("marina-ku4erenko")
    @Tag("negative")
    @Feature("Корзина")
    @Test
    @DisplayName("Проверка формирования ошибки 'Неверный код' на корзине")
    void checkErrorMessageOnBasketTest() {
        sportmasterPage.
                openPage().
                selectCategory(NAME_CATEGORY_SPORTS).
                selectSection(NAME_SECTION_YOGA).
                selectSubsection(NAME_SUBSECTION_ACCESSORIES).
                addToBasket(ITEM_QUANTITY).
                goToBasketAndCheckTitle(TITLE_BASKET).
                selectPickupMethod().
                selectPickupPoint().
                clickButtonSelectPickupPoint().
                inputUserName(USER_NAME).
                inputMail(MAIL).
                inputMobile(MOBILE).
                clickCheckboxOnBasket().
                clickGetCodeOnBasket().
                inputAuthCodeOnBasket(AUTH_CODE).
                clickButtonSubmitOrderOnBasket().
                checkErrorMessageOnBasket(ERROR_MESSAGE_ON_BASKET);
    }
}
