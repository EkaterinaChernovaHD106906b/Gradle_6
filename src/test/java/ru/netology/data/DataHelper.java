package ru.netology.data;

import lombok.Value;


public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class CardNumber {
        private String number;
    }


    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");

    }

    public static CardNumber getCardNumber() {
        return new CardNumber("5559 0000 0000 0001");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
}
