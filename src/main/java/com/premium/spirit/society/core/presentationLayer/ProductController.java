package com.premium.spirit.society.core.presentationLayer;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductCategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductCategoryFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductSubcategoryFormBO;
import com.premium.spirit.society.core.businessLayer.service.AuthorizationChecker;
import com.premium.spirit.society.core.businessLayer.service.ProductCategoryService;
import com.premium.spirit.society.core.businessLayer.service.ProductService;
import com.premium.spirit.society.core.businessLayer.service.ProductSubcategoryService;
import com.premium.spirit.society.core.dataLayer.entity.ProductCategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;
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
import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 6. 1. 2015.
 */
@Controller
public class ProductController {
    private final ProductService productService;
    private final ProductSubcategoryService productSubcategoryService;
    private final AuthorizationChecker authorizationChecker;
    private final ProductCategoryService productCategoryService;

    private final Mapper dozer;

    @Autowired
    public ProductController(ProductService productService, ProductSubcategoryService productCategoryService, AuthorizationChecker authorizationChecker, ProductCategoryService productCategoryService1, Mapper dozer) {
        this.productService = productService;
        this.productSubcategoryService = productCategoryService;
        this.authorizationChecker = authorizationChecker;
        this.productCategoryService = productCategoryService1;

        this.dozer = dozer;
    }

    @RequestMapping(value = "/{productCategoryUrl}/{productSubcategoryUrl}/{productUrl}", method = RequestMethod.GET)
    public String productGET(@RequestParam(value = "maxResults", defaultValue = "20", required = false) int maxResults, Model model, HttpServletRequest request, @PathVariable("productUrl") String productUrl, @PathVariable("productCategoryUrl") String productCategoryUrl, @PathVariable("productSubcategoryUrl") String productSubcategoryUrl) {


        List<ProductCategoryDisplayBO> productCategoryDisplayBOs = productCategoryService.getAllUnhidden();
        for (ProductCategoryDisplayBO category : productCategoryDisplayBOs) {
            if (category.getUrl().equals(productCategoryUrl)) {

                List<ProductSubcategoryEntity> productSubcategoryEntities = category.getProductSubcategoriesList();
                for (ProductSubcategoryEntity subcategory : productSubcategoryEntities) {
                    if (subcategory.getUrl().equals(productSubcategoryUrl)) {

                        Set<ProductEntity> productEntities = subcategory.getProductsList();
                        for (ProductEntity product : productEntities) {
                            if (product.getUrl().equals(productUrl)) {
                                List<String> pictureList = new PictureLoader(dozer.map(product, ProductDisplayBO.class), false).loadPictures();

                                model.addAttribute("product", product);
                                model.addAttribute("pictureList", pictureList);
                                model.addAttribute("categories", productCategoryDisplayBOs);
                                model.addAttribute("subcategories", productSubcategoryEntities);
                                model.addAttribute("categoryPictures",new PictureLoader(productCategoryDisplayBOs).loadCategoryPictures());
                                return "product/productView";
                            }
                        }
                    }
                }

            }
        }
        return "deniedView";
    }

    @RequestMapping(value = "/product/products", method = RequestMethod.GET)
    public String productListGET(@RequestParam(value = "maxResults", defaultValue = "20", required = false) int maxResults, Model model, HttpServletRequest request) {
        model.addAttribute("maxResult", maxResults);
        return "product/productListView";
    }


    @RequestMapping(value = "/product/{catId}", method = RequestMethod.GET)
    public String productOverviewGET(@PathVariable("catId") int catId, HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        ProductFormBO product = productService.getById(catId, ProductFormBO.class, ProductEntity.class);
        if (product == null) {
            return "deniedView";
            // Neexistujici product
        }
        model.addAttribute("product", product);
        return "product/overviewView";

    }

    @RequestMapping(value = "/product/create", method = RequestMethod.GET)
    public String productAddGET(HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        model.addAttribute("product", new ProductFormBO());
        return "product/createView";
    }

    @RequestMapping(value = "/product/create", method = RequestMethod.POST)
    public String productAddPOST(@ModelAttribute("product") @Valid ProductFormBO product, HttpServletRequest request, Model model) {
        int subcatID = product.getProductSubcategoryID();
        ProductSubcategoryFormBO subcategory = productSubcategoryService.getById(subcatID, ProductSubcategoryFormBO.class, ProductSubcategoryEntity.class);

        int catID = subcategory.getProductCategoryID();
        ProductCategoryFormBO productCategory = productCategoryService.getById(catID, ProductCategoryFormBO.class, ProductCategoryEntity.class);

        subcategory.setProductCategory(dozer.map(productCategory, ProductCategoryEntity.class));
        product.setProductSubcategory(dozer.map(subcategory, ProductSubcategoryEntity.class));
        product.setProductCategoryID(productCategory.getId());

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }

        product.setProductSubcategory(dozer.map(subcategory, ProductSubcategoryEntity.class));

