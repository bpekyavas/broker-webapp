package com.mse.brokerwebapp.infrastructure.rest;

import com.mse.brokerwebapp.application.controller.LoginController;
import com.mse.brokerwebapp.application.model.request.RegisterRequest;
import com.mse.brokerwebapp.domain.service.RegistrationService;
import com.mse.brokerwebapp.domain.validator.RegistrationValidator;
import com.mse.brokerwebapp.domain.vo.CurrentUser;
import com.mse.brokerwebapp.domain.vo.enumtype.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class RestLoginController implements LoginController {

    private RegistrationValidator registrationValidator;
    private RegistrationService registrationService;

    public RestLoginController(RegistrationValidator registrationValidator, RegistrationService registrationService) {
        this.registrationValidator = registrationValidator;
        this.registrationService = registrationService;
    }

    @RequestMapping("/user")
    public Map<String, Object> retrieveUserDetails(Principal user) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", user.getName());
        map.put("roles", AuthorityUtils.authorityListToSet(((Authentication) user)
                .getAuthorities()));
        if(((Authentication) user).getPrincipal() instanceof CurrentUser
                && ((CurrentUser) ((Authentication) user).getPrincipal()).getUserType().equals(UserType.TRADER))
        {
            map.put("traderId", ((CurrentUser) ((Authentication) user).getPrincipal()).getId());
        }

        return map;
    }

    @Override
    @PostMapping("/api/v1/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest registerRequest) {
        registrationValidator.validate(registerRequest);
        registrationService.register(registerRequest);
    }
}
