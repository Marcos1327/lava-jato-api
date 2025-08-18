package com.lava_jato.util;

import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ValidationException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
        if(value instanceof Boolean) return false;
        if (value instanceof String) return ((String) value).trim().isEmpty();
        if(value instanceof Collection) return ((Collection<?>)value).isEmpty();
        return false;
    }

    public static void validarCamposObrigatorios(Map<String, Object> campos){
        List<String> camposObrigatorios = new ArrayList<>();

        for (Map.Entry<String, Object> entry : campos.entrySet()) {
            String nomeCampo = entry.getKey();
            Object valor = entry.getValue();

            if(isNullOrEmpty(valor)){
                camposObrigatorios.add(nomeCampo);
            }
        }
        if(!camposObrigatorios.isEmpty()){
            throw new ValidationException("Os seguintes campos são obrigatórios: " + String.join(", ", camposObrigatorios));
        }
    }
}
