package com.premium.spirit.society.core.presentationLayer;


import com.premium.spirit.society.core.businessLayer.BO.display.ProductCategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductDisplayBO;
import com.premium.spirit.society.core.businessLayer.service.OrderService;
import com.premium.spirit.society.core.businessLayer.service.ProductCategoryService;
import com.premium.spirit.society.core.businessLayer.service.ProductService;
import com.premium.spirit.society.core.util.PictureLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a controller class which handles requests connected with a home page.
 */
@Controller
public class HomeController {

    /**
     * Handles the request to access home page.
     *
     * @param model the model to be filled for view.
     * @return the string of a view to be rendered.
     */

    private final OrderService orderService;
    private final ProductCategoryService productCategoryService;
    private final ProductService productService;

    @Autowired
    public HomeController(OrderService orderService, ProductCategoryService productCategoryService, ProductService productService) {
        this.orderService = orderService;
        this.productCategoryService = productCategoryService;
        this.productService = productService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homeGET(Model model, HttpServletRequest request) {
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

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public String shopGET(@RequestParam(value = "maxResults", defaultValue = "20", required = false) int maxResults, Model model, HttpServletRequest request) {
        model.addAttribute("categories", productCategoryService.getAllUnhidden());
        List<ProductCategoryDisplayBO> productCategoryDisplayBOs = productCategoryService.getAllUnhidden();
        List<ProductDisplayBO> products = productService.getEverythingWithPagination(0, maxResults);
        List<String> pictureList = new ArrayList<>();
        List<String> secondPictureList = new ArrayList<>();
        for (ProductDisplayBO product : products) {
            pictureList.add(new PictureLoader(product, false).loadPictures().get(0));
            secondPictureList.add(new PictureLoader(product, false, true).loadPictures().get(0));
        }
        model.addAttribute("maxResult", maxResults);
        model.addAttribute("products", products);
        model.addAttribute("categoryPictures",new PictureLoader(productCategoryDisplayBOs).loadCategoryPictures());
        model.addAttribute("pictureList", pictureList);
        model.addAttribute("secondPictureList", secondPictureList);
        model.addAttribute("categories", productCategoryDisplayBOs);
        return "shop/shopView";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactGET(Model model, HttpServletRequest request) {
        return "contactView";
    }

    @RequestMapping(value = "/licenceAgreement", method = RequestMethod.GET)
    public String licenceAgreementGET(Model model, HttpServletRequest request) {
        return "licenceAgreementView";
    }
}
