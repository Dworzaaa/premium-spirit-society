package com.premium.spirit.society.core.presentationLayer;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductCategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductCategoryFormBO;
import com.premium.spirit.society.core.businessLayer.service.AuthorizationChecker;
import com.premium.spirit.society.core.businessLayer.service.ProductCategoryService;
import com.premium.spirit.society.core.businessLayer.service.ProductService;
import com.premium.spirit.society.core.dataLayer.entity.ProductCategoryEntity;
import com.premium.spirit.society.core.util.JSONEncoder;
import com.premium.spirit.society.core.util.PictureLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 26. 12. 2014.
 */
@Controller
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;
    private final ProductService productService;
    private final AuthorizationChecker authorizationChecker;


    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService, ProductService productService, AuthorizationChecker authorizationChecker) {
        this.productCategoryService = productCategoryService;
        this.productService = productService;
        this.authorizationChecker = authorizationChecker;
    }


    @RequestMapping(value = "/{categoryUrl}", method = RequestMethod.GET)
    public String categoryGET(@RequestParam(value = "maxResults", defaultValue = "20", required = false) int maxResults, Model model, HttpServletRequest request, @PathVariable("categoryUrl") String categoryUrl) {
        List<ProductCategoryDisplayBO> productCategoryDisplayBOs = productCategoryService.getAllUnhidden();

        for (ProductCategoryDisplayBO category : productCategoryDisplayBOs) {
            if (category.getUrl().equals(categoryUrl)) {
                model.addAttribute("subcategories", category.getProductSubcategoriesList());

                List<ProductDisplayBO> products = productService.getEverythingByCatWithPagination(maxResults, 1, category.getId());
                List<String> pictureList = new ArrayList<>();
                List<String> secondPictureList = new ArrayList<>();

                for (ProductDisplayBO product : products) {
                    pictureList.add(new PictureLoader(product, true).loadPictures().get(0));
                    secondPictureList.add(new PictureLoader(product, false, true).loadPictures().get(0));
                }

                model.addAttribute("maxResult", maxResults);
                model.addAttribute("categoryPictures", new PictureLoader(productCategoryDisplayBOs).loadCategoryPictures());
                model.addAttribute("products", products);
                model.addAttribute("pictureList", pictureList);
                model.addAttribute("secondPictureList", secondPictureList);
                model.addAttribute("categories", productCategoryDisplayBOs);
                model.addAttribute("categoryId", category.getId());
                model.addAttribute("subcategories", category.getProductSubcategoriesList());
                return "category/categoryView";
            }
        }
        return "nonExistView";
    }


    @RequestMapping(value = {"/category/categories", "category", "categories"}, method = RequestMethod.GET)
    public String categoryListGET(@RequestParam(value = "maxResults", defaultValue = "20", required = false) int maxResults, Model model, HttpServletRequest request) {
        model.addAttribute("maxResult", maxResults);
        return "category/categoryListView";
    }


    @RequestMapping(value = "/category/{catId}", method = RequestMethod.GET)
    public String categoryOverviewGET(@PathVariable("catId") int catId, HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        ProductCategoryFormBO category = productCategoryService.getById(catId, ProductCategoryFormBO.class, ProductCategoryEntity.class);
        if (category == null) {
            return "nonExistView";
            // Neexistujici category
        }
        model.addAttribute("category", category);
        return "category/overviewView";

    }

    @RequestMapping(value = "/category/create", method = RequestMethod.GET)
    public String categoryAddGET(HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        model.addAttribute("category", new ProductCategoryFormBO());
        return "category/createView";
    }

    @RequestMapping(value = "/category/create", method = RequestMethod.POST)
    public String categoryAddPOST(@ModelAttribute("category") @Valid ProductCategoryFormBO category, HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }

        int productCategoryId = productCategoryService.save(category, ProductCategoryEntity.class);
        return "redirect:/category/" + Integer.toString(productCategoryId);
    }


    @RequestMapping(value = "/category/{catId}/edit", method = RequestMethod.GET)
    public String categoryEditGET(@PathVariable("catId") int catId, HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        ProductCategoryFormBO category = productCategoryService.getById(catId, ProductCategoryFormBO.class, ProductCategoryEntity.class);
        if (category == null) {
            return "nonExistView";
            // Neexistujici category
        }
        model.addAttribute("category", category);
        return "category/editView";
    }


    @RequestMapping(value = "/category/{catId}/edit", method = RequestMethod.POST)
    public String categoryEditPOST(@ModelAttribute("category") @Valid ProductCategoryFormBO productCategoryFormBO, @PathVariable("catId") int catId, HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        productCategoryFormBO.setId(catId);
        productCategoryService.save(productCategoryFormBO, ProductCategoryEntity.class);
        return "category/overviewView";
    }


    @RequestMapping(value = "/category/{categoryID}/hide", method = RequestMethod.GET)
    public String userHideGET(@PathVariable("categoryID") int categoryID, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        ProductCategoryFormBO category = productCategoryService.getById(categoryID, ProductCategoryFormBO.class, ProductCategoryEntity.class);
        if (category == null) {
            return "nonExistView";
            // Neexistujici category
        }

        int catId = category.getId();
        productCategoryService.hide(catId);
        return "redirect:/category/" + category.getId();
    }

    @RequestMapping(value = "/category/{categoryID}/restore", method = RequestMethod.GET)
    public String userRevoverGET(@PathVariable("categoryID") int categoryID, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        ProductCategoryFormBO category = productCategoryService.getById(categoryID, ProductCategoryFormBO.class, ProductCategoryEntity.class);
        if (category == null) {
            return "nonExistView";
            // Neexistujici category
        }
        if (category == null) {
            return "user/" + category + "/hide";
            // Neexistujici uzivatel
        }
        int catId = category.getId();
        productCategoryService.restore(catId);
        return "redirect:/category/" + category.getId();
    }


    @RequestMapping(value = "/category/list-search", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String patientsListSearchGET(
            @RequestParam("maxResults") int maxResults,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("search") String searchString, HttpServletRequest request) {

        int userCount = productCategoryService.getCountOfUnhidden(searchString);
        List<ProductCategoryDisplayBO> categories = productCategoryService.getBySearchStringWithPagination(maxResults, pageNumber, searchString);
        JSONEncoder e = new JSONEncoder();

        return e.encodeCategory(categories, userCount);
    }
}
