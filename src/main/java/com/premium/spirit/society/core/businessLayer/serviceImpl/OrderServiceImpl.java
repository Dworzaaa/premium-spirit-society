package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.itextpdf.text.*;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.*;
import com.premium.spirit.society.core.businessLayer.BO.display.OrderDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormWrapperBO;
import com.premium.spirit.society.core.businessLayer.service.OrderService;
import com.premium.spirit.society.core.dataLayer.DAO.OrderDAO;
import com.premium.spirit.society.core.dataLayer.entity.OrderEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    private final String BLUE = "C1DAD6";
    private final String GREY = "B2BEB5";
    private final String WHITE = "#FFFFFF";
    private final String CURRENCY="€";

    @Autowired
    private OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    private MessageSource messageSource;

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
                + System.getProperty("file.separator") + userId);
        return folder.getPath();
    }

    @Override
    @Transactional
    public OrderFormBO getByOrderNumber(String orderNumber) {
        OrderEntity orderEntity = orderDAO.getByOrderNumber(orderNumber);
        return dozer.map(orderEntity, OrderFormBO.class);
    }


    @Override
    public void createPdf(String pdfFilename, List<ProductFormWrapperBO> productFormWrapperBOs, OrderFormBO order, Locale locale) {
        this.order = order;
        Document doc = new Document(PageSize.A4, 0f, 0f, 0f, 0f);
        PdfWriter docWriter = null;
        initializeFonts();
        int vat = Integer.parseInt(messageSource.getMessage("VAT", null, locale));

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
            doc.newPage();

            String pictureFolder = System.getProperty("user.home")
                    + System.getProperty("file.separator") + "PremiumSpiritSociety"
                    + System.getProperty("file.separator") + "graphic"
                    + System.getProperty("file.separator");
            String filename = pictureFolder + "logoSmall.jpg";
            Image image = Image.getInstance(filename);


            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setWidthPercentage(95);

            PdfPTable innerHeaderTable = new PdfPTable(2);
            innerHeaderTable.setWidthPercentage(100);

            PdfPCell topHeaderCell1 = new PdfPCell();
            topHeaderCell1.setBorder(Rectangle.NO_BORDER);

            topHeaderCell1.addElement(image);


            Paragraph idParagraph = new Paragraph();
            Chunk chunkParagraph = new Chunk("\n\nInvoice: " + (String.valueOf(order.getOrderNumber())), FontFactory.getFont(FontFactory.HELVETICA, 16f, Font.BOLD));
            idParagraph.add(chunkParagraph);
            idParagraph.setAlignment(Element.ALIGN_RIGHT);
            idParagraph.setIndentationRight(50);
            doc.add(new Paragraph("\n"));

            PdfPCell topHeaderCell2 = new PdfPCell();
            topHeaderCell2.setBorder(Rectangle.NO_BORDER);

            topHeaderCell2.addElement(idParagraph);

            innerHeaderTable.addCell(topHeaderCell1);
            innerHeaderTable.addCell(topHeaderCell2);
            headerTable.addCell(innerHeaderTable);
            doc.add(headerTable);


            doc.add(new Paragraph("\n"));


            Paragraph subjectParagraph = new Paragraph();
            PdfPTable subjectTable = new PdfPTable(2);

            PdfPCell supplierCell = new PdfPCell();
            PdfPTable supplierNestedTable = new PdfPTable(1);
            supplierNestedTable.setWidthPercentage(100);
            PdfPCell supplierHeaderCell = new PdfPCell();
            Paragraph supplierHeaderParagraph = new Paragraph("Supplier:\n");
            supplierHeaderCell.setBackgroundColor(WebColors.getRGBColor(BLUE));
            supplierHeaderParagraph.setAlignment(Element.ALIGN_LEFT);
            supplierHeaderCell.addElement(supplierHeaderParagraph);
            supplierHeaderCell.setBorder(Rectangle.NO_BORDER);
            supplierNestedTable.addCell(supplierHeaderCell);
            supplierCell.addElement(supplierNestedTable);

            Paragraph supplierParagraph = new Paragraph();
            supplierParagraph.setAlignment(Element.ALIGN_LEFT);
            Chunk supplierChunk = new Chunk(messageSource.getMessage("SupplierInfo", null, locale));
            supplierParagraph.add(supplierChunk);
            supplierCell.addElement(supplierParagraph);
            subjectTable.addCell(supplierCell);

            PdfPCell customerCell = new PdfPCell();
            PdfPTable customerNestedTable = new PdfPTable(1);
            customerNestedTable.setWidthPercentage(100);
            PdfPCell customerHeaderCell = new PdfPCell();

            Paragraph customerHeaderParagraph = new Paragraph("Customer:\n");
            customerHeaderParagraph.setAlignment(Element.ALIGN_LEFT);
            customerHeaderCell.setBackgroundColor(WebColors.getRGBColor("#C1DAD6"));
            customerHeaderCell.addElement(customerHeaderParagraph);
            customerHeaderCell.setBorder(Rectangle.NO_BORDER);
            customerNestedTable.addCell(customerHeaderCell);
            customerCell.addElement(customerNestedTable);


            Paragraph customerParagraph = new Paragraph();
            customerParagraph.setAlignment(Element.ALIGN_LEFT);
            Chunk customerChunk = new Chunk("\n"
                    + order.getUser().getContact().getFirstName() + "\n"
                    + order.getUser().getContact().getLastName() + "\n"
                    + order.getUser().getContact().getAddressStreet() + " " + order.getUser().getContact().getAddressHn() + "\n"
                    + order.getUser().getContact().getAddressCity() + "\n"
                    + order.getUser().getContact().getAddressCountry() + "\n"
                    + order.getUser().getContact().getAddressPostalcode() + "\n\n");
            customerParagraph.add(customerChunk);
            customerCell.addElement(customerParagraph);
            customerParagraph.setAlignment(Element.ALIGN_LEFT);
            subjectTable.addCell(customerCell);
            subjectTable.setWidthPercentage(95);
            subjectParagraph.add(subjectTable);
            doc.add(subjectParagraph);

            doc.add(new Paragraph("\n"));

            PdfPTable paymentTable = new PdfPTable(1);
            paymentTable.setWidthPercentage(95);

            PdfPTable innerPaymentTable = new PdfPTable(2);
            innerPaymentTable.setWidthPercentage(100);

            PdfPCell paymentCell1 = new PdfPCell();
            paymentCell1.setBorder(Rectangle.NO_BORDER);
            Paragraph paymentParagraph1 = new Paragraph();

            String paymentMethod = "Forma uhrady: " + order.getPaymentMethod() + "\n";
            if (order.getPaymentMethod().equals("bankTransfer")) {
                paymentMethod += "   IBAN: XXXXXXXXXXXXXXXX \n" +
                        "        BIC: XXXXXXXXX\n" +
                        "        Variabilni symbol: " + order.getOrderNumber() + "\n";
            }
            paymentParagraph1.add(new Chunk(paymentMethod));
            paymentParagraph1.setAlignment(Element.ALIGN_CENTER);
            paymentCell1.addElement(paymentParagraph1);

            PdfPCell paymentCell2 = new PdfPCell();
            paymentCell2.setBorder(Rectangle.NO_BORDER);
            Paragraph paymentParagraph2 = new Paragraph();
            DateTime issueOfInvoice = new DateTime();
            DateTime paymentDate = issueOfInvoice.plusDays(31);
            paymentParagraph2.add(new Chunk("Datum vystaveni: " + issueOfInvoice.toString("dd.MM.yyyy")
                    + "\nDatum splatnosti: " + paymentDate.toString("dd.MM.yyyy") + "\n"
            ));
            paymentParagraph2.setAlignment(Element.ALIGN_CENTER);
            paymentCell2.addElement(paymentParagraph2);

            innerPaymentTable.addCell(paymentCell1);
            innerPaymentTable.addCell(paymentCell2);
            paymentTable.addCell(innerPaymentTable);
            doc.add(paymentTable);

            Paragraph productParagraph = new Paragraph();
            PdfPTable productTable = new PdfPTable(9);
            productTable.setWidthPercentage(95);

            BaseColor headerColor = WebColors.getRGBColor(BLUE);
            PdfPCell headerCell1 = new PdfPCell(new Paragraph("product ID"));
            headerCell1.setBackgroundColor(headerColor);
            productTable.addCell(headerCell1);
            PdfPCell headerCell2 = new PdfPCell(new Paragraph("name"));
            headerCell2.setBackgroundColor(headerColor);
            productTable.addCell(headerCell2);
            PdfPCell headerCell3 = new PdfPCell(new Paragraph("volume"));
            headerCell3.setBackgroundColor(headerColor);
            productTable.addCell(headerCell3);
            PdfPCell headerCell4 = new PdfPCell(new Paragraph("ethanol volume"));
            headerCell4.setBackgroundColor(headerColor);
            productTable.addCell(headerCell4);
            PdfPCell headerCell5 = new PdfPCell(new Paragraph("price without VAT"));
            headerCell5.setBackgroundColor(headerColor);
            productTable.addCell(headerCell5);
            PdfPCell headerCell7 = new PdfPCell(new Paragraph("VAT"));
            headerCell7.setBackgroundColor(headerColor);
            productTable.addCell(headerCell7);
            PdfPCell headerCell9 = new PdfPCell(new Paragraph("Price with VAT"));
            headerCell9.setBackgroundColor(headerColor);
            productTable.addCell(headerCell9);
            PdfPCell headerCell6 = new PdfPCell(new Paragraph("amount"));
            headerCell6.setBackgroundColor(headerColor);
            productTable.addCell(headerCell6);
            PdfPCell headerCell8 = new PdfPCell(new Paragraph("total price with VAT"));
            headerCell8.setBackgroundColor(headerColor);
            productTable.addCell(headerCell8);

            doc.add(new Paragraph("\n"));
            Double totalPriceForOrder = 0.0;
            for (int i = 0; i != productFormWrapperBOs.size(); i++) {
                BaseColor myColor;
                if (i % 2 == 0) {
                    myColor = WebColors.getRGBColor(WHITE);
                } else {
                    myColor = WebColors.getRGBColor(GREY);
                }
                PdfPCell productId = new PdfPCell(new Paragraph(String.valueOf(productFormWrapperBOs.get(i).getId())));
                productId.setBackgroundColor(myColor);
                PdfPCell productName = new PdfPCell(new Paragraph(productFormWrapperBOs.get(i).getName()));
                productName.setBackgroundColor(myColor);
                String volume = String.valueOf(productFormWrapperBOs.get(i).getVolume()) + " ml";
                if (volume.equals("0 ml"))
                    volume = "-";
                PdfPCell productVolume = new PdfPCell(new Paragraph(volume));
                productVolume.setBackgroundColor(myColor);
                String ethanolVolume = String.valueOf(productFormWrapperBOs.get(i).getEthanolVolume()) + "%";
                if (ethanolVolume.equals("0%"))
                    ethanolVolume = "-";
                PdfPCell productEthanolVolume = new PdfPCell(new Paragraph(ethanolVolume));
                productEthanolVolume.setBackgroundColor(myColor);
                Double price = Double.valueOf(productFormWrapperBOs.get(i).getPrice());
                price = price - (price / 100 * vat);
                PdfPCell productPrice = new PdfPCell(new Paragraph(String.valueOf(price)+" "+CURRENCY));
                productPrice.setBackgroundColor(myColor);
                PdfPCell priceWithVAT = new PdfPCell(new Paragraph(String.valueOf(Double.valueOf(productFormWrapperBOs.get(i).getPrice()))+" "+CURRENCY));
                priceWithVAT.setBackgroundColor(myColor);
                PdfPCell productVAT = new PdfPCell(new Paragraph(String.valueOf(vat) + "%"));
                productVAT.setBackgroundColor(myColor);
                PdfPCell productAmount = new PdfPCell(new Paragraph(String.valueOf(productFormWrapperBOs.get(i).getOrderAmount())));
                productAmount.setBackgroundColor(myColor);
                PdfPCell totalPrice = new PdfPCell(new Paragraph(String.valueOf((vat / 100 + 1) * productFormWrapperBOs.get(i).getPrice() * productFormWrapperBOs.get(i).getOrderAmount())+" "+CURRENCY));
                totalPrice.setBackgroundColor(myColor);

                productTable.addCell(productId);
                productTable.addCell(productName);
                productTable.addCell(productVolume);
                productTable.addCell(productEthanolVolume);
                productTable.addCell(productPrice);
                productTable.addCell(productVAT);
                productTable.addCell(priceWithVAT);
                productTable.addCell(productAmount);
                productTable.addCell(totalPrice);

                totalPriceForOrder += productFormWrapperBOs.get(i).getPrice() * productFormWrapperBOs.get(i).getOrderAmount();
            }

            productParagraph.add(productTable);
            doc.add(productParagraph);

            doc.add(new Paragraph("\n"));

            PdfPTable summaryTable = new PdfPTable(4); // 3 columns.
            summaryTable.setWidthPercentage(95);


            PdfPCell vatSummaryCell2 = new PdfPCell();
            vatSummaryCell2.setBackgroundColor(headerColor);
            Paragraph vatSummaryParagraph2 = new Paragraph();
            Chunk vatSummaryChunk2 = new Chunk("Celkem bez dane\n");
            vatSummaryParagraph2.add(vatSummaryChunk2);
            vatSummaryCell2.addElement(vatSummaryParagraph2);
            summaryTable.addCell(vatSummaryCell2);

            PdfPCell vatSummaryCell1 = new PdfPCell();
            vatSummaryCell1.setBackgroundColor(headerColor);
            Paragraph vatSummaryParagraph1 = new Paragraph();
            Chunk vatSummaryChunk1 = new Chunk("Danova sazba\n");
            vatSummaryParagraph1.add(vatSummaryChunk1);
            vatSummaryCell1.addElement(vatSummaryParagraph1);
            summaryTable.addCell(vatSummaryCell1);

            PdfPCell vatSummaryCell3 = new PdfPCell();
            vatSummaryCell3.setBackgroundColor(headerColor);
            Paragraph vatSummaryParagraph3 = new Paragraph();
            Chunk vatSummaryChunk3 = new Chunk("Dan\n");
            vatSummaryParagraph3.add(vatSummaryChunk3);
            vatSummaryCell3.addElement(vatSummaryParagraph3);
            summaryTable.addCell(vatSummaryCell3);

            PdfPCell vatSummaryCell4 = new PdfPCell();
            vatSummaryCell4.setBackgroundColor(headerColor);
            Paragraph vatSummaryParagraph4 = new Paragraph();
            Chunk vatSummaryChunk4 = new Chunk("Celkem s dani\n");
            vatSummaryParagraph4.add(vatSummaryChunk4);
            vatSummaryCell4.addElement(vatSummaryParagraph4);
            summaryTable.addCell(vatSummaryCell4);


            PdfPCell vatSummaryCell2b = new PdfPCell();
            Paragraph vatSummaryParagraph2b = new Paragraph();
            Chunk vatSummaryChunk2b = new Chunk(Double.toString(Double.valueOf(totalPriceForOrder - (Double.valueOf(totalPriceForOrder) / 100 * vat)))+" "+CURRENCY );
            vatSummaryParagraph2b.add(vatSummaryChunk2b);
            vatSummaryCell2b.addElement(vatSummaryParagraph2b);
            summaryTable.addCell(vatSummaryCell2b);

            PdfPCell vatSummaryCell1b = new PdfPCell();
            Paragraph vatSummaryParagraph1b = new Paragraph();
            Chunk vatSummaryChunk1b = new Chunk(vat + "%\n");
            vatSummaryParagraph1b.add(vatSummaryChunk1b);
            vatSummaryCell1b.addElement(vatSummaryParagraph1b);
            summaryTable.addCell(vatSummaryCell1b);

            PdfPCell vatSummaryCell3b = new PdfPCell();
            Paragraph vatSummaryParagraph3b = new Paragraph();
            Chunk vatSummaryChunk3b = new Chunk(Double.toString(Double.valueOf((Double.valueOf(totalPriceForOrder) / 100 * vat))) +" "+CURRENCY);
            vatSummaryParagraph3b.add(vatSummaryChunk3b);
            vatSummaryCell3b.addElement(vatSummaryParagraph3b);
            summaryTable.addCell(vatSummaryCell3b);

            PdfPCell vatSummaryCell4b = new PdfPCell();
            Paragraph vatSummaryParagraph4b = new Paragraph();
            Chunk vatSummaryChunk4b = new Chunk(totalPriceForOrder+" "+CURRENCY);
            vatSummaryParagraph4b.add(vatSummaryChunk4b);
            vatSummaryCell4b.addElement(vatSummaryParagraph4b);
            summaryTable.addCell(vatSummaryCell4b);

            doc.add(summaryTable);

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
