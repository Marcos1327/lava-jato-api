package com.lava_jato.util;

import com.lava_jato.exceptions.handlers.BusinessException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class Util {

    public static boolean isNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }

    public static void validarEmail(String email){
        EmailValidator emailValidator = EmailValidator.getInstance();
        if(!emailValidator.isValid(email)){
            throw new BusinessException("E-mail invalido");
        }
    }

    public static boolean isNullOrEmpty(Boolean temporario) {
        return temporario == null;
    }

    public static boolean isNullOrEmpty(Object value) {
        if (value == null) return true;
        if (value instanceof String) return ((String) value).trim().isEmpty();
        return false;
    }
}
