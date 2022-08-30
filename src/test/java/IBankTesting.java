import com.codeborne.selenide.Configuration;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class IBankTesting {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void replenishmentOfTheFirstCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var cardNumber = DataHelper.getCardNumber();
        new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode)
                .topUpBalanceFirstCard()
                .cardReplenishment(cardNumber, "1000");
    }

    @Test
    void replenishmentOfTheSecondCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var cardNumber = DataHelper.getCardNumber();
        new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode)
                .topUpBalanceSecondCard()
                .cardReplenishment(cardNumber, "2000");
        DashboardPage page = new DashboardPage();
        int expected = 30000;
        int actual = page.getCardBalance(1);
        Assertions.assertEquals(actual, expected);


    }


}

