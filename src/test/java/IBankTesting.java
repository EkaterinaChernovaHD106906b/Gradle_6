import com.codeborne.selenide.Configuration;
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
    void successfulLogin() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        new LoginPage()
                .validLogin(authInfo);
        new VerificationPage()
                .validVerify(verificationCode);


    }

    @Test
    void replenishmentOfTheFirstCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        new LoginPage()
                .validLogin(authInfo);
        new VerificationPage()
                .validVerify(verificationCode);
        new DashboardPage()
                .topUpBalanceFirstCard();
       new TopUpPage()
                .cardReplenishment();
    }

    @Test
    void  replenishmentOfTheSecondCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        new LoginPage()
                .validLogin(authInfo);
        new VerificationPage()
                .validVerify(verificationCode);
        new DashboardPage()
                .topUpBalanceSecondCard();
        new TopUpPage()
                .cardReplenishment();
    }


}

