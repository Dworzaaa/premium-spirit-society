package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.premium.spirit.society.core.businessLayer.BO.display.OrderDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormWrapperBO;
import com.premium.spirit.society.core.businessLayer.service.OrderService;
import com.premium.spirit.society.core.dataLayer.DAO.OrderDAO;
import com.premium.spirit.society.core.dataLayer.entity.OrderEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 2. 1. 2015.
 */
@Service
public class OrderServiceImpl extends GenericServiceImpl<OrderFormBO, OrderEntity> implements OrderService {

    private OrderDAO orderDAO;
    private BaseFont bfBold;
    private BaseFont bf;
    private OrderFormBO order;
    private int pageNumber = 0;


    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    @Override
    @Transactional
    public List<OrderDisplayBO> getOrdersByUserId(int id) {
        List<OrderEntity> listOfOrders = orderDAO.getOrdersByUserId(id);
        List<OrderDisplayBO> orderDisplayBOs = new ArrayList<>();
        for (OrderEntity order : listOfOrders) {
            OrderDisplayBO orderDisplayBO = dozer.map(order, OrderDisplayBO.class);
            orderDisplayBOs.add(orderDisplayBO);
        }
        return orderDisplayBOs;
    }

    @Override
    public String createFileName(String username, String orderNumber) {
        return DigestUtils.sha256Hex(orderNumber + "{" + username + "}");
    }


    @Override
    public String getInvoiceBaseUrl(int userId) {
        File folder = new File(System.getProperty("user.home")
                + System.getProperty("file.separator") + "PremiumSpiritSociety"
                + System.getProperty("file.separator") + "invoices"
                + System.getProperty("file.separator") + userId     )         ;
             return folder.getPath();
    }


    @Override
    public void createPdf(String pdfFilename, List<ProductFormWrapperBO> productFormWrapperBOs, OrderFormBO order) {
        this.order = order;
        Document doc = new Document();
        PdfWriter docWriter = null;
        initializeFonts();

        try {
            String path = System.getProperty("user.home")
                    + System.getProperty("file.separator") + "PremiumSpiritSociety"
                    + System.getProperty("file.separator") + "invoices"
                    + System.getProperty("file.separator") + order.getUserID();


            File theDir = new File(path);
            // create directory
            if (!theDir.exists()) {
                boolean result = false;
                try {
                    theDir.mkdirs();
                    result = true;
                } catch (SecurityException se) {
                    //TODO: log error
                }
                if (result)
                    // TODO : log success
                    System.out.print("directory created");
            }

            path += System.getProperty("file.separator") + pdfFilename;


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
