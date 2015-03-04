package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.businessLayer.service.ExportToDocxService;
import com.premium.spirit.society.core.businessLayer.service.UserService;
import com.premium.spirit.society.core.util.LoggingService;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


@Service
public class ExportToDocxServiceImpl implements ExportToDocxService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    private final String delimiter = ": ";

    private LoggingService logger = new LoggingService();

    private String translateValue(String value, Locale locale) {
        if (value.equals("true"))
            return messageSource.getMessage("label.yes", null, locale);
        else if (value.equals("false"))
            return messageSource.getMessage("label.no", null, locale);
        else if (value.equals("null") || value.equals(null) || value.equals("") || value.equals("NA")) {
            return messageSource.getMessage("label.NA", null, locale);
        }

        return value;
    }

    private String translateComment(String value, Locale locale) {
        if (value.equals("null") || value.equals(null) || value.equals("") || value.equals("NA")) {
            return messageSource.getMessage("label.noComments", null, locale);
        }
        return value;
    }

    private static String getDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        reportDate = reportDate.replace(' ', '_');
        reportDate = reportDate.replace('/', '_');
        return reportDate;
    }

    public String export(OrderFormBO orderFormBO, UserFormBO user, Locale locale) {
        String date = getDate();
        String name = date + ".docx";

        String downloadFolder = System.getProperty("user.home")
                + System.getProperty("file.separator") + "Download_Links"
                + System.getProperty("file.separator");
        File f = null;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0) {
            downloadFolder.replace("\\", "/");
            name = name.replace(":", "_");
            f = new File(downloadFolder + name);
            if (!f.getParentFile().exists())
                f.getParentFile().mkdirs();
            if (f.exists())
                f.delete();
            try {
                f.createNewFile();
            } catch (IOException e) {
                logger.logError(
                        "Couldn't create new file when trying to save pdf file.",
                        e);
            }
        } else {
            downloadFolder = "/usr/local/tomcat/webapps/GENEPI/resources/downloads/";

            f = new File(downloadFolder + name);
            if (!f.getParentFile().exists())
                f.getParentFile().mkdirs();
            if (f.exists())
                f.delete();
            try {
                f.createNewFile();
            } catch (IOException e) {
                logger.logError(
                        "Couldn't create new file when trying to save xlsx file.",
                        e);
            }
        }
        logger.setLogger(ExportToDocxService.class);
        WordprocessingMLPackage document = null;
        try {
            document = WordprocessingMLPackage.createPackage();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        for (ProductFormBO productFormBO : orderFormBO.getProducts()) {

            this.addTitlePage(document, productFormBO, locale);

        }

        try {
            document.save(new File(downloadFolder + name));

        } catch (Docx4JException e) {
            e.printStackTrace();
        }
        return name;
    }

    private void addTitlePage(WordprocessingMLPackage wordMLPackage, ProductFormBO productFormBO,
                              Locale locale) {
        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", messageSource.getMessage("label.patient", null,
                locale) + " ID " + productFormBO.getId());
        addContent(wordMLPackage, productFormBO, locale);
    }


    private void addContent(WordprocessingMLPackage document, ProductFormBO productFormBO,
                            Locale locale) {

        document.getMainDocumentPart().addStyledParagraphOfText("Heading1", messageSource.getMessage("label.pharmacotherapy", null,
                locale));

    }
}