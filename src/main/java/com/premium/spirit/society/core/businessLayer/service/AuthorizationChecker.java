package com.premium.spirit.society.core.businessLayer.service;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizationChecker {

    public boolean checkAuthorization(HttpServletRequest request);

    public boolean onlyResearcher();

    public boolean isAdmin();

    public boolean isUserFromUrl(String username);

    public boolean isSuperDoctor();
}
