package com.lava_jato.util;

import com.lava_jato.exceptions.handlers.BusinessException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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
}
