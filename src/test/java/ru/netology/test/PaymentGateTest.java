package ru.netology.test;


import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentGateTest {


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open(System.getProperty("sut.url"));
        SQLHelper.cleanDataBase();
    }

    @DisplayName("1.Отправка формы с валидными данными, где указана карта со статусом APPROVED")
    @Test
//Тест 1
    void shouldFillCardFormWithStatusApprovedValidData() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getNumberStatusApproved());
        paymentGate.successfulMessageVisibility();
        var statusExpected = "APPROVED";
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(statusExpected, statusActual);

    }

    @DisplayName("2.Отправка формы с валидными данными, где указана карта со статусом DECLINED")
    @Test
//Тест 2
    void shouldFillCardFormWithStatusDeclinedValidData() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getNumberStatusDeclined());
        paymentGate.errorMessageVisibility();
        var statusExpected = "DECLINED";
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(statusExpected, statusActual);

    }

    @DisplayName("3.Отправка формы, при заполнении поля Номер карты 15 цифрами. Остальные поля заполнены валидными данными.")
    @Test
//Тест 3
    void shouldCardNumberWith15DigitFieldValidData() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getNumber15());
        paymentGate.invalidFormat();
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(null, statusActual);
    }

    @DisplayName("4.Отправка формы с пустым полем Месяц. Остальные поля заполнены валидными данными.")
    @Test
//Тест 4
    void shouldFormWithEmptyMonthField() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getEmptyMonth());
        paymentGate.invalidFormat();
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(null, statusActual);
    }

    @DisplayName("5.Отправка формы с пустым полем Год. Остальные поля заполнены валидными данными.")
    @Test
//Тест 5
    void shouldFormWithEmptyYearField() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getEmptyYear());
        paymentGate.invalidFormat();
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(null, statusActual);
    }

    @DisplayName("6.Отправка формы с пустым полем Владелец. Остальные поля заполнены валидными данными.")
    @Test
//Тест 6
    void shouldFormWithEmptyHolderField() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getEmptyHolder());
        paymentGate.fieldRequiredFill();
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(null, statusActual);
    }

    @DisplayName("7.Отправка формы с пустым полем CVC/CVV. Остальные поля заполнены валидными данными.")
    @Test
//Тест 7
    void shouldFormWithEmptyCVCField() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getEmptyCvc());
        paymentGate.invalidFormat();
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(null, statusActual);
    }

    @DisplayName("8.Отправка формы, при заполнении поля Месяц числом больше 12. Остальные поля заполнены валидными данными.")
    @Test
//Тест 8
    void shouldFillingMonthFieldWithNumberGreaterThan12() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getMonthMore12());
        paymentGate.validityPeriodIncorrectly();
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(null, statusActual);
    }

    @DisplayName("9.Отправка формы со всеми пустыми полями")
    @Test
//Тест 9
    void shouldAllEmptyFields() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getAllEmptyFields());
        paymentGate.invalidFormat();
        paymentGate.fieldRequiredFill();
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(null, statusActual);
    }

    @DisplayName("10.Отправка формы, при заполнении поля Год меньше текущего. Остальные поля заполнены валидными данными.")
    @Test
//Тест 10
    void shouldFillInYearLessThanCurrentOne() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getYearLessThanCurrentOne());
        paymentGate.cardExpired();
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(null, statusActual);
    }

    @DisplayName("11.Отправка формы, при заполнении поля Владелец только одним словом на латинице. Остальные поля заполнены валидными данными.")
    @Test
//Тест 11
    void shouldOwnerFieldIsFilledInWithOneWordInLatin() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getOneWord());
        paymentGate.invalidFormat();
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(null, statusActual);
    }

    @DisplayName("12.Отправка формы, при заполнении поля Владелец кириллицей. Остальные поля заполнены валидными данными.")
    @Test
//Тест 12
    void shouldHolderInCyrillic() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getHolderInCyrillic());
        paymentGate.invalidFormat();
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(null, statusActual);
    }

    @DisplayName("13.Отправка формы, при заполнении поля Владелец цифрами. Остальные поля заполнены валидными данными.")
    @Test
//Тест 13
    void shouldHolderInNumbers() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getHolderInNumbers());
        paymentGate.invalidFormat();
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(null, statusActual);
    }

    @DisplayName("14.Отправка формы, при заполнении поля Владелец спецсимволами. Остальные поля заполнены валидными данными.")
    @Test
//Тест 14
    void shouldHolderOfSpecialCharacters() {
        var dashboardPage = new DashboardPage();
        var paymentGate = dashboardPage.paymentGatePage();
        paymentGate.cleanField();
        paymentGate.FillFormFields(DataHelper.getHolderOfSpecialCharacters());
        paymentGate.invalidFormat();
        var statusActual = SQLHelper.getPaymentGateStatus();
        assertEquals(null, statusActual);
    }
}
