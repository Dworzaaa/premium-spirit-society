package com.premium.spirit.society.core.presentationLayer;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormWrapperBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.businessLayer.service.ExportToPdfService;
import com.premium.spirit.society.core.businessLayer.service.OrderService;
import com.premium.spirit.society.core.businessLayer.service.ProductService;
import com.premium.spirit.society.core.businessLayer.service.UserService;
import com.premium.spirit.society.core.dataLayer.entity.OrderEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import com.premium.spirit.society.core.util.PictureLoader;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.DecimalFormat;
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

    private final OrderFormBO order;
    private List<ProductFormWrapperBO> productFormWrapperBOs;

    private final ProductService productService;
    private final ExportToPdfService exportService;

    private final OrderService orderService;
    private final UserService userSerivce;

    private final Mapper dozer;

    @Autowired
    public OrderController(OrderFormBO order, ProductService productService, ExportToPdfService exportService, OrderService orderService, UserService userSerivce, Mapper dozer) {
        this.order = order;
        this.productService = productService;
        this.exportService = exportService;
        this.orderService = orderService;
        this.userSerivce = userSerivce;
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
                productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO));
            }
        }
        model.addAttribute("order", order);
        model.addAttribute("pictureList", pictureList);
        model.addAttribute("productWrappers", productFormWrapperBOs);
        return "order/shoppingCartView";
    }

    private BaseFont bfBold;
    private BaseFont bf;

    @RequestMapping(value = "/order/finishOrder", method = RequestMethod.POST)
    public String orderFinsihOrderPOST(HttpServletRequest request, Locale locale, Model model, @ModelAttribute("order") @Valid OrderFormBO order) {
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
                productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO));
            }
        }
        // Just a sample code simulating finish of the order
        String name = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        UserFormBO user = userSerivce.getUserByUsername(name);
        //  user.setOrders(new ArrayList<OrderFormBO>());
        //  user.getOrders().add(order);
        // userSerivce.update(user, UserEntity.class);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.format(new Date()).toString();
        long dateLong = Long.parseLong(sdf.format(new Date()).toString());
        dateLong = dateLong * 1000000;
        List<OrderFormBO> orders = orderService.getAll(OrderFormBO.class, OrderEntity.class);
        if (orders.size() > 0)
            dateLong += orderService.getById(orders.get(orders.size() - 1).getId(), OrderFormBO.class, OrderEntity.class).getId() + 1;
        else
            dateLong++;

