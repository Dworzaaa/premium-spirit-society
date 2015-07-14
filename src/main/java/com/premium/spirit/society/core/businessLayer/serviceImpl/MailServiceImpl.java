package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormWrapperBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.businessLayer.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The Class MailService.
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MessageSource messageSource;

    /**
     * The Constant logger.
     */
    private static final Logger logger = LoggerFactory
            .getLogger(MailServiceImpl.class);


    /**
     * Send mail.
     *
     * @param recipient the recipient
     * @param map       the map
     * @throws Exception the exception
     */
    public void sendMail(String recipient, HashMap<String, Object> map,
                         Locale locale) throws Exception {
        // Recipient's email
        // ID needs to be
        // mentioned.

        // Sender's email ID needs to be mentioned
        String from = "mailbot@premium-spirit-society.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            String encodingOptions = "text/html; charset=UTF-8";
            message.setHeader("Content-Type", encodingOptions);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    recipient));
            if (map.get("subject").equals("changeOfThePassword")) {
                message.setSubject("Changed password to premium-spirit-society eshop");
                UserFormBO user = (UserFormBO) map.get("user");


                String[] messageParameters = new String[]{user.getUsername(),
                        (String) map.get("password")};
                String text = messageSource.getMessage("changedPassword",
                        messageParameters, locale);
                System.out.println(text);
                message.setText(text, "UTF-8");
                Transport.send(message);

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date today = Calendar.getInstance().getTime();
                String reportDate = df.format(today);
                logger.info(reportDate
                        + " Message about the change of the password was sent to user "
                        + user.getId());
            } else if (map.get("subject").equals("creationOfANewUser")) {
                message.setSubject("New account to premium-spirit-society eshop", "utf-8");
                UserFormBO user = (UserFormBO) map.get("user");
                String[] messageParameters = new String[]{user.getUsername(),
                        (String) map.get("password")};
                String text = messageSource.getMessage("userCreated",
                        messageParameters, locale);
                System.out.println(text);
                message.setText(text, "UTF-8");


                Transport.send(message);

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date today = Calendar.getInstance().getTime();
                String reportDate = df.format(today);
                logger.info(reportDate
                        + " Message about the creation of a new account was sent to user "
                        + user.getId());
            } else if (map.get("subject").equals("creationOfANewOrder")) {
                message.setSubject("New order from created", "utf-8");
                UserFormBO user = (UserFormBO) map.get("user");
                List<ProductFormWrapperBO> productFormWrapperBOs = (List<ProductFormWrapperBO>) map.get("productFormWrapperBOs");
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date today = Calendar.getInstance().getTime();
                String reportDate = df.format(today);

                String[] messageParameters = new String[]{user.getUsername(), reportDate};
                String text = "<html><body>\n";

                        text+=messageSource.getMessage("orderCreated",
                        messageParameters, locale);
                text+="<br><br>";

                for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                    text += productFormWrapperBO.getName();
                    text += "   ";
                    text += productFormWrapperBO.getAmount();
                    text += "   ";
                    text += productFormWrapperBO.getPrice();
                    text += "   ";
                    text += (productFormWrapperBO.getAmount() * productFormWrapperBO.getPrice());
                    text += "\n";
                }
                text += "<br><a href=\"http://premium-spirit-society.com/invoices/" + user.getId() + "/" + map.get("invoice").toString()+"\">faktura</a>";
                text+="</body></html>";
                System.out.println(text);
                message.setText(text, "UTF-8");

                Transport.send(message);

                logger.info(reportDate
                        + " Message about the creation of a new order was sent to user "
                        + user.getId());
            } else {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date today = Calendar.getInstance().getTime();
                String reportDate = df.format(today);
                logger.error(reportDate
                        + " Tried to send message with unknown subject");
            }
            // Send message

        } catch (MessagingException mex) {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            mex.printStackTrace(pw);
            logger.info(reportDate + " Error when attempting to send an email: "
                    + sw.toString());

            mex.printStackTrace();
        }
    }

    public void sendMailToAdmin(String recipient, HashMap<String, Object> map,
                                Locale locale, String orderNumber) throws Exception {

        String from = "mailbot@premium-spirit-society.com";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            String encodingOptions = "text/html; charset=UTF-8";
            message.setHeader("Content-Type", encodingOptions);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    "Dworza@gmail.com"));
           if (map.get("subject").equals("creationOfANewOrder")) {
                message.setSubject("New order from created", "utf-8");
                UserFormBO user = (UserFormBO) map.get("user");
                List<ProductFormWrapperBO> productFormWrapperBOs = (List<ProductFormWrapperBO>) map.get("productFormWrapperBOs");
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date today = Calendar.getInstance().getTime();
                String reportDate = df.format(today);

                String[] messageParameters = new String[]{user.getUsername(), reportDate,orderNumber};
                String text = messageSource.getMessage("orderCreatedAdmin",
                                        messageParameters,  locale);

                for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                    text += productFormWrapperBO.getName();
                    text += "   ";
                    text += productFormWrapperBO.getAmount();
                    text += "   ";
                    text += productFormWrapperBO.getPrice();
                    text += "   ";
                    text += (productFormWrapperBO.getAmount() * productFormWrapperBO.getPrice());
                    text += "\n";
                }
                text += "\n\n <a href=\"http://premium-spirit-society.com/invoices/" + user.getId() + "/" + map.get("invoice").toString() + "\" \n\n";
                System.out.println(text);
                message.setText(text, "UTF-8");

                Transport.send(message);
            } else {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date today = Calendar.getInstance().getTime();
                String reportDate = df.format(today);
                logger.error(reportDate
                        + " Tried to send message with unknown subject");
            }
            // Send message

        } catch (MessagingException mex) {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            mex.printStackTrace(pw);
            logger.info(reportDate + " Error when attempting to send an email: "
                    + sw.toString());

            mex.printStackTrace();
        }
    }


    public void notifyChangedPassword(UserFormBO user, String password, Locale locale) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("subject", "changeOfThePassword");
            map.put("user", user);
            map.put("password", password);
            this.sendMail(user.getContact().getEmail(), map, locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notifyOrderCreated(UserFormBO user, List<ProductFormWrapperBO> productFormWrapperBOs, String invoice, Locale locale, OrderFormBO order) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("subject", "creationOfANewOrder");
            map.put("user", user);
            map.put("invoice", invoice);
            map.put("productFormWrapperBOs", productFormWrapperBOs);

            this.sendMail(user.getContact().getEmail(), map, locale);
            this.sendMailToAdmin(user.getContact().getEmail(), map, locale,order.getOrderNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
