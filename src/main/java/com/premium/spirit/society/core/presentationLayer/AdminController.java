package com.premium.spirit.society.core.presentationLayer;

import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.businessLayer.service.AuthorizationChecker;
import com.premium.spirit.society.core.businessLayer.service.ProductCategoryService;
import com.premium.spirit.society.core.businessLayer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Martin on 2. 1. 2015.
 */
@Controller
public class AdminController {

    private final UserService userService;
    private final ProductCategoryService categoryService;
    private final AuthorizationChecker authorizationChecker;

    @Autowired
    public AdminController(UserService userService, ProductCategoryService categoryService, AuthorizationChecker authorizationChecker) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.authorizationChecker = authorizationChecker;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminGET(Model model, HttpServletRequest request) {
        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        return "adminView";
    }


    @RequestMapping(value = "/admin/createUser", method = RequestMethod.GET)
    public String createUserGET(Model model, HttpServletRequest request) {
        model.addAttribute("user", new UserFormBO());
        return "admin/userCreateView";
    }

    @RequestMapping(value = "/admin/customers", method = RequestMethod.GET)
    public String customersGET(
            @RequestParam(value = "maxResults", defaultValue = "20", required = false) int maxResults,
            Model model, HttpServletRequest request) {
        model.addAttribute("maxResult", maxResults);
        return "admin/customerListView";
    }
}