// TODO predelat ordernumber
        order.setOrderNumber(Long.toString(dateLong));

        order.setDate(new Date());
        order.setState(1);
        order.setUser(dozer.map(user, UserEntity.class));
        order.setUserID(user.getId());
        System.out.println(request.getHeader("paymentMethod"));
        order.setProducts(this.order.getProducts());

        if (orderService.getOrdersByUserId(user.getId()).size() == 0)
            orderService.save(order, OrderEntity.class);  //orderService.save(order, OrderEntity.class);
        else
            orderService.merge(order, OrderEntity.class);


        String pdfFilename = "";

        pdfFilename = "test.pdf";
        createPDF(pdfFilename, productFormWrapperBOs);


        this.order.setProducts(new ArrayList<ProductFormBO>());


        return "/order/thanksView";
    }

    private int pageNumber = 0;


    private void createPDF(String pdfFilename, List<ProductFormWrapperBO> productFormWrapperBOs) {

        Document doc = new Document();
        PdfWriter docWriter = null;
        initializeFonts();

        try {
            String path = "D:/" + pdfFilename;
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.addAuthor("PremiumSpiritSociety");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("premium-spirit-society.com");
            doc.addTitle("Invoice");
            doc.setPageSize(PageSize.LETTER);

            doc.open();
            PdfContentByte cb = docWriter.getDirectContent();
            doc.newPage();
            boolean beginPage = true;
            int y = 0;
/*
                for (int i = 0; i != productFormWrapperBOs.size(); i++) {
                if (beginPage) {
                    beginPage = false;
                    generateLayout(doc, cb);
                    generateHeader(doc, cb);
                    y = 615;
                }
                generateDetail(doc, cb, i, y);
                y = y - 15;
                if (y < 50) {
                    printPageNumber(cb);
                    doc.newPage();
                    beginPage = true;
                }
            }
*/
            PdfPTable mainFrame = new PdfPTable(1);

            String imageUrl = "http://www.kissedbutts.com/wpkb-content/uploads/3500-0191-ass-fucking.jpg";
            Image image2 = Image.getInstance(new URL(imageUrl));
            image2.scalePercent(30f);

            image2.setAbsolutePosition(700, 0);
            doc.add(image2);
            doc.add(new Paragraph(String.valueOf(order.getId())));


            mainFrame.setWidthPercentage(95);


            PdfPTable table = new PdfPTable(7); // 3 columns.

            table.addCell(new PdfPCell(new Paragraph("ID")));
            table.addCell(new PdfPCell(new Paragraph("name")));
            table.addCell(new PdfPCell(new Paragraph("volume")));
            table.addCell(new PdfPCell(new Paragraph("ethanol volume")));
            table.addCell(new PdfPCell(new Paragraph("price")));
            table.addCell(new PdfPCell(new Paragraph("amount")));
            table.addCell(new PdfPCell(new Paragraph("price together")));


            for (int i = 0; i != productFormWrapperBOs.size(); i++) {
                PdfPCell productId = new PdfPCell(new Paragraph(String.valueOf(productFormWrapperBOs.get(i).getId())));
                PdfPCell productName = new PdfPCell(new Paragraph(productFormWrapperBOs.get(i).getName()));
                PdfPCell productVolume = new PdfPCell(new Paragraph(String.valueOf(productFormWrapperBOs.get(i).getVolume())));
                PdfPCell productEthanolVolume = new PdfPCell(new Paragraph(String.valueOf(productFormWrapperBOs.get(i).getEthanolVolume())));
                PdfPCell productPrice = new PdfPCell(new Paragraph(String.valueOf(productFormWrapperBOs.get(i).getPrice())));
                PdfPCell productAmount = new PdfPCell(new Paragraph(String.valueOf(productFormWrapperBOs.get(i).getAmount())));
                PdfPCell productPriceForAll = new PdfPCell(new Paragraph(String.valueOf(productFormWrapperBOs.get(i).getPrice() * productFormWrapperBOs.get(i).getAmount())));

                table.addCell(productId);
                table.addCell(productName);
                table.addCell(productVolume);
                table.addCell(productEthanolVolume);
                table.addCell(productPrice);
                table.addCell(productAmount);
                table.addCell(productPriceForAll);

            }

            mainFrame.addCell(table);

            table.setWidthPercentage(95);

            doc.add(mainFrame);


        } catch (Exception dex) {
            dex.printStackTrace();
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (docWriter != null) {
                docWriter.close();
            }
        }
    }

    private void generateLayout(Document doc, PdfContentByte cb) {

        try {

            cb.setLineWidth(1f);

            // Invoice Header box layout
            cb.rectangle(420, 700, 150, 60);
            cb.moveTo(420, 720);
            cb.lineTo(570, 720);
            cb.moveTo(420, 740);
            cb.lineTo(570, 740);
            cb.moveTo(480, 700);
            cb.lineTo(480, 760);
            cb.stroke();

            // Invoice Header box Text Headings
            createHeadings(cb, 422, 743, "Account No.");
            createHeadings(cb, 422, 723, "Invoice No.");
            createHeadings(cb, 422, 703, "Invoice Date");

            // Invoice Detail box layout
            cb.rectangle(20, 50, 550, 600);
            cb.moveTo(20, 630);
            cb.lineTo(570, 630);
            cb.moveTo(50, 50);
            cb.lineTo(50, 650);
            cb.moveTo(150, 50);
            cb.lineTo(150, 650);
            cb.moveTo(430, 50);
            cb.lineTo(430, 650);
            cb.moveTo(500, 50);
            cb.lineTo(500, 650);
            cb.stroke();

            // Invoice Detail box Text Headings
            createHeadings(cb, 22, 633, "Qty");
            createHeadings(cb, 52, 633, "Item Number");
            createHeadings(cb, 152, 633, "Item Description");
            createHeadings(cb, 432, 633, "Price");
            createHeadings(cb, 502, 633, "Ext Price");


            //add the images
            Image companyLogo = Image.getInstance("/resources/logo.jpg");
            companyLogo.setAbsolutePosition(25, 700);
            companyLogo.scalePercent(25);
            doc.add(companyLogo);

        } catch (Exception dex) {
            dex.printStackTrace();
        }

    }

    private void generateHeader(Document doc, PdfContentByte cb) {

        try {

            createHeadings(cb, 200, 750, "Company Name");
            createHeadings(cb, 200, 735, "Address Line 1");
            createHeadings(cb, 200, 720, "Address Line 2");
            createHeadings(cb, 200, 705, "City, State - ZipCode");
            createHeadings(cb, 200, 690, "Country");

            createHeadings(cb, 482, 743, "ABC0001");
            createHeadings(cb, 482, 723, "123456");
            createHeadings(cb, 482, 703, "09/26/2012");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateDetail(Document doc, PdfContentByte cb, int index, int y) {
        DecimalFormat df = new DecimalFormat("0.00");

        try {

            createContent(cb, 48, y, String.valueOf(index + 1), PdfContentByte.ALIGN_RIGHT);
            createContent(cb, 52, y, String.valueOf(order.getProducts().get(index).getId()), PdfContentByte.ALIGN_LEFT);
            createContent(cb, 152, y, order.getProducts().get(index).getName(), PdfContentByte.ALIGN_LEFT);

            double price = Double.valueOf(order.getProducts().get(index).getPrice());
            double extPrice = order.getProducts().get(index).getPrice() * 2;

            // TODO 2 je tam jen docasne
            createContent(cb, 498, y, df.format(price), PdfContentByte.ALIGN_RIGHT);
            createContent(cb, 568, y, df.format(extPrice), PdfContentByte.ALIGN_RIGHT);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void createHeadings(PdfContentByte cb, float x, float y, String text) {
        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.setTextMatrix(x, y);
        cb.showText(text.trim());
        cb.endText();
    }

    private void printPageNumber(PdfContentByte cb) {
        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber + 1), 570, 25, 0);
        cb.endText();

        pageNumber++;
    }

    private void createContent(PdfContentByte cb, float x, float y, String text, int align) {
        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();
    }

    private void initializeFonts() {
        try {
            bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}