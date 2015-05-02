package com.premium.spirit.society.core.presentationLayer;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.UserDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormWrapperBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.businessLayer.service.*;
import com.premium.spirit.society.core.dataLayer.entity.ContactEntity;
import com.premium.spirit.society.core.dataLayer.entity.OrderEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import com.premium.spirit.society.core.util.JSONEncoder;
import com.premium.spirit.society.core.util.PictureLoader;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by Martin on 27. 12. 2014.
 */

@Controller
@SessionAttributes({"user"})
public class UserController {

    private final UserService userService;

    private final AuthorizationChecker authorizationChecker;
    private final MailService mailService;
    private final OrderService orderService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final UserService userSerivce;

    @Autowired
    public UserController(UserService userService, AuthorizationChecker authorizationChecker, MailService mailService, OrderService orderService, ProductService productService, ProductCategoryService productCategoryService, UserService userSerivce) {
        this.userService = userService;
        this.authorizationChecker = authorizationChecker;
        this.mailService = mailService;
        this.orderService = orderService;
        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.userSerivce = userSerivce;
    }

    @RequestMapping(value = "/user/users", method = RequestMethod.GET)
    public String usersGET(Model model, HttpServletRequest request) {
        model.addAttribute("userList", userService.getAll(UserFormBO.class, UserEntity.class));
        return "user/userListView";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userGET(Model model, HttpServletRequest request) {
        return "user/userView";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGET(Model model, HttpServletRequest request) {

        model.addAttribute("uniqueUsername", true);
        model.addAttribute("uniqueEmail", true);
        model.addAttribute("user", new UserFormBO());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(
            @ModelAttribute("user") @Valid UserFormBO user,
            BindingResult result, Locale locale, Model model, HttpServletRequest request) {

        boolean uniqueUsername = userService.isUniqueUsername(user);
        boolean uniqueEmail = true;

        if (!user.getContact().getEmail().equals("")) {
            uniqueEmail = userService.isUniqueEmail(user);
        }

        if (result.hasErrors() || !uniqueUsername || !uniqueEmail) {
            model.addAttribute("uniqueUsername", uniqueUsername);
            model.addAttribute("uniqueEmail", uniqueEmail);
            return "signup";
        } else {
            user.setCustomer(true);
            user.setEnabled(true);
            userService.create(user, locale);

            // autologin
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            model.addAttribute("categories", productCategoryService.getAllUnhidden());
            List<ProductDisplayBO> promotedProducts = productService.getPromoted();
            List<String> promotionTextList = new ArrayList<>();
            List<String> pictureList = new ArrayList<>();
            List<String> urlList = new ArrayList<>();
            List<String> promotionHeaderList = new ArrayList<>();
            for (ProductDisplayBO product : promotedProducts) {
                pictureList.add(new PictureLoader(product).loadPictures().get(0));
                promotionTextList.add(product.getPromotionText());
                urlList.add(product.getProductSubcategory().getProductCategory().getUrl() + "/" + product.getProductSubcategory().getUrl() + "/" + product.getUrl());
                promotionHeaderList.add(product.getPromotionHeader());
            }
            model.addAttribute("promotionTextList", promotionTextList);
            model.addAttribute("pictureList", pictureList);
            model.addAttribute("urlList", urlList);
            model.addAttribute("promotionHeaderList", promotionHeaderList);
            return "homeView";
        }
    }

    /**
     * Handles the request to access page with user's overview.
     *
     * @param username the id of a user for whom the overview is to be rendered.
     * @param model    the model to be filled for view.
     * @return the string of a view to be rendered.
     */
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public String userOverviewGET(@PathVariable("username") String username,
                                  Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }

        model.addAttribute("user", userService.getUserByUsername(username));
        return "user/overviewView";
    }

    @RequestMapping(value = "/user/{username}/edit", method = RequestMethod.GET)
    public String userEditGET(@PathVariable("username") String username,
                              Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        } else {

            model.addAttribute("user", userService.getUserByUsername(username));
            model.addAttribute("uniqueUsername", true);
            model.addAttribute("uniqueEmail", true);
            model.addAttribute("isAdmin", authorizationChecker.isAdmin());
            return "user/editView";
        }
    }

    @RequestMapping(value = "/user/{username}/edit", method = RequestMethod.POST)
    public String userEditPOST(@ModelAttribute("user") @Valid UserFormBO user, BindingResult result,
                               @PathVariable("username") String username,
                               Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        } else {

            boolean uniqueUsername = userService.isUniqueUsername(user);
            boolean uniqueEmail = userService.isUniqueEmail(user);

            if (result.hasErrors() || !uniqueUsername || !uniqueEmail) {
                model.addAttribute("uniqueUsername", uniqueUsername);
                model.addAttribute("uniqueEmail", uniqueEmail);
                model.addAttribute("isAdmin", authorizationChecker.isAdmin());
                return "user/editView";
            } else {
                userService.update(user, UserEntity.class);
            }
            if (!authorizationChecker.isAdmin()) {
                return "redirect:/profile";
            } else {
                return "redirect:/user/" + user.getUsername();
            }
        }
    }


