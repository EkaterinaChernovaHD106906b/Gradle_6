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
    public static class CardNumberFirst {
        private String numberFirstCard;

    }

    @Value
    public static class CardNumberSecond {
        private String numberSecondCard;

    }


    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");

    }

    public static CardNumberFirst getCardNumberFirst() {
        return new CardNumberFirst("5559 0000 0000 0001");
    }

    public static CardNumberSecond getCardNumberSecond() {
        return new CardNumberSecond("5559 0000 0000 0002");
    }


    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
}
