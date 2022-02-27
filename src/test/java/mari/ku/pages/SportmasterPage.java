package mari.ku.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SportmasterPage {

    private SelenideElement
            buttonRegion = $("[data-selenium=cityButton]"),
            textAuthCodeErrorMessage = $("[data-selenium=auth-code-error-message]"),
            textErrorMessageOnBasket = $("[data-selenium=error-message]"),
            buttonAuth = $("[data-selenium=user-btn]"),
            buttonPickup = $("[data-selenium=pickup]"),
            buttonSubmitOrderOnBasket = $("[data-selenium=submitButton]"),
            fieldSearchRegion = $("[data-selenium=smTextField][placeholder='Ваш город...']"),
            fieldSearchProduct = $("[data-selenium=smTextField][placeholder='Поиск']"),
            fieldMobile = $("[data-selenium=smTextField][placeholder='Введите номер телефона']"),
            fieldAuthCode = $("[data-selenium=smTextField][placeholder='Введите код подтверждения']"),
            fieldAuthCodeOnBasket = $("[data-selenium=smTextField][placeholder='Введите код']"),
            fieldUserName = $("[data-selenium=smTextField][placeholder='Введите ФИО']"),
            fieldMale = $("[data-selenium=smTextField][placeholder='Введите E‑mail']"),
            buttonSearchProduct = $("[data-selenium=smButton]"),
            iconCompare = $("[data-selenium=comparelist-goods] [data-selenium=sm_badge_item]"),
            iconBasket = $("[data-selenium=basket-btn] [data-selenium=sm_badge_item]"),
            titleCompare = $("[data-selenium=compare-title]"),
            titleBasket = $("[data-selenium=cartTitle]"),
            textCompareEmpty = $("[data-selenium=compare-empty]"),
            textBasketEmpty = $("[data-selenium=title]"),
            buttonSelectPickupPoint = $("[data-selenium=select-btn]"),
            buttonGetCodeOnBasket = $("[data-selenium=getCode]"),
            basketAllDelete = $("[data-selenium=cart-group-delete]");

    private ElementsCollection
            cityNames = $$(".cityName"),
            titleProducts = $$("[data-selenium=product-name]"),
            categories = $$("[data-selenium=section_item]"),
            sections = $$(".sm-link.sm-link_black"),
            productCardImages = $$("[data-selenium=product-card-image]"),
            compareToggles = $$("[data-selenium=compareToggle]"),
            addToCartButtons = $$("[data-selenium=addToCartButton]"),
            getButtons = $$("[data-selenium=submit-text]"),
            pickupPoints = $$("[data-selenium=pickup-point]"),
            checkboxOnBasket = $$("[data-selenium=checkbox]"),
            productDeleteButtons = $$("[data-selenium=delete-button]");

    @Step("Открываем главную страницу Sportmaster")
    public SportmasterPage openPage() {
        open("https://new.sportmaster.ru/");

        return this;
    }

    @Step("Открываем окно выбора региона доставки")
    public SportmasterPage openWindowRegion() {
        buttonRegion.click();

        return this;
    }

    @Step("Вбиваем искомый регион доставки {searchQuery} и выбираем из выпадающего списка {expectedResult}")
    public SportmasterPage inputSearchRegion(String searchQuery, String expectedResult) {
        fieldSearchRegion.setValue(searchQuery);
        cityNames.findBy(text(expectedResult)).click();

        return this;
    }

    @Step("Проверяем отображение региона {searchQuery} в шапке сайта")
    public SportmasterPage checkRegion(String searchQuery) {
        buttonRegion.shouldHave(text(searchQuery));

        return this;
    }

    @Step("Вбиваем искомый товар {searchItem} в поле поиска")
    public SportmasterPage inputSearchItem(String searchItem) {
        fieldSearchProduct.setValue(searchItem);

        return this;
    }

    @Step("Кликаем на кнопку Найти")
    public SportmasterPage clickButtonSearch() {
        buttonSearchProduct.click();

        return this;
    }

    @Step("Проверяем наличие текста {searchItem} в каждой карточке товара на SRP")
    public SportmasterPage checkNameItem(String searchItem) {
        for (int i = 0; i < 60; i++) {
            titleProducts.get(i).shouldHave(text(searchItem));
        }

        return this;
    }

    @Step("Выбираем категорию {category}")
    public SportmasterPage selectCategory(String category) {
        categories.findBy(text(category)).click();

        return this;
    }

    @Step("Выбираем раздел {section}")
    public SportmasterPage selectSection(String section) {
        sections.findBy(text(section)).click();

        return this;
    }

    @Step("Выбираем подраздел {subsection}")
    public SportmasterPage selectSubsection(String subsection) {
        $("[data-selenium=image][alt=" + subsection + "]").click();

        return this;
    }

    @Step("Добавляем в сравнение {itemQuantity} товаров и проверяем отображение числа {itemQuantity} на иконке сравнения")
    public SportmasterPage addToCompare(int itemQuantity) {
        for (int i = 0; i < itemQuantity; i++) {
            productCardImages.get(i).hover();
            compareToggles.get(i).click();
            iconCompare.shouldHave(text(String.valueOf(i + 1)));
        }
        return this;
    }

    @Step("Добавляем в корзину {itemQuantity} товаров и проверяем отображение числа {itemQuantity} на иконке корзины")
    public SportmasterPage addToBasket(int itemQuantity) {
        for (int i = 0; i < itemQuantity; i++) {
            productCardImages.get(i).hover();
            addToCartButtons.get(0).click();
            iconBasket.shouldHave(text(String.valueOf(i + 1)));
        }
        return this;
    }

    @Step("Переходим в {section} и проверяем заголовок {section}")
    public SportmasterPage goToCompareAndCheckTitle(String section) {
        iconCompare.click();
        titleCompare.shouldHave(text(section));

        return this;
    }

    @Step("Переходим в {section} и проверяем заголовок {section}")
    public SportmasterPage goToBasketAndCheckTitle(String section) {
        iconBasket.click();
        titleBasket.shouldHave(text(section));

        return this;
    }

    @Step("Удаляем из сравнения все товары и проверяем наличие сообщения об отсутствии товаров")
    public SportmasterPage deleteAllFromCompare(int itemQuantity, String compareEmpty) {
        for (int i = 0; i < itemQuantity; i++) {
            productCardImages.get(0).hover();
            productDeleteButtons.get(0).click();
        }
        textCompareEmpty.shouldHave(text(compareEmpty));

        return this;
    }

    @Step("Удаляем из корзины все товары и проверяем наличие сообщения об отсутствии товаров")
    public SportmasterPage deleteAllFromBasket(String basketEmpty) {
        basketAllDelete.click();
        textBasketEmpty.shouldHave(text(basketEmpty));

        return this;
    }

    @Step("Открываем окно авторизации")
    public SportmasterPage openWindowAuth() {
        buttonAuth.click();

        return this;
    }

    @Step("Вводим в поле номер телефона {mobile} и нажимаем на кнопку 'Получить код'")
    public SportmasterPage inputMobileAndClickGetCode(String mobile) {
        fieldMobile.setValue(mobile);
        getButtons.findBy(text("Получить код")).click();

        return this;
    }

    @Step("Вводим в поле код подтверждения {authCode}")
    public SportmasterPage inputAuthCode(String authCode) {
        fieldAuthCode.setValue(authCode);

        return this;
    }

    @Step("Проверяем появление ошибки с текстом '{authCodeErrorMessage}'")
    public SportmasterPage checkAuthCodeErrorMessage(String authCodeErrorMessage) {
        textAuthCodeErrorMessage.shouldHave(text(authCodeErrorMessage));

        return this;
    }

    @Step("Выбираем способ получения Самовывоз")
    public SportmasterPage selectPickupMethod() {
        buttonPickup.click();

        return this;
    }

    @Step("Выбираем первый по списку пункт самовывоза")
    public SportmasterPage selectPickupPoint() {
        pickupPoints.get(0).click();

        return this;
    }

    @Step("Для выбранного магазина нажимаем кнопку 'Выбрать'")
    public SportmasterPage clickButtonSelectPickupPoint() {
        buttonSelectPickupPoint.click();

        return this;
    }

    @Step("Заполняем данные получателя: ФИО '{userName}'")
    public SportmasterPage inputUserName(String userName) {
        fieldUserName.setValue(userName);

        return this;
    }

    @Step("Заполняем данные получателя: E‑mail '{mail}'")
    public SportmasterPage inputMail(String mail) {
        fieldMale.setValue(mail);

        return this;
    }

    @Step("Заполняем данные получателя: E‑mail '{mobile}'")
    public SportmasterPage inputMobile(String mobile) {
        fieldMobile.setValue(mobile);

        return this;
    }

    @Step("Принимаем пользовательское соглашение")
    public SportmasterPage clickCheckboxOnBasket() {
        checkboxOnBasket.get(0).click();

        return this;
    }

    @Step("Нажимаем на кнопку 'Получить код' на Корзине")
    public SportmasterPage clickGetCodeOnBasket() {
        buttonGetCodeOnBasket.click();

        return this;
    }

    @Step("Вводим в поле код подтверждения {authCode} на Корзине")
    public SportmasterPage inputAuthCodeOnBasket(String authCode) {
        fieldAuthCodeOnBasket.setValue(authCode);

        return this;
    }

    @Step("Нажимаем на кнопку 'Оформить заказ' на Корзине")
    public SportmasterPage clickButtonSubmitOrderOnBasket() {
        buttonSubmitOrderOnBasket.click();

        return this;
    }

    @Step("Проверяем появление ошибки с текстом '{errorMessageOnBasket}'")
    public SportmasterPage checkErrorMessageOnBasket(String errorMessageOnBasket) {
        textErrorMessageOnBasket.shouldHave(text(errorMessageOnBasket));

        return this;
    }
}

