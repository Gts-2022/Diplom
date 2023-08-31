package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    //Описываем элементы страницы
    private SelenideElement heading = $$(".heading").find(exactText("Путешествие дня"));
    private SelenideElement paymentButton = $$(".button__text").find(exactText("Купить"));
    private SelenideElement creditButton = $$(".button__content").find(exactText("Купить в кредит"));

    //Описываем способы взаимодействия с элементами страницы
    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public PaymentGatePage paymentGatePage() {
        paymentButton.click();
        return new PaymentGatePage();
    }

    public CreditGatePage creditGatePage() {
        creditButton.click();
        return new CreditGatePage();
    }
}