    /**
     * Handles the request to access page where user's password is changed.
     *
     * @param username the id of a user whose password is edited.
     * @param model    the model to be filled for view.
     * @return the string of a view to be rendered.
     */
    @RequestMapping(value = "/user/{username}/change-password", method = RequestMethod.GET)
    public String userChangePasswordGET(@PathVariable("username") String username,
                                        Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        } else {
            UserFormBO user = userService.getUserByUsername(username);
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("samePasswords", true);
            model.addAttribute("isAdmin", authorizationChecker.isAdmin());

            return "user/changePassword";
        }
    }

    /**
     * @param user     the user whose password was edited in form at front-end. It is grabbed
     *                 from POST string and validated.
     * @param result   the result of binding form from front-end to an UserEntity. It
     *                 is used to determine if there were some errors during binding.
     * @param username the name  of a user whose password is edited.
     * @param locale   the user's locale.
     * @param model    the model to be filled for view.
     * @return the string of a view to be rendered if the binding has errors,
     * otherwise, the string of an address to which the user will be
     * redirected.
     */
    @RequestMapping(value = "/user/{username}/change-password", method = RequestMethod.POST)
    public String userChangePasswordPOST(
            @ModelAttribute("user") @Valid UserFormBO user, BindingResult result,
            @PathVariable("username") String username,
            @RequestParam("passwordAgain") String passwordAgain,
            Locale locale, Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        } else if (result.hasErrors() || !user.getPassword().equals(passwordAgain)) {
            if (!user.getPassword().equals(passwordAgain)) {
                model.addAttribute("samePasswords", false);
                model.addAttribute("isAdmin", authorizationChecker.isAdmin());
            }
            return "user/changePassword";
        } else {
            userService.changePassword(user);
            mailService.notifyChangedPassword(user, passwordAgain, locale);
            if (!authorizationChecker.isAdmin()) {
                return "redirect:/profile";
            } else {
                return "redirect:/user/" + username;
            }
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profileGET(Model model, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        model.addAttribute("user", userService.getUserByUsername(auth.getName()));
        return "user/profileView";
    }



    @RequestMapping(value = "/reset-password", method = RequestMethod.GET)
    public String resetPasswordGET(Model model, HttpServletRequest request) {
        model.addAttribute("contact", new ContactEntity());
        return "/user/resetPasswordView";
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public String resetPasswordPOST(@ModelAttribute("contact") ContactEntity contact, Model model, Locale locale,HttpServletRequest request) {
        UserFormBO user = userService.getUserByEmail(contact.getEmail().toString());
        if (user==null)
        {
            model.addAttribute("mailInvalid", true);
            return "/user/resetPasswordView";
        }
        else {
            String password = RandomStringUtils.randomAlphanumeric(10);
            user.setPassword(password);
            userService.changePassword(user);
            mailService.notifyChangedPassword(user, password, locale);
        }

        model.addAttribute("categories", productCategoryService.getAllUnhidden());
        List<ProductDisplayBO> promotedProducts = productService.getPromoted();
        List<String> promotionTextList = new ArrayList<>();
        List<String> pictureList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        List<String> promotionHeaderList = new ArrayList<>();
        for (ProductDisplayBO product : promotedProducts) {
            pictureList.add(new PictureLoader(product).loadPictures().get(0));
            promotionTextList.add(product.getPromotionText());
            urlList.add(product.getProductSubcategory().getProductCategory().getUrl() + "/" + product.getProductSubcategory().getUrl() + "/" + product.getUrl());
            promotionHeaderList.add(product.getPromotionHeader());
        }
        model.addAttribute("promotionTextList", promotionTextList);
        model.addAttribute("pictureList", pictureList);
        model.addAttribute("urlList", urlList);
        model.addAttribute("promotionHeaderList", promotionHeaderList);
        return "/user/passwordReseted";
    }


    @RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
    public String profileEditGET(Model model, HttpServletRequest request) {


        Authentication auth = SecurityContextHolder.getContext()

                .getAuthentication();
        String username = auth.getName();

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";

        } else {
            model.addAttribute("user", userService.getUserByUsername(username));
            model.addAttribute("uniqueUsername", true);
            model.addAttribute("uniqueEmail", true);
            model.addAttribute("isAdmin", authorizationChecker.isAdmin());
            return "user/profileEditView";
        }
    }


    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public String profileEditPost(@ModelAttribute("user") @Valid UserFormBO user, BindingResult result,
                                  Model model, HttpServletRequest request) {


        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String username = auth.getName();


        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        } else {

            boolean uniqueUsername = userService.isUniqueUsername(user.getId(), user.getUsername());
            boolean uniqueEmail = userService.isUniqueEmail(user.getId(), user.getContact().getEmail());

            if (result.hasErrors() || !uniqueUsername || !uniqueEmail) {
                model.addAttribute("uniqueUsername", uniqueUsername);
                model.addAttribute("uniqueEmail", uniqueEmail);
                model.addAttribute("isAdmin", authorizationChecker.isAdmin());
                return "user/profileEditView";
            } else {
                userService.update(user, UserEntity.class);
            }
            if (!authorizationChecker.isAdmin()) {
                return "redirect:/profile";
            } else {
                return "redirect:/user/" + user.getUsername();
            }
        }
    }

    @RequestMapping(value = {"/profile/orders", "users/orders", "user/{username}/orders"}, method = RequestMethod.GET)
    public String ordersGET(Model model, HttpServletRequest request) {
        UserFormBO user = null;
        List<List<ProductFormWrapperBO>> listOfProductFormWrappers = new ArrayList<>();
        List<ProductFormWrapperBO> productFormWrapperBOs = new ArrayList<>();

        String uri = request.getRequestURI();
        String[] tokens = uri.split("/");
        if (tokens.length == 4) {
            if (isInteger(tokens[3])) {
                user = userService.getById(Integer.parseInt(tokens[2]), UserFormBO.class, UserEntity.class);
            } else {
                Authentication auth = SecurityContextHolder.getContext()
                        .getAuthentication();
                String username = auth.getName();
                user = userService.getUserByUsername(username);
            }
        }
        if (uri.equals("/profile/orders")) {
            Authentication auth = SecurityContextHolder.getContext()
                    .getAuthentication();
            String username = auth.getName();
            user = userService.getUserByUsername(username);

        }
        if (uri.equals("/users/orders")) {
            List<OrderFormBO> orders = orderService.getAll(OrderFormBO.class, OrderEntity.class);

            // sort orders
            orders.sort(new Comparator<OrderFormBO>() {
                @Override
                public int compare(OrderFormBO o1, OrderFormBO o2) {
                    return o2.getId() - o1.getId();
                }
            });
            List<String> listOfInvoices = new ArrayList<>();
            for (OrderFormBO order : orders) {
                productFormWrapperBOs = new ArrayList<>();
                for (ProductFormBO productFormBO : order.getProducts()) {
                    boolean wrapperContainsCurrentProduct = false;
                    for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                        if (productFormWrapperBO.getId() == productFormBO.getId()) {
                            wrapperContainsCurrentProduct = true;
                            productFormWrapperBO.setOrderAmount(productFormWrapperBO.getOrderAmount() + 1);
                            break;
                        }

                    }
                    if (!wrapperContainsCurrentProduct) {
                        productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO,order));
                    }
                }
                listOfProductFormWrappers.add(productFormWrapperBOs);
                String invoice = orderService.getInvoiceBaseUrl(order.getUserID());
                invoice += System.getProperty("file.separator");
                invoice += order.getInvoice();
                invoice += ".pdf";
                listOfInvoices.add(invoice);
            }
            for (List<ProductFormWrapperBO> productFormWrapperBO : listOfProductFormWrappers) {
                productFormWrapperBO.sort(new Comparator<ProductFormWrapperBO>() {
                    @Override
                    public int compare(ProductFormWrapperBO o1, ProductFormWrapperBO o2) {
                        return o2.getOrderId() - o1.getOrderId();
                    }
                });
            }
            model.addAttribute("listOfInvoices", listOfInvoices);
            model.addAttribute("listOfProductFormWrappers", listOfProductFormWrappers);
            model.addAttribute("orders", orders);
            // TODO: presmerovavat na jinou adresu
            return "user/ordersView";
        }

        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String username = auth.getName();
        user = userService.getUserByUsername(username);
        if (!authorizationChecker.checkAuthorization(request)) {
            model.addAttribute("isAdmin", false);
        } else {
            model.addAttribute("isAdmin", true);
        }

        if (user != null) {

            // sort orders
            user.getOrders().sort(new Comparator<OrderFormBO>() {
                @Override
                public int compare(OrderFormBO o1, OrderFormBO o2) {
                    return o2.getId() - o1.getId();
                }
            });

            for (OrderFormBO order : user.getOrders()) {
                productFormWrapperBOs = new ArrayList<>();
                for (ProductFormBO productFormBO : order.getProducts()) {
                    boolean wrapperContainsCurrentProduct = false;
                    for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                        if (productFormWrapperBO.getId() == productFormBO.getId()) {
                            productFormWrapperBO.setOrderId(order.getId());
                            wrapperContainsCurrentProduct = true;
                            productFormWrapperBO.setOrderAmount(productFormWrapperBO.getOrderAmount() + 1);
                            break;
                        }

                    }
                    if (!wrapperContainsCurrentProduct) {
                        productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO,order));
                    }
                }
                listOfProductFormWrappers.add(productFormWrapperBOs);
            }


            for (List<ProductFormWrapperBO> productFormWrapperBO : listOfProductFormWrappers) {
                productFormWrapperBO.sort(new Comparator<ProductFormWrapperBO>() {
                    @Override
                    public int compare(ProductFormWrapperBO o1, ProductFormWrapperBO o2) {
                        return o2.getOrderId() - o1.getOrderId();
                    }
                });
            }


            String invoice = orderService.getInvoiceBaseUrl(user.getId());
            invoice += System.getProperty("file.separator");
            model.addAttribute("invoiceUrl", invoice);
            model.addAttribute("listOfProductFormWrappers", listOfProductFormWrappers);
            model.addAttribute("user", user);
            model.addAttribute("orders", user.getOrders());


        }

        return "user/ordersView";
    }


    @RequestMapping(value = "/invoices/{userId}/{invoiceUrl}", method = RequestMethod.GET)
    public String showInvoiceGET(@PathVariable("userId") int userId, @PathVariable("invoiceUrl") String invoiceUrl, HttpServletResponse response, HttpServletRequest request) throws IOException {
        FileInputStream fis = null;


        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String username = auth.getName();


        if ((username.equals(userSerivce.getById(userId, UserFormBO.class, UserEntity.class).getUsername()) || authorizationChecker.checkAuthorization(request))) {
            String invoice = orderService.getInvoiceBaseUrl(userId);
            invoice += System.getProperty("file.separator");
            invoice += invoiceUrl;
            invoice += ".pdf";

            try {
                fis = new FileInputStream(invoice);
                InputStream is = fis;
                org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
                return null;
            } catch (IOException ex) {
                throw new RuntimeException("IOError writing file to output stream");
            } finally {
                fis.close();
            }
        } else
            return "deniedView";
    }


    @RequestMapping(value = "/profile/change-password", method = RequestMethod.GET)
    public String profileChangePasswordGET(Model model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String username = auth.getName();

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";

        } else {
            UserFormBO user = userService.getUserByUsername(username);
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("samePasswords", true);
            model.addAttribute("isAdmin", authorizationChecker.isAdmin());
            return "user/profileChangePassword";
        }
    }


    @RequestMapping(value = "/profile/change-password", method = RequestMethod.POST)
    public String profileChangePasswordPOST(@ModelAttribute("user") @Valid UserFormBO user, BindingResult result,
                                            @RequestParam("passwordAgain") String passwordAgain,
                                            Locale locale, Model model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String username = auth.getName();

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        } else if (result.hasErrors() || !user.getPassword().equals(passwordAgain)) {
            if (!user.getPassword().equals(passwordAgain)) {
                model.addAttribute("samePasswords", false);
                model.addAttribute("isAdmin", authorizationChecker.isAdmin());
            }
            return "user/profileChangePassword";
        } else {
            userService.changePassword(user);
            mailService.notifyChangedPassword(user, passwordAgain, locale);
            if (!authorizationChecker.isAdmin()) {
                return "redirect:/profile";
            } else {
                return "redirect:/user/" + username;
            }
        }
    }


    @RequestMapping(value = "/user/{username}/hide", method = RequestMethod.GET)
    public String userHideGET(@PathVariable("username") String username, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        UserFormBO user = userService.getUserByUsername(username);
        if (user == null) {
            return "user/" + username + "/hide";
            // Neexistujici uzivatel
        }
        int userId = user.getId();
        userService.hide(userId);
        userService.revokeRoles(userId);
        return "redirect:/user/" + user.getUsername();
    }

    @RequestMapping(value = "/user/{username}/restore", method = RequestMethod.GET)
    public String userRecovereGET(@PathVariable("username") String username, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        UserFormBO user = userService.getUserByUsername(username);
        if (user == null) {
            return "user/" + username + "/restore";
            // Neexistujici uzivatel
        }
        int userId = user.getId();
        userService.restoreUser(userId);
        return "redirect:/user/" + user.getUsername();
    }


    @RequestMapping(value = "/user/list-search", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String patientsListSearchGET(
            @RequestParam("maxResults") int maxResults,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("search") String searchString, HttpServletRequest request) {

        int userCount = userService.getCountOfUnhiddenCustomers(searchString);
        List<UserDisplayBO> users = userService.getBySearchStringWithPagination(maxResults, pageNumber, searchString);
        JSONEncoder e = new JSONEncoder();
        return e.encodeUser(users, userCount);
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
