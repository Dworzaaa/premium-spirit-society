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
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        //String referer = request.getHeader("Referer");
        //return "redirect:" + referer;
    }


    @RequestMapping(value = "/shopping-cart", method = RequestMethod.GET)
    public String shoppingCartGET(HttpServletRequest request, Model model) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String username = auth.getName();
        UserFormBO user = userService.getUserByUsername(username);
        if (user != null) {
            order.setUser(dozer.map(user, UserEntity.class));
            order.setUserID(user.getId());
        }
        List<String> pictureList = new ArrayList<>();
        List<ProductFormBO> products = new ArrayList<>();
        if (order.getProducts() != null)
            for (int i = 0; i != order.getProducts().size(); i++) {

                ProductFormBO product = productService.getById(order.getProducts().get(i).getId(), ProductFormBO.class, ProductEntity.class);
                order.getProducts().get(i).setProductSubcategory(dozer.map(product.getProductSubcategory(), ProductSubcategoryEntity.class));
                if (!products.contains(product))
                    products.add(product);
                if (!pictureList.contains(new PictureLoader(dozer.map(product, ProductDisplayBO.class), true).loadPictures().get(0)))
                    pictureList.add(new PictureLoader(dozer.map(product, ProductDisplayBO.class), true).loadPictures().get(0));
            }
        else
            order.setProducts(new ArrayList<ProductFormBO>());


        productFormWrapperBOs = new ArrayList<>();

        productFormWrapperBOs = new ArrayList<>();
        for (ProductFormBO productFormBO : products) {
            boolean wrapperContainsCurrentProduct = false;
            for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                if (productFormWrapperBO.getId() == productFormBO.getId()) {
                    wrapperContainsCurrentProduct = true;
                    productFormWrapperBO.setOrderAmount(productFormWrapperBO.getOrderAmount() + 1);
                    break;
                }

            }
            if (!wrapperContainsCurrentProduct) {
                productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO, order));
                productFormWrapperBOs.get(productFormWrapperBOs.size()-1).setOrderAmount(1);
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
                    productFormWrapperBO.setOrderAmount(productFormWrapperBO.getOrderAmount() + 1);
                    break;
                }

            }
            if (!wrapperContainsCurrentProduct) {
                productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO, order));
                productFormWrapperBOs.get(productFormWrapperBOs.size()-1).setOrderAmount(1);
            }
        }

        String name = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        UserFormBO user = userService.getUserByUsername(name);
        order.setUser(dozer.map(user, UserEntity.class));
        order.setUserID(user.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.format(new Date()).toString();
        long dateLong = Long.parseLong(sdf.format(new Date()).toString());
        dateLong = dateLong * 100;
        int orderCount = user.getOrders().size() + 1;
        String orderNumber =  Integer.toString( order.getUserID() ) + Long.toString(dateLong) +Long.toString(orderCount);
        order.setOrderNumber(orderNumber);
        order.setDate(new Date());
        order.setState(1);
        // TODO: dat do konfiguraku
        order.setShippingPrice(123);
        order.setProducts(this.order.getProducts());
        String pdfFilename = orderService.createFileName(user.getUsername(), orderNumber) + ".pdf";
        order.setInvoice(pdfFilename);

        if (orderService.getOrdersByUserId(user.getId()).size() == 0)
            orderService.save(order, OrderEntity.class);  //orderService.save(order, OrderEntity.class);
        else
            orderService.merge(order, OrderEntity.class);

        List<ProductFormBO> products = new ArrayList<>();

        if (order.getProducts() != null)
            for (int i = 0; i != order.getProducts().size(); i++) {


                ProductFormBO product = productService.getById(order.getProducts().get(i).getId(), ProductFormBO.class, ProductEntity.class);
                order.getProducts().get(i).setProductSubcategory(dozer.map(product.getProductSubcategory(), ProductSubcategoryEntity.class));
                if (!products.contains(product))
                    products.add(product);
            }
        else
            order.setProducts(new ArrayList<ProductFormBO>());


        productFormWrapperBOs = new ArrayList<>();
        for (ProductFormBO productFormBO : products) {
            boolean wrapperContainsCurrentProduct = false;
            for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                if (productFormWrapperBO.getId() == productFormBO.getId()) {
                    wrapperContainsCurrentProduct = true;
                    productFormWrapperBO.setOrderAmount(productFormWrapperBO.getOrderAmount() + 1);
                    break;
                }


            }
            if (!wrapperContainsCurrentProduct) {
                productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO, order));
                productFormWrapperBOs.get(productFormWrapperBOs.size()-1).setOrderAmount(1);
            }
        }

        for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
            for (int i = 0; i != productFormWrapperBO.getOrderAmount(); i++) {
                ProductFormBO productFormBO = dozer.map(productFormWrapperBO, ProductFormBO.class);
                order.getProducts().add(productFormBO);
            }
        }
        orderService.createPdf(pdfFilename, productFormWrapperBOs, order, locale);
        order.setProducts(new ArrayList<ProductFormBO>());
        mailService.notifyOrderCreated(user, productFormWrapperBOs, pdfFilename, locale);
        model.addAttribute("productFormWrapperBOs", productFormWrapperBOs);
        model.addAttribute("order", order);
        this.order.setProducts(new ArrayList<ProductFormBO>());

        return "order/finishOrderView";
    }

    @RequestMapping(value = "/orderChange/{productId}/{amount}", method = RequestMethod.POST)
    public
    @ResponseBody
    String orderChangePOST(@PathVariable("productId") int productId, @PathVariable("amount") int amount, Model model) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String username = auth.getName();
        UserFormBO user = userService.getUserByUsername(username);
        if (user != null) {
            order.setUser(dozer.map(user, UserEntity.class));
            order.setUserID(user.getId());
        }
        List<String> pictureList = new ArrayList<>();
        List<ProductFormBO> products = new ArrayList<>();

        if (order.getProducts() != null)
            for (int i = 0; i != order.getProducts().size(); i++) {
                ProductFormBO product = productService.getById(order.getProducts().get(i).getId(), ProductFormBO.class, ProductEntity.class);
                order.getProducts().get(i).setProductSubcategory(dozer.map(product.getProductSubcategory(), ProductSubcategoryEntity.class));
                if (!products.contains(product))
                    products.add(product);
                if (!pictureList.contains(new PictureLoader(dozer.map(product, ProductDisplayBO.class), true).loadPictures().get(0)))
                    pictureList.add(new PictureLoader(dozer.map(product, ProductDisplayBO.class), true).loadPictures().get(0));
            }
        else
            order.setProducts(new ArrayList<ProductFormBO>());


        productFormWrapperBOs = new ArrayList<>();
        for (ProductFormBO productFormBO : products) {
            boolean wrapperContainsCurrentProduct = false;
            for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                if (productFormWrapperBO.getId() == productFormBO.getId()) {
                    if (productFormWrapperBO.getId() == productId) {
                        wrapperContainsCurrentProduct = true;
                        productFormWrapperBO.setOrderAmount(amount);
                        break;
                    }
                    wrapperContainsCurrentProduct = true;
                    productFormWrapperBO.setOrderAmount(productFormWrapperBO.getOrderAmount() + 1);
                    break;
                }

            }
            if (!wrapperContainsCurrentProduct) {
                ProductFormWrapperBO productFormWrapperBO = new ProductFormWrapperBO(productFormBO, order);
                if (productFormWrapperBO.getId() == productFormBO.getId()) {
                    if (productFormWrapperBO.getId() == productId) {
                        productFormWrapperBO.setOrderAmount(amount);
                    } else
                        productFormWrapperBO.setOrderAmount(1);
                    productFormWrapperBOs.add(productFormWrapperBO);

                }
            }
        }

        order.setProducts(new ArrayList<ProductFormBO>());
        for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
            for (int i = 0; i != productFormWrapperBO.getOrderAmount(); i++) {
                ProductFormBO productFormBO = dozer.map(productFormWrapperBO, ProductFormBO.class);
                order.getProducts().add(productFormBO);
            }
        }

        model.addAttribute("order", order);
        model.addAttribute("pictureList", pictureList);
        model.addAttribute("productWrappers", productFormWrapperBOs);
        return "";
    }
}