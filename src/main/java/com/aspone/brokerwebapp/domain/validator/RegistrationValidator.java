package com.aspone.brokerwebapp.domain.validator;

import com.aspone.brokerwebapp.application.model.request.RegisterRequest;
import com.aspone.brokerwebapp.domain.exception.StringValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RegistrationValidator {

    public void validate(RegisterRequest registerRequest) {
        validateUserName(registerRequest.getUserName());
        validatePassword(registerRequest.getPassword());
        validateName(registerRequest.getName());
    }

    private void validateUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            throw new StringValidationException("Username can not be empty!");
        }
        if (StringUtils.containsWhitespace(userName)) {
            throw new StringValidationException("Username can not contain whitespace!");
        }
        if (userName.length() < 4
                || userName.length() > 20) {
            throw new StringValidationException("Username length must be between 4-20 characters!");
        }
    }

    private void validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            throw new StringValidationException("Password can not be empty!");
        }
        if (StringUtils.containsWhitespace(password)) {
            throw new StringValidationException("Password can not contain whitespace!");
        }
        if (password.length() < 6) {
            throw new StringValidationException("Password length can not be less than 6 characters!");
        }
    }

    private void validateName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new StringValidationException("Name can not be empty!");
        }
        if (name.length() < 4) {
            throw new StringValidationException("Name length can not be less than 4 characters!");
        }
    }
}
