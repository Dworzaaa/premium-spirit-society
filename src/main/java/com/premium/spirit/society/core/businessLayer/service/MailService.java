package com.premium.spirit.society.core.businessLayer.service;

import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormWrapperBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * The Interface MailService extends GenericService
 */
public interface MailService {
    public void sendMail(String recipient, HashMap<String, Object> map, Locale locale) throws Exception;

    public void notifyChangedPassword(UserFormBO user, String password, Locale locale);
    public void notifyOrderCreated(UserFormBO user, List<ProductFormWrapperBO> productFormWrapperBOs, String invoice, Locale locale, OrderFormBO order);

}
