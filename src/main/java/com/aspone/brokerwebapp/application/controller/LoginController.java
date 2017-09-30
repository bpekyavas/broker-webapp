package com.aspone.brokerwebapp.application.controller;

import java.security.Principal;
import java.util.Map;

public interface LoginController {

    Map<String, Object> retrieveUserDetails(Principal user);

}
