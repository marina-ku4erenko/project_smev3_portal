package mari.ku.tests;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData extends TestBase {


    Faker faker = new Faker(new Locale("ru"));

    String MOBILE = String.valueOf(faker.number().numberBetween(1000000000, 2147483647));
    String AUTH_CODE = String.valueOf(faker.number().numberBetween(1000, 9999));
    String USER_NAME = faker.name().firstName() +" "+ faker.name().lastName();

    public static String MAIL = "tester1.test@mail.test";

    public static String NAME_CATEGORY_SPORTS = "ВИДЫ СПОРТА";
    public static String NAME_SECTION_DARTS = "Дартс";
    public static String NAME_SECTION_YOGA = "Йога";
    public static String NAME_SUBSECTION_DART = "Дротики";
    public static String NAME_SUBSECTION_ACCESSORIES = "Аксессуары";
    public static String TITLE_COMPARE = "Сравнение товаров";
    public static String TITLE_BASKET = "Корзина";
    public static String TEXT_COMPARE_EMPTY = "Нет товаров для сравнения";
    public static String TEXT_BASKET_EMPTY = "В вашей корзине пусто";
    public static String AUTH_CODE_ERROR_MESSAGE = "Некорректный код подтверждения";
    public static String ERROR_MESSAGE_ON_BASKET = "Неверный код";
    public static Integer ITEM_QUANTITY = 3;

}
