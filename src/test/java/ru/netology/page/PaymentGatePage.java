package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentGatePage {

    //Описываем элементы страницы(поля, всплывающие сообщения, сообщения об ошибках)

    private SelenideElement heading = $$(".heading").find(exactText("Оплата по карте "));
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");//parent() – ищет только предка на уровень выше
    // Вариант 2 .private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $(byText("Месяц")).parent().$(".input__control");
    // Вариант 2 .private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement yearField = $(byText("Год")).parent().$(".input__control");
    private SelenideElement holderField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvcField = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement invalidFormatMessage = $(byText("Неверный формат"));
    private SelenideElement cardExpiredMessage = $(byText("Истёк срок действия карты"));
    private SelenideElement validityPeriodIncorrectlyMessage = $(byText("Неверно указан срок действия карты"));
    private SelenideElement fieldRequiredFillMessage = $(byText("Поле обязательно для заполнения"));
    private SelenideElement successfulMessage = $(byText("Операция одобрена Банком."));
    private SelenideElement errorMessage = $(byText("Ошибка! Банк отказал в проведении операции."));


    //Описываем способы взаимодействия с элементами страницы
    public PaymentGatePage() {

        heading.shouldBe(visible);
    }

    public void invalidFormat() {
        invalidFormatMessage.shouldBe(visible);
    }

    public void cardExpired() {
        cardExpiredMessage.shouldBe(visible);
    }

    public void fieldRequiredFill() {
        fieldRequiredFillMessage.shouldBe(visible);
    }

    public void validityPeriodIncorrectly() {
        validityPeriodIncorrectlyMessage.shouldBe(visible);
    }

    //Проверяем видимость всплывающих сообщений(Одобрено/Отказ)
    public void successfulMessageVisibility() {
        successfulMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorMessageVisibility() {
        errorMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    //Заполняем поля карты
    public void FillFormFields(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        holderField.setValue(cardInfo.getHolder());
        cvcField.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    //Очищаем поля карты
    public void cleanField() {
        cardNumberField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        monthField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        yearField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        holderField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        cvcField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }


}
