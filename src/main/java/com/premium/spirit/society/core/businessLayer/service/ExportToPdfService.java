package com.premium.spirit.society.core.businessLayer.service;

import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;

import java.io.FileNotFoundException;
import java.util.Locale;

/**
 * The Interface ExportToPdfService extends GenericService
 */
public interface ExportToPdfService {
    public String export(OrderFormBO orderFormBO, UserFormBO user, Locale locale) throws FileNotFoundException;
}
