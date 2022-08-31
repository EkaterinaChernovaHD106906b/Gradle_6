package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TopUpPage {

    private SelenideElement sum = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");

    private SelenideElement topUp = $("[data-test-id='action-transfer']");


    public DashboardPage cardReplenishment(DataHelper.CardNumber cardNumber, String amount) {
        sum.setValue(amount);
        from.setValue(cardNumber.getNumber());
        topUp.click();
        return new DashboardPage();
    }
}
