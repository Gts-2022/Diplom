package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.SQLHelper;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.ApiHelper.requestApi;
import static ru.netology.data.DataHelper.getNumberStatusApproved;
import static ru.netology.data.DataHelper.getNumberStatusDeclined;

public class ApiTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @BeforeEach
    public void cleanBase() {
        SQLHelper.cleanDataBase();
    }

    @Test
        //Запрос "Купить" в Базу данных карты со статусом Approved.ОР 200
    void shouldRequestWithValidDataDebitCardStatusApproved() {
        var apiValidCardApproved = getNumberStatusApproved();
        var response = requestApi(apiValidCardApproved, "/api/v1/pay");
        assertTrue(response.contains(SQLHelper.getPaymentGateStatus()), "Значение статуса в ответе не соответствует ожидаемому");

    }

    @Test
        //Запрос "Купить" в Базу данных карты со статусом Declined.ОР 200
    void shouldRequestWithValidDataDebitCardStatusDeclined() {
        var apiValidCardDeclined = getNumberStatusDeclined();
        var response = requestApi(apiValidCardDeclined, "/api/v1/pay");
        assertTrue(response.contains(SQLHelper.getPaymentGateStatus()), "Значение статуса в ответе не соответствует ожидаемому");

    }

    @Test
        //Запрос "Купить в кредит" в Базу данных карты со статусом Approved.ОР 200
    void shouldRequestWithValidDataCreditCardStatusApproved() {
        var apiValidCardApproved = getNumberStatusApproved();
        var response = requestApi(apiValidCardApproved, "/api/v1/credit");
        assertTrue(response.contains(SQLHelper.getCreditGateStatus()), "Значение статуса в ответе не соответствует ожидаемому");

    }

    @Test
        //Запрос "Купить в кредит" в Базу данных карты со статусом Declined.ОР 200.
    void shouldRequestWithValidDataCreditCardStatusDeclined() {
        var apiValidCardDeclined = getNumberStatusDeclined();
        var response = requestApi(apiValidCardDeclined, "/api/v1/credit");
        assertTrue(response.contains(SQLHelper.getCreditGateStatus()), "Значение статуса в ответе не соответствует ожидаемому");

    }
}