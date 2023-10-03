package ru.netology.data;


import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.Value;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataHelper {

    private DataHelper() {
    }

    private static final Faker fakerEN = new Faker(new Locale("en"));


    @Step("Генератор данных в поле Месяц")
    public static String getMonth() {
        String Month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        return Month;
    }

    @Step("Генератор данных в поле Год")
    public static String getYear() {
        String Year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        return Year;
    }

    @Step("Генератор невалидный в поле Год ,который меньше текущего на 1")
    public static String getPreviousYear() {
        String PreviousYear = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return PreviousYear;
    }

    @Step("Генератор данных в поле Владелец")
    public static String getHolder() {
        return fakerEN.name().lastName() + " " + fakerEN.name().firstName();
    }

    @Step("Генератор данных в поле CVC/CVV 3 цифры")
    public static String getCvc() {
        return fakerEN.number().digits(3);
    }

    @Step("+1. Карта со статусом  Approved")
    public static CardInfo getNumberStatusApproved() {
        return new CardInfo("4444 4444 4444 4441", getMonth(), getYear(), getHolder(), getCvc());

    }

    @Step("+2. Карта со статусом Declined")
    public static CardInfo getNumberStatusDeclined() {
        return new CardInfo("4444 4444 4444 4442", getMonth(), getYear(), getHolder(), getCvc());

    }

    @Step("-3. Форма ,где номером карты с 15 цифрами")
    public static CardInfo getNumber15() {
        return new CardInfo(" 4444 4444 4444 444_", getMonth(), getYear(), getHolder(), getCvc());

    }

    @Step("-4. Форма с пустым полем Месяц")
    public static CardInfo getEmptyMonth() {
        return new CardInfo("4444 4444 4444 4441", "", getYear(), getHolder(), getCvc());

    }

    @Step("-5. Форма с пустым полем Год")
    public static CardInfo getEmptyYear() {
        return new CardInfo("4444 4444 4444 4441", getMonth(), "", getHolder(), getCvc());

    }

    @Step("-6. Форма с пустым полем Владелец")
    public static CardInfo getEmptyHolder() {
        return new CardInfo("4444 4444 4444 4441", getMonth(), getYear(), "", getCvc());

    }

    @Step("-7. Форма с пустым полем CVC/CVV")
    public static CardInfo getEmptyCvc() {
        return new CardInfo("4444 4444 4444 4441", getMonth(), getYear(), getHolder(), "");

    }

    @Step("-8.Отправка формы, при заполнении поля Месяц числом больше 12. Остальные поля заполнены валидными данными")
    public static CardInfo getMonthMore12() {
        return new CardInfo("4444 4444 4444 4441", "13", getYear(), getHolder(), getCvc());

    }

    @Step("-9.Отправка формы со всеми пустыми полями")
    public static CardInfo getAllEmptyFields() {
        return new CardInfo("", "", "", "", "");

    }

    @Step("-10.Отправка формы, при заполнении поля Год меньше текущего. Остальные поля заполнены валидными данными")
    public static CardInfo getYearLessThanCurrentOne() {
        return new CardInfo("4444 4444 4444 4441", getMonth(), getPreviousYear(), getHolder(), getCvc());

    }

    @Step("-11.Отправка формы, при заполнении поля Владелец только одним словом на латинице. Остальные поля заполнены валидными данными.")
    public static CardInfo getOneWord() {
        return new CardInfo("4444 4444 4444 4441", getMonth(), getYear(), " Anton", getCvc());

    }

    @Step("-12.Отправка формы, при заполнении поля Владелец кириллицей. Остальные поля заполнены валидными данными.")
    public static CardInfo getHolderInCyrillic() {
        return new CardInfo("4444 4444 4444 4441", getMonth(), getYear(), "Антон Антонов", getCvc());

    }

    @Step("-13.Отправка формы, при заполнении поля Владелец цифрами. Остальные поля заполнены валидными данными.")
    public static CardInfo getHolderInNumbers() {
        return new CardInfo("4444 4444 4444 4441", getMonth(), getYear(), "123456789", getCvc());

    }

    @Step("-14.Отправка формы, при заполнении поля Владелец спецсимволами. Остальные поля заполнены валидными данными.")
    public static CardInfo getHolderOfSpecialCharacters() {
        return new CardInfo("4444 4444 4444 4441", getMonth(), getYear(), "!!!", getCvc());

    }


    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

}