package ru.netology.test;

import com.codeborne.selenide.Configuration;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class IBankTesting {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void replenishmentOfTheFirstCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var authInfo = getAuthInfo();
        var verificationCode = getVerificationCodeFor(authInfo);
        var cardNumber = getCardNumberSecond();
        new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode);
        DashboardPage page = new DashboardPage();

        int currentBalanceFirstCard = page.getCardBalance(0);
        int currentBalanceSecondCard = page.getCardBalance(1);
        new DashboardPage()
                .topUpBalanceFirstCard()
                .cardReplenishmentFirst(cardNumber, "1000");

        int amount = 1000;
        int expected = currentBalanceFirstCard + amount;
        int actual = page.getCardBalance(0);
        Assertions.assertEquals(expected, actual);
        int expected2 = currentBalanceSecondCard - amount;
        int actual2 = page.getCardBalance(1);
        Assertions.assertEquals(expected2, actual2);
    }


    @Test
    void replenishmentOfTheSecondCard() {


        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        var authInfo = getAuthInfo();
        var verificationCode = getVerificationCodeFor(authInfo);
        var cardNumber = getCardNumberFirst();


        new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode);
        DashboardPage page = new DashboardPage();

        int currentBalanceFirstCard = page.getCardBalance(0);
        int currentBalanceSecondCard = page.getCardBalance(1);
        new DashboardPage()
                .topUpBalanceSecondCard()
                .cardReplenishmentSecond(cardNumber, "2000")
                .upDate();
        int amount = 2000;
        int expected = currentBalanceFirstCard - amount;
        int actual = page.getCardBalance(0);
        Assertions.assertEquals(expected, actual);
        int expected2 = currentBalanceSecondCard + amount;
        int actual2 = page.getCardBalance(1);
        Assertions.assertEquals(expected2, actual2);
    }


    @Test
    void replenishmentOverLimit() {


        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        var authInfo = getAuthInfo();
        var verificationCode = getVerificationCodeFor(authInfo);
        var cardNumber = getCardNumberSecond();


        new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode);
        DashboardPage page = new DashboardPage();

        int currentBalanceFirstCard = page.getCardBalance(0);
        int currentBalanceSecondCard = page.getCardBalance(1);
        new DashboardPage()
                .topUpBalanceFirstCard()
                .cardReplenishmentFirst(cardNumber, "100000")
                .upDate();
        int amount = 100000;
        int expected = currentBalanceFirstCard;
        int actual = page.getCardBalance(0);
        Assertions.assertEquals(expected, actual);
        int expected2 = currentBalanceSecondCard;
        int actual2 = page.getCardBalance(1);
        Assertions.assertEquals(expected2, actual2);
    }


}