        int productId = productService.save(product, ProductEntity.class);
        return "redirect:/product/" + Integer.toString(productId);

    }


    @RequestMapping(value = "/product/{catId}/edit", method = RequestMethod.GET)
    public String productEditGET(@PathVariable("catId") int catId, HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        ProductFormBO product = productService.getById(catId, ProductFormBO.class, ProductEntity.class);
        if (product == null) {
            return "deniedView";
            // Neexistujici product
        }

        model.addAttribute("pictureList", new PictureLoader(product).loadPictures());
        model.addAttribute("product", product);
        return "product/editView";
    }


    @RequestMapping(value = "/product/{subcatId}/edit", method = RequestMethod.POST)
    public String productEditPOST(@ModelAttribute("product") @Valid ProductFormBO product, @PathVariable("subcatId") int subcatId, HttpServletRequest request, Model model) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }

        int subcatID = product.getProductSubcategoryID();
        ProductSubcategoryFormBO subcategory = productSubcategoryService.getById(subcatID, ProductSubcategoryFormBO.class, ProductSubcategoryEntity.class);

        int catID = subcategory.getProductCategoryID();
        ProductCategoryFormBO productCategory = productCategoryService.getById(catID, ProductCategoryFormBO.class, ProductCategoryEntity.class);

        subcategory.setProductCategory(dozer.map(productCategory, ProductCategoryEntity.class));
        product.setProductSubcategory(dozer.map(subcategory, ProductSubcategoryEntity.class));
        product.setProductCategoryID(productCategory.getId());

        productService.update(product, ProductEntity.class);
        return "product/overviewView";
    }

    @RequestMapping(value = "/product/{productID}/hide", method = RequestMethod.GET)
    public String userHideGET(@PathVariable("productID") int productID, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        ProductFormBO product = productService.getById(productID, ProductFormBO.class, ProductEntity.class);
        if (product == null) {
            return "deniedView";
            // Neexistujici product
        }
        if (product == null) {
            return "user/" + product + "/hide";
            // Neexistujici uzivatel
        }
        int catId = product.getId();
        productService.hide(catId);
        return "redirect:/product/" + product.getId();
    }

    @RequestMapping(value = "/product/{productID}/restore", method = RequestMethod.GET)
    public String userRestoreGET(@PathVariable("productID") int productID, HttpServletRequest request) {

        if (!authorizationChecker.checkAuthorization(request)) {
            return "deniedView";
        }
        ProductFormBO product = productService.getById(productID, ProductFormBO.class, ProductEntity.class);
        if (product == null) {
            return "deniedView";
            // Neexistujici product
        }
        if (product == null) {
            return "user/" + product + "/hide";
            // Neexistujici uzivatel
        }
        int catId = product.getId();
        productService.restore(catId);
        return "redirect:/product/" + product.getId();
    }


    @RequestMapping(value = "/product/list-search", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String productsListSearchGET(
            @RequestParam("maxResults") int maxResults,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("search") String searchString, HttpServletRequest request,Model model) {

        int userCount = productService.getCountOfUnhidden(searchString);
        List<ProductDisplayBO> categories = productService.getBySearchStringWithPagination(maxResults, pageNumber, searchString);
        JSONEncoder e = new JSONEncoder();
        return e.encodeProduct(categories, userCount);
    }

    @RequestMapping(value = "/product/listInSubcat-search", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String productsInSubcatListSearchGET(
            @RequestParam("maxResults") int maxResults,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("subcategoryId") int subcategoryId,
            @RequestParam("search") String searchString, HttpServletRequest request) {

        int userCount = productService.getCountOfUnhidden(searchString);
        List<ProductDisplayBO> categories = productService.getEverythingByCatWithPagination(maxResults, pageNumber, subcategoryId);
        JSONEncoder e = new JSONEncoder();
        return e.encodeProduct(categories, userCount);
    }

    @RequestMapping(value = "/product/listInSubcatCat-search", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String productsInSubcatCatListSearchGET(
            @RequestParam("maxResults") int maxResults,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("subcategoryId") int subcategoryId,
            @RequestParam("categoryId") int categoryId,
            @RequestParam("search") String searchString, HttpServletRequest request) {

        int userCount = productService.getCountOfUnhidden(searchString);
        List<ProductDisplayBO> categories = productService.getEverythingBySubcatAndCatWithPagination(maxResults, pageNumber, subcategoryId, categoryId);
        JSONEncoder e = new JSONEncoder();
        return e.encodeProduct(categories, userCount);
    }

    @RequestMapping(value = "/product/listInCat-search", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String productsInCatListSearchGET(
            @RequestParam("maxResults") int maxResults,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("categoryId") int categoryId,
            @RequestParam("search") String searchString, HttpServletRequest request) {

        int userCount = productService.getCountOfUnhidden(searchString);
        List<ProductDisplayBO> categories = productService.getEverythingByCatWithPagination(maxResults, pageNumber, categoryId);
        JSONEncoder e = new JSONEncoder();
        return e.encodeProduct(categories, userCount);
    }

    @RequestMapping(value = "/product/listAll-search", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String productsAllSearchGET(
            @RequestParam("maxResults") int maxResults,
            @RequestParam("pageNumber") int pageNumber,
            HttpServletRequest request) {

        int userCount = productService.getCountOfUnhidden("");
        List<ProductDisplayBO> categories = productService.getEverythingWithPagination(maxResults, pageNumber);
        JSONEncoder e = new JSONEncoder();
        return e.encodeProduct(categories, userCount);
    }
}
