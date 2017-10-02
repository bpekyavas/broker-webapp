package com.mse.brokerwebapp.application.controller;

import com.mse.brokerwebapp.application.model.request.RegisterRequest;

import java.security.Principal;
import java.util.Map;

public interface LoginController {

    Map<String, Object> retrieveUserDetails(Principal user);

    void register(RegisterRequest registerRequest);
}
