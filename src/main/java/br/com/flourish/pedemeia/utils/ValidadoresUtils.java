package br.com.flourish.pedemeia.utils;

import org.apache.commons.validator.routines.EmailValidator;

public final class ValidadoresUtils {
    public static boolean validarEmail(String email) {

        EmailValidator emailAddr = EmailValidator.getInstance();
        return emailAddr.isValid(email);
    }
}
