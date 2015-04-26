package com.premium.spirit.society.core.presentationLayer;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormWrapperBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.businessLayer.service.*;
import com.premium.spirit.society.core.dataLayer.entity.OrderEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import com.premium.spirit.society.core.util.PictureLoader;
import com.premium.spirit.society.core.util.paypalfunctions;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;
import  javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Martin on 11. 1. 2015.
 */
@Controller
@Scope("request")
public class OrderController {

    public final OrderFormBO order;
    private List<ProductFormWrapperBO> productFormWrapperBOs;

    private final ProductService productService;
    private final ExportToPdfService exportService;
    private final OrderService orderService;
    private final UserService userService;
    private final MailService mailService;

    private final Mapper dozer;

    @Autowired
    public OrderController(OrderFormBO order, ProductService productService, ExportToPdfService exportService, OrderService orderService, UserService userService, MailService mailService, Mapper dozer) {
        this.order = order;
        this.productService = productService;
        this.exportService = exportService;
        this.orderService = orderService;
        this.userService = userService;
        this.mailService = mailService;
        this.dozer = dozer;
    }

    @RequestMapping(value = "/order/addToCart/{productId}", method = RequestMethod.POST)
    public String orderAddToCartPOST(@PathVariable("productId") int productId, @ModelAttribute("product") @Valid ProductFormBO product, HttpServletRequest request, Model model) {
        if (order.getProducts() == null)
            order.setProducts(new ArrayList<ProductFormBO>());
        for (int i = 0; i != product.getOrderAmount(); i++)
            order.getProducts().add(product);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/order/addToCartAjax/{productId}", method = RequestMethod.POST)
    public void orderAddToCartAjaxPOST(@PathVariable("productId") int productId, @ModelAttribute("product") @Valid ProductFormBO product, HttpServletRequest request, Model model,
                                       @RequestParam("amount") int amount) {
        if (order.getProducts() == null)
            order.setProducts(new ArrayList<ProductFormBO>());
        for (int i = 0; i != product.getOrderAmount(); i++)
            order.getProducts().add(product);
    }


    @RequestMapping(value = "/order/removeFromCartAjax/{productId}", method = RequestMethod.POST)
    public void removeFromCartAjaxPOST(@PathVariable("productId") int productId, @ModelAttribute("product") @Valid ProductFormBO product, HttpServletRequest request, Model model,
                                       @RequestParam("amount") int amount) {
        if (order.getProducts() == null)
            order.setProducts(new ArrayList<ProductFormBO>());

        for (int i = 0; i != product.getOrderAmount(); i++) {
            if (order.getProducts().get(i).getId() == productId) {
                order.getProducts().remove(i);
                break;
            }
        }
    }


    @RequestMapping(value = "/order/removeAllFromCartAjax/{productId}", method = RequestMethod.POST)
    public void removeAllFromCartAjaxPOST(@PathVariable("productId") int productId, @ModelAttribute("product") @Valid ProductFormBO product, HttpServletRequest request, Model model,
                                          @RequestParam("amount") int amount) {
        if (order.getProducts() == null)
            order.setProducts(new ArrayList<ProductFormBO>());

        for (int i = 0; i != product.getOrderAmount(); i++) {
            if (order.getProducts().get(i).getId() == productId) {
                order.getProducts().remove(i);
            }
        }
    }

    @RequestMapping(value = "/shopping-cart", method = RequestMethod.GET)
    public String shoppingCartGET(HttpServletRequest request, Model model) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String username = auth.getName();
        UserFormBO user = userService.getUserByUsername(username);
if (user!=null) {
    order.setUser(dozer.map(user, UserEntity.class));
    order.setUserID(user.getId());
}
        List<String> pictureList = new ArrayList<>();

        if (order.getProducts() != null)
            for (int i = 0; i != order.getProducts().size(); i++) {

                ProductFormBO product = productService.getById(order.getProducts().get(i).getId(), ProductFormBO.class, ProductEntity.class);
                order.getProducts().get(i).setProductSubcategory(dozer.map(product.getProductSubcategory(), ProductSubcategoryEntity.class));

                if (!pictureList.contains(new PictureLoader(dozer.map(product, ProductDisplayBO.class), true).loadPictures().get(0)))
                    pictureList.add(new PictureLoader(dozer.map(product, ProductDisplayBO.class), true).loadPictures().get(0));
            }
        else
            order.setProducts(new ArrayList<ProductFormBO>());


        productFormWrapperBOs = new ArrayList<>();
        for (ProductFormBO productFormBO : order.getProducts()) {
            boolean wrapperContainsCurrentProduct = false;
            for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                if (productFormWrapperBO.getId() == productFormBO.getId()) {
                    wrapperContainsCurrentProduct = true;
                    productFormWrapperBO.setAmount(productFormWrapperBO.getAmount() + 1);
                    break;
                }

            }
            if (!wrapperContainsCurrentProduct) {
                productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO,order));
            }
        }

          model.addAttribute("order", order);
        model.addAttribute("pictureList", pictureList);
        model.addAttribute("productWrappers", productFormWrapperBOs);
        return "order/shoppingCartView";
    }


    @RequestMapping(value = "/finishOrder", method = RequestMethod.POST)
    public String orderFinsihOrderPOST(HttpServletRequest request, Locale locale, Model model, @ModelAttribute("order") @Valid OrderFormBO order, HttpSession session, HttpServletResponse response) {
        productFormWrapperBOs = new ArrayList<>();
        for (ProductFormBO productFormBO : this.order.getProducts()) {
            boolean wrapperContainsCurrentProduct = false;
            for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                if (productFormWrapperBO.getId() == productFormBO.getId()) {
                    wrapperContainsCurrentProduct = true;
                    productFormWrapperBO.setAmount(productFormWrapperBO.getAmount() + 1);
                    break;
                }

            }
            if (!wrapperContainsCurrentProduct) {
                productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO,order));
            }
        }
        // Just a sample code simulating finish of the order
        String name = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        UserFormBO user = userService.getUserByUsername(name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.format(new Date()).toString();
        long dateLong = Long.parseLong(sdf.format(new Date()).toString());
        dateLong = dateLong * 100000;
        int orderCount = user.getOrders().size() + 1;
        String orderNumber = Long.toString(dateLong) + Long.toString(order.getUserID()) + Long.toString(orderCount);
        order.setOrderNumber(orderNumber);

        order.setDate(new Date());
        order.setState(1);
        order.setUser(dozer.map(user, UserEntity.class));
        order.setUserID(user.getId());
        System.out.println(request.getHeader("paymentMethod"));
        order.setProducts(this.order.getProducts());
        String pdfFilename = orderService.createFileName(user.getUsername(), orderNumber) + ".pdf";
        order.setInvoice(pdfFilename);

        if (orderService.getOrdersByUserId(user.getId()).size() == 0)
            orderService.save(order, OrderEntity.class);  //orderService.save(order, OrderEntity.class);
        else
            orderService.merge(order, OrderEntity.class);


        orderService.createPdf(pdfFilename, productFormWrapperBOs, order, locale);

        productFormWrapperBOs = new ArrayList<>();
        for (ProductFormBO productFormBO : order.getProducts()) {
            boolean wrapperContainsCurrentProduct = false;
            for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                if (productFormWrapperBO.getId() == productFormBO.getId()) {
                    wrapperContainsCurrentProduct = true;
                    productFormWrapperBO.setAmount(productFormWrapperBO.getAmount() + 1);
                    break;
                }

            }
            if (!wrapperContainsCurrentProduct) {
                productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO,order));
            }
        }


        mailService.notifyOrderCreated(user, productFormWrapperBOs, pdfFilename, locale);

        this.order.setProducts(new ArrayList<ProductFormBO>());

        return "/order/thanksView";
    }
}