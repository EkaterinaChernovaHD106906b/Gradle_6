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
        var cardNumber = getCardNumber();
        new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode)
                .topUpBalanceFirstCard()
                .cardReplenishment(cardNumber, "1000");
        DashboardPage page = new DashboardPage();
        int expected = 11000;
        int actual = page.getCardBalance(0);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void replenishmentOfTheSecondCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var authInfo = getAuthInfo();
        var verificationCode = getVerificationCodeFor(authInfo);
        var cardNumber = getCardNumber();
        new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode)
                .topUpBalanceSecondCard()
                .cardReplenishment(cardNumber, "2000");
        DashboardPage page = new DashboardPage();
        int expected = 12000;
        int actual = page.getCardBalance(1);
        Assertions.assertEquals(actual, expected);


    }



    }




