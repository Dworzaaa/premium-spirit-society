package com.premium.spirit.society.core.presentationLayer;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductCategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductSubcategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductCategoryFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductSubcategoryFormBO;
import com.premium.spirit.society.core.businessLayer.service.AuthorizationChecker;
import com.premium.spirit.society.core.businessLayer.service.ProductCategoryService;
import com.premium.spirit.society.core.businessLayer.service.ProductService;
import com.premium.spirit.society.core.businessLayer.service.ProductSubcategoryService;
import com.premium.spirit.society.core.dataLayer.entity.ProductCategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;
import com.premium.spirit.society.core.util.JSONEncoder;
import com.premium.spirit.society.core.util.PictureLoader;
import org.dozer.Mapper;
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
public class ProductSubcategoryController {

    private final ProductSubcategoryService productSubcategoryService;
    private final ProductCategoryService productCategoryService;
    private final AuthorizationChecker authorizationChecker;
    private final ProductService productService;

    private final Mapper dozer;

    @Autowired
    public ProductSubcategoryController(ProductSubcategoryService productSubcategoryService, ProductCategoryService productCategoryService, AuthorizationChecker authorizationChecker, ProductService productService, Mapper dozer) {
        this.productSubcategoryService = productSubcategoryService;
        this.productCategoryService = productCategoryService;
        this.authorizationChecker = authorizationChecker;
        this.productService = productService;
        this.dozer = dozer;
    }

    @RequestMapping(value = "/{categoryUrl}/{subcategoryUrl}", method = RequestMethod.GET)
    public String categoryGET(@RequestParam(value = "maxResults", defaultValue = "20", required = false) int maxResults, Model model, HttpServletRequest request, @PathVariable("subcategoryUrl") String subcategoryUrl, @PathVariable("categoryUrl") String categoryUrl) {
        List<ProductCategoryDisplayBO> productCategoryDisplayBOs = productCategoryService.getAllUnhidden();

        for (ProductCategoryDisplayBO category : productCategoryDisplayBOs) {
            if (category.getUrl().equals(categoryUrl)) {

                List<ProductSubcategoryEntity> productSubcategoryEntities = category.getProductSubcategoriesList();
                for (ProductSubcategoryEntity subcategory : productSubcategoryEntities) {
                    if (subcategory.getUrl().equals(subcategoryUrl)) {

                        List<ProductDisplayBO> products = productService.getEverythingBySubcatAndCatWithPagination(maxResults, 1, subcategory.getId(), category.getId());
                        List<String> pictureList = new ArrayList<>();
                        List<String> secondPictureList = new ArrayList<>();
                        for (ProductDisplayBO product : products) {
                            pictureList.add(new PictureLoader(product, false).loadPictures().get(0));
                            secondPictureList.add(new PictureLoader(product, false, true).loadPictures().get(0));
                        }
                        model.addAttribute("maxResult", maxResults);
                        model.addAttribute("categoryPictures",new PictureLoader(productCategoryDisplayBOs).loadCategoryPictures());
                        model.addAttribute("products", products);
                        model.addAttribute("pictureList", pictureList);
                        model.addAttribute("secondPictureList", secondPictureList);
                        model.addAttribute("categories", productCategoryDisplayBOs);
                        model.addAttribute("categoryId", category.getId());
                        model.addAttribute("subcategories", productSubcategoryEntities);
                        return "subcategory/subcategoryView";
                    }
                }

            }
        }

        return "nonExistView";
    }

    @RequestMapping(value = "/subcategory/subcategories", method = RequestMethod.GET)
    public String categoryListGET(@RequestParam(value = "maxResults", defaultValue = "20", required = false) int maxResults, Model model, HttpServletRequest request) {
        model.addAttribute("maxResult", maxResults);
        return "subcategory/subcategoryListView";
    }


    @RequestMapping(value = "/subcategory/{catId}", method = RequestMethod.GET)
    public String subcategoryOverviewGET(@PathVariable("catId") int catId, HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        ProductSubcategoryFormBO subcategory = productSubcategoryService.getById(catId, ProductSubcategoryFormBO.class, ProductSubcategoryEntity.class);
        if (subcategory == null) {
            return "nonExistView";
            // Neexistujici subcategory
        }
        model.addAttribute("subcategory", subcategory);
        return "subcategory/overviewView";

    }

