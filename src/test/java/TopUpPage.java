import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TopUpPage {
    private SelenideElement sum = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");

    private SelenideElement topUp = $("[data-test-id='action-transfer']");



    public DashboardPage cardReplenishment() {
        sum.setValue("1000");
        from.setValue("5559 0000 0000 0001");
        topUp.click();
        return new DashboardPage();
    }
}