    @RequestMapping(value = "/subcategory/create", method = RequestMethod.GET)
    public String subcategoryAddGET(HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        model.addAttribute("subcategory", new ProductSubcategoryFormBO());
        return "subcategory/createView";
    }

    @RequestMapping(value = "/subcategory/create", method = RequestMethod.POST)
    public String subcategoryAddPOST(@ModelAttribute("subcategory") @Valid ProductSubcategoryFormBO subcategory, HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        int catID = subcategory.getProductCategoryID();
        ProductCategoryFormBO productCategory = productCategoryService.getById(catID, ProductCategoryFormBO.class, ProductCategoryEntity.class);
        subcategory.setProductCategory(dozer.map(productCategory, ProductCategoryEntity.class));

        int subcategoryId = productSubcategoryService.save(subcategory, ProductSubcategoryEntity.class);

        return "redirect:/subcategory/" + Integer.toString(subcategoryId);
    }


    @RequestMapping(value = "/subcategory/{catId}/edit", method = RequestMethod.GET)
    public String subcategoryEditGET(@PathVariable("catId") int catId, HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        ProductSubcategoryFormBO subcategory = productSubcategoryService.getById(catId, ProductSubcategoryFormBO.class, ProductSubcategoryEntity.class);
        if (subcategory == null) {
            return "nonExistView";
            // Neexistujici subcategory
        }
        model.addAttribute("subcategory", subcategory);
        return "subcategory/editView";
    }


    @RequestMapping(value = "/subcategory/{subcatId}/edit", method = RequestMethod.POST)
    public String subcategoryEditPOST(@ModelAttribute("subcategory") @Valid ProductSubcategoryFormBO productSubcategoryFormBO, @PathVariable("subcatId") int subcatId, HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }

        int catID = productSubcategoryFormBO.getProductCategoryID();
        productSubcategoryFormBO.setProductCategory(dozer.map(productCategoryService.getById(catID, ProductCategoryFormBO.class, ProductCategoryEntity.class), ProductCategoryEntity.class));
        productSubcategoryFormBO.setId(subcatId);
        productSubcategoryService.update(productSubcategoryFormBO, ProductSubcategoryEntity.class);
        return "subcategory/overviewView";
    }

    @RequestMapping(value = "/subcategory/{subcategoryID}/hide", method = RequestMethod.GET)
    public String userHideGET(@PathVariable("subcategoryID") int subcategoryID, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        ProductSubcategoryFormBO subcategory = productSubcategoryService.getById(subcategoryID, ProductSubcategoryFormBO.class, ProductSubcategoryEntity.class);
        if (subcategory == null) {
            return "nonExistView";
            // Neexistujici subcategory
        }
        if (subcategory == null) {
            return "user/" + subcategory + "/hide";
            // Neexistujici uzivatel
        }
        int catId = subcategory.getId();
        productSubcategoryService.hide(catId);
        return "redirect:/subcategory/" + subcategory.getId();
    }

    @RequestMapping(value = "/subcategory/{subcategoryID}/restore", method = RequestMethod.GET)
    public String userRestoreGET(@PathVariable("subcategoryID") int subcategoryID, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        ProductSubcategoryFormBO subcategory = productSubcategoryService.getById(subcategoryID, ProductSubcategoryFormBO.class, ProductSubcategoryEntity.class);
        if (subcategory == null) {
            return "nonExistView";
            // Neexistujici subcategory
        }
        if (subcategory == null) {
            return "user/" + subcategory + "/hide";
            // Neexistujici uzivatel
        }
        int catId = subcategory.getId();
        productSubcategoryService.restore(catId);
        return "redirect:/subcategory/" + subcategory.getId();
    }


    @RequestMapping(value = "/subcategory/list-search", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String patientsListSearchGET(
            @RequestParam("maxResults") int maxResults,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("search") String searchString, HttpServletRequest request) {

        int userCount = productSubcategoryService.getCountOfUnhidden(searchString);
        List<ProductSubcategoryDisplayBO> categories = productSubcategoryService.getBySearchStringWithPagination(maxResults, pageNumber, searchString);
        JSONEncoder e = new JSONEncoder();
        return e.encodeSubcategory(categories, userCount);
    }
}
